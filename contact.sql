-- Create users table (email + password + role)
CREATE TABLE IF NOT EXISTS users (
    user_id INT PRIMARY KEY AUTO_INCREMENT,
    email VARCHAR(100) UNIQUE,
    password VARCHAR(100),
    role ENUM('USER','ADMIN') NOT NULL
);

-- Sample users
INSERT INTO users (email, password, role) VALUES
('admin@gmail.com', 'admin123', 'ADMIN'),
('user@gmail.com', 'user123', 'USER');