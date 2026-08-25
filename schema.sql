- Crear la base de datos para la tienda
CREATE DATABASE tienda_utng;

-- Conéctate a la base de datos tienda_utng antes de ejecutar lo siguiente

-- Crear la tabla de productos con los campos id_producto, nombre y precio
CREATE TABLE productos (
    id_producto SERIAL PRIMARY KEY,
    nombre VARCHAR(100) NOT NULL,
    precio NUMERIC(10, 2) NOT NULL
);

-- Insertar un registro de prueba inicial para que tu método listar() no regrese la tabla vacía
INSERT INTO productos (nombre, precio) VALUES ('Laptop hp', 18500.00);
