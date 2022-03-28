package Modelo;

import Conexion.Conexion;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class productoDAO extends Conexion {

    /**
     * Este método registra el producto en la base de datos
     *
     * @param producto
     * @throws Exception
     */
    public void registrar(Producto producto) throws Exception {
        String sql;

        sql = "INSERT INTO Producto (codigoProducto, Producto, Descripcion, Imagen, PrecioVenta, PrecioCompra, Stock, "
                + "fechaRegistro, idCategoria, idMarca, idProveedor, estado) "
                + "VALUES('" + producto.getCodigoProducto() + "', '"
                + producto.getProducto() + "', '"
                + producto.getDescripcion() + "', '"
                + producto.getImagen() + "', "
                + producto.getPrecioVenta() + ", "
                + producto.getPrecioCompra() + ", "
                + producto.getStock() + ", '"
                + producto.getFechaRegistro() + "', "
                + producto.getCategoria().getCodigo() + ", "
                + producto.getMarca().getCodigo() + ", "
                + producto.getProveedor().getCodigo() + ", 1)";

        try {
            this.conectar(false);
            this.ejecutarOrden(sql);
            this.cerrar(true);
        } catch (Exception e) {
            this.cerrar(false);
            throw e;
        } finally {
        }
    }

    /**
     * Este método lista todos los productos registrados en la base de datos
     *
     * @return {LIst} retorna una lista, representa el listado de productos
     * @throws Exception
     */
    public List<Producto> listar() throws Exception {
        List<Producto> productos = null;
        Producto prod;
        ResultSet rs = null;
        String sql = "SELECT P.idProducto, P.codigoProducto, P.estado, P.producto, P.imagen, P.descripcion, "
                + "P.precioVenta, P.precioCompra, P.stock, C.categoria, M.marca "
                + "FROM producto P inner join categoria C "
                + "on C.idCategoria = P.idCategoria inner join Marca M "
                + "on M.idMarca = P.idMarca";

        try {
            this.conectar(false);
            rs = this.ejecutarOrdenDatos(sql);
            productos = new ArrayList<>();
            while (rs.next() == true) {
                prod = new Producto();
                prod.setCodigo(rs.getInt("idProducto"));
                prod.setCodigoProducto(rs.getString("codigoProducto"));
                prod.setEstado(rs.getBoolean("estado"));
                prod.setProducto(rs.getString("producto"));
                prod.setImagen(rs.getString("imagen"));
                prod.setDescripcion(rs.getString("descripcion"));
                prod.setPrecioVenta(rs.getDouble("precioVenta"));
                prod.setPrecioCompra(rs.getDouble("precioCompra"));
                prod.setStock(rs.getInt("stock"));
                prod.setCategoria(new Categoria());
                prod.getCategoria().setCategoria(rs.getString("categoria"));
                prod.setMarca(new Marca());
                prod.getMarca().setMarca(rs.getString("marca"));
                productos.add(prod);
            }
            this.cerrar(false);
        } catch (Exception e) {
            this.cerrar(false);
            throw e;
        } finally {
            this.cerrar(false);
        }
        return productos;
    }

    public void eliminar(Producto producto) throws Exception {
        String sql = "DELETE FROM Producto WHERE idProducto=" + producto.getCodigo();
        try {
            this.conectar(false);
            this.ejecutarOrden(sql);
        } catch (Exception e) {
            throw e;
        }
    }

    public Producto leer(Producto producto) throws Exception {
        Producto prod = null;
        ResultSet rs = null;
        String sql = "SELECT P.idProducto, P.codigoProducto, P.estado, P.producto, P.imagen, P.descripcion, "
                + "P.fechaRegistro, P.precioVenta, P.precioCompra, P.stock, P.idCategoria, P.idMarca, P.idProveedor "
                + "FROM producto P WHERE P.idProducto = " + producto.getCodigo();
        try {
            this.conectar(false);
            rs = this.ejecutarOrdenDatos(sql);
            if (rs.next() == true) {
                prod = new Producto();
                prod.setCodigo(rs.getInt("idProducto"));
                prod.setCodigoProducto(rs.getString("codigoProducto"));
                prod.setEstado(rs.getBoolean("estado"));
                prod.setProducto(rs.getString("producto"));
                prod.setImagen(rs.getString("imagen"));
                prod.setDescripcion(rs.getString("descripcion"));
                prod.setFechaRegistro(rs.getDate("fechaRegistro"));
                prod.setPrecioVenta(rs.getDouble("precioVenta"));
                prod.setPrecioCompra(rs.getDouble("precioCompra"));
                prod.setStock(rs.getInt("stock"));
                prod.setCategoria(new Categoria());
                prod.getCategoria().setCodigo(rs.getInt("idCategoria"));
                prod.setMarca(new Marca());
                prod.getMarca().setCodigo(rs.getInt("idMarca"));
                prod.setProveedor(new Proveedor());
                prod.getProveedor().setCodigo(rs.getInt("idProveedor"));
            }
        } catch (Exception e) {
            throw e;
        } finally {
            this.cerrar(false);
        }
        return prod;
    }

    public void actualizar(Producto producto) throws Exception {
        String sql;
        sql = "UPDATE producto SET codigoProducto = '" + producto.getCodigoProducto()
                + "', Producto = '" + producto.getProducto()
                + "', Descripcion = '" + producto.getDescripcion()
                + "', Imagen = '" + producto.getImagen()
                + "', PrecioVenta = " + producto.getPrecioVenta()
                + ", PrecioCompra = " + producto.getPrecioCompra()
                + ", Stock = " + producto.getStock()
                + ", fechaRegistro = '" + producto.getFechaRegistro()
                + "', Estado = " + (producto.isEstado() == true ? "1" : "0")
                + ", idCategoria = " + producto.getCategoria().getCodigo()
                + ", idMarca = " + producto.getMarca().getCodigo()
                + ", idProveedor = " + producto.getProveedor().getCodigo()
                + " WHERE idProducto = " + producto.getCodigo();

        try {
            this.conectar(false);
            this.ejecutarOrden(sql);
        } catch (Exception e) {
            throw e;
        } finally {
            this.cerrar(false);
        }
    }
}
