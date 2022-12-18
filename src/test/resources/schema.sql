create table authors
(
    id          INT,
    firstname   VARCHAR(50),
    lastname    VARCHAR(50),
    slug        VARCHAR(50),
    photo       VARCHAR(100),
    description TEXT
);

create table users
(
    id       INT,
    email    VARCHAR(50),
    name     VARCHAR(50),
    password VARCHAR(100),
    phone    VARCHAR(50)
);

create table books
(
    id INT,
    pub_date DATE,
    is_bestseller INT,
    slug VARCHAR(50),
    title VARCHAR(1000),
    image VARCHAR(1000),
    description VARCHAR(5000),
    price INT,
    discount NUMERIC,
    author_id INT,
    in_cart_amount INT,
    postponed_amount INT,
    purchase_amount INT
)