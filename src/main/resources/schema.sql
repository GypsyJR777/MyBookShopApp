DROP TABLE IF EXISTS books;
DROP TABLE IF EXISTS authors;

create table authors
(
    id        BIGSERIAL PRIMARY KEY,
    firstName VARCHAR(50) NOT NULL,
    lastName  VARCHAR(50) NOT NULL
);

create table books
(
    id       BIGSERIAL PRIMARY KEY,
    authorId BIGINT REFERENCES authors (id),
    title    VARCHAR(100) NOT NULL,
    priceOld VARCHAR(50) DEFAULT NULL,
    price    VARCHAR(50) DEFAULT NULL
);