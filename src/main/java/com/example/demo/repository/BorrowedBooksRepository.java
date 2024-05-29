package com.example.demo.repository;

import com.example.demo.model.BorrowedBooks;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BorrowedBooksRepository extends JpaRepository<BorrowedBooks, Long> {
}
