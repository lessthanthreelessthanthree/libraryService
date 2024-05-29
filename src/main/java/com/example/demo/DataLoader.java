package com.example.demo;

import com.example.demo.model.Book;
import com.example.demo.model.Borrower;
import com.example.demo.repository.BookRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class DataLoader {

    @Bean
    CommandLineRunner initBookTable(BookRepository bookRepository) {
        return args -> {
            bookRepository.save(new Book("978-3-16-148410-0", "The Great Gatsby", "F. Scott Fitzgerald"));
            bookRepository.save(new Book("978-0-7432-7356-5", "1984", "George Orwell"));
            bookRepository.save(new Book("978-0-452-28423-4", "To Kill a Mockingbird", "Harper Lee"));
        };
    }
}
