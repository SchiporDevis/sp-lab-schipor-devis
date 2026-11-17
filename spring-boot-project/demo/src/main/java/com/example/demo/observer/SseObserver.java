package com.example.demo.observer;

import com.example.demo.Book;
import org.springframework.http.MediaType;
import org.springframework.web.servlet.mvc.method.annotation.SseEmitter;

import java.io.IOException;
import java.io.UncheckedIOException;

/** ConcreteObserver participant: pushes the book as JSON to the SSE client. */
public class SseObserver implements Observer<Book> {

    private final SseEmitter emitter;

    public SseObserver(SseEmitter emitter) {
        this.emitter = emitter;
    }

    @Override
    public void update(Book book) {
        try {
            emitter.send(book, MediaType.APPLICATION_JSON);
        } catch (IOException e) {
            throw new UncheckedIOException(e);
        }
    }
}
