
package Modelo;

import java.sql.Date;
import lombok.Data;
@Data
public class Producto {
    public int codigo;
    private String codigoProducto;
    private String producto;
    private String descripcion;
    private double precioVenta;
    private double precioCompra;
    private int stock;
    private String unidadMedida;
    private boolean estado;
    private Categoria categoria;
    private Marca marca;
    private Date fechaRegistro;   
}
