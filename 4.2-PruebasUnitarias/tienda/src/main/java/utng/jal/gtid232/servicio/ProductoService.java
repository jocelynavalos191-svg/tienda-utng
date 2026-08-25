package utng.jal.gtid232.servicio;

import utng.jal.gtid232.dao.ProductoDAO;
import utng.jal.gtid232.excepcion.PrecioInvalidoException;
import utng.jal.gtid232.excepcion.ProductoNoEncontradoException;
import utng.jal.gtid232.excepcion.StockInsuficienteException;

public class ProductoService {

    private final ProductoDAO dao;

    public ProductoService(ProductoDAO dao) {
        if (dao == null) {
            throw new IllegalArgumentException("El DAO no puede ser null");
        }
        this.dao = dao;
    }

    public int registrar(Producto producto) {
        validarProducto(producto);
        return dao.insert(producto);
    }

    public void vender(String codigo, int cantidad) {
        if (codigo == null || codigo.trim().isEmpty()) {
            throw new IllegalArgumentException("El codigo del producto no puede ser nulo o vacio");
        }
        if (cantidad <= 0) {
            throw new IllegalArgumentException("La cantidad a vender debe ser mayor a cero");
        }

        Producto producto = dao.findByCodigo(codigo)
                .orElseThrow(() -> new ProductoNoEncontradoException(codigo));

        if (producto.getStock() < cantidad) {
            throw new StockInsuficienteException(codigo, producto.getStock(), cantidad);
        }

        dao.updateStock(codigo, producto.getStock() - cantidad);
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

    private void validarProducto(Producto producto) {
        if (producto == null) {
            throw new IllegalArgumentException("El producto no puede ser null");
        }
        if (producto.getPrecio() < 0) {
            throw new PrecioInvalidoException(producto.getPrecio());
        }
        if (producto.getStock() < 0) {
            throw new IllegalArgumentException("El stock no puede ser negativo");
        }
    }
}


