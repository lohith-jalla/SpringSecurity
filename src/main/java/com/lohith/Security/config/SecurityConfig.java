package com.lohith.Security.config;

import com.lohith.Security.service.MyUserDetailsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
@EnableMethodSecurity
public class SecurityConfig {


    @Autowired
    MyUserDetailsService myUserDetailsService;

    @Bean
    SecurityFilterChain defaultSecurityFilterChain(HttpSecurity http) throws Exception {
      return http
                .csrf(customizer -> customizer.disable())
                .authorizeHttpRequests(requests -> (
                        requests.requestMatchers("/h2-console/**").permitAll())
                        .anyRequest().authenticated())
                .sessionManagement(
                session ->session.sessionCreationPolicy(
                        SessionCreationPolicy.STATELESS))
                .httpBasic(Customizer.withDefaults())
                .formLogin(Customizer.withDefaults())
                .build();
    }

    @Bean
    public AuthenticationProvider authenticationProvider() {
        DaoAuthenticationProvider provider = new DaoAuthenticationProvider();
        provider.setPasswordEncoder(new  BCryptPasswordEncoder(12));
        provider.setUserDetailsService(myUserDetailsService);
        return provider;
    }

//    @Bean
//    public UserDetailsService userDetailsService() { //we are using this for InMemory security without any database.
//        List<UserDetails> users = new ArrayList<>(); //List of user details
//        UserDetails user= User.withUsername("user")
//                .password("{noop}userPass") //{noop} this tells the string must be encoded or hashed before any request to avoid any forgery.
//                .roles("USER")
//                .build();
//
//        UserDetails admin= User.withUsername("admin")
//                .password("{noop}adminPass")
//                .roles("ADMIN")
//                .build();
//
//        users.add(user);
//        users.add(admin);
//
//       //return new InMemoryUserDetailsManager(user,admin) ; // instead of passing all the user as parameters we can also pass a list of UserDetails.
//        return new InMemoryUserDetailsManager(users);
//    }
}
