package com.example.demo;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.OneToMany;

import java.util.ArrayList;
import java.util.List;

@Entity
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
public class Section extends BaseElement {

    private String title;

    @OneToMany(targetEntity = BaseElement.class, cascade = CascadeType.ALL)
    private List<Element> children = new ArrayList<>();

    public Section() {
        this.children = new ArrayList<>();
    }

    public Section(String title) {
        this.title = title;
        this.children = new ArrayList<>();
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public List<Element> getChildren() {
        return children;
    }

    public void setChildren(List<Element> children) {
        this.children = children;
    }

    public void render() {
        System.out.println(title);
    }

    @Override
    public void accept(Visitor visitor) {
        visitor.visit(this);
        if (children != null) {
            for (Element child : children) {
                child.accept(visitor);
            }
        }
    }

    @Override
    public void add(Element element) {
        if (children == null) {
            children = new ArrayList<>();
        }
        children.add(element);
    }

    @Override
    public void remove(Element element) {
        if (children != null) {
            children.remove(element);
        }
    }

    @Override
    public Element get(int index) {
        return children.get(index);
    }
}
