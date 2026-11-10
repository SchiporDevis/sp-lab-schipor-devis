package com.example.demo;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.Entity;
import jakarta.persistence.Transient;

@Entity
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
public class Paragraph extends BaseElement {
    private String text;

    @Transient
    @JsonIgnore
    private AlignStrategy alignStrategy; // Referința către strategie

    public Paragraph() {
    }

    public Paragraph(String text) {
        this.text = text;
        this.alignStrategy = null; // Inițial, nu are nicio strategie
    }

    public void setAlignStrategy(AlignStrategy alignStrategy) {
        this.alignStrategy = alignStrategy;
    }

    @JsonIgnore
    public AlignStrategy getAlignStrategy() {
        return alignStrategy;
    }

    // Metoda pentru a obține textul (dacă alte clase au nevoie de el)
    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public void render() {
        if (alignStrategy != null) {
            alignStrategy.render(this.text);
        } else {
            System.out.println("Paragraph: " + text);
        }
    }

    @Override
    public void accept(Visitor visitor) {
        visitor.visit(this);
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
