package com.example.demo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import com.example.demo.difexamples.ClientComponent;
import com.example.demo.difexamples.SingletonComponent;
import com.example.demo.difexamples.TransientComponent;

@SpringBootApplication
public class DemoApplication {

    public static void main(String[] args) {
        // Obținem contextul de Dependency Injection (DI) de la Spring
        ApplicationContext context = SpringApplication.run(DemoApplication.class, args);

        System.out.println("\n--- TESTARE TRANSIENT COMPONENT ---");
        TransientComponent transientBean1 = context.getBean(TransientComponent.class);
        transientBean1.operation();

        // De fiecare dată când cerem un TransientComponent, se creează o instanță nouă
        TransientComponent transientBean2 = context.getBean(TransientComponent.class);
        transientBean2.operation();

        System.out.println("\n--- TESTARE SINGLETON COMPONENT ---");
        SingletonComponent singletonBean1 = context.getBean(SingletonComponent.class);
        singletonBean1.operation();

        // Returnează exact aceeași instanță unică creată la pornire
        SingletonComponent singletonBean2 = context.getBean(SingletonComponent.class);
        singletonBean2.operation();

        System.out.println("\n--- TESTARE CLIENT COMPONENT ---");
        ClientComponent client = context.getBean(ClientComponent.class);
        client.operation();
    }
}