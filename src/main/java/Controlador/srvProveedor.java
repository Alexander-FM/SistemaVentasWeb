package Controlador;

import Modelo.Proveedor;
import Modelo.proveedorDAO;
import java.io.IOException;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet(name = "srvProveedor", urlPatterns = {"/Proveedor"})
public class srvProveedor extends HttpServlet {

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        String accion = "";

        if (request.getParameter("accion") != null) {
            accion = request.getParameter("accion");
            //li=listar, le=leer, nu=nuevo; re=registrar,
            //pr=presentar,ac=actualizar
            switch (accion) {
                case "li":
                    this.listarProveedores(request, response);
                    break;
                case "nu":
                    request.setAttribute("accion", "re");
                    this.presentarFormulario(request, response);
                    break;
                case "re":
                    this.registrarProveedor(request, response);
                    break;
                case "pr":
                    this.presentarProveedor(request, response);
                    break;
                case "ac":
                    this.actualizarProveedor(request, response);
                    break;
                case "eli":
                    this.eliminarProveedor(request, response);
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

    private void listarProveedores(HttpServletRequest request, HttpServletResponse response) {
        proveedorDAO dao = new proveedorDAO();
        List<Proveedor> provees = null;

        try {
            provees = dao.listar();
            request.setAttribute("proveedores", provees);
        } catch (Exception e) {
            request.setAttribute("msje", "No se pudo listar los proveedores" + e.getMessage());
        } finally {
            dao = null;
        }
        try {
            this.getServletConfig().getServletContext().getRequestDispatcher("/WEB-INF/Vista/Proveedores.jsp"
            ).forward(request, response);
        } catch (Exception e) {
            request.setAttribute("msje", "No se pudo realizar la operacion");
        }
    }

    private void presentarFormulario(HttpServletRequest request, HttpServletResponse response) {
        try {
            this.getServletConfig().getServletContext().
                    getRequestDispatcher("/WEB-INF/Vista/Proveedores.jsp"
                    ).forward(request, response);
        } catch (Exception e) {
            request.setAttribute("msje", "No se pudo realizar la operacion");
        }
    }

    private void registrarProveedor(HttpServletRequest request, HttpServletResponse response) {

        proveedorDAO daoProd;
        Proveedor emp = null;

        if (request.getParameter("txtRazonSocial") != null
                && request.getParameter("txtRUC") != null
                && request.getParameter("txtDireccion") != null
                && request.getParameter("txtTelefono") != null) {

            emp = new Proveedor();
            emp.setRazonSocial(request.getParameter("txtRazonSocial"));
            emp.setRuc(request.getParameter("txtRUC"));
            emp.setDireccion(request.getParameter("txtDireccion"));
            emp.setTelefono(request.getParameter("txtTelefono"));
            if (request.getParameter("chkVigencia") != null) {
                emp.setEstado(true);
            } else {
                emp.setEstado(false);
            }
            daoProd = new proveedorDAO();
            try {
                daoProd.registrar(emp);
                response.sendRedirect("Proveedor?accion=li");
            } catch (Exception e) {
                request.setAttribute("msje", "No se pudo registrar el proveedor" + e.getMessage());
                request.setAttribute("proveedor", emp);
                request.setAttribute("accion", "re");
                this.presentarFormulario(request, response);
            } finally {
            }
        }
    }

    private void presentarProveedor(HttpServletRequest request, HttpServletResponse response) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    private void actualizarProveedor(HttpServletRequest request, HttpServletResponse response) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    private void eliminarProveedor(HttpServletRequest request, HttpServletResponse response) {
        proveedorDAO dao;
        Proveedor emp;
        if (request.getParameter("cod") != null) {
            emp = new Proveedor();
            emp.setCodigo(Integer.parseInt(request.getParameter("cod")));
            dao = new proveedorDAO();
            try {
                dao.eliminar(emp);
                response.sendRedirect("Proveedor?accion=li");
            } catch (Exception e) {
                request.setAttribute("msje", "No se pudo eliminar el proveedor" + e.getMessage());
            }
        }    }

}
