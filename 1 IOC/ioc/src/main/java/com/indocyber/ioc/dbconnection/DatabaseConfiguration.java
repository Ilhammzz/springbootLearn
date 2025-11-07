package com.indocyber.ioc.dbconnection;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

/**
 * salah satu contoh penggunaan IOC
 */
@Configuration
public class DatabaseConfiguration {

    @Profile("sqlServer")
    @Bean
    public DatabaseConnection connectToSqlServer() {
        System.out.println("Connected to SQL Server");
        return new SqlServerConnection();
    }

    @Profile("oracle")
    @Bean
    public DatabaseConnection connectToOracle() {
        System.out.println("Connected to Oracle");
        return new OracleConnection();
    }
}
