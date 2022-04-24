package com.talky.socialservice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan(basePackages = {"com.talky.commons", "com.talky.socialservice"})
public class SocialServiceApplication {

  public static void main(String[] args) {
    SpringApplication.run(SocialServiceApplication.class, args);
  }

}
