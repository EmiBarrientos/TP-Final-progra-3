package com.example.demo.config;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Info;
import org.springframework.context.annotation.Configuration;

@OpenAPIDefinition(
        info = @Info(
                title = "CheckInTech API",
                version = "1.0",
                description = "Documentación de la API para gestión de hotel"
        )
)
@Configuration
public class OpenApiConfig {
}