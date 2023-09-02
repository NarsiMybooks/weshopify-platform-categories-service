package com.weshopify.platform.config;

import org.springframework.context.annotation.Configuration;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Contact;
import io.swagger.v3.oas.annotations.info.Info;
import io.swagger.v3.oas.annotations.info.License;
import io.swagger.v3.oas.annotations.tags.Tag;

@Configuration
@OpenAPIDefinition(info = @Info(title = "Weshopify-Categories-Service",
								description = "Weshopify-Categories-Service",
								license = @License(name = "All Rights Rserved@2023 By TechhubVault.com"),
								version = "v1",
								termsOfService = "All Rights Rserved@2023 By TechhubVault.com",
								contact = @Contact(email = "info@techhubvault.com",
								name = "TechHubVault",url = "https://www.techhubvault.com")),
                  tags = {@Tag(name = "/v1/categories",description = "Weshopify-Categories-Service")})
public class OpenAPI3Config {

}
