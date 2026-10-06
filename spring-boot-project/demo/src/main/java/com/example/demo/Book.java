package com.example.demo;

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

    public void addAuthor(Author author) {
        this.authors.add(author);
    }

    public void addContent(Element element) {
        this.content.add(element);
    }

    public void print() {
        System.out.println("Book: " + title + "\n");

        if (!authors.isEmpty()) {
            System.out.println("Authors:");
            for (Author author : authors) {
                author.print();
            }
            System.out.println();
        }

        for (Element element : content) {
            element.print();
        }
    }
}