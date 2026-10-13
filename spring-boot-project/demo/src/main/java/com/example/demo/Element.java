package com.example.demo;


public interface Element {
    void accept(Visitor visitor);
    void add(Element element);
    void remove(Element element);
    Element get(int index);
}
