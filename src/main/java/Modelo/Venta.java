package Modelo;

import java.sql.Date;
import lombok.Data;

/**
 * La clase venta
 * @author Luigui Alexander Fuentes Medina
 */
@Data
public class Venta {
    private int idVenta;
    private TipoComprobante tipoComprobante;
    private String serieComprobante;
    private String numeroComprobante;
    private Date fecha;
    private double total;
    private double impuesto;
    private Cliente cliente;
    private Empleado empleado;
    private boolean estado;
}
