package com.management.book.Entities;


import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity // This annotation specifies that the class is an entity and is mapped to a database table.
@Table(name = "bookinfo") // This annotation specifies the name of the database table to be used for mapping.
@Data // This annotation generates getters, setters, toString, equals, and hashCode methods.
@AllArgsConstructor // This annotation generates a constructor with parameters for all fields in the class.
@NoArgsConstructor // This annotation generates a no-argument constructor.
public class Book {

    @Id // This annotation specifies the primary key of the entity.
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    // This annotation specifies the primary key generation strategy.Here we are using IDENTITY strategy which relies on the database to generate the primary key.
    @Column(name = "BookId")
    private int BookId; // Primary key for the Book entity
    @Column(name = "BookTitle")
    private String bookTitle;
    @Column(name = "Isbn")
    private long isbn;
    private boolean Availability;
    private String Author;

    @Column(length = 300) // This annotation specifies the column properties. Here we are setting the length of the Publisher column to 300 characters.
    private String BookDescription;
}
