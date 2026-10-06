package com.example.demo;

public class AlignCenter implements AlignStrategy {
    @Override
    public void render(String text) {
        System.out.println("        Paragraph: " + text); // Spații pentru centrare
    }
}