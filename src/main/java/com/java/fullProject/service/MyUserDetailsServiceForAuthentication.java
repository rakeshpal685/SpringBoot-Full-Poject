package com.java.fullProject.service;

import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.factory.PasswordEncoderFactories;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class MyUserDetailsServiceForAuthentication implements UserDetailsService {
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        //return new User("rakesh","rakesh@123", Collections.emptyList());
        PasswordEncoder encoder = PasswordEncoderFactories.createDelegatingPasswordEncoder();//This is used to get bcrypt encoder type.

        return  User
                .withUsername("rakesh")
                //.password("{noop}rex123")//Use{noop} for NoOpPasswordEncoder, which store the password in plain text, highly recommended not to use in production
                .password(encoder.encode("rak123"))//here we are encoding our plain password using Bcrypt encoder
                //.roles("USER","ADMIN")
                .authorities("admin")
                .build();

       /* Use the below process to extract user details from DB
In this method, we retrieve the User object using the DAO, and if it exists, wrap it into a MyUserPrincipal object,
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
