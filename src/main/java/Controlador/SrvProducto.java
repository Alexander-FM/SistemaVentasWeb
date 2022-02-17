package Controlador;

import Modelo.Categoria;
import Modelo.Marca;
import Modelo.Producto;
import Modelo.categoriaDAO;
import Modelo.marcaDAO;
import Modelo.productoDAO;
import java.io.IOException;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet(name = "SrvProducto", urlPatterns = {"/Producto"})
public class SrvProducto extends HttpServlet {

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String accion = "";

        if (request.getParameter("accion") != null) {
            accion = request.getParameter("accion");
            //li=listar, le=leer, nu=nuevo; re=registrar,
            //pr=presentar,ac=actualizar
            switch (accion) {
                case "li":
                    this.listarProductos(request, response);
                    break;
                case "nu":
                    request.setAttribute("accion", "re");
                    this.presentarFormulario(request, response);
                    break;
                case "re":
                    this.registrarProducto(request, response);
                    break;
                case "pr":
                    this.presentarProducto(request, response);
                    break;
                case "ac":
                    this.actualizarProducto(request, response);
                    break;
                case "eli":
                    this.eliminarProducto(request, response);
                    break;
            }
        } else {
            request.setAttribute(
                    "msje", "No se indicó la operación a realizar");
            this.getServletConfig().getServletContext().getRequestDispatcher("/WEB-INF/vistas/error.jsp"
            ).forward(request, response);
        }
    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

    private void listarProductos(HttpServletRequest request, HttpServletResponse response) {
        productoDAO dao = new productoDAO();
        List<Producto> productos = null;

        try {
            this.cargarMarcas(request);
            this.cargarCategorias(request);
            productos = dao.listar();
            request.setAttribute("productos", productos);
        } catch (Exception e) {
            request.setAttribute("msje", "No se pudo listar los productos");
        } finally {
            dao = null;
        }
        try {
            this.getServletConfig().getServletContext().getRequestDispatcher("/WEB-INF/Vista/Productos.jsp"
            ).forward(request, response);
        } catch (Exception e) {
            request.setAttribute("msje", "No se pudo realizar la operacion");
        }
    }

    private void presentarFormulario(HttpServletRequest request, HttpServletResponse response) {
        try {
            this.cargarMarcas(request);
            this.cargarCategorias(request);
            this.getServletConfig().getServletContext().
                    getRequestDispatcher("/WEB-INF/Vista/Productos.jsp"
                    ).forward(request, response);
        } catch (Exception e) {
            request.setAttribute("msje", "No se pudo realizar la operacion");
        }
    }

    private void cargarCategorias(HttpServletRequest request) {
        categoriaDAO dao = new categoriaDAO();
        List<Categoria> cats = null;

        try {
            cats = dao.listar();
            request.setAttribute("categorias", cats);
        } catch (Exception e) {
            request.setAttribute("msje", e.getMessage());
        } finally {
            cats = null;
            dao = null;
        }
    }

    private void cargarMarcas(HttpServletRequest request) {
        marcaDAO dao = new marcaDAO();
        List<Marca> mars = null;

        try {
            mars = dao.listar();
            request.setAttribute("marcas", mars);
        } catch (Exception e) {
            request.setAttribute("msje", e.getMessage());
        } finally {
            mars = null;
            dao = null;
        }
    }

    private void registrarProducto(HttpServletRequest request, HttpServletResponse response) {

        productoDAO daoProd;
        Producto prod = null;
        Categoria cate;
        Marca mar;

        if (request.getParameter("cboCategoria") != null
                && request.getParameter("cboMarca") != null
                && request.getParameter("codigoProducto") != null
                && request.getParameter("producto") != null
                && request.getParameter("descripcion") != null
                && request.getParameter("cboUnidadMedida") != null
                && request.getParameter("precio") != null
                && request.getParameter("stock") != null) {

            prod = new Producto();
            prod.setCodigoProducto(request.getParameter("codigoProducto"));
            prod.setProducto(request.getParameter("producto"));
            prod.setDescripcion(request.getParameter("descripcion"));
            prod.setUnidadMedida(request.getParameter("cboUnidadMedida"));
            prod.setPrecio(Double.parseDouble(request.getParameter("precio")));
            prod.setStock(Integer.parseInt(request.getParameter("stock")));

            cate = new Categoria();
            cate.setCodigo(Integer.parseInt(request.getParameter("cboCategoria")));
            prod.setCategoria(cate);
            mar = new Marca();
            mar.setCodigo(Integer.parseInt(request.getParameter("cboMarca")));
            prod.setMarca(mar);

            if (request.getParameter("chkVigencia") != null) {
                prod.setEstado(true);
            } else {
                prod.setEstado(false);
            }
            daoProd = new productoDAO();
            try {
                daoProd.registrar(prod);
                response.sendRedirect("Producto?accion=li");
            } catch (Exception e) {
                request.setAttribute("msje", "No se pudo registrar el producto" + e.getMessage());
                request.setAttribute("producto", prod);
                request.setAttribute("accion", "re");
                this.presentarFormulario(request, response);
            } finally {
            }
        }
    }

    private void presentarProducto(HttpServletRequest request, HttpServletResponse response) {
    }

    private void actualizarProducto(HttpServletRequest request, HttpServletResponse response) {
    }

    private void eliminarProducto(HttpServletRequest request, HttpServletResponse response) {
        productoDAO dao;
        Producto cat;
        if (request.getParameter("cod") != null) {
            cat = new Producto();
            cat.setCodigo(Integer.parseInt(request.getParameter("cod")));
            dao = new productoDAO();
            try {
                dao.eliminar(cat);
                response.sendRedirect("Producto?accion=li");
            } catch (Exception e) {
                request.setAttribute("msje", "No se pudo eliminar el producto" + e.getMessage());
            }
        }
    }

}
