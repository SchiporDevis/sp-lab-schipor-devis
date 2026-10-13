package com.example.demo;

public class TableOfContents implements Element {
    private String text;

    public TableOfContents(String text) {
        this.text = text;
    }

    public void render() {
        System.out.println("TableOfContents: " + text);
    }

    @Override
    public void accept(Visitor visitor) {
        visitor.visit(this);
    }

    @Override
    public void add(Element element) {
        throw new UnsupportedOperationException();
    }

    @Override
    public void remove(Element element) {
        throw new UnsupportedOperationException();
    }

    @Override
    public Element get(int index) {
        throw new UnsupportedOperationException();
    }
}