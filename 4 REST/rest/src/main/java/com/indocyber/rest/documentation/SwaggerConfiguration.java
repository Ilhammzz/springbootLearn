package com.indocyber.rest.documentation;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SwaggerConfiguration {

    @Bean
    public OpenAPI appDocumentation() {
        var info = new Info()
                .title("Spring boot REST Example")
                .description("Latihan membuat Spring boot RESTful API")
                .contact(new Contact().name("John Doe").email("doe@indocyber.id"))
                .version("v 0.1.0");

        return new OpenAPI().info(info);
    }
}
