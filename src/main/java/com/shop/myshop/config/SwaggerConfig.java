package com.shop.myshop.config;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Info;
import lombok.RequiredArgsConstructor;
import org.springdoc.core.models.GroupedOpenApi;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@OpenAPIDefinition(
    info = @Info(title = "Myshop app",
    description = "Myshop 에서 제공하는 모든 API 에 대한 명세",
    version = "v1")
)
@RequiredArgsConstructor
@Configuration
public class SwaggerConfig {

  @Bean
  public GroupedOpenApi createdOpenApi(){
    String[] paths = {"/v1/**"};
    return GroupedOpenApi.builder()
        .group("MYSHOP API v1")
        .pathsToMatch(paths)
        .build();
  }
}
