package com.example.demo.service;

import com.example.demo.model.BorrowedBooks;
import com.example.demo.repository.BorrowedBooksRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
public class BorrowedBooksService {
    private final BorrowedBooksRepository borrowedBooksRepository;

    @Autowired
    public BorrowedBooksService(BorrowedBooksRepository borrowedBooksRepository) {
        this.borrowedBooksRepository = borrowedBooksRepository;
    }

    public void borrowBook(BorrowedBooks borrowedBooks) {
        borrowedBooks.setBorrowedAt(LocalDateTime.now());
        borrowedBooksRepository.save(borrowedBooks);
    }
}
