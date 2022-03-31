package com.talky.assetservice.security;

import com.talky.commons.security.Auth0AdienceValidatorConfig;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.oauth2.jwt.JwtDecoder;

@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {

//  @Value("${auth0.audience}")
//  private String audience;
//
//  @Value("${spring.security.oauth2.client.provider.auth0.issuer-uri}")
//  private String issuer;

//  @Bean
//  public JwtDecoder jwtDecoder() {
//    return Auth0AdienceValidatorConfig.jwtDecoder(issuer, audience);
//  }

  @Override
  protected void configure(HttpSecurity http) throws Exception {
    http.csrf().disable();
    http.cors().disable();

    http
      .authorizeRequests()
      .antMatchers(HttpMethod.GET, "/api/v1/assets/**").permitAll()
      .antMatchers("/**").authenticated()
      .and()
      .oauth2ResourceServer().jwt();
  }


}
