package com.java.fullProject.service;

import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.factory.PasswordEncoderFactories;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

/*In spring security config class, I will pass this class to AuthenticationManagerBuilder, as our class has implemented UserDetailsService,
our loadUserByUsername method will be called. In this method we can check the username and password provided by the user is correct or not using
either inmemory details or by using DB details.*/
@Service
public class MyUserDetailsServiceForAuthentication implements UserDetailsService {
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        //return new User("rakesh","rakesh@123", Collections.emptyList());
        PasswordEncoder encoder = PasswordEncoderFactories.createDelegatingPasswordEncoder();//This is used to get bcrypt encoder type.

        //Here we are using inmemory user and password and below we have DB username and password
        return  User
                .withUsername("rakesh")
                //.password("{noop}rex123")//Use{noop} for NoOpPasswordEncoder, which store the password in plain text, highly recommended not to use in production
                .password(encoder.encode("rak123"))//here we are encoding our plain password using Bcrypt encoder
                //.roles("USER","ADMIN")
                .authorities("admin")
                .build();

       /* Use the below process to extract user details from DB and compare it with the credentials provided by the user
Here we will retrieve the User object using the DAO, and if it exists, wrap it into a MyUserPrincipal object,
which implements UserDetails, and returns it.
Here User is the entity class.
       User user = userRepository.findByUsername(username);
        if (user == null) {
            throw new UsernameNotFoundException(username);
        }
        return new MyUserPrincipal(user);
    }

    //Another independent class
    public class MyUserPrincipal implements UserDetails {
        private User user;

        public MyUserPrincipal(User user) {
            this.user = user;
        }
        ...*/
    }
}
