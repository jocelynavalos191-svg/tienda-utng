package com.utng.tienda.dao;

import java.util.List;
import java.util.Optional;

import com.utng.tienda.modelo.Producto;

/**
 * Contrato CRUD para el acceso a datos de Producto.
 */
public interface ProductoDAO {

    void insertar(Producto producto);

    Optional<Producto> buscarPorCodigo(String codigo);

    List<Producto> listarTodos();

    void actualizar(Producto producto);

    void eliminar(String codigo);
}