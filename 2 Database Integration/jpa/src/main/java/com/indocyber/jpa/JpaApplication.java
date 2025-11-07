package com.indocyber.jpa;

import com.indocyber.jpa.menus.MainMenu;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.util.Scanner;

@SpringBootApplication
public class JpaApplication {

    public static void main(String[] args) {
        var context = SpringApplication.run(JpaApplication.class, args);
        context.getBean(MainMenu.class).run();
    }

}
