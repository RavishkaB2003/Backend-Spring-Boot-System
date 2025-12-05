package com.management.book.Services;


import com.management.book.CustomExceptionHandling.BookNotFoundException;
import com.management.book.DTO.BookDTO;
import com.management.book.Entities.Book;

import com.management.book.Repository.BookRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class BookService {

    //dependency injections using constructor injection
    private final BookRepository bookRepository;


    public BookService(BookRepository bookRepository) {
        this.bookRepository = bookRepository;
    }


    // Add service methods here to interact with the BookRepository
    public List<BookDTO> getAllBooks() {
        List<Book> books = bookRepository.findAll();
        if (books.isEmpty()) {
            return List.of();
        }
        return books.stream().map(
                book -> new BookDTO(
                        book.getBookId(),
                        book.getBookTitle(),
                        book.getIsbn(),
                        book.isAvailability(),
                        book.getAuthor(),
                        book.getBookDescription()
                )
        ).toList();

    }

    //get book by id
    public BookDTO getBookByID(int id) {
        Book book = bookRepository.findById(id)
                .orElseThrow(
                        () -> new BookNotFoundException("Book with ID" + id + " not found")
                );
        return new BookDTO(
                book.getBookTitle(),
                book.getIsbn(),
                book.isAvailability(),
                book.getAuthor(),
                book.getBookDescription()
        );


    }

    //get book by isbn
    public BookDTO getBookByIsbn(Long isbn) {
        Book book = bookRepository.findByIsbn(isbn)
                .orElseThrow(
                        () -> new BookNotFoundException("Book with ISBN" + isbn + " not found")
                );

        return new BookDTO(
                book.getBookTitle(),
                book.getIsbn(),
                book.isAvailability(),
                book.getAuthor(),
                book.getBookDescription()
        );
    }


    //get book by title
    public BookDTO getBookByTitle(String bookTitle) {
        Book book = bookRepository.findByBookTitleContainingIgnoreCase(bookTitle)
                .orElseThrow(
                        () -> new BookNotFoundException("Book with Title" + bookTitle + " not found")
                );
        return new BookDTO(
                book.getBookTitle(),
                book.getIsbn(),
                book.getAuthor(),
                book.getBookDescription()
        );
    }


    //Add new book
    public BookDTO addBook(BookDTO bookDTO) {
        // Create entity manually
        Book bookEntity = new Book();
        bookEntity.setBookTitle(bookDTO.getBookTitle());
        bookEntity.setIsbn(bookDTO.getIsbn());
        bookEntity.setAvailability(bookDTO.isAvailability());
        bookEntity.setAuthor(bookDTO.getAuthor());
        bookEntity.setBookDescription(bookDTO.getBookDescription());

        // Save to DB
        Book savedBook = bookRepository.save(bookEntity);

        // Convert saved entity back to DTO
        BookDTO savedDTO = new BookDTO();
        savedDTO.setBookTitle(savedBook.getBookTitle());
        savedDTO.setIsbn(savedBook.getIsbn());
        savedDTO.setAvailability(savedBook.isAvailability());
        savedDTO.setAuthor(savedBook.getAuthor());
        savedDTO.setBookDescription(savedBook.getBookDescription());

        return savedDTO;
    }

    //delete book by id
    public void deleteBookById(int id){
        Book book = bookRepository.findById(id)
                .orElseThrow( ()-> new BookNotFoundException("Book with ID" + id + " not found"));
        bookRepository.delete(book);
    }



    //update book
    //private service to convert DTO to Entity
    private BookDTO convertToDTO(Book book){
        BookDTO bookDTO = new BookDTO();
        bookDTO.setBookTitle(book.getBookTitle());
        bookDTO.setIsbn(book.getIsbn());
        bookDTO.setAvailability(book.isAvailability());
        bookDTO.setAuthor(book.getAuthor());
        bookDTO.setBookDescription(book.getBookDescription());
        return bookDTO;
    }

    public BookDTO updateBook(int id, BookDTO bookDTO){
        Book exisitingbook = bookRepository.findById(id)
                .orElseThrow( ()-> new BookNotFoundException("Book with ID" + id + " not found"));

        if(bookDTO.getBookTitle() != null){
            exisitingbook.setBookTitle(bookDTO.getBookTitle());
        }
        if(bookDTO.getIsbn() != 0){
            exisitingbook.setIsbn(bookDTO.getIsbn());
        }
        if(bookDTO.getAuthor() != null){
            exisitingbook.setAuthor(bookDTO.getAuthor());
        }
        if(bookDTO.getBookDescription() != null) {
            exisitingbook.setBookDescription(bookDTO.getBookDescription());
        }

        if(bookDTO.isAvailability() != exisitingbook.isAvailability()){
            exisitingbook.setAvailability(bookDTO.isAvailability());
        }
        Book updatedBook = bookRepository.save(exisitingbook);
        return convertToDTO(updatedBook);
    }




}






