package com.management.book.CustomExceptionHandling;




//Custom Exception for Book Not Found Scenario
public class BookNotFoundException extends RuntimeException{
    public BookNotFoundException(String message){
        super(message);
    }
}
