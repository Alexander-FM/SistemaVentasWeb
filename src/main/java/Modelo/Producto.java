
package Modelo;

import java.sql.Date;
import lombok.Data;
@Data
public class Producto {
    public int codigo;
    private String codigoProducto;
    private String producto;
    private String imagen;
    private String descripcion;
    private double precioVenta;
    private double precioCompra;
    private int stock;
    private boolean estado;
    private Categoria categoria;
    private Marca marca;
    private Proveedor proveedor;
    private Date fechaRegistro;   
}
