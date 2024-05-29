package com.example.demo.service;

import com.example.demo.model.BorrowedBooks;
import com.example.demo.repository.BorrowedBooksRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Optional;

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

    public boolean isBookAvailable(Long bookId) {
        return borrowedBooksRepository.findByBookIdAndReturnedFalse(bookId).isEmpty();
    }

    public BorrowedBooks returnBook(Long borrowerId, Long bookId) {
        Optional<BorrowedBooks> borrowedBookOptional = borrowedBooksRepository.findByBorrowerIdAndBookIdAndReturnedFalse(borrowerId, bookId);

        if (borrowedBookOptional.isEmpty()) {
            throw new RuntimeException("Borrowed book not found");
        }

        BorrowedBooks borrowedBook = borrowedBookOptional.get();
        borrowedBook.setReturned(true);
        return borrowedBooksRepository.save(borrowedBook);
    }
}
