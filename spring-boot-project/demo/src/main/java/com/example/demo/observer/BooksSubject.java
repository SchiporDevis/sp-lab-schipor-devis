package com.example.demo.observer;

import com.example.demo.Book;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

/** ConcreteSubject participant ("allBooksSubject"); singleton bean. */
@Component
public class BooksSubject implements Subject<Book> {

    private final List<Observer<Book>> observers = new CopyOnWriteArrayList<>();
    private volatile Book state;

    @Override
    public void attach(Observer<Book> observer) {
        observers.add(observer);
    }

    @Override
    public void detach(Observer<Book> observer) {
        observers.remove(observer);
    }

    @Override
    public void notifyObservers() {
        Book book = state;
        for (Observer<Book> observer : observers) {
            try {
                observer.update(book);
            } catch (RuntimeException e) {
                // client deconectat -> il scoatem din lista
                observers.remove(observer);
            }
        }
    }

    /** Schimba starea subiectului si notifica toti observatorii. */
    public void add(Book book) {
        this.state = book;
        notifyObservers();
    }

    public Book getState() {
        return state;
    }
}
