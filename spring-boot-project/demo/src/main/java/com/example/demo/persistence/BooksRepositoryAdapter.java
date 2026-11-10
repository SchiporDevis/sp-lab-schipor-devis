package com.example.demo.persistence;

import com.example.demo.Book;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;

@Component
@Primary
public class BooksRepositoryAdapter implements CrudRepository<Book, Integer> {

    private final BooksRepository booksRepository;

    @Autowired
    public BooksRepositoryAdapter(BooksRepository booksRepository) {
        this.booksRepository = booksRepository;
    }

    public BooksRepository getBooksRepository() {
        return booksRepository;
    }

    @Override
    public Book save(Book entity) {
        return booksRepository.save(entity);
    }

    @Override
    public Optional<Book> findById(Integer id) {
        return booksRepository.findById(id);
    }

    @Override
    public List<Book> findAll() {
        return booksRepository.findAll();
    }

    @Override
    public void deleteById(Integer id) {
        booksRepository.deleteById(id);
    }
}
