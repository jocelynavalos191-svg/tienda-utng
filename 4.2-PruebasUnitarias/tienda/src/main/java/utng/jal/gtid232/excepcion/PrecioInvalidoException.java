package utng.jal.gtid232.excepcion;

/**
 * Excepción lanzada cuando se intenta asignar un precio negativo a un producto.
 */
public class PrecioInvalidoException extends RuntimeException {

    private final double precio;

    /**
     * @param precio Valor numérico del precio inválido ingresado.
     */
    public PrecioInvalidoException(double precio) {
        super(String.format("Precio invalido: %.2f. El precio debe ser mayor o igual a cero (>=0).", precio));
        this.precio = precio;
    }

    public double getPrecio() {
        return precio;
    }
}