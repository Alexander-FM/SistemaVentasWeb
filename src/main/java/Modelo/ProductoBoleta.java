package Modelo;

import lombok.Data;

@Data
public class ProductoBoleta {
    private int id;
    private String producto;
    private int cant;
    private double precioVenta;
    private double precioTotal;
}
