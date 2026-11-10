package com.example.demo.commands;

import com.example.demo.Book;

import java.util.Optional;

public class UpdateBookCommand implements Command<Optional<Book>> {

    private final CommandContext context;
    private final Integer id;
    private final Book updatedBook;

    public UpdateBookCommand(CommandContext context, Integer id, Book updatedBook) {
        this.context = context;
        this.id = id;
        this.updatedBook = updatedBook;
    }

    @Override
    public Optional<Book> execute() {
        Optional<Book> existing = context.getBooksRepository().findById(id);
        if (existing.isPresent()) {
            Book book = existing.get();
            if (updatedBook.getTitle() != null) {
                book.setTitle(updatedBook.getTitle());
            }
            if (updatedBook.getAuthors() != null) {
                book.setAuthors(updatedBook.getAuthors());
            }
            if (updatedBook.getContent() != null) {
                book.setContent(updatedBook.getContent());
            }
            return Optional.of(context.getBooksRepository().save(book));
        }
        return Optional.empty();
    }
}
