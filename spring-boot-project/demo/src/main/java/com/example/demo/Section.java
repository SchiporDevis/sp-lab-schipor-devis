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

    @Override
    public void print() {
        System.out.println(title);
        for (Element element : children) {
            element.print();
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
