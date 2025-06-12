drop database if exists akihabara_db;
create database akihabara_db;
USE akihabara_db;

CREATE TABLE IF NOT EXISTS productos (
    id INT AUTO_INCREMENT PRIMARY KEY,
    nombre VARCHAR(255) NOT NULL,
    categoria VARCHAR(100),
    precio DECIMAL(10,2),
    stock INT
);

CREATE TABLE clientes (
id INT AUTO_INCREMENT PRIMARY KEY,
nombre VARCHAR(255) NOT NULL,
email VARCHAR(255) NOT NULL UNIQUE,
telefono VARCHAR(20),
fecha_registro DATETIME DEFAULT CURRENT_TIMESTAMP
);
