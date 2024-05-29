package com.example.demo.controller;

import com.example.demo.dto.BorrowerDto;
import com.example.demo.model.Borrower;
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

@RestController
@RequestMapping("api/borrowers")
public class BorrowerController {

    private final BorrowerService borrowerService;

    @Autowired
    public BorrowerController(BorrowerService borrowerService) {
        this.borrowerService = borrowerService;
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
}
