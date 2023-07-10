package com.java.fullProject.someConceptTesting.springSecurity.myCustomFilter;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

/*He are capturing the username and password and validate them ourselves, we will not let
UsernamePasswordAuthenticationFilter to work over here.*/
public class MyAuthenticationFilter extends UsernamePasswordAuthenticationFilter {

  private AuthenticationManager authenticationManager;

 // public MyAuthenticationFilter() {}

  public MyAuthenticationFilter(AuthenticationManager authenticationManager) {
    // super(authenticationManager);
    this.authenticationManager = authenticationManager;
  }

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

    /* Now we will give this token to the AuthenticationManager to validate the token and internally it
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

/*  Once the token is generated,internally AbstractAuthenticationProcessingFilter class will call the successfulAuthentication method and pass the
 token that is generated, as a parameter to authResult, and inside we can  take the token and set it to SecurityContextHolder, so that we don't
 have to authenticate again for the user.*/
  @Override
  protected void successfulAuthentication(HttpServletRequest request, HttpServletResponse response, FilterChain chain, Authentication authResult) throws IOException, ServletException {
    System.out.println("My successfulAuthentication method is called");
    SecurityContextHolder.getContext().setAuthentication(authResult);
  }

  private String capturePassword(HttpServletRequest request) {
    return request.getParameter("password");
  }

  private String captureUsername(HttpServletRequest request) {
    return request.getParameter(
        "username"); // NB default the username field is denoted by username in the form
  }

  /*Now we will register our authentication filter to the Security filter chain, and remember that we have to place it
      before the UsernamePasswordAuthenticationFilter because I want my attemptAuthentication() method to be called
  We can do this by adding this to our configuration file, i.e, SecurityConfiguration class in our case*/
}
