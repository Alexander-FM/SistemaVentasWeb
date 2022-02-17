package Controlador;

import Modelo.Cliente;
import Modelo.clienteDAO;
import java.io.IOException;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet(name = "SrvCliente", urlPatterns = {"/Cliente"})
public class SrvCliente extends HttpServlet {

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        String accion = "";

        if (request.getParameter("accion") != null) {
            accion = request.getParameter("accion");
            //li=listar, le=leer, nu=nuevo; re=registrar,
            //pr=presentar,ac=actualizar
            switch (accion) {
                case "li":
                    this.listarCliente(request, response);
                    break;
                case "nu":
                    request.setAttribute("accion", "re");
                    this.presentarFormulario(request, response);
                    break;
                case "re":
                    this.registrarCliente(request, response);
                    break;
                case "pr":
                    this.presentarCliente(request, response);
                    break;
                case "ac":
                    this.actualizarCliente(request, response);
                    break;
                case "eli":
                    this.eliminarCliente(request, response);
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

    private void listarCliente(HttpServletRequest request, HttpServletResponse response) {
        clienteDAO dao = new clienteDAO();
        List<Cliente> clientes = null;

        try {
            clientes = dao.listar();
            request.setAttribute("clientes", clientes);
        } catch (Exception e) {
            request.setAttribute("msje", "No se pudo listar los clientes" + e.getMessage());
        } finally {
            dao = null;
        }
        try {
            this.getServletConfig().getServletContext().getRequestDispatcher("/WEB-INF/Vista/Clientes.jsp"
            ).forward(request, response);
        } catch (Exception e) {
            request.setAttribute("msje", "No se pudo realizar la operacion");
        }
    }

    private void presentarFormulario(HttpServletRequest request, HttpServletResponse response) {
        try {
            this.getServletConfig().getServletContext().
                    getRequestDispatcher("/WEB-INF/Vista/Clientes.jsp"
                    ).forward(request, response);
        } catch (Exception e) {
            request.setAttribute("msje", "No se pudo realizar la operacion");
        }
    }

    private void registrarCliente(HttpServletRequest request, HttpServletResponse response) {
        
        clienteDAO daoCli;
        Cliente cli = null;

        if (request.getParameter("txtNombres") != null
                && request.getParameter("cboTipoDoc") != null
                && request.getParameter("txtNumeroDoc") != null
                && request.getParameter("txtDepartamento") != null
                && request.getParameter("txtDistrito") != null
                && request.getParameter("txtProvincia") != null
                && request.getParameter("txtDireccion") != null
                && request.getParameter("txtCorreo") != null
                && request.getParameter("txtTelefono") != null) {

            cli = new Cliente();
            cli.setNombres(request.getParameter("txtNombres"));
            cli.setTipoDocumento(request.getParameter("cboTipoDoc"));
            cli.setNumeroDocumento(request.getParameter("txtNumeroDoc"));
            cli.setDepartamento(request.getParameter("txtDepartamento"));
            cli.setDistrito(request.getParameter("txtDistrito"));
            cli.setProvincia(request.getParameter("txtProvincia"));
            cli.setCalle(request.getParameter("txtDireccion"));
            cli.setEmail(request.getParameter("txtCorreo"));
            cli.setTelefono(request.getParameter("txtTelefono"));
            if (request.getParameter("chkVigencia") != null) {
                cli.setEstado(true);
            } else {
                cli.setEstado(false);
            }
            daoCli = new clienteDAO();
            try {
                daoCli.registrar(cli);
                response.sendRedirect("Cliente?accion=li");
            } catch (Exception e) {
                request.setAttribute("msje", "No se pudo registrar el cliente" + e.getMessage());
                request.setAttribute("cliente", cli);
                request.setAttribute("accion", "re");
                this.presentarFormulario(request, response);
            } finally {
            }
        }
    }

    private void presentarCliente(HttpServletRequest request, HttpServletResponse response) {
    }

    private void actualizarCliente(HttpServletRequest request, HttpServletResponse response) {
    }

    private void eliminarCliente(HttpServletRequest request, HttpServletResponse response) {
        clienteDAO dao;
        Cliente emp;
        if (request.getParameter("cod") != null) {
            emp = new Cliente();
            emp.setCodigo(Integer.parseInt(request.getParameter("cod")));
            dao = new clienteDAO();
            try {
                dao.eliminar(emp);
                response.sendRedirect("Cliente?accion=li");
            } catch (Exception e) {
                request.setAttribute("msje", "No se pudo eliminar el empleado" + e.getMessage());
            }
        }
    }

}
