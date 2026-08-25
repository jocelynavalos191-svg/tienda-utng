package dao;

import utng.jal.gtid232.dao.Producto;

public class ProductoService {

    // Constructor por defecto
    public ProductoService() {}

    // Constructor inyectando DAO
    public ProductoService(Producto dao) {
    }

    public double calcularTotalConDescuento(Producto producto, double descuento) {
        if (producto == null) {
            throw new IllegalArgumentException("El producto no puede ser null");
        }
        if (descuento < 0) {
            throw new IllegalArgumentException("El descuento no puede ser negativo");
        }
        if (descuento > 1) {
            throw new IllegalArgumentException("El descuento no puede ser mayor que 100%");
        }
        return producto.getPrecio() * (1 - descuento);
    }
}