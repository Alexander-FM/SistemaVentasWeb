package Modelo;

import lombok.Data;

/**
 * La clase detalle venta
 * @author Luigui Alexander Fuentes Medina
 */
@Data
public class DetalleVenta {
    private int idDetalleVenta;
    private Venta venta;
    private Producto producto;
    private int cantidad;
    private double precioVenta;
}
