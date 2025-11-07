package com.indocyber.security.documentation;

import io.swagger.v3.oas.models.Components;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.security.SecurityRequirement;
import io.swagger.v3.oas.models.security.SecurityScheme;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class DocumentationConfiguration {

    @Value("${spring.application.name}")
    private String appName;

    @Bean
    public OpenAPI appDocumentation() {
        var info = new Info()
                .title(appName)
                .description("Contoh JWT Auth Configuration")
                .contact(new Contact().name("John Doe").email("doe@indocyber.id"))
                .version("v0.1.0");

        String bearerAuth = "bearerAuth";
        var securityRequirement = new SecurityRequirement().addList(bearerAuth);

        var securityScheme = new SecurityScheme()
                .name(bearerAuth)
                .type(SecurityScheme.Type.HTTP)
                .scheme("Bearer")
                .bearerFormat("JWT");
        var securitySchemes = new Components().addSecuritySchemes(bearerAuth, securityScheme);

        return new OpenAPI().info(info)
                .addSecurityItem(securityRequirement)
                .components(securitySchemes);
    }
}
