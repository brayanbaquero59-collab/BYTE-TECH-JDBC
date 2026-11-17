-- BYTE TECH - Base de datos del sistema de inventario
CREATE DATABASE IF NOT EXISTS byte_tech
    CHARACTER SET utf8mb4
    COLLATE utf8mb4_unicode_ci;

USE byte_tech;

CREATE TABLE IF NOT EXISTS productos (
    id INT AUTO_INCREMENT PRIMARY KEY,
    nombre VARCHAR(100) NOT NULL,
    categoria VARCHAR(60) NOT NULL,
    marca VARCHAR(60) NOT NULL,
    precio DECIMAL(12,2) NOT NULL,
    stock INT NOT NULL DEFAULT 0,
    descripcion VARCHAR(255),
    fecha_registro TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    CONSTRAINT chk_precio CHECK (precio >= 0),
    CONSTRAINT chk_stock CHECK (stock >= 0)
);

-- Datos de demostración: se insertan únicamente si la tabla está vacía.
INSERT INTO productos (nombre, categoria, marca, precio, stock, descripcion)
SELECT 'Portátil Aspire 5', 'Portátiles', 'Acer', 2499000.00, 8, 'Portátil para estudio y trabajo'
WHERE NOT EXISTS (SELECT 1 FROM productos);

INSERT INTO productos (nombre, categoria, marca, precio, stock, descripcion)
SELECT 'Teclado mecánico K552', 'Accesorios', 'Redragon', 189900.00, 15, 'Teclado mecánico retroiluminado'
WHERE (SELECT COUNT(*) FROM productos) = 1;

INSERT INTO productos (nombre, categoria, marca, precio, stock, descripcion)
SELECT 'Monitor 24 pulgadas', 'Monitores', 'LG', 679900.00, 6, 'Monitor Full HD'
WHERE (SELECT COUNT(*) FROM productos) = 2;
