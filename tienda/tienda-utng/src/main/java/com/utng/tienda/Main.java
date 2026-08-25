package com.utng.tienda;

import java.util.List;

import com.utng.tienda.dao.ProductoDAO;
import com.utng.tienda.dao.ProductoDAOImpl;
import com.utng.tienda.modelo.Producto;
import com.utng.tienda.servicio.ProductoService;


 // Clase de entrada que demuestra el flujo CRUD completo


public class Main {

    public static void main(String[] args) {
        ProductoDAO dao = new ProductoDAOImpl();
        ProductoService service = new ProductoService(dao);

        // Create
        Producto producto = new Producto("P001", "Mouse inalambrico", 250.0, 15);
        service.registrar(producto);
        System.out.println("Producto registrado: " + producto);

        // Read
        List<Producto> productos = service.listar();
        System.out.println("Catalogo actual:");
        productos.forEach(System.out::println);

        // Update (venta que reduce stock)
        service.vender("P001", 3);
        System.out.println("Venta realizada, stock actualizado.");

        // Delete
        service.eliminar("P001");
        System.out.println("Producto eliminado: P001");
    }
}