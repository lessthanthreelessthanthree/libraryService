package com.example.demo.dto;

import jakarta.validation.constraints.NotNull;

public class BookDto {
    private Long id;
    @NotNull
    private String isbnNumber;
    @NotNull
    private String title;
    @NotNull
    private String author;

    public BookDto() {}

    public BookDto(Long id, String isbnNumber, String title, String author) {
        this.id = id;
        this.isbnNumber = isbnNumber;
        this.title = title;
        this.author = author;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getIsbnNumber() {
        return isbnNumber;
    }

    public void setIsbnNumber(String isbnNumber) {
        this.isbnNumber = isbnNumber;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }
}
