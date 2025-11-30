package com.management.book.Repository;

import com.management.book.Entities.Book;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface BookRepository extends JpaRepository<Book,Integer> {

    //get book by isbn, using optional to handle null values
    Optional<Book> findByIsbn(long Isbn);

    Optional<Book>findByBookTitleContainingIgnoreCase(String bookTitle);



}
