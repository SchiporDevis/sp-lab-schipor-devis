package com.example.demo;

import java.util.ArrayList;
import java.util.List;

public class Section implements Element {

    private String title;
    private List<Element> children;

    public Section(String title) {
        this.title = title;
        this.children = new ArrayList<>();
    }

    public void render() {
        System.out.println(title);
    }

    @Override
    public void accept(Visitor visitor) {
        visitor.visit(this);
        for (Element child : children) {
            child.accept(visitor);
        }
    }

    @Override
    public void add(Element element) {
        children.add(element);
    }

    @Override
    public void remove(Element element) {
        children.remove(element);
    }

    @Override
    public Element get(int index) {
        return children.get(index);
    }
}
