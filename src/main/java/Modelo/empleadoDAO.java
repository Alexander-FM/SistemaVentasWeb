package Modelo;

import Conexion.Conexion;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class empleadoDAO extends Conexion {

    public Empleado identificar(Empleado emple) throws Exception {
        Empleado emp = null;
        ResultSet rs = null;

        String sql = "SELECT E.idEmpleado, E.nombre, E.apellidos, E.cargo, "
                + "CONVERT(VARCHAR(MAX), DECRYPTBYPASSPHRASE('password', E.clave)) as [Llave] "
                + "FROM Empleado E WHERE E.estado = 1 AND E.userName = '" + emple.getLogin() + "'";
        try {
            if (!emple.getClave().isEmpty()) {
                this.conectar(false);
                rs = this.ejecutarOrdenDatos(sql);
                if (rs.next()) {
                    emp = new Empleado();
                    emp.setCodigo(rs.getInt("idEmpleado"));
                    emp.setNombre(rs.getString("nombre"));
                    emp.setApellidos(rs.getString("apellidos"));
                    emp.setCargo(rs.getString("cargo"));
                    emp.setClave(rs.getString("Llave"));
                    emp.setEstado(true);
                }
                rs.close();
                if (!emp.getClave().equals(emple.getClave())) {
                    return null;
                }
            }
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
        return emp;
    }

    public void registrar(Empleado empleado) throws Exception {
        String sql;

        sql = "INSERT INTO Empleado (nombre, apellidos, tipoDocumento, numeroDocumento, direccion, telefono, email, "
                + "fechaNacimiento, estado, cargo, userName, clave) "
                + "VALUES('" + empleado.getNombre() + "', '"
                + empleado.getApellidos() + "', '"
                + empleado.getTipoDocumento() + "', '"
                + empleado.getNumeroDocumento() + "', '"
                + empleado.getDireccion() + "', '"
                + empleado.getTelefono() + "', '"
                + empleado.getEmail() + "', '"
                + empleado.getFechaNacimiento() + "', "
                + (empleado.isEstado() == true ? "1" : "0")
                + ", '" + empleado.getCargo() + "', '"
                + empleado.getLogin() 
                + "', ENCRYPTBYPASSPHRASE('password', '" + empleado.getClave() + "'))";

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

    public List<Empleado> listar() throws Exception {
        List<Empleado> empleados = null;
        Empleado emp;
        ResultSet rs = null;
        String sql = "SELECT e.idEmpleado, e.nombre, e.apellidos, e.tipoDocumento, e.numeroDocumento, e.email, "
                + "e.telefono, e.fechaNacimiento, e.estado, e.cargo, e.userName FROM empleado e ";

        try {
            this.conectar(false);
            rs = this.ejecutarOrdenDatos(sql);
            empleados = new ArrayList<>();
            while (rs.next() == true) {
                emp = new Empleado();
                emp.setCodigo(rs.getInt("idEmpleado"));
                emp.setNombre(rs.getString("nombre"));
                emp.setApellidos(rs.getString("apellidos"));
                emp.setTipoDocumento(rs.getString("tipoDocumento"));
                emp.setNumeroDocumento(rs.getString("numeroDocumento"));
                emp.setEmail(rs.getString("email"));
                emp.setTelefono(rs.getString("telefono"));
                emp.setFechaNacimiento(rs.getString("fechaNacimiento"));
                emp.setEstado(rs.getBoolean("estado"));
                emp.setCargo(rs.getString("cargo"));
                emp.setLogin(rs.getString("userName"));
                empleados.add(emp);
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
        return empleados;
    }

    public void eliminar(Empleado empleado) throws Exception {
        String sql = "DELETE FROM Empleado WHERE idEmpleado=" + empleado.getCodigo();
        try {
            this.conectar(false);
            this.ejecutarOrden(sql);
        } catch (Exception e) {
            throw e;
        }
    }

    public Empleado leer(Empleado empleado) throws Exception {
        Empleado emp = null;
        ResultSet rs = null;
        String sql = "SELECT e.idEmpleado, e.nombre, e.apellidos, e.tipoDocumento, e.numeroDocumento, e.email, "
                + "e.telefono, e.fechaNacimiento, e.estado, e.cargo, "
                + "e.userName, CONVERT(VARCHAR(MAX), DECRYPTBYPASSPHRASE('password', e.clave)) as [Llave] FROM empleado e "
                + "WHERE C.idEmpleado = " + empleado.getCodigo();

        try {
            this.conectar(false);
            rs = this.ejecutarOrdenDatos(sql);
            if (rs.next() == true) {
                emp = new Empleado();
                emp.setCodigo(empleado.getCodigo());
                emp.setNombre(rs.getString("nombre"));
                emp.setApellidos(rs.getString("apellidos"));
                emp.setTipoDocumento(rs.getString("tipoDocumento"));
                emp.setNumeroDocumento(rs.getString("numeroDocumento"));
                emp.setDireccion(rs.getString("Direccion"));
                emp.setTelefono(rs.getString("telefono"));
                emp.setEmail(rs.getString("email"));
                emp.setFechaNacimiento(rs.getString("fechaNacimiento"));
                emp.setEstado(rs.getBoolean("estado"));
                emp.setCargo(rs.getString("cargo"));
                emp.setLogin(rs.getString("userName"));
                emp.setClave(rs.getString("Llave"));
            }
        } catch (Exception e) {
            throw e;
        } finally {
            if (rs != null && rs.isClosed() == false) {
                rs.close();
            }
            rs = null;
        }
        return emp;
    }

    public void actualizar(Empleado empleado) throws Exception {
        String sql;

        sql = "UPDATE empleado SET nombre = '" + empleado.getNombre()
                + "', apellidos = '" + empleado.getApellidos()
                + "', tipoDocumento = '" + empleado.getTipoDocumento()
                + "', numeroDocumento = '" + empleado.getNumeroDocumento()
                + "', Direccion = '" + empleado.getDireccion()
                + "', telefono = '" + empleado.getTelefono()
                + "', email = '" + empleado.getEmail()
                + "', fechaNacimiento = '" + empleado.getFechaNacimiento()
                + "', estado = " + (empleado.isEstado() == true ? "1" : "0")
                + ", cargo = '" + empleado.getCargo()
                + "', userName = '" + empleado.getLogin()
                + "', clave = ENCRYPTBYPASSPHRASE('password', '" + empleado.getClave() + "')"
                + " WHERE idEmpleado = " + empleado.getCodigo();

        try {
            this.conectar(false);
            this.ejecutarOrden(sql);
        } catch (Exception e) {
            throw e;
        } finally {
        }
    }

}
