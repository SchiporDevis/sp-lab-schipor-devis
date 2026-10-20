package com.example.demo;

import java.util.ArrayList;
import java.util.List;

import java.util.ArrayList;
import java.util.List;

public class Book {
    private String title;
    private List<Author> authors;
    private List<Element> content;

    public Book(String title) {
        this.title = title;
        this.authors = new ArrayList<>();
        this.content = new ArrayList<>();
    }

    public String getTitle() {
        return title;
    }

    public void addAuthor(Author author) {
        this.authors.add(author);
    }

    public void addContent(Element element) {
        this.content.add(element);
    }

    public void accept(Visitor visitor) {
        visitor.visit(this);
        for (Element element : content) {
            element.accept(visitor);
        }
    }
}