package com.talky.gateway;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Info;
import org.springdoc.core.GroupedOpenApi;
import org.springdoc.core.SwaggerUiConfigParameters;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.gateway.route.RouteDefinition;
import org.springframework.cloud.gateway.route.RouteDefinitionLocator;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Lazy;
import org.springframework.web.reactive.config.EnableWebFlux;

import java.util.ArrayList;
import java.util.List;

@SpringBootApplication
@EnableWebFlux
public class GatewayApplication {

  public static void main(String[] args) {
    SpringApplication.run(GatewayApplication.class, args);
  }


  //  @Bean
//  public List<GroupedOpenApi> apis(SwaggerUiConfigParameters swaggerUiConfigParameters, RouteDefinitionLocator locator) {
//    List<GroupedOpenApi> groups = new ArrayList<>();
//    List<RouteDefinition> definitions = locator.getRouteDefinitions().collectList().block();
//    for (RouteDefinition definition : definitions) {
//      System.out.println("id: " + definition.getId()+ "  "+definition.getUri().toString());
//    }
//    definitions.stream().filter(routeDefinition -> routeDefinition.getId().matches(".*-service")).forEach(routeDefinition -> {
//      String name = routeDefinition.getId().replaceAll("-service", "");
//      swaggerUiConfigParameters.addGroup(name);
//      GroupedOpenApi.builder().pathsToMatch("/" + name + "/**").group(name).build();
//    });
//    return groups;
//  }
//  @Bean
//  @Lazy(false)
//  public List<GroupedOpenApi> apis(SwaggerUiConfigParameters swaggerUiConfigParameters, RouteDefinitionLocator locator) {
//    List<GroupedOpenApi> groups = new ArrayList<>();
//    List<RouteDefinition> definitions = locator.getRouteDefinitions().collectList().block();
//    for (RouteDefinition definition : definitions) {
//      System.out.println("id: " + definition.getId() + "  " + definition.getUri().toString());
//    }
//    definitions.stream().filter(routeDefinition -> routeDefinition.getId().matches(".*-service")).forEach(routeDefinition -> {
//      String name = routeDefinition.getId().replaceAll("-service", "");
//      swaggerUiConfigParameters.addGroup(name);
//      GroupedOpenApi.builder().pathsToMatch("/" + name + "/**").group(name).build();
//    });
//    return groups;
//  }

  @Autowired
  RouteDefinitionLocator locator;

  @Bean
  public List<GroupedOpenApi> apis() {
    List<GroupedOpenApi> groups = new ArrayList<>();
    List<RouteDefinition> definitions = locator.getRouteDefinitions().collectList().block();
    assert definitions != null;
    definitions.stream().filter(routeDefinition -> routeDefinition.getId().matches(".*-service")).forEach(routeDefinition -> {
      String name = routeDefinition.getId().replaceAll("-service", "");
      groups.add(GroupedOpenApi.builder().pathsToMatch("/" + name + "/**").group(name).build());
    });
    return groups;
  }

}
