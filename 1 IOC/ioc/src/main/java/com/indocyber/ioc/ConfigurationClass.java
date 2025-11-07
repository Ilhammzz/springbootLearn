package com.indocyber.ioc;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ConfigurationClass {
    @Value("${application.title}")
    private String applicationTitle;

    @Bean
    public SimpleClass simpleClass3() {
        System.out.println("mengambil dari applicaiton.properties: " + applicationTitle);
        return new SimpleClass();
    }
}
