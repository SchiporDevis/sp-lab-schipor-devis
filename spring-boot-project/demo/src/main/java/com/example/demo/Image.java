package com.example.demo;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.Entity;

import java.util.concurrent.TimeUnit;

@Entity
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
public class Image extends BaseElement { // Sau implementează Picture, dacă ați fost instruiți astfel

    @JsonProperty("imageName")
    private String imageName;

    public Image() {
    }

    public Image(String name) {
        this.imageName = name;
        try {
            // Simulăm încărcarea grea a imaginii (5 secunde)
            TimeUnit.SECONDS.sleep(5);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    @JsonProperty("imageName")
    public String getImageName() {
        return imageName;
    }

    public void setImageName(String imageName) {
        this.imageName = imageName;
    }

    public void render() {
        System.out.println("Image with name: " + imageName);
    }

    @Override
    public void accept(Visitor visitor) {
        visitor.visit(this);
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
