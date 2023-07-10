/*
package com.java.fullProject.service;

import java.util.Arrays;
import java.util.Collection;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

In spring security config class, I will pass this class to AuthenticationManagerBuilder, as our class has implemented UserDetailsService,
our loadUserByUsername method will be called. In this method we can check the username and password provided by the user is correct or not
by using DB details.

@Service
public class MyUserDetailsServiceForDBAuthentication implements UserDetailsService {

    @Autowired
   // private UserRepo userRepository;
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

        Use the below process to extract user details from DB and compare it with the credentials provided by the user
Here we will retrieve the User object using the DAO, and if it exists, wrap it into a MyUserPrincipal object,
which implements UserDetails, and returns it.
Here User is the entity class.

       Optional<User> userinfo = userRepository.findByUsername(username);
        return userinfo.map(MyUserPrincipal::new).orElseThrow(()->new UsernameNotFoundException(username+" username not found in DB"));
    }

    //Another independent class
    public class MyUserPrincipal implements UserDetails {
        private String username;
        private String password;
        private List<GrantedAuthority> authorities;


        public MyUserPrincipal(User userinfo) {
            this.username = userinfo.getUsername();
            this.password = userinfo.getPassword();
            this.authorities = Arrays.stream(userinfo.getAuthorities().split(",")).map(SimpleGrantedAuthority::new)
                    .collect(Collectors.toList()); 
        }

        @Override
        public Collection<? extends GrantedAuthority> getAuthorities() {
            return authorities;
        }

        @Override
        public String getPassword() {
            return password;
        }

        @Override
        public String getUsername() {
            return username;
        }

        @Override
        public boolean isAccountNonExpired() {
            return true;
        }

        @Override
        public boolean isAccountNonLocked() {
            return true;
        }

        @Override
        public boolean isCredentialsNonExpired() {
            return true;
        }

        @Override
        public boolean isEnabled() {
            return true;
        }
    }
}
*/
