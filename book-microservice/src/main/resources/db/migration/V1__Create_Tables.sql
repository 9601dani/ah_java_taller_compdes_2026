CREATE TABLE category (
    name VARCHAR(255) PRIMARY KEY
);

CREATE TABLE books (
    id CHAR(36) PRIMARY KEY,
    title VARCHAR(255) NOT NULL,
    author_name VARCHAR(255) NOT NULL,
    publication_date DATE,
    state VARCHAR(50),
    category VARCHAR(255)
);