package Modelo;

import Conexion.Conexion;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class ventaDAO extends Conexion {

  /**
   * Este método lista todos las ventas registrados en la base de datos
   *
   * @return {List} retorna una lista, representa el listado de las ventas realizadas
   */
  public List<Venta> listarVentas() throws Exception {
    List<Venta> ventas = null;
    Venta ven;
    ResultSet rs = null;
    String sql = "SELECT V.idVenta, TC.tipoComprobante, V.serieComprobante, V.numeroComprobante, "
        + "V.total, V.impuesto, V.fecha, E.nombre, E.apellidos, C.nombresCompletos as cliente, V.estado "
        + "FROM VENTA V "
        + "INNER JOIN TipoComprobante TC ON V.idTipoComprobante = TC.idTipoComprobante "
        + "INNER JOIN Cliente C ON V.idCliente = C.idCliente "
        + "INNER JOIN Empleado E ON V.idEmpleado = E.idEmpleado "
        + "ORDER BY V.idVenta ASC";

    try {
      this.conectar(false);
      rs = this.ejecutarOrdenDatos(sql);
      ventas = new ArrayList<>();
      while (rs.next() == true) {
        ven = new Venta();
        ven.setIdVenta(rs.getInt("idVenta"));
        ven.setTipoComprobante(new TipoComprobante());
        ven.getTipoComprobante().setTipoComprobante(rs.getString("tipoComprobante"));
        ven.setSerieComprobante(rs.getString("serieComprobante"));
        ven.setNumeroComprobante(rs.getString("numeroComprobante"));
        ven.setTotal(rs.getDouble("total"));
        ven.setImpuesto(rs.getDouble("impuesto"));
        ven.setFecha(rs.getDate("fecha"));
        ven.setEmpleado(new Empleado());
        ven.getEmpleado().setNombre(rs.getString("nombre") + " " + rs.getString("apellidos"));
        ven.setCliente(new Cliente());
        ven.getCliente().setNombres(rs.getString("cliente"));
        ven.setEstado(rs.getBoolean("estado"));
        ventas.add(ven);
      }
      this.cerrar(false);
    } catch (Exception e) {
      this.cerrar(false);
      throw e;
    } finally {
      this.cerrar(false);
    }
    return ventas;
  }

  /**
   * Este método lista los detalle de una venta
   *
   * @param {Integer} id de la venta
   * @return {List} retorna una lista, representa el listado del detalle de la venta
   */
  public List<DetalleVenta> listarDetallesVentas(Venta venta) throws Exception {
    List<DetalleVenta> detalleVentas = null;
    DetalleVenta det;
    ResultSet rs = null;
    String sql = "SELECT P.producto, P.imagen, DV.cantidad, DV.precioVenta FROM Venta V "
        + "INNER JOIN DetalleVenta DV on V.idVenta = DV.idVenta "
        + "INNER JOIN Cliente C on C.idCliente = V.idCliente "
        + "INNER JOIN Producto P on DV.idProducto = P.idProducto "
        + "WHERE V.idVenta = '" + venta.getIdVenta() + "'"
        + "GROUP BY P.producto, P.imagen, DV.cantidad, DV.precioVenta";

    try {
      this.conectar(false);
      rs = this.ejecutarOrdenDatos(sql);
      detalleVentas = new ArrayList<>();
      while (rs.next() == true) {
        det = new DetalleVenta();
        det.setProducto(new Producto());
        det.getProducto().setProducto(rs.getString("producto"));
        det.getProducto().setImagen(rs.getString("imagen"));
        det.setCantidad(rs.getInt("cantidad"));
        det.setPrecioVenta(rs.getDouble("precioVenta"));
        detalleVentas.add(det);
      }
      this.cerrar(false);
    } catch (Exception e) {
      this.cerrar(false);
      throw e;
    } finally {
      this.cerrar(false);
    }
    return detalleVentas;
  }

  /**
   * Este método anulará la venta, y no se podrá revertir
   *
   * @param {Integer} representa el id de la venta
   */
  public void anularVenta(Venta venta) throws Exception {
    String sql = "UPDATE Venta SET estado = 0 WHERE idVenta = '" + venta.getIdVenta() + "'";
    try {
      this.conectar(false);
      this.ejecutarOrden(sql);
      this.cerrar(false);
    } catch (Exception e) {
      this.cerrar(false);
      throw e;
    }
  }

  /**
   * Este método, actualiza el stock del producto que fue vendido
   *
   * @param {Integer} representa el id de la venta
   */
  public void actualizarStockProducto(DetalleVenta detalleVenta) throws Exception {
    String sql = "UPDATE Producto SET stock = stock - '" + detalleVenta.getCantidad() + "'"
        + "WHERE idProducto = '" + detalleVenta.getProducto().getCodigo()+ "'";
    try {
      this.conectar(false);
      this.ejecutarOrden(sql);
      this.cerrar(false);
    } catch (Exception e) {
      this.cerrar(false);
      throw e;
    }
  }
}
