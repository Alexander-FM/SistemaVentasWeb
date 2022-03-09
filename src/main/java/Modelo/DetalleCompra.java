package Modelo;

import lombok.Data;

@Data
public class DetalleCompra {
private int idDetalleCompra;
private Compra compra;
private Producto producto;
private int cantidad;
private double precioCompra;
}
