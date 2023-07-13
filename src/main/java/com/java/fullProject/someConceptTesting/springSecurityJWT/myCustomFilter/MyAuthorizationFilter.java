package com.java.fullProject.someConceptTesting.springSecurityJWT.myCustomFilter;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
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

import javax.crypto.SecretKey;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.List;


/*In this Filter class we will validate the JWT token that the client will pass with the consecutive request,
and see whether the token is valid or not,upon successful validation we will create an authentication token and
 set it to securityContextHolder, we have to call this filter in the config class's securityFilter
 method*/
public class MyAuthorizationFilter extends BasicAuthenticationFilter {
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
            //Here I am encoding the secret key, that is required for JWT creation
            SecretKey key= Keys.hmacShaKeyFor("MyTopSecretKey".getBytes(StandardCharsets.UTF_8));

            Claims claim = Jwts.parserBuilder()
                    .setSigningKey(key)
                    .build()
                    .parseClaimsJws(actualToken)
                    .getBody();
            System.out.println(claim);

            String role=claim.get("roles", String.class);//Getting the role from the token, roles is a large string separated by ",".
            List<GrantedAuthority> listOfRoles = AuthorityUtils.commaSeparatedStringToAuthorityList(role);//converting the roles string to a list using utility method.

//Here we are creating a new authentication token using the user, password, role and returning it back
            UsernamePasswordAuthenticationToken authToken = new UsernamePasswordAuthenticationToken(claim.getSubject(), null, listOfRoles);
        return authToken;
        }catch (Exception e){
            System.out.println(e.getMessage());
        }
        return null;
    }
}