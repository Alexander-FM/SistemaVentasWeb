package Modelo;

import java.sql.Date;
import lombok.Data;

/**
 * La clase compra
 * @author Luigui Alexander Fuentes Medina
 */
@Data
public class Compra {
    private int idCompra;
    private TipoComprobante tipoComprobante;
    private String serieComprobante;
    private String numeroComprobante;
    private Date fecha;
    private double total;
    private double impuesto;
    private Proveedor proveedor;
    private Producto producto;
    private boolean estado;
}
