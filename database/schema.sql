CREATE DATABASE IF NOT EXISTS myapp_db;
use myapp_db;

-- skip the user auth for now
-- simple table to prove the database is structured .
CREATE TABLE IF NOT EXISTS users(
    id int AUTO_INCREMENT PRIMARY KEY,
    name VARCHAR(255)NOT NULL,
    phone_number VARCHAR(20) NOT NULL,
    email VARCHAR(255) NOT NULL UNIQUE,
    password_hash VARCHAR(255) NOT NULL,
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP
);