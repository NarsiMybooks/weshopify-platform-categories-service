package com.weshopifyapp.features.categories.config;

import org.springframework.context.annotation.Configuration;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Info;
import io.swagger.v3.oas.annotations.info.License;

@Configuration
@OpenAPIDefinition(info = @Info(title = "Categories-Service",description = "Categories",
license = @License(name = "All Rights Reserved by durgasoft@2023")
))
public class OpenApiConfig {

}
