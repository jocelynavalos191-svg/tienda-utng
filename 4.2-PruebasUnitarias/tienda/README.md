## Pruebas

### Resumen de Ejecución
- **Número de tests:** 12 tests en total (4 en `ProductoTest` y 8 en `ProductoServiceTest`)
- **Cobertura alcanzada en ProductoService:** 85.7% (Cumple con el requisito mínimo de >= 70%)
- **Reporte gráfico:** Ver archivo `cobertura.png` en la raíz del proyecto.

### Cómo ejecutar las pruebas

Ejecutar todas las pruebas unitarias desde la terminal:
```bash
mvn test

## Excepciones

El sistema cuenta con tres excepciones de dominio personalizadas dentro del paquete `excepcion`:

| Excepción | Cuándo se lanza | Cómo manejarla |
| :--- | :--- | :--- |
| `ProductoNoEncontradoException` | Cuando se realiza una operación (ej. `vender`) con un código de producto inexistente. | Capturar mediante `catch (ProductoNoEncontradoException e)` y notificar al usuario que verifique el código. |
| `StockInsuficienteException` | Cuando se intenta vender una cantidad mayor al stock actual disponible. | Capturar `catch (StockInsuficienteException e)` y acceder a `e.getStockActual()` y `e.getCantidadSolicitada()` para retroalimentar al usuario. |
| `PrecioInvalidoException` | Al registrar o instanciar un producto con precio negativo (`precio < 0`). | Capturar `catch (PrecioInvalidoException e)` y solicitar un precio válido mayor o igual a cero (`e.getPrecio()`). |

