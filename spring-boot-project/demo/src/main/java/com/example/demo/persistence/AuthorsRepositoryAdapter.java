package com.example.demo.persistence;

import com.example.demo.Author;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;

@Component
public class AuthorsRepositoryAdapter implements CrudRepository<Author, Integer> {

    private final AuthorsRepository authorsRepository;

    @Autowired
    public AuthorsRepositoryAdapter(AuthorsRepository authorsRepository) {
        this.authorsRepository = authorsRepository;
    }

    public AuthorsRepository getAuthorsRepository() {
        return authorsRepository;
    }

    @Override
    public Author save(Author entity) {
        return authorsRepository.save(entity);
    }

    @Override
    public Optional<Author> findById(Integer id) {
        return authorsRepository.findById(id);
    }

    @Override
    public List<Author> findAll() {
        return authorsRepository.findAll();
    }

    @Override
    public void deleteById(Integer id) {
        authorsRepository.deleteById(id);
    }
}
