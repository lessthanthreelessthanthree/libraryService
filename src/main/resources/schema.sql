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

INSERT INTO borrower (name, email) VALUES
('John Doe', 'john.doe@example.com'),
('Jane Smith', 'jane.smith@example.com'),
('Alice Johnson', 'alice.johnson@example.com');

INSERT INTO book (isbn_number, title, author) VALUES
('978-3-16-148410-0', 'The Great Gatsby', 'F. Scott Fitzgerald'),
('978-0-14-028329-7', '1984', 'George Orwell'),
('978-0-452-28423-4', 'To Kill a Mockingbird', 'Harper Lee');