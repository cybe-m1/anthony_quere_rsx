package com.talky.userservice.security;

import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {

  @Override
  protected void configure(HttpSecurity http) throws Exception {
    http.csrf().disable();
    http.cors().disable();

    http
      .authorizeRequests()
      .antMatchers(HttpMethod.GET, "/api/v1/users").permitAll()
      .antMatchers(HttpMethod.GET, "/api/v1/users/me").authenticated()
      .antMatchers(HttpMethod.GET, "/api/v1/users/**").permitAll()
      .antMatchers("/api/**").authenticated()
      .and()
      .oauth2ResourceServer().jwt();
  }


}
