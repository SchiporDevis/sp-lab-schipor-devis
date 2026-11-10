package com.example.demo.controllers;

import com.example.demo.Book;
import com.example.demo.commands.CommandContext;
import com.example.demo.commands.CreateBookCommand;
import com.example.demo.commands.DeleteBookCommand;
import com.example.demo.commands.GetAllBooksCommand;
import com.example.demo.commands.GetBookByIdCommand;
import com.example.demo.commands.UpdateBookCommand;
import com.example.demo.persistence.BooksRepository;
import com.example.demo.persistence.BooksRepositoryAdapter;
import com.example.demo.persistence.CrudRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/books")
public class BooksController {

    private final CommandContext commandContext;

    @Autowired
    public BooksController(CrudRepository<Book, Integer> booksRepository, CommandContext commandContext) {
        this.commandContext = commandContext;
        this.commandContext.setBooksRepository(booksRepository);
    }

    public BooksController(CrudRepository<Book, Integer> booksRepository) {
        this.commandContext = new CommandContext(booksRepository);
    }

    public BooksController(BooksRepository booksRepository) {
        this.commandContext = new CommandContext(new BooksRepositoryAdapter(booksRepository));
    }

    @GetMapping
    public ResponseEntity<List<Book>> getAllBooks() {
        List<Book> books = new GetAllBooksCommand(commandContext).execute();
        return ResponseEntity.ok(books);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Book> getBookById(@PathVariable Integer id) {
        Optional<Book> book = new GetBookByIdCommand(commandContext, id).execute();
        return book.map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<Book> createBook(@RequestBody(required = false) Book book) {
        Book createdBook = new CreateBookCommand(commandContext, book).execute();
        return ResponseEntity.status(HttpStatus.CREATED).body(createdBook);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Book> updateBook(@PathVariable Integer id, @RequestBody Book book) {
        Optional<Book> updatedBook = new UpdateBookCommand(commandContext, id, book).execute();
        return updatedBook.map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteBook(@PathVariable Integer id) {
        Boolean deleted = new DeleteBookCommand(commandContext, id).execute();
        if (deleted) {
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.notFound().build();
    }
}
