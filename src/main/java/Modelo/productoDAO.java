package Modelo;

import Conexion.Conexion;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class productoDAO extends Conexion {

    public void registrar(Producto producto) throws Exception {
        String sql;

        sql = "INSERT INTO Producto (codigoProducto, Producto, Descripcion, PrecioVenta, Stock, UnidadMedida, "
                + "Estado, idCategoria, idMarca) "
                + "VALUES('" + producto.getCodigoProducto() + "', '"
                + producto.getProducto() + "', '"
                + producto.getDescripcion() + "', "
                + producto.getPrecioVenta()+ ", "
                + producto.getStock() + ", '"
                + producto.getUnidadMedida() + "', "
                + (producto.isEstado() == true ? "1" : "0")
                + ", " + producto.getCategoria().getCodigo()
                + ", " + producto.getMarca().getCodigo() + ")";

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

    public List<Producto> listar() throws Exception {
        List<Producto> productos = null;
        Producto prod;
        ResultSet rs = null;
        String sql = "SELECT P.idProducto, P.codigoProducto, P.estado, P.producto, P.descripcion, "
                + "P.precioVenta, P.stock, p.unidadMedida, C.categoria, M.marca "
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
                prod.setDescripcion(rs.getString("descripcion"));
                prod.setPrecioVenta(rs.getDouble("precio"));
                prod.setStock(rs.getInt("stock"));
                prod.setUnidadMedida(rs.getString("unidadMedida"));
                prod.setCategoria(new Categoria());
                prod.getCategoria().setCategoria(rs.getString("categoria"));
                prod.setMarca(new Marca());
                prod.getMarca().setMarca(rs.getString("marca"));
                productos.add(prod);
            }
            rs.close();
            this.cerrar(true);
        } catch (Exception e) {
            this.cerrar(false);
            throw e;
        } finally {
            if (rs != null && rs.isClosed() == false) {
                rs.close();
            }
            rs = null;
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

}
