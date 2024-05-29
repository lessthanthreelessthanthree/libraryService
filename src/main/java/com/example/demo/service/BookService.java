package com.example.demo.service;

import com.example.demo.dto.BookDto;
import com.example.demo.model.Book;
import com.example.demo.repository.BookRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class BookService {

    private final BookRepository bookRepository;

    @Autowired
    public BookService(BookRepository bookRepository) {
        this.bookRepository = bookRepository;
    }

    public List<BookDto> getAllBooks() {
        return bookRepository.findAll().stream()
                .map(this::convertToDTO)
                .collect(Collectors.toList());
    }

    public void registerNewBook(Book book) {
        bookRepository.save(book);
    }

    private BookDto convertToDTO(Book book) {
        return new BookDto(book.getId(), book.getIsbnNumber(), book.getTitle(), book.getAuthor());
    }
}
