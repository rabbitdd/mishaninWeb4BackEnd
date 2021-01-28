package com.web4.mishanin.Web4.configuration;

import com.web4.mishanin.Web4.data.CustomerUserDetailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {
    private final String point1Dep = "/Web4-0.0.1-SNAPSHOT/auth/";
    private final String point2Dep = "/Web4-0.0.1-SNAPSHOT/signUp/";
    private final String point1Test = "/auth";
    private final String point2Test = "/signUp";
    @Override
    public void configure(HttpSecurity httpSecurity) throws Exception{
        httpSecurity.cors();
        httpSecurity.csrf().disable()
                .authorizeRequests()
                .antMatchers(point1Test).permitAll()
                .antMatchers(point2Test).permitAll()
                .and().httpBasic()
                .and().sessionManagement().disable();
    }

    @Autowired
    CustomerUserDetailService service;

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        System.out.println("auth");
        auth.userDetailsService(service);
    }

    @Bean
    public PasswordEncoder getPasswordEncoder() {
        return new BCryptPasswordEncoder(12);
    }
}
