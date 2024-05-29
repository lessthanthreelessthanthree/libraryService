package com.example.demo.repository;

import com.example.demo.model.BorrowedBooks;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface BorrowedBooksRepository extends JpaRepository<BorrowedBooks, Long> {
    List<BorrowedBooks> findByBookIdAndReturnedFalse(Long bookId);
}
