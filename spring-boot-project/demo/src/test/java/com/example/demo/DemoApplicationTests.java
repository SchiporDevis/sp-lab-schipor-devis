package com.example.demo;

import com.example.demo.controllers.BooksController;
import com.example.demo.persistence.AuthorsRepositoryAdapter;
import com.example.demo.persistence.BooksRepositoryAdapter;
import com.example.demo.persistence.CrudRepository;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

@SpringBootTest(properties = {
        "spring.datasource.url=jdbc:h2:mem:testbooksdb;DB_CLOSE_DELAY=-1"
})
class DemoApplicationTests {

    @Autowired
    private BooksController booksController;

    @Autowired
    private CrudRepository<Book, Integer> booksCrudRepository;

    @Autowired
    private BooksRepositoryAdapter booksRepositoryAdapter;

    @Autowired
    private AuthorsRepositoryAdapter authorsRepositoryAdapter;

    @Test
    void contextLoadsAndCrudFlowWorks() throws Exception {
        assertNotNull(booksController);
        assertNotNull(booksCrudRepository);
        assertNotNull(booksRepositoryAdapter);
        assertNotNull(authorsRepositoryAdapter);

        // 1. Verificăm cartea pre-populată la startup (GET /books)
        ResponseEntity<List<Book>> allResponse = booksController.getAllBooks();
        assertEquals(HttpStatus.OK, allResponse.getStatusCode());
        assertNotNull(allResponse.getBody());
        assertFalse(allResponse.getBody().isEmpty());

        // 2. Testăm crearea unei cărți noi (POST /books) prin CreateBookCommand
        Book newBook = new Book("Spring Data JPA Book");
        newBook.addAuthor(new Author("John Vlissides"));
        Section section = new Section("Introduction");
        section.add(new Paragraph("First paragraph"));
        section.add(new ImageProxy("cover.png"));
        section.add(new Table("Summary Table"));
        newBook.addContent(section);

        ResponseEntity<Book> createdResponse = booksController.createBook(newBook);
        assertEquals(HttpStatus.CREATED, createdResponse.getStatusCode());
        Book savedBook = createdResponse.getBody();
        assertNotNull(savedBook);
        assertNotNull(savedBook.getId());
        assertEquals("Spring Data JPA Book", savedBook.getTitle());

        Integer id = savedBook.getId();

        // 3. Testăm citirea după ID (GET /books/{id}) și serializarea JSON cu Jackson
        ResponseEntity<Book> byIdResponse = booksController.getBookById(id);
        assertEquals(HttpStatus.OK, byIdResponse.getStatusCode());
        assertNotNull(byIdResponse.getBody());
        assertEquals("Spring Data JPA Book", byIdResponse.getBody().getTitle());

        ObjectMapper mapper = new ObjectMapper();
        String json = mapper.writeValueAsString(byIdResponse.getBody());
        assertTrue(json.contains("Spring Data JPA Book"));
        assertFalse(json.contains("realImg"));

        // 4. Testăm actualizarea (PUT /books/{id})
        Book updatePayload = new Book("Updated Title");
        ResponseEntity<Book> updatedResponse = booksController.updateBook(id, updatePayload);
        assertEquals(HttpStatus.OK, updatedResponse.getStatusCode());
        assertNotNull(updatedResponse.getBody());
        assertEquals("Updated Title", updatedResponse.getBody().getTitle());

        // 5. Testăm ștergerea (DELETE /books/{id})
        ResponseEntity<Void> deleteResponse = booksController.deleteBook(id);
        assertEquals(HttpStatus.NO_CONTENT, deleteResponse.getStatusCode());

        ResponseEntity<Book> afterDelete = booksController.getBookById(id);
        assertEquals(HttpStatus.NOT_FOUND, afterDelete.getStatusCode());
    }
}
