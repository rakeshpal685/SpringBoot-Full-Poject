package com.java.fullProject.someConceptTesting.springSecurityJWTandJWE.myCustomFilter;

import com.java.fullProject.someConceptTesting.springSecurityJWTandJWE.configuration.JWEConstants;
import com.java.fullProject.someConceptTesting.springSecurityJWTandJWE.dto.JWEData;
import com.java.fullProject.someConceptTesting.springSecurityJWTandJWE.service.JWEService;
import com.java.fullProject.someConceptTesting.springSecurityJWTandJWE.service.JWTService;
import io.jsonwebtoken.Claims;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter;

import java.io.IOException;
import java.util.List;


/*In this Filter class we will validate the JWT token that the client will pass with the consecutive request,
and see whether the token is valid or not,upon successful validation we will create an authentication token and
 set it to securityContextHolder, we have to call this filter in the config class's securityFilter
 method*/
public class MyAuthorizationFilter extends BasicAuthenticationFilter {

    private JWTService jwtService;
    
    private JWEService jweService;
    public MyAuthorizationFilter(AuthenticationManager authenticationManager) {
        super(authenticationManager);
    }

//    This method will be called internally by doFilter() of BasicAuthenticationFilter class
    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain chain)
            throws IOException, ServletException {
        String authorizationHeader = request.getHeader("Authorization");//Getting the JWT token value from the request header with key as Authorization
        System.out.println("Authorization Header : " + authorizationHeader);

        if(authorizationHeader==null || authorizationHeader.startsWith("Bearer")){
        chain.doFilter(request, response);
        return;
        }

//        Here I am passing the JWT token value that we just got from the header to getAuthentication() method
        UsernamePasswordAuthenticationToken authentication = getAuthentication(authorizationHeader);

        /*   Once the request has been served the contextHolder is cleared internally, now we will place our
         authentication token inside contextHolder to get the user info whenever I need it in future*/
        SecurityContextHolder.getContext().setAuthentication(authentication);

        //Here we will pass the request and response to the next filter in chain
        chain.doFilter(request,response);
    }
    private UsernamePasswordAuthenticationToken getAuthentication(String authorizationHeader) {
        String actualToken = authorizationHeader.replace("Bearer ", "");//Our Token has a keyword Bearer, we will remove it here to just get the token
        System.out.println("My JWT token : "+ actualToken);

        // Here we will decode the JWT token and If my token is tempered then this method will internally throw an exception
        try{
            //Here I am encoding the secret key, that is required for JWT creation, not needed for RSA authentication
            //SecretKey key= Keys.hmacShaKeyFor("MyTopSecretKey".getBytes(StandardCharsets.UTF_8));
            
            //As our filter class is not annotated then jwtService won't get injected and hence we follow the below technique to get the bean
            jwtService =(JWTService) MyDemoApplicationContext.getBean("jwtService");//assigning the bean to the reference variable
            
            Claims claim = jwtService.validateToken(actualToken);
            System.out.println(claim);
            if(claim !=  null){
                String role=claim.get("roles", String.class);//Getting the role from the token, roles is a large string separated by ",".
                List<GrantedAuthority> listOfRoles = AuthorityUtils.commaSeparatedStringToAuthorityList(role);//converting the roles string to a list using utility method.
                
                //Here we are creating a new authentication token using the user, password, role and returning it back
                UsernamePasswordAuthenticationToken authToken = new UsernamePasswordAuthenticationToken(claim.getSubject(), null, listOfRoles);
                return authToken;
            }
        }catch (Exception e){
            System.out.println(e.getMessage());
        }
        return null;
        
        
    //------------------------THIS IS FOR JWE TOKEN GENERATION (Remove the above exact code for JWT, if using this)----------------------------
        
/*        // Here we will decode the JWT token and If my token is tempered then this method will internally throw an exception
        try{
            //Here I am encoding the secret key, that is required for JWT creation, not needed for RSA authentication
            //SecretKey key= Keys.hmacShaKeyFor("MyTopSecretKey".getBytes(StandardCharsets.UTF_8));
            
            //As our filter class is not annotated then jwtService won't get injected and hence we follow the below technique to get the bean
            jweService =(JWEService) MyDemoApplicationContext.getBean("jweService");//assigning the bean to the reference variable
            
            JWTClaimsSet claim = jweService.validateToken(actualToken);
            System.out.println(claim);
            
            if(claim !=  null){
                String role = (String)claim.getClaim("roles");//Getting the role from the token, roles is a large string separated by ",".
                System.out.println("Fetching teh roles for the claim--->"+role);
                
                List<GrantedAuthority> listOfRoles = AuthorityUtils.commaSeparatedStringToAuthorityList(role);//converting the roles string to a list using utility method.
                
                //Here we are creating a new authentication token using the user, password, role and returning it back
                UsernamePasswordAuthenticationToken authToken = new UsernamePasswordAuthenticationToken(claim.getSubject(), null, listOfRoles);
                return authToken;
            }
        }catch (Exception e){
            System.out.println(e.getMessage());
        }
        return null;*/
   
    //------------------------THIS ID END OF JWE TOKEN GENERATION----------------------------
    }
}