package Modelo;

import Conexion.Conexion;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class marcaDAO extends Conexion {

    public List<Marca> listar() throws Exception {
        List<Marca> mar = null;
        Marca marca;
        ResultSet rs = null;
        String sql = "SELECT* FROM Marca";

        try {
            this.conectar(false);
            rs = this.ejecutarOrdenDatos(sql);
            mar = new ArrayList<>();
            while (rs.next() == true) {
                marca = new Marca();
                marca.setCodigo(rs.getInt("idMarca"));
                marca.setMarca(rs.getString("marca"));
                marca.setEstado(rs.getBoolean("estado"));
                mar.add(marca);
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
        return mar;
    }

    public void registrar(Marca marca) throws Exception {
        String sql;

        sql = "INSERT INTO Marca (marca, estado) "
                + "VALUES('" + marca.getMarca() + "', "
                + (marca.isEstado() == true ? "1" : "0") + ")";

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

    public void actualizar(Marca marca) throws Exception {
        String sql;

        sql = "UPDATE Marca SET Marca = '" + marca.getMarca()
                + "', Estado = " + (marca.isEstado() == true ? "1" : "0")
                + " WHERE idMarca = " + marca.getCodigo();

        try {
            this.conectar(false);
            this.ejecutarOrden(sql);
        } catch (Exception e) {
            throw e;
        } finally {
        }
    }

    public Marca leer(Marca marca) throws Exception {
        Marca mar = null;
        ResultSet rs = null;
        String sql = "SELECT M.Marca, M.Estado FROM Marca M  WHERE M.idMarca = " + marca.getCodigo();

        try {
            this.conectar(false);
            rs = this.ejecutarOrdenDatos(sql);
            if (rs.next() == true) {
                mar = new Marca();
                mar.setCodigo(marca.getCodigo());
                mar.setMarca(rs.getString("Marca"));
                mar.setEstado(rs.getBoolean("Estado"));
            }
        } catch (Exception e) {
            throw e;
        } finally {
            if (rs != null && rs.isClosed() == false) {
                rs.close();
            }
            rs = null;
        }
        return mar;
    }

    public void eliminar(Marca marca) throws Exception {
        String sql = "DELETE FROM Marca WHERE idMarca=" + marca.getCodigo();
        try {
            this.conectar(false);
            this.ejecutarOrden(sql);
        } catch (Exception e) {
            throw e;
        }
    }

}
