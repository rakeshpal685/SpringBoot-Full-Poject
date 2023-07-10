package com.java.fullProject.someConceptTesting.springSecurity.configuration;

import com.java.fullProject.someConceptTesting.springSecurity.myCustomFilter.MyAuthenticationFilter;
import com.java.fullProject.someConceptTesting.springSecurity.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.ProviderManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity // This will create spring security filter chain, use (debug=true)//to see the logs and filter chain
public class SecurityConfiguration {

  @Autowired private StudentService studentService;

  @Bean
  public SecurityFilterChain securityFilter(HttpSecurity http) throws Exception {
    http.cors()
        .and()
        .csrf()
        .disable()
        .authorizeHttpRequests(
            request ->
                request
                    .requestMatchers(HttpMethod.POST, "/students")
                    .permitAll()
                    .requestMatchers("/test")
                    .authenticated()
            // .anyRequest().permitAll()
            )
        .formLogin().and()//This will give us a login page
           // .addFilterBefore(myAuthenticationFilter(), UsernamePasswordAuthenticationFilter.class )//Here we are telling that place my custom filer that I have created before going to the UsernamePasswordAuthenticationFilter in the filter chain
        .httpBasic()//This will give us a login popup, in both the case, the login url is http://localhost:8080/login,then hit any GET endpoint,http://localhost:8080/logout

 /*When I am using userDetailsService for DB authentication, a cookie is generated for our session
 with our first successful request, and once we are logged in, then if we retry any endpoint with
 no Auth next time, the same cookie which have all our info will se share, and we will be able to
 access the endpoints, hence this thing is not stateless, with the below sessionManagement we can
 change the state of our session,(When we set it STATELESS then our session won't go to the security
 context looking for the authorized object and hence for every request it needs to be authorized) */
        // .and().sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS)
    ;
    return http.build();
  }
  /*Here we are using userDetailsService for DB authentication
  Here in the configuration file we are telling Spring that I have extended UserDetailsService class
  in my service class and provided my own implementation for the loadUserByUsername method, go and call that
  for DB authentication.*/
  @Autowired
  public void configure(AuthenticationManagerBuilder auth) throws Exception {
    auth.userDetailsService(studentService);
  }

public MyAuthenticationFilter myAuthenticationFilter(){
    AuthenticationManager authenticationManager = new ProviderManager();// This is not present in boot3
    MyAuthenticationFilter authFilter = new MyAuthenticationFilter(authenticationManager);
    return authFilter;
}

}
