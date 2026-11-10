package com.example.demo.commands;

import com.example.demo.Book;

import java.util.Optional;

public class DeleteBookCommand implements Command<Boolean> {

    private final CommandContext context;
    private final Integer id;

    public DeleteBookCommand(CommandContext context, Integer id) {
        this.context = context;
        this.id = id;
    }

    @Override
    public Boolean execute() {
        Optional<Book> existing = context.getBooksRepository().findById(id);
        if (existing.isPresent()) {
            context.getBooksRepository().deleteById(id);
            return true;
        }
        return false;
    }
}
