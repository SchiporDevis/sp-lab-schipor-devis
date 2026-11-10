package com.example.demo;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.Entity;
import jakarta.persistence.Transient;

@Entity
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
public class ImageProxy extends BaseElement { // Trebuie să fie un Element pentru a fi adăugat în Section
    private String url; // Numele imaginii

    @Transient
    @JsonIgnore
    private Image realImg; // Referința către imaginea reală, inițial null[cite: 4]

    public ImageProxy() {
    }

    public ImageProxy(String url) {
        this.url = url;
        this.realImg = null; // Imaginea reală NU este încărcată la instanțierea proxy-ului
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    @JsonIgnore
    public Image getRealImg() {
        return realImg;
    }

    // Metoda privată descrisă în nota din diagramă[cite: 4]
    private Image loadImage() {
        if (realImg == null) {
            realImg = new Image(url); // Aici se întâmplă acele 5 secunde de întârziere
        }
        return realImg;
    }

    public void render() {
        loadImage();
        realImg.render();
    }

    @Override
    public void accept(Visitor visitor) {
        visitor.visit(this);
    }

    // --- Proxy-ul trebuie să respecte interfața Element ---
    @Override
    public void add(Element element) {
        throw new UnsupportedOperationException("O imagine (proxy) nu poate conține alte elemente.");
    }

    @Override
    public void remove(Element element) {
        throw new UnsupportedOperationException("O imagine (proxy) nu poate conține alte elemente.");
    }

    @Override
    public Element get(int index) {
        throw new UnsupportedOperationException("O imagine (proxy) nu poate conține alte elemente.");
    }
}
