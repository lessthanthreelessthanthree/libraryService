package com.example.demo.controller;

import com.example.demo.dto.BookDto;
import com.example.demo.model.Book;
import com.example.demo.service.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/books")
public class BookController {

    private final BookService bookService;

    @Autowired
    public BookController(BookService bookService) {
        this.bookService = bookService;
    }

    @GetMapping
    public List<BookDto> getAllBooks() {
        return bookService.getAllBooks();
    }

    @PostMapping("/register")
    public ResponseEntity<String> registerNewBook(@RequestBody BookDto newBookDto) {
        Book book = new Book(newBookDto.getIsbnNumber(), newBookDto.getTitle(), newBookDto.getAuthor());
        bookService.registerNewBook(book);
        return new ResponseEntity<>("Book registered successfully", HttpStatus.CREATED);
    }

}
