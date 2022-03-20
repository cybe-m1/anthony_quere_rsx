package com.talky.userservice.security;

import com.talky.commons.security.Auth0AdienceValidatorConfig;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

@Configuration
@Import({Auth0AdienceValidatorConfig.class})
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {

  @Override
  protected void configure(HttpSecurity http) throws Exception {
    http.csrf().disable();
    http.cors().disable();

    http
      .authorizeRequests()
      .antMatchers("/**").permitAll()
      .and()
      .oauth2ResourceServer().jwt();
  }


}
