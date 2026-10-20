package com.example.demo;

public class RenderContentVisitor implements Visitor {
    @Override
    public void visit(Book book) {
        System.out.println("Book: " + book.getTitle() + "\n");
    }

    @Override
    public void visit(Section section) {
        section.render();
    }

    @Override
    public void visit(TableOfContents tableOfContents) {
        tableOfContents.render();
    }

    @Override
    public void visit(Paragraph paragraph) {
        paragraph.render();
    }

    @Override
    public void visit(ImageProxy imageProxy) {
        imageProxy.render();
    }

    @Override
    public void visit(Image image) {
        image.render();
    }

    @Override
    public void visit(Table table) {
        table.render();
    }
}