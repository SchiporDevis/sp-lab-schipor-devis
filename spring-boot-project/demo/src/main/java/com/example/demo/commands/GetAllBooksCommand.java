package com.example.demo.commands;

import com.example.demo.Book;

import java.util.List;

public class GetAllBooksCommand implements Command<List<Book>> {

    private final CommandContext context;

    public GetAllBooksCommand(CommandContext context) {
        this.context = context;
    }

    @Override
    public List<Book> execute() {
        return context.getBooksRepository().findAll();
    }
}
