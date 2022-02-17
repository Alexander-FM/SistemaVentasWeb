package Modelo;

import Conexion.Conexion;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class clienteDAO extends Conexion {

    public List<Cliente> listar() throws Exception {
        List<Cliente> clientes = null;
        Cliente cli;
        ResultSet rs = null;
        String sql = "SELECT* FROM Cliente";

        try {
            this.conectar(false);
            rs = this.ejecutarOrdenDatos(sql);
            clientes = new ArrayList<>();
            while (rs.next() == true) {
                cli = new Cliente();
                cli.setCodigo(rs.getInt("idCliente"));
                cli.setNombres(rs.getString("nombresCompletos"));
                cli.setTipoDocumento(rs.getString("tipoDocumento"));
                cli.setNumeroDocumento(rs.getString("numeroDocumento"));
                cli.setDepartamento(rs.getString("departamento"));
                cli.setProvincia(rs.getString("provincia"));
                cli.setDistrito(rs.getString("distrito"));
                cli.setEstado(rs.getBoolean("estado"));
                cli.setCalle(rs.getString("calle"));
                cli.setTelefono(rs.getString("telefono"));
                cli.setEmail(rs.getString("email"));
                clientes.add(cli);
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
        return clientes;
    }

    public void eliminar(Cliente cliente) throws Exception {
        String sql = "DELETE FROM Cliente WHERE idCliente=" + cliente.getCodigo();
        try {
            this.conectar(false);
            this.ejecutarOrden(sql);
        } catch (Exception e) {
            throw e;
        }
    }

    public void registrar(Cliente cliente) throws Exception {
        String sql;

        sql = "INSERT INTO Cliente (nombresCompletos, tipoDocumento, numeroDocumento, departamento, provincia, distrito, "
                + "calle, telefono, estado, email) "
                + "VALUES('" + cliente.getNombres() + "', '"
                + cliente.getTipoDocumento() + "', '"
                + cliente.getNumeroDocumento() + "', '"
                + cliente.getDepartamento() + "', '"
                + cliente.getProvincia() + "', '"
                + cliente.getDistrito() + "', '"
                + cliente.getCalle() + "', '"
                + cliente.getTelefono() + "', "
                + (cliente.isEstado() == true ? "1" : "0")
                + ", '" + cliente.getEmail() + "')";

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
