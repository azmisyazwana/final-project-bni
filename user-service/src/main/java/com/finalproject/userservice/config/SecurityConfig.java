package com.finalproject.userservice.config;


import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import java.util.List;

@Configuration
@EnableWebSecurity
@RequiredArgsConstructor
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    private final UserDetailsService userService;
//    private final SecurityFilter securityFilter;

    @Bean
    public PasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception{
        auth.userDetailsService(userService)
                .passwordEncoder(passwordEncoder());
    }

    @Bean
    @Override
    protected AuthenticationManager authenticationManager() throws  Exception{
        return super.authenticationManager();
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception{
//        http.authorizeRequests()
//                .antMatchers("/auth").permitAll()
//                .anyRequest().authenticated();

        http.csrf().disable()
                .authorizeHttpRequests()
                .antMatchers("/auth/**").permitAll()
                .antMatchers("/admin/**").permitAll()
                .antMatchers("/users/**").permitAll()
//                .antMatchers("/admin/**").hasAuthority("ROLE_ADMIN")


//                .antMatchers("users/admin/**").permitAll()
//                        .antMatchers("/users/**").permitAll()

                .anyRequest().authenticated();

//        http.httpBasic().and()
//                .authorizeRequests()
//                .antMatchers(HttpMethod.GET, "/auth/**").hasRole("ADMIN");

//        http.csrf().disable()
//                .authorizeRequests()
//                .antMatchers("/auth/**").permitAll()
//                .anyRequest().authenticated()
//                .and()
//                .httpBasic();

//        http.cors().and().csrf().disable().authorizeRequests()
//                        .antMatchers("/auth/**").permitAll()
//                        .anyRequest().authenticated();



        http.sessionManagement().sessionCreationPolicy((SessionCreationPolicy.STATELESS));
//        http.addFilterBefore(securityFilter, UsernamePasswordAuthenticationFilter.class);
    }

}
