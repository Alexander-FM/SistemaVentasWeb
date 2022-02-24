package Modelo;

import Conexion.Conexion;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class proveedorDAO extends Conexion {

    public List<Proveedor> listar() throws Exception {
        List<Proveedor> proveedores = null;
        Proveedor pro;
        ResultSet rs = null;
        String sql = "SELECT * FROM Proveedor";

        try {
            this.conectar(false);
            rs = this.ejecutarOrdenDatos(sql);
            proveedores = new ArrayList<>();
            while (rs.next() == true) {
                pro = new Proveedor();
                pro.setCodigo(rs.getInt("idProveedor"));
                pro.setRazonSocial(rs.getString("razonSocial"));
                pro.setRuc(rs.getString("ruc"));
                pro.setDireccion(rs.getString("direccion"));
                pro.setTelefono(rs.getString("telefono"));
                pro.setEstado(rs.getBoolean("estado"));
                proveedores.add(pro);
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
        return proveedores;
    }
    
     public List<Proveedor> listarProveedoresActivos() throws Exception {
        List<Proveedor> proveedores = null;
        Proveedor pro;
        ResultSet rs = null;
        String sql = "SELECT idProveedor, razonSocial FROM Proveedor WHERE estado = 1";

        try {
            this.conectar(false);
            rs = this.ejecutarOrdenDatos(sql);
            proveedores = new ArrayList<>();
            while (rs.next() == true) {
                pro = new Proveedor();
                pro.setCodigo(rs.getInt("idProveedor"));
                pro.setRazonSocial(rs.getString("razonSocial"));
                proveedores.add(pro);
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
        return proveedores;
    }

    public void eliminar(Proveedor proveedor) throws Exception {
        String sql = "DELETE FROM Proveedor WHERE idProveedor=" + proveedor.getCodigo();
        try {
            this.conectar(false);
            this.ejecutarOrden(sql);
        } catch (Exception e) {
            throw e;
        }
    }

    public void registrar(Proveedor proveedor) throws Exception {
        String sql;

        sql = "INSERT INTO Proveedor (razonSocial, ruc, direccion, telefono, estado) "
                + "VALUES('" + proveedor.getRazonSocial() + "', '"
                + proveedor.getRuc() + "', '"
                + proveedor.getDireccion() + "', '"
                + proveedor.getTelefono() + "', "
                + (proveedor.isEstado() == true ? "1" : "0") + ")";

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
}
