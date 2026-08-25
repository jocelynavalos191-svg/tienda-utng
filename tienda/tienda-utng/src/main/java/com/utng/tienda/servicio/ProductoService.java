package com.utng.tienda.servicio;

import java.util.List;
import java.util.Optional;

import com.utng.tienda.dao.ProductoDAO;
import com.utng.tienda.modelo.Producto;

/**
 * Contiene la logica de negocio del catalogo de productos,
 * apoyandose en ProductoDAO para la persistencia.
 */
public class ProductoService {

    private final ProductoDAO dao;

    public ProductoService(ProductoDAO dao) {
        this.dao = dao;
    }

    public void registrar(Producto producto) {
        if (producto == null || producto.getNombre() == null || producto.getNombre().isBlank()) {
            throw new IllegalArgumentException("El producto debe tener nombre");
        }
        if (producto.getPrecio() < 0) {
            throw new IllegalArgumentException("El precio no puede ser negativo");
        }
        dao.insertar(producto);
    }

    public Optional<Producto> buscar(String codigo) {
        return dao.buscarPorCodigo(codigo);
    }

    public List<Producto> listar() {
        return dao.listarTodos();
    }

    public void actualizarProducto(Producto producto) {
        dao.actualizar(producto);
    }

    public void eliminar(String codigo) {
        dao.eliminar(codigo);
    }

    /**
     * Vende una cantidad de producto, descontando stock.
     */
    public void vender(String codigo, int cantidad) {
        Producto producto = dao.buscarPorCodigo(codigo)
                .orElseThrow(() -> new IllegalStateException("Producto no encontrado: " + codigo));

        if (producto.getStock() < cantidad) {
            throw new IllegalStateException("Stock insuficiente para " + codigo);
        }

        producto.setStock(producto.getStock() - cantidad);
        dao.actualizar(producto);
    }
}