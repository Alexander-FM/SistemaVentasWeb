package Modelo;

import Conexion.Conexion;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

/**
 * La clase panel administrativo dao
 *
 * @author Administrator
 */
public class panelAdministrativoDAO extends Conexion {

    private int resultado = 0;
    private double totalCaja = 0.0;
    /**
     * Esta consulta permite listar los 10 productos cuando el stock es menor o
     * igual a 5.
     *
     * @return {List} retorna una lista.
     * @throws Exception
     */
    public List<Producto> listar10ProductosConStockMinimo() throws Exception {
        List<Producto> productos = null;
        Producto prod;
        ResultSet rs = null;
        String sql = "SELECT TOP(10) P.producto, P.precioCompra, P.precioVenta, P.stock "
                + "FROM Producto P WHERE P.stock <= 5";
        try {
            this.conectar(false);
            rs = this.ejecutarOrdenDatos(sql);
            productos = new ArrayList<>();
            while (rs.next() == true) {
                prod = new Producto();
                prod.setProducto(rs.getString("producto"));
                prod.setPrecioCompra(rs.getDouble("precioCompra"));
                prod.setPrecioVenta(rs.getDouble("precioVenta"));
                prod.setStock(rs.getInt("stock"));
                productos.add(prod);
            }
            rs.close();
        } catch (Exception e) {
            this.cerrar(false);
            throw e;
        } finally {
            this.cerrar(false);
        }
        return productos;
    }

    /**
     * Esta consulta permite listar ultimos 10 productos registrados
     *
     * @return {List} retorna una lista.
     * @throws Exception
     */
    public List<Producto> listar10UltimosProductos() throws Exception {
        List<Producto> productos = null;
        Producto prod;
        ResultSet rs = null;
        String sql = "SELECT TOP(10) P.producto, P.descripcion, P.precioVenta "
                + "FROM Producto P "
                + "ORDER BY P.fechaRegistro DESC";
        try {
            this.conectar(false);
            rs = this.ejecutarOrdenDatos(sql);
            productos = new ArrayList<>();
            while (rs.next() == true) {
                prod = new Producto();
                prod.setProducto(rs.getString("producto"));
                prod.setDescripcion(rs.getString("descripcion"));
                prod.setPrecioVenta(rs.getDouble("precioVenta"));
                productos.add(prod);
            }
            rs.close();
        } catch (Exception e) {
            this.cerrar(false);
            throw e;
        } finally {
            this.cerrar(false);
        }
        return productos;
    }
    
    /**
     * Esta consulta devuelve el total de ventas realizadas
     * @return {int} retorna un valor entero, representa el total de ventas
     * @throws Exception
     */
    public int totalVentas() throws Exception {
        ResultSet rs = null;
        String sql = "SELECT COUNT(V.idVenta) AS 'totalVentas' FROM Venta V";
        try {
            this.conectar(false);
            rs = this.ejecutarOrdenDatos(sql);
            if (rs.next() == true) {
                resultado = rs.getInt("totalVentas");
            }
        } catch (Exception e) {
            throw e;
        } finally {
            this.cerrar(false);
        }
        return resultado;
    }

    /**
     * Esta consulta devuelve el total de compras realizadas
     * @return {int} retorna un valor entero, representa el total de compras
     * @throws Exception
     */
    public int totalCompras() throws Exception {
        ResultSet rs = null;
        String sql = "SELECT COUNT(C.idCompra) AS 'totalCompras' FROM Compra C";
        try {
            this.conectar(false);
            rs = this.ejecutarOrdenDatos(sql);
            if (rs.next() == true) {
                resultado = rs.getInt("totalCompras");
            }
        } catch (Exception e) {
            throw e;
        } finally {
            this.cerrar(false);
        }
        return resultado;
    }
    
    /**
     * Esta consulta devuelve el total de clientes registrados
     * @return {int} retorna un valor entero, representa el total de clientes
     * @throws Exception
     */
    public int totalClientes() throws Exception {
        ResultSet rs = null;
        String sql = "SELECT COUNT(C.idCliente) AS 'totalClientes' FROM Cliente C";
        try {
            this.conectar(false);
            rs = this.ejecutarOrdenDatos(sql);
            if (rs.next() == true) {
                resultado = rs.getInt("totalClientes");
            }
        } catch (Exception e) {
            throw e;
        } finally {
            this.cerrar(false);
        }
        return resultado;
    }
    
    /**
     * Esta consulta devuelve el monto total de las ventas realizadas
     * @return {double} retorna un valor double, representa la suma de ventas
     * @throws Exception
     */
    public double totalCaja() throws Exception {
        ResultSet rs = null;
        String sql = "SELECT SUM(DV.precioVenta) AS 'totalCaja' FROM detalleVenta DV";
        try {
            this.conectar(false);
            rs = this.ejecutarOrdenDatos(sql);
            if (rs.next() == true) {
                totalCaja = rs.getDouble("totalCaja");
            }
        } catch (Exception e) {
            throw e;
        } finally {
            this.cerrar(false);
        }
        return totalCaja;
    }

}
