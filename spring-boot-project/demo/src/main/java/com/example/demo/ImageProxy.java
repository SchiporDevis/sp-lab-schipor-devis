package com.example.demo;

public class ImageProxy implements Element { // Trebuie să fie un Element pentru a fi adăugat în Section
    private String url; // Numele imaginii
    private Image realImg; // Referința către imaginea reală, inițial null[cite: 4]

    public ImageProxy(String url) {
        this.url = url;
        this.realImg = null; // Imaginea reală NU este încărcată la instanțierea proxy-ului
    }

    // Metoda privată descrisă în nota din diagramă[cite: 4]
    private Image loadImage() {
        if (realImg == null) {
            realImg = new Image(url); // Aici se întâmplă acele 5 secunde de întârziere
        }
        return realImg;
    }

    @Override
    public void print() {
        // Când se cere printarea, ne asigurăm că imaginea reală există, apoi o printăm
        loadImage();
        realImg.print();
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