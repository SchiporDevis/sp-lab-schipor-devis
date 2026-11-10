package com.example.demo;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.OneToMany;

import java.util.ArrayList;
import java.util.List;

@Entity
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
public class Book {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private String title;

    @ManyToMany(cascade = CascadeType.ALL)
    private List<Author> authors = new ArrayList<>();

    @OneToMany(targetEntity = BaseElement.class, cascade = CascadeType.ALL)
    private List<Element> content = new ArrayList<>();

    public Book() {
        this.authors = new ArrayList<>();
        this.content = new ArrayList<>();
    }

    public Book(String title) {
        this.title = title;
        this.authors = new ArrayList<>();
        this.content = new ArrayList<>();
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public List<Author> getAuthors() {
        return authors;
    }

    public void setAuthors(List<Author> authors) {
        this.authors = authors;
    }

    public List<Element> getContent() {
        return content;
    }

    public void setContent(List<Element> content) {
        this.content = content;
    }

    public void addAuthor(Author author) {
        if (this.authors == null) {
            this.authors = new ArrayList<>();
        }
        this.authors.add(author);
    }

    public void addContent(Element element) {
        if (this.content == null) {
            this.content = new ArrayList<>();
        }
        this.content.add(element);
    }

    public void accept(Visitor visitor) {
        visitor.visit(this);
        if (content != null) {
            for (Element element : content) {
                element.accept(visitor);
            }
        }
    }
}
