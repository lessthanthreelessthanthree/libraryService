CREATE TABLE borrower (
    id INT AUTO_INCREMENT PRIMARY KEY,
    name VARCHAR(255) NOT NULL,
    email VARCHAR(255) NOT NULL UNIQUE
);

CREATE TABLE book (
    id INT AUTO_INCREMENT PRIMARY KEY,
    isbn_number VARCHAR(20) NOT NULL,
    title VARCHAR(255) NOT NULL,
    author VARCHAR(255) NOT NULL
);

CREATE TABLE borrowed_book (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    borrower_id BIGINT NOT NULL,
    book_id BIGINT NOT NULL,
    borrowed_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    returned BOOLEAN DEFAULT FALSE,
    CONSTRAINT fk_borrower FOREIGN KEY (borrower_id) REFERENCES borrower(id),
    CONSTRAINT fk_book FOREIGN KEY (book_id) REFERENCES book(id)
);