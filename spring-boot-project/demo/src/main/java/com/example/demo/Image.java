package com.example.demo;
import java.util.concurrent.TimeUnit;

public class Image implements Element { // Sau implementează Picture, dacă ați fost instruiți astfel
    private String imageName;

    public Image(String name) {
        this.imageName = name;
        try {
            // Simulăm încărcarea grea a imaginii (5 secunde)
            TimeUnit.SECONDS.sleep(5);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void print() {
        System.out.println("Image with name: " + imageName);
    }



    /** EXTRA **********************************************************************************/
    // --- Metodele din Element (care aruncă excepții pentru că e Leaf) ---
    @Override
    public void add(Element element) {
        throw new UnsupportedOperationException("O imagine nu poate conține alte elemente.");
    }

    @Override
    public void remove(Element element) {
        throw new UnsupportedOperationException("O imagine nu poate conține alte elemente.");
    }

    @Override
    public Element get(int index) {
        throw new UnsupportedOperationException("O imagine nu poate conține alte elemente.");
    }
}