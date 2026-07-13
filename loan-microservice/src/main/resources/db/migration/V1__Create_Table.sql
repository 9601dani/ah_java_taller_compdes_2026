CREATE TABLE loans (
    id CHAR(36) PRIMARY KEY,
    user_name VARCHAR(100) NOT NULL,
    book_id CHAR(36) NOT NULL,
    is_active BOOLEAN NOT NULL DEFAULT TRUE,
    init_date DATE NOT NULL,
    end_date DATE NOT NULL,
    created_at TIMESTAMP,
    updated_at TIMESTAMP
);