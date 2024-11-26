CREATE DATABASE tourism_db;

USE tourism_db;

CREATE TABLE tourist_info (
    id INT AUTO_INCREMENT PRIMARY KEY,
    name VARCHAR(255) NOT NULL,
    email VARCHAR(255) NOT NULL,
    feedback TEXT
);
