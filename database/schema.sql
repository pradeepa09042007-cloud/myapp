CREATE DATABASE IF NOT EXISTS myapp_db;
use myapp_db;

-- skip the user auth for now
--simple table to prove the database is structured .
CREATE TABLE IF NOT EXISTS items(
    id int AUTO_INCREMENT PRIMARY KEY,
    name VARCHAR(255)NOT NULL,
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP
);