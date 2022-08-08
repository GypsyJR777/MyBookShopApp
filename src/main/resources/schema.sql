DROP TABLE IF EXISTS books;
DROP TABLE IF EXISTS authors;

create table authors
(
    id         INT AUTO_INCREMENT PRIMARY KEY,
    firstName VARCHAR(50) NOT NULL,
    lastName  VARCHAR(50) NOT NULL
);

create table books
(
    id       INT AUTO_INCREMENT PRIMARY KEY,
    authorId INT,
    title    VARCHAR(100) NOT NULL,
    priceOld VARCHAR(50) DEFAULT NULL,
    price    VARCHAR(50) DEFAULT NULL,
    FOREIGN KEY (authorId) REFERENCES authors (id)
);