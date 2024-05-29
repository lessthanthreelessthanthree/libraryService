package com.example.demo.repository;

import com.example.demo.model.BorrowedBooks;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface BorrowedBooksRepository extends JpaRepository<BorrowedBooks, Long> {
    List<BorrowedBooks> findByBookIdAndReturnedFalse(Long bookId);

    Optional<BorrowedBooks> findByBorrowerIdAndBookIdAndReturnedFalse(Long borrowerId, Long bookId);
}
