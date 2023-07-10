package com.java.fullProject.configurations;


import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;

@Configuration
@EnableWebSecurity// This will create spring security filter chain
/*@EnableMethodSecurity//This is to tell that i have implemented method level security rather then
.requestMatchers("/empController/getAll").hasAuthority("ADMIN")*/
public class SpringSecurityConfiguration {

/*    The below bean is created to tell spring which URLs has to be protected and which are not.
     * This thing is common irrespective of the authentication type we are using*/
/*    @Bean
    public SecurityFilterChain securityFilter(HttpSecurity http) throws Exception {
        http
                .cors()
                .and()
                .csrf()
                .disable()
                .authorizeHttpRequests(request -> request
                        //.requestMatchers("/**").permitAll()//to permit everything after /
                        //.requestMatchers("/empController/getAll", "/empController/getEmployeeById/{id}").permitAll()//To tell which endpoints are allowed by all
                        .requestMatchers("/xyz").denyAll()//to deny these url for all
                        .requestMatchers("/abc").authenticated()//To authenticate for the provided url
                        .requestMatchers("/xyz").hasAnyAuthority("admin","user")//To authorize this URL can be accessed by users with the given multiple roles,admin/user are case-sensitive, use the same thing as written in DB
                        .requestMatchers("/xyz").hasAuthority("admin")//To authorize this URL can be accessed by admin only
                        //.requestMatchers("/empController/getAll").hasRole("ADMIN")//To authorize this URL can be accessed by users with the given role, has role is basically same as hasAuthority, just the difference is that
                       //.requestMatchers("/empController/getAll").hasAuthority("ADMIN")//To authorize this URL can be accessed by users with the Admin authority
                      //Instead of using.hasAuthority on a specific URL we can use @PreAuthorize("hasAuthority('ADMIN')") on the controller end point too, and use @EnableMethodSecurity on top of this class to anable it.
                        //in the DB the authority should be ROLE_ADMIN in hasRole case and just ADMIN in hasAuthority case.
                        .requestMatchers(HttpMethod.GET, "/lmn").authenticated()//If we have two methods of different type like GET nad POST,we can specify that also
                        .requestMatchers(HttpMethod.POST, "/lmn").authenticated()
                        //.anyRequest().authenticated()//Except the above endpoints authenticate all the available endpoints
                        .anyRequest().permitAll()//to permit all the available endpoints
                )
                //.formLogin(); If we have a login form then use this.
                .httpBasic();
        return http.build();
    }*/

    /*This is the process of using inMemory authentication, when we use InMemoryUserDetailsManager, spring automatically pick up the inMemory authentication,
    In this case spring won't provide us any default password in console.*/
/*
   @Bean
    public UserDetailsService configure() {
        PasswordEncoder encoder = PasswordEncoderFactories.createDelegatingPasswordEncoder();//This is used to get bcrypt encoder type.
        UserDetails normalUser = User
                .withUsername("rex")
                //.password("{noop}rex123")//Use{noop} for NoOpPasswordEncoder, which store the password in plain text, highly recommended not to use in production
                .password(encoder.encode("rex123"))//here we are encoding our plain password using Bcrypt encoder
                //.roles("USER")
                .authorities("USER","ADMIN")
                .build();
        UserDetails adminUser = User //here we are creating another user with different role
                .withUsername("rexy")
                .password("{noop}rexy123")//Use{noop} for NoOpPasswordEncoder, which store the password in plain text, highly recommended not to use in production
                .roles("ADMIN")
                //.authorities("USER")
                .build();
        return new InMemoryUserDetailsManager(normalUser,adminUser);
    }
*/




/*This is how we do DB configuration. If our DB has the tables in the same format as spring specified then
    * we don't have to do anything extra to fetch the users and passwords for them here.
    * -- users table structure

create table users(
	username varchar(50) not null primary key,
	password varchar(50) not null,
	enabled boolean not null
);

-- authorities table structure

create table authorities (
	username varchar(50) not null,
	authority varchar(50) not null,
	constraint fk_authorities_users foreign key(username) references users(username)
);



    @Autowired
    private DataSource dataSource; //import sql datasource, spring will automatically read the datasources from properties file
    @Autowired
    public void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.jdbcAuthentication()
                .dataSource(dataSource)


We don't have to write these if we are using DB tables as specified by Spring,else if we are using any other DB
table then to fetch the username, password,authority we have to run the query
       .usersByUsernameQuery("select u.username,u.password,u.enabled from usertable u where u.username=?")
                .authoritiesByUsernameQuery("select a.username,a.authority from authorities a where a.username=?")

                //.passwordEncoder(NoOpPasswordEncoder.getInstance());
                .passwordEncoder(new BCryptPasswordEncoder());//for rex the password is rex123

    }
    * If we have our own DB table for authentication use below code
*//*
@Bean
public UserDetailsService configure() {
    *//*Here the code will go to MyUserDetailsServiceForDBAuthentication class and hit loadUserByUsername method
    and fetch the user details from DB and do the authentication.*//*
    PasswordEncoder encoder = PasswordEncoderFactories.createDelegatingPasswordEncoder();//This is used to get bcrypt encoder type.
    return new MyUserDetailsServiceForDBAuthentication();
}
*/


/*
This is how we use UserDetailsService for authentication
    @Autowired
    private MyUserDetailsServiceForAuthentication MyUserDtlsService;


 When the data in the form is submitted then AuthenticationManagerBuilder is called, AuthenticationManagerBuilder will call MyUserDetailsServiceForAuthentication
class and as MyUserDetailsServiceForAuthentication class implements UserDetailsService it will by default call loadUserByUsername method and see if
the value given in the form is present in the DB or not.*//*

    @Autowired
    public void configure(AuthenticationManagerBuilder auth) throws Exception {
        //Here we are passing our class to AuthenticationManagerBuilder to match the user data
        auth.userDetailsService(MyUserDtlsService)
                //.passwordEncoder(NoOpPasswordEncoder.getInstance());
                .passwordEncoder(new BCryptPasswordEncoder());
    }
*/

}
