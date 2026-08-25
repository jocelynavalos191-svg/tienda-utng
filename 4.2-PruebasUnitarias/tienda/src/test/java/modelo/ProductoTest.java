package modelo;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

import utng.jal.gtid232.modelo.Producto;

class ProductoTest {

    @Test
    void constructor_datosValidos_creaProductoCorrectamente() {

        Producto producto = new Producto(
                "P001",
                "Mouse",
                500.0,
                10
        );

        assertAll(
                "Los datos del producto deben inicializarse correctamente",
                () -> assertEquals(
                        "P001",
                        producto.getCodigo(),
                        "El código debe ser P001"
                ),
                () -> assertEquals(
                        "Mouse",
                        producto.getNombre(),
                        "El nombre debe ser Mouse"
                ),
                () -> assertEquals(
                        500.0,
                        producto.getPrecio(),
                        "El precio debe ser 500"
                ),
                () -> assertEquals(
                        10,
                        producto.getStock(),
                        "El stock debe ser 10"
                )
        );
    }

    @Test
    void constructor_precioNegativo_lanzaExcepcion() {

        assertThrows(
                IllegalArgumentException.class,
                () -> new Producto(
                        "P001",
                        "Mouse",
                        -100,
                        10
                ),
                "Un precio negativo debe lanzar una excepción"
        );
    }

    @Test
    void constructor_nombreNulo_lanzaExcepcion() {

        assertThrows(
                IllegalArgumentException.class,
                () -> new Producto(
                        "P001",
                        null,
                        100,
                        10
                ),
                "Un nombre nulo debe lanzar una excepción"
        );
    }

    @Test
    void constructor_datosValidos_productoActivoPorDefecto() {

        Producto producto = new Producto(
                "P001",
                "Mouse",
                500,
                10
        );

        assertTrue(
                producto.isActivo(),
                "Un producto nuevo debe estar activo por defecto"
        );
    }
}



