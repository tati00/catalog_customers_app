package com.paco.catalog_customers.config;

import io.swagger.v3.oas.models.Components;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.security.SecurityRequirement;
import io.swagger.v3.oas.models.security.SecurityScheme;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class OpenApiConfig {

    @Bean
    public OpenAPI customOpenAPI() {
        return new OpenAPI()
            // Aplica la seguridad de forma global a todos los endpoints
            .addSecurityItem(new SecurityRequirement().addList("BearerAuth"))
            .components(new Components()
                // Define el esquema de seguridad tipo Bearer Token
                .addSecuritySchemes("BearerAuth", new SecurityScheme()
                    .name("BearerAuth")
                    .type(SecurityScheme.Type.HTTP)
                    .scheme("bearer")
                    .bearerFormat("JWT")
                    .description("Introduce el token JWT devuelto por el login para autenticar las peticiones.")));
    }
}