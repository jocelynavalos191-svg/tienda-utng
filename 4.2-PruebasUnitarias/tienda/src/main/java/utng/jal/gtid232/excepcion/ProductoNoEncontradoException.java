package utng.jal.gtid232.excepcion;

/**
 * Excepción lanzada cuando no se encuentra un producto en la base de datos o almacenamiento.
 */
public class ProductoNoEncontradoException extends RuntimeException {

    private final String codigo;

    /**
     * @param codigo Código del producto que no fue encontrado.
     */
    public ProductoNoEncontradoException(String codigo) {
        super("Producto no encontrado con codigo: " + codigo);
        this.codigo = codigo;
    }

    public String getCodigo() {
        return codigo;
    }
}