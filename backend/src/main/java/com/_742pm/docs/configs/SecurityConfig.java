package com._742pm.docs.configs;

import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpStatus;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.web.authentication.HttpStatusEntryPoint;

@Configuration
public class SecurityConfig extends WebSecurityConfigurerAdapter
{
    @Override
    protected void configure(HttpSecurity http) throws Exception
    {
       http.antMatcher("/**").authorizeRequests()
                         .antMatchers("/login**").permitAll()
                         .anyRequest()
                         .authenticated()
                         .and()
                         .logout()
                         .and().oauth2Login();
    }
}
