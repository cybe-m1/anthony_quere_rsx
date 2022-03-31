package com.talky.assetservice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan(basePackages = {"com.talky.commons", "com.talky.assetservice"})
public class AssetServiceApplication {

  public static void main(String[] args) {
    SpringApplication.run(AssetServiceApplication.class, args);
  }

}
