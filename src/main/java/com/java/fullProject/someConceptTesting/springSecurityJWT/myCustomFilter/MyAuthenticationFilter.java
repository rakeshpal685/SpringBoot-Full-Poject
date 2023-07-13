package com.java.fullProject.someConceptTesting.springSecurityJWT.myCustomFilter;

import com.java.fullProject.someConceptTesting.springSecurityJWT.dto.AuthorityDTO;
import com.java.fullProject.someConceptTesting.springSecurityJWT.dto.StudentDTO;
import com.java.fullProject.someConceptTesting.springSecurityJWT.service.JWTConstants;
import com.java.fullProject.someConceptTesting.springSecurityJWT.service.JWTData;
import com.java.fullProject.someConceptTesting.springSecurityJWT.service.JWTService;
import com.java.fullProject.someConceptTesting.springSecurityJWT.service.StudentServiceImpl;
import io.jsonwebtoken.security.Keys;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.*;

import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;


import javax.crypto.SecretKey;

/*In this class we will first Authenticate the user using DB, then after authenticating we will create a
JWT token and send it back*/
public class MyAuthenticationFilter extends UsernamePasswordAuthenticationFilter {

  private final AuthenticationManager authenticationManager;

  private JWTService jwtService;

 // public MyAuthenticationFilter() {}

  public MyAuthenticationFilter(AuthenticationManager authenticationManager) {
    // super(authenticationManager);
    this.authenticationManager = authenticationManager;
  }

