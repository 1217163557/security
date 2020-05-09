package com.example.hibernateone.configer;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.security.servlet.WebSecurityEnablerConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationDetailsSource;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.ForwardAuthenticationSuccessHandler;
import org.springframework.security.web.authentication.WebAuthenticationDetails;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

import javax.servlet.http.HttpServletRequest;

/**
 * @author admin
 */

@EnableWebSecurity
public class security extends WebSecurityConfigurerAdapter {
    @Autowired
    private CustomUserDetailsService userDetailsService;
    @Autowired
    private SuccessAuthenticationHandler successHandler;
    @Autowired
    private AuthenticationDetailsSource<HttpServletRequest, WebAuthenticationDetails> authenticationDetailsSource;
    @Autowired
    private CustomAuthenticationProvider customAuthenticationProvider;
    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
            .authorizeRequests()
                .antMatchers("/", "/user").hasRole("USER")
                .antMatchers("/admin").hasRole("ADMIN")
//                .antMatchers("/login2").permitAll()
                .antMatchers("/index").permitAll()
                .anyRequest()
                .authenticated()

                .and()
            .formLogin()
                .loginPage("/user/home")
                .loginProcessingUrl("/login")
//                .successHandler(successHandler)
                .usernameParameter("username")
                .passwordParameter("password")
                .successForwardUrl("/user/login2")
                .defaultSuccessUrl("/user/login2")
                .failureForwardUrl("/user/error")
//                .authenticationDetailsSource(authenticationDetailsSource)
                .permitAll()
                .and()

            .logout()
                .permitAll()
                .and()
                .csrf().disable();


    }

//    @Override
//    protected void configure(AuthenticationManagerBuilder auth) throws Exception {

//        auth.inMemoryAuthentication()
//                .passwordEncoder(new BCryptPasswordEncoder())
//                .withUser("root")
//                .password(new BCryptPasswordEncoder().encode("root"))
//                .roles("ADMIN","USER")
//                .and()
//                .passwordEncoder(new BCryptPasswordEncoder())
//                .withUser("user")
//                .password(new BCryptPasswordEncoder().encode("user"))
//
//                .roles("USER");


//    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {

        auth.userDetailsService(userDetailsService).passwordEncoder(passwordEncoder());
        auth.authenticationProvider(customAuthenticationProvider);

    }
    @Bean
    public PasswordEncoder passwordEncoder(){
        return  new BCryptPasswordEncoder();
    }
}
