package Modelo;

import Conexion.Conexion;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class categoriaDAO extends Conexion {

    public List<Categoria> listar() throws Exception {
        List<Categoria> productos = null;
        Categoria cat;
        ResultSet rs = null;
        String sql = "SELECT* FROM Categoria";

        try {
            this.conectar(false);
            rs = this.ejecutarOrdenDatos(sql);
            productos = new ArrayList<>();
            while (rs.next() == true) {
                cat = new Categoria();
                cat.setCodigo(rs.getInt("idCategoria"));
                cat.setCategoria(rs.getString("categoria"));
                cat.setEstado(rs.getBoolean("estado"));
                productos.add(cat);
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

    public void registrar(Categoria categoria) throws Exception {
        String sql;

        sql = "INSERT INTO Categoria (categoria, estado) "
                + "VALUES('" + categoria.getCategoria() + "', "
                + (categoria.isEstado() == true ? "1" : "0") + ")";

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

    public void actualizar(Categoria categoria) throws Exception {
        String sql;

        sql = "UPDATE Categoria SET Categoria = '" + categoria.getCategoria()
                + "', Estado = " + (categoria.isEstado() == true ? "1" : "0")
                + " WHERE idCategoria = " + categoria.getCodigo();

        try {
            this.conectar(false);
            this.ejecutarOrden(sql);
        } catch (Exception e) {
            throw e;
        } finally {
        }
    }

    public Categoria leer(Categoria categoria) throws Exception {
        Categoria cat = null;
        ResultSet rs = null;
        String sql = "SELECT C.Categoria, C.Estado FROM Categoria C  WHERE C.idCategoria = " + categoria.getCodigo();

        try {
            this.conectar(false);
            rs = this.ejecutarOrdenDatos(sql);
            if (rs.next() == true) {
                cat = new Categoria();
                cat.setCodigo(categoria.getCodigo());
                cat.setCategoria(rs.getString("Categoria"));
                cat.setEstado(rs.getBoolean("Estado"));
            }
        } catch (Exception e) {
            throw e;
        } finally {
            if (rs != null && rs.isClosed() == false) {
                rs.close();
            }
            rs = null;
        }
        return cat;
    }

    public void eliminar(Categoria categoria) throws Exception {
        String sql = "DELETE FROM Categoria WHERE idCategoria=" + categoria.getCodigo();
        try {
            this.conectar(false);
            this.ejecutarOrden(sql);
        } catch (Exception e) {
            throw e;
        }
    }

}
