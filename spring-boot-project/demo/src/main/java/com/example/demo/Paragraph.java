package com.example.demo;

import javax.swing.*;

public class Paragraph implements Element {
    private String text;
    private AlignStrategy alignStrategy; // Referința către strategie

    public Paragraph(String text) {
        this.text = text;
        this.alignStrategy = null; // Inițial, nu are nicio strategie
    }

    public void setAlignStrategy(AlignStrategy alignStrategy) {
        this.alignStrategy = alignStrategy;
    }

    // Metoda pentru a obține textul (dacă alte clase au nevoie de el)
    public String getText() {
        return text;
    }

    @Override
    public void print() {
        if (alignStrategy == null) {
            // Comportament implicit (fără strategie)
            System.out.println("Paragraph: " + text);
        } else {
            // Delegăm afișarea către strategie
            alignStrategy.render(this.text);
        }
    }

    // ... (restul metodelor din interfața Element rămân aceleași: add, remove, get care aruncă excepții)
    @Override
    public void add(Element element) {
        throw new UnsupportedOperationException("Un paragraf nu poate conține alte elemente.");
    }

    @Override
    public void remove(Element element) {
        throw new UnsupportedOperationException("Un paragraf nu poate conține alte elemente.");
    }

    @Override
    public Element get(int index) {
        throw new UnsupportedOperationException("Un paragraf nu poate conține alte elemente.");
    }
}