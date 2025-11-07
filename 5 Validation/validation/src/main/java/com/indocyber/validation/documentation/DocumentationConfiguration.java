package com.indocyber.validation.documentation;


import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;
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
                .description("Contoh custom validation")
                .contact(new Contact().name("John Doe").email("doe@indocyber.id"))
                .version("v 0.1.0");

        return new OpenAPI().info(info);
    }
}
