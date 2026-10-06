package com.example.demo;

public class AlignRight implements AlignStrategy {
    @Override
    public void render(String text) {
        System.out.println("                                Paragraph: " + text); // Spații pentru aliniere dreapta
    }
}