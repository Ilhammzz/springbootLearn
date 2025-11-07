package com.indocyber.ioc;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class IocApplication {

    public static void main(String[] args) {
        ApplicationContext context = SpringApplication.run(IocApplication.class, args);

        System.out.println("Jumlah dari bean: " + context.getBeanDefinitionCount());
        for (String beanDefinitionName : context.getBeanDefinitionNames()) {
            System.out.println(beanDefinitionName);
        }
        System.out.println("Hasil bean 'test': " + context.getBean("test"));
        System.out.println("Hasil bean 'another class': " + context.getBean(AnotherClass.class));
    }

    // DEPENDENCY INJECTION
    // Sebagai contoh kita mencoba @Bean di class @SpringBootApplication,
    // Kedepannya disarankan untuk hanya tambahkan @Bean di @Configuration class
    @Bean
    public static String test(@Qualifier("simpleClass1") SimpleClass sc) {
        System.out.println(sc.someMethod());
        return "hello world";
    }

    @Bean
    public SimpleClass simpleClass1() {
        return new SimpleClass();
    }

    @Bean
    public SimpleClass simpleClass2() {
        return new SimpleClass();
    }

    @Bean
    public AnotherClass anotherClass() {
        return new AnotherClass();
    }

}
