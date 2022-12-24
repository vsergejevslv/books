package com.vadims.books.repository;

import com.vadims.books.domain.Book;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BookRepository extends JpaRepository<Book, Long> {

    @Query("SELECT b FROM Book b WHERE b.title = ?1")
    List<Book> findByTitle(String title);

    @Query("SELECT b FROM Book b WHERE b.author = ?1")
    List<Book> findByAuthor(String author);
}
