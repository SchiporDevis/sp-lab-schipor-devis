package com.example.demo.observer;

/** Observer participant. */
public interface Observer<T> {
    void update(T data);
}