  private static String getAllRole(StudentDTO student) {
    //Here I am getting all the roles from the student and adding it to roleList to pass it in the token
    ArrayList<String>roleList = new ArrayList<>();
    List<AuthorityDTO> roles= student.getRoles();
    roles.stream().forEach(r->roleList.add(r.getRole()));
    return String.join(",",roleList);//This will convert the array into a string separated by ",".
  }

/*  Here my class is extending to UsernamePasswordAuthenticationFilter class which extends to
   AbstractAuthenticationProcessingFilter class, there inside doFilter() attemptAuthentication() is
   called, as we have overriden this method, now our method is called*/
  @Override
  public Authentication attemptAuthentication(
      HttpServletRequest request, HttpServletResponse response) throws AuthenticationException {

    // Here we will capture the username and password from the request object using the methods
    // written below.
    String username = captureUsername(request);
    String password = capturePassword(request);

    /*Now we will pass the username and password to get validated and ask whether it is valid authentication object or not.
    The default implementation in this case is UsernamePasswordAuthenticationToken*/
    UsernamePasswordAuthenticationToken authToken =
        new UsernamePasswordAuthenticationToken(username, password, new ArrayList<>());

    /* Now we will give this token to the AuthenticationManager to validate the token, and internally it
    will use AuthenticationProvider to validate the token*/
    /*   Internally Here the authenticate method will take the token and pass it to its implementation class,
    i.e, ProviderManager, then the provider manager will loop through all the available authentication
    provider and find the particular provider and use provider.authenticate() method to take the authentication
    and validate it, in our case it is AbstractUserDetailsAuthenticationProvider, inside this we have retrieveUser
    method, it will go inside and we can see we have loadedUser variable which will call loadUserByUserName(),
    which we have override in StudentServiceImpl class. */

    Authentication token = null;
    try {
      token = authenticationManager.authenticate(authToken);
    } catch (BadCredentialsException e) {
      e.printStackTrace();
    }
    return token;
  }

/*  Once the token is generated in the attemptAuthentication() above,internally
AbstractAuthenticationProcessingFilter class's doFilter() will call the successfulAuthentication method and
pass the token that is generated, as a parameter to authResult, and inside we can take the token and set
it to SecurityContextHolder, so that we don't  have to authenticate again for the user.The authResult,i.e,
Authentication token has all the details about the user*/
  @Override
  protected void successfulAuthentication(HttpServletRequest request, HttpServletResponse response, FilterChain chain, Authentication authResult) throws IOException, ServletException {
    System.out.println("My successfulAuthentication method is called");
    SecurityContextHolder.getContext().setAuthentication(authResult);

//    Here we are building the JWT token with the claims

/*    Here we are getting the user details from the Authentication object that we got in the argument.*/
    String username = ((User) authResult.getPrincipal()).getUsername();//Here the username means the value that we are passing during login(email in this case)

    //Here we will get the details from the DB by using the getUser() method in the service
   /*Few things to note here.i.e, our Filer class is not a component and hence we cannot autowire Service
    class here, Hence we have created MyDemoApplicationContext class to get the bean, see the class for more info*/

    StudentServiceImpl studentServiceImpl = (StudentServiceImpl)MyDemoApplicationContext.getBean("studentServiceImpl");//getting the bean of Student service
    StudentDTO student = studentServiceImpl.getUser(username);//passing my username to the service to get the Student details

    //Here I am encoding the secret key, that is required for JWT creation
    SecretKey key= Keys.hmacShaKeyFor("MyTopSecretKey".getBytes(StandardCharsets.UTF_8));


  //Now using the StudentDTO object we can get the student details and use it to set the claims in JWT

    /*If we want to create some custom claims then we can do it by using .claim, but if we want to put a lot
      of claims then we can do it using .claim, but the bes way is to declare a map and pass it to .claims();

    Map<String,Object> claims = new HashMap<>();
    claims.put("firstName","bholi");
    claims.put("lastName","surat");
*/

/* Here I have created a DTO called JWTData, which will get all the values from StudentDTO and set it in,
 so that we can pass the values to the jwtService.generateToken() method. which will generate the token for us*/
    JWTData jwtData= new JWTData();
    jwtData.setIssuer(JWTConstants.ISSUER);
    jwtData.setEmail(student.getEmail());
    jwtData.setSubject(student.getFirstName());
    jwtData.setUserId(student.getUserId());
    jwtData.setAudience(student.getFirstName() +" "+ student.getLastName());
    jwtData.setRolesString(getAllRole(student));

    //Like above as our filter class is not annotated then jwtService won't get injected and hence we follow the below technique to get the bean
     jwtService =(JWTService) MyDemoApplicationContext.getBean("jwtService");//assigning the bean to the reference variable

    String token=jwtService.generateToken(jwtData);//here we are generating the token
//            This thing has been moved to JWTService class and we used RSA to sign the token
/*            = Jwts.builder()
            .setIssuer("www.rakesh.com")//Issuer name
            .setSubject(username)//coming from above
            .setAudience(student.getFirstName()+" "+student.getLastName())//To whom this token belongs
            .setExpiration(Date.from(ZonedDateTime.now().plusHours(1).toInstant()))//expires in 1 hour
//            .setIssuedAt(new Date(System.currentTimeMillis()))
//            .setExpiration(new Date(System.currentTimeMillis() + 1000 * 60 * 60 * 10))
            .setId(student.getUserId())
            .claim("email",student.getEmail())//this is my custom claim
            .claim("roles",getAllRole(student))//Here I am getting all the roles for the user above and adding it
//          .addClaims(claims)//Here I can specify multiple claims together, that we declared above
            .signWith(key)//the encrypted key is formed above
            .compact();*/

    response.addHeader("user id",student.getUserId());//we can send back any info that we need back in header
    response.addHeader("Authorization","Bearer "+token);// we can add Bearer too in front of the token
  }

  private String capturePassword(HttpServletRequest request) {
    return request.getParameter("password");
  }

  private String captureUsername(HttpServletRequest request) {
    return request.getParameter(
        "username"); // Here by default the username field is denoted by username in the form and same goes for teh password
  }

  /*Now we will register our authentication filter to the Security filter chain, and remember that we have to place it
      before the UsernamePasswordAuthenticationFilter because I want my attemptAuthentication() method to be called
  We can do this by adding this to our configuration file, i.e, SecurityConfiguration class in our case in securityFilter
  method*/

}
