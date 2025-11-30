package com.management.book.DTO;


import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;



@Data
@NoArgsConstructor
@AllArgsConstructor
public class BookDTO {

    @NotNull(message = "Book Id must not be empty")
    private Integer BookId; //wrapper class to allow null values
    @NotEmpty(message = "Book Title must not be empty")
    private String bookTitle;
    @NotNull(message = "Book Isbn must not be empty")
    private Long isbn; //
    @NotEmpty(message = "Book Availability must not be empty")
    private boolean Availability;
    @NotEmpty(message = "Book Author must not be empty")
    private String Author;
    @NotEmpty(message = "Book Description must not be empty")
    private String BookDescription;


    // Output Contructor For Isbn API

    public BookDTO(String BookTitle, long Isbn, boolean Availability, String Author, String BookDescription) {
        this.bookTitle = BookTitle;
        this.isbn = Isbn;
        this.Availability = Availability;
        this.Author = Author;
        this.BookDescription = BookDescription;
    }

    //Two output for book title search

    public BookDTO(String BookTitle, long Isbn, String Author, String BookDescription) {
        this.bookTitle = BookTitle;
        this.isbn = Isbn;
        this.Author = Author;
        this.BookDescription = BookDescription;
    }





}
