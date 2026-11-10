package com.example.demo;

import com.example.demo.persistence.CrudRepository;
import org.h2.server.web.JakartaWebServlet;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.boot.web.servlet.ServletRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.ApplicationContext;
import com.example.demo.difexamples.ClientComponent;
import com.example.demo.difexamples.SingletonComponent;
import com.example.demo.difexamples.TransientComponent;

@SpringBootApplication
public class DemoApplication {

    public static void main(String[] args) {
        SpringApplication.run(DemoApplication.class, args);
    }

    @Bean
    @ConditionalOnProperty(name = "spring.h2.console.enabled", havingValue = "true")
    public ServletRegistrationBean<JakartaWebServlet> h2ConsoleServletRegistration() {
        return new ServletRegistrationBean<>(new JakartaWebServlet(), "/h2-console/*");
    }

    @Bean
    public CommandLineRunner seedDatabase(CrudRepository<Book, Integer> booksRepository) {
        return args -> {
            if (booksRepository.findAll().isEmpty()) {
                Book book = new Book("Design Patterns - Lab 5-6");
                book.addAuthor(new Author("Erich Gamma"));
                book.addAuthor(new Author("Richard Helm"));

                book.addContent(new TableOfContents("Cuprins: Capitolul 1"));

                Section cap1 = new Section("Capitolul 1");
                cap1.add(new Paragraph("Paragraph 1"));
                cap1.add(new Paragraph("Paragraph 2"));
                cap1.add(new ImageProxy("ImageOne"));

                Image img = new Image();
                img.setImageName("ImageTwo");
                cap1.add(img);

                cap1.add(new Table("Table 1"));
                book.addContent(cap1);

                booksRepository.save(book);
            }
        };
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
