package utng.jal.gtid232.excepcion;

/**
 * Excepción lanzada cuando el stock disponible es insuficiente para realizar una venta.
 */
public class StockInsuficienteException extends RuntimeException {

    private final String codigoProducto;
    private final int stockActual;
    private final int cantidadSolicitada;

    /**
     * @param codigoProducto Código del producto afectado.
     * @param stockActual Stock actualmente disponible en el inventario.
     * @param cantidadSolicitada Cantidad que se intentó vender.
     */
    public StockInsuficienteException(String codigoProducto, int stockActual, int cantidadSolicitada) {
        super(String.format("Stock insuficiente para el producto %s. Stock actual: %d, solicitado: %d",
                codigoProducto, stockActual, cantidadSolicitada));
        this.codigoProducto = codigoProducto;
        this.stockActual = stockActual;
        this.cantidadSolicitada = cantidadSolicitada;
    }

    public String getCodigoProducto() {
        return codigoProducto;
    }

    public int getStockActual() {
        return stockActual;
    }

    public int getCantidadSolicitada() {
        return cantidadSolicitada;
    }
}