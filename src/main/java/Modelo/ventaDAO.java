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
   * @throws Exception
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
}
