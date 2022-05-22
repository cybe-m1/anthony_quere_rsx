package com.talky.socialservice;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Info;
import io.swagger.v3.oas.annotations.servers.Server;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan(basePackages = {"com.talky.commons", "com.talky.socialservice"})
@OpenAPIDefinition(
  servers = { @Server(url = "${exposed-url}") },
  info = @Info(title = "Social API", version = "v1", description = "Documentation of Social API")
)
public class SocialServiceApplication {

  public static void main(String[] args) {
    SpringApplication.run(SocialServiceApplication.class, args);
  }

}
