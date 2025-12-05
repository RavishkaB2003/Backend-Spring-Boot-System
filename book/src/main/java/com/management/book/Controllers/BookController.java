package com.management.book.Controllers;


import com.management.book.DTO.BookDTO;
import com.management.book.Entities.Book;
import com.management.book.Services.BookService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;


import java.util.List;
@RestController
@RequestMapping("/api/v1/books")
public class BookController {

    private final BookService bookService;

    public BookController(BookService bookService) {
        this.bookService = bookService;
    }


    //get all books api
    @GetMapping("/all")
    public ResponseEntity<List<BookDTO>> getAllBooks() {
        return new ResponseEntity<List<BookDTO>>(bookService.getAllBooks(), HttpStatus.OK);
    }


    //get book by id api
    @GetMapping("/book={id}")
    public ResponseEntity<BookDTO> getBookByID(@PathVariable int id) {
        if (id <= 0) {
            throw new IllegalArgumentException("ID must be positive");
        }
        BookDTO bookDTO = bookService.getBookByID(id);
        return ResponseEntity.ok(bookDTO);
    }

    //get book by isbn api
    @GetMapping("/by-isbn")
    public ResponseEntity<BookDTO> getBookByIsbn(@RequestParam Long isbn) {
        if (isbn <= 0) {
            throw new IllegalArgumentException("ISBN must be positive");
        }
        BookDTO bookDTO = bookService.getBookByIsbn(isbn);
        return ResponseEntity.ok(bookDTO);
    }


    //get book by title api
    @GetMapping("/by-title")
    public ResponseEntity<BookDTO> getBookByTitle(@RequestParam String bookTitle) {
        if (bookTitle == null || bookTitle.trim().isEmpty()) {
            throw new IllegalArgumentException("Book title must not be empty");
        }
        BookDTO bookDTO = bookService.getBookByTitle(bookTitle);
        return ResponseEntity.ok(bookDTO);
    }

    //delete book api
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<String> deleteBook(@PathVariable int id) {
        bookService.deleteBookById(id);
        return ResponseEntity.ok("Book with ID " + id + " has been deleted.");
    }


    //Add new book api
    @PreAuthorize("hasAuthority('ROLE_ADMIN')")
    @PostMapping("/add")
    public ResponseEntity<BookDTO> addNewBook( @Valid @RequestBody BookDTO bookDTO) {
        BookDTO createdBook = bookService.addBook(bookDTO);
        return new ResponseEntity<>(createdBook, HttpStatus.CREATED);
    }


    //update book api
    @PreAuthorize("hasAuthority('ROLE_ADMIN')")
    @PutMapping("/update/{id}")
    public ResponseEntity<BookDTO> updateBook(@PathVariable int id, @RequestBody BookDTO bookDTO) {
        BookDTO updatedBook = bookService.updateBook(id, bookDTO);
        return ResponseEntity.ok(updatedBook);
    }


}


