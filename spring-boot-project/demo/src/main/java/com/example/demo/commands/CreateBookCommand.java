package com.example.demo.commands;

import com.example.demo.Book;
import com.example.demo.persistence.CrudRepository;

public class CreateBookCommand implements Command<Book> {

    private final CommandContext context;
    private final Book requestBook;

    public CreateBookCommand(CommandContext context) {
        this(context, null);
    }

    public CreateBookCommand(CommandContext context, Book requestBook) {
        this.context = context;
        this.requestBook = requestBook;
    }

    @Override
    public Book execute() {
        Book book;
        if (requestBook != null) {
            book = new Book(requestBook.getTitle() != null ? requestBook.getTitle() : "New Book");
            if (requestBook.getAuthors() != null) {
                book.setAuthors(requestBook.getAuthors());
            }
            if (requestBook.getContent() != null) {
                book.setContent(requestBook.getContent());
            }
        } else {
            book = new Book("New Book");
        }
        CrudRepository<Book, Integer> booksRepository = context.getBooksRepository();
        book = booksRepository.save(book);
        return book;
    }
}
