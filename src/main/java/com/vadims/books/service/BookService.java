package com.vadims.books.service;

import com.vadims.books.domain.Book;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface BookService {
    Book update(Long id, Book book);

    Book save(Book book);

    void delete(Long id);

    List<Book> findAll();

    Book findById(Long id);

    List<Book> findByTitle(String title);

    List<Book> findByAuthor(String author);
}