package com.example.demo.controller;

import com.example.demo.dto.BorrowerDto;
import com.example.demo.model.Book;
import com.example.demo.model.BorrowedBooks;
import com.example.demo.model.Borrower;
import com.example.demo.service.BookService;
import com.example.demo.service.BorrowedBooksService;
import com.example.demo.service.BorrowerService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@RestController
@RequestMapping("api/borrowers")
public class BorrowerController {

    private final BorrowerService borrowerService;
    private final BookService bookService;
    private final BorrowedBooksService borrowedBooksService;

    @Autowired
    public BorrowerController(BorrowerService borrowerService, BookService bookService, BorrowedBooksService borrowedBooksService) {
        this.borrowerService = borrowerService;
        this.bookService = bookService;
        this.borrowedBooksService = borrowedBooksService;
    }

    @GetMapping
    public ResponseEntity<List<BorrowerDto>> getAllBorrowers() {
        List<BorrowerDto> borrowers = borrowerService.getAllBorrowers();
        if (borrowers.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(borrowers, HttpStatus.OK);
    }

    @PostMapping("/register")
    public ResponseEntity<String> registerNewBorrower(@RequestBody @Valid BorrowerDto newBorrowerDto) {
        boolean isRegistered = borrowerService.isEmailRegistered(newBorrowerDto.getEmail());
        if (isRegistered) {
            return new ResponseEntity<>("Email already registered", HttpStatus.BAD_REQUEST);
        }
        Borrower borrower = new Borrower(newBorrowerDto.getName(), newBorrowerDto.getEmail());
        borrowerService.registerNewBorrower(borrower);
        return new ResponseEntity<>("Borrower registered successfully", HttpStatus.CREATED);
    }

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public Map<String, String> handleValidationExceptions(MethodArgumentNotValidException ex) {
        BindingResult result = ex.getBindingResult();
        Map<String, String> errors = new HashMap<>();
        for (FieldError error : result.getFieldErrors()) {
            errors.put(error.getField(), error.getDefaultMessage());
        }
        return errors;
    }

    @PostMapping("/{borrowerId}/borrow/{bookId}")
    public ResponseEntity<String> borrowBook(@PathVariable Long borrowerId, @PathVariable Long bookId) {
        Optional<Borrower> borrowerOptional = borrowerService.getBorrowerById(borrowerId);
        Optional<Book> bookOptional = bookService.getBookById(bookId);

        if (borrowerOptional.isEmpty()) {
            return new ResponseEntity<>("Borrower not found", HttpStatus.NOT_FOUND);
        }

        if (bookOptional.isEmpty()) {
            return new ResponseEntity<>("Book not found", HttpStatus.NOT_FOUND);
        }

        if (!borrowedBooksService.isBookAvailable(bookId)) {
            return new ResponseEntity<>("Book has not been returned. Book is not available", HttpStatus.NOT_FOUND);
        }

        Borrower borrower = borrowerOptional.get();
        Book book = bookOptional.get();

        BorrowedBooks borrowedBooks = new BorrowedBooks();
        borrowedBooks.setBorrower(borrower);
        borrowedBooks.setBook(book);

        borrowedBooksService.borrowBook(borrowedBooks);

        return new ResponseEntity<>("Book borrowed successfully", HttpStatus.OK);
    }
}
