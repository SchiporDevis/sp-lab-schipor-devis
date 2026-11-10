package com.example.demo.commands;

import com.example.demo.Author;
import com.example.demo.Book;
import com.example.demo.persistence.BooksRepository;
import com.example.demo.persistence.BooksRepositoryAdapter;
import com.example.demo.persistence.CrudRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class CommandContext {

    private CrudRepository<Book, Integer> booksRepository;
    private CrudRepository<Author, Integer> authorsRepository;

    public CommandContext() {
    }

    public CommandContext(CrudRepository<Book, Integer> booksRepository) {
        this.booksRepository = booksRepository;
    }

    @Autowired
    public CommandContext(CrudRepository<Book, Integer> booksRepository,
                          CrudRepository<Author, Integer> authorsRepository) {
        this.booksRepository = booksRepository;
        this.authorsRepository = authorsRepository;
    }

    public CommandContext(BooksRepository booksRepository) {
        this.booksRepository = new BooksRepositoryAdapter(booksRepository);
    }

    public CrudRepository<Book, Integer> getBooksRepository() {
        return booksRepository;
    }

    public void setBooksRepository(CrudRepository<Book, Integer> booksRepository) {
        this.booksRepository = booksRepository;
    }

    public void setBooksRepository(BooksRepository booksRepository) {
        this.booksRepository = new BooksRepositoryAdapter(booksRepository);
    }

    public CrudRepository<Author, Integer> getAuthorsRepository() {
        return authorsRepository;
    }

    public void setAuthorsRepository(CrudRepository<Author, Integer> authorsRepository) {
        this.authorsRepository = authorsRepository;
    }
}
