package com.example.demo.service;

import com.example.demo.dto.BorrowerDto;
import com.example.demo.model.Borrower;
import com.example.demo.repository.BorrowerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class BorrowerService {

    private BorrowerRepository borrowerRepository;

    @Autowired
    public BorrowerService(BorrowerRepository borrowerRepository) {
        this.borrowerRepository = borrowerRepository;
    }

    public List<BorrowerDto> getAllBorrowers() {
        return borrowerRepository.findAll().stream()
                .map(this::convertToDTO)
                .collect(Collectors.toList());
    }

    public void registerNewBorrower(Borrower borrower) {
        borrowerRepository.save(borrower);
    }

    public boolean isEmailRegistered(String email) {
        return borrowerRepository.existsByEmail(email);
    }

    private BorrowerDto convertToDTO(Borrower borrower) {
        return new BorrowerDto(borrower.getId(), borrower.getName(), borrower.getEmail());
    }
}
