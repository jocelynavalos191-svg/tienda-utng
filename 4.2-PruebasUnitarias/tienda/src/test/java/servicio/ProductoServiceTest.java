package utng.jal.gtid232.servicio;

import utng.jal.gtid232.dao.ProductoDAOMemoria;
import utng.jal.gtid232.excepcion.PrecioInvalidoException;
import utng.jal.gtid232.excepcion.ProductoNoEncontradoException;
import utng.jal.gtid232.excepcion.StockInsuficienteException;
import utng.jal.gtid232.modelo.Producto;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ProductoServiceTest {

    private ProductoDAOMemoria dao;
    private ProductoService service;

    @BeforeEach
    void setUp() {
        dao = new ProductoDAOMemoria();
        service = new ProductoService(dao);
    }

    @Test
    void constructor_daoNulo_lanzaIllegalArgumentException() {
        assertThrows(IllegalArgumentException.class,
                () -> new ProductoService(null),
                "Un DAO nulo debe lanzar IllegalArgumentException");
    }

    @Test
    void registrar_productoValido_retornaId() {
        Producto producto = new Producto("P001", "Mouse", 500.0, 10);
        int resultado = service.registrar(producto);
        assertEquals(1, resultado, "Registrar un producto válido debe retornar el ID 1");
    }

    @Test
    void registrar_productoNulo_lanzaIllegalArgumentException() {
        assertThrows(IllegalArgumentException.class,
                () -> service.registrar(null),
                "Registrar un producto null debe lanzar IllegalArgumentException");
    }

    @Test
    void registrar_precioNegativo_lanzaPrecioInvalidoException() {
        Producto producto = new Producto("P001", "Mouse", -50.0, 10);
        assertThrows(PrecioInvalidoException.class,
                () -> service.registrar(producto),
                "Registrar con precio negativo debe lanzar PrecioInvalidoException");
    }

    @Test
    void vender_stockSuficiente_reduceStockCorrectamente() {
        Producto producto = new Producto("P001", "Mouse", 500.0, 10);
        service.registrar(producto);
        service.vender("P001", 3);

        Producto resultado = dao.findByCodigo("P001").orElseThrow();
        assertEquals(7, resultado.getStock(), "Deberían quedar 7 unidades tras vender 3");
    }

    @Test
    void vender_productoInexistente_lanzaProductoNoEncontradoException() {
        assertThrows(ProductoNoEncontradoException.class,
                () -> service.vender("NO_EXISTE", 1),
                "Vender un producto inexistente debe lanzar ProductoNoEncontradoException");
    }

    @Test
    void vender_sinStock_verificaDetallesDeExcepcion() {
        Producto producto = new Producto("P001", "Mouse", 500.0, 2);
        service.registrar(producto);

        StockInsuficienteException ex = assertThrows(StockInsuficienteException.class,
                () -> service.vender("P001", 5),
                "Vender más del stock disponible debe lanzar StockInsuficienteException");

        assertEquals("P001", ex.getCodigoProducto());
        assertEquals(2, ex.getStockActual());
        assertEquals(5, ex.getCantidadSolicitada());
    }

    @Test
    void calcularTotalConDescuento_precio100Descuento10_retorna90() {
        Producto producto = new Producto("P001", "Mouse", 100.0, 10);
        double resultado = service.calcularTotalConDescuento(producto, 0.10);
        assertEquals(90.0, resultado, 0.001);
    }

    @Test
    void calcularTotalConDescuento_productoNulo_lanzaIllegalArgumentException() {
        assertThrows(IllegalArgumentException.class,
                () -> service.calcularTotalConDescuento(null, 0.10));
    }

    @Test
    void calcularTotalConDescuento_descuentoNegativo_lanzaIllegalArgumentException() {
        Producto producto = new Producto("P001", "Mouse", 100.0, 10);
        assertThrows(IllegalArgumentException.class,
                () -> service.calcularTotalConDescuento(producto, -0.10));
    }

    @Test
    void calcularTotalConDescuento_descuentoMayorQueUno_lanzaIllegalArgumentException() {
        Producto producto = new Producto("P001", "Mouse", 100.0, 10);
        assertThrows(IllegalArgumentException.class,
                () -> service.calcularTotalConDescuento(producto, 1.10));
    }
}
