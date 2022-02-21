package Controlador;

import Modelo.Empleado;
import Modelo.empleadoDAO;
import java.io.IOException;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet(name = "SrvEmpleado", urlPatterns = {"/Empleado"})
public class SrvEmpleado extends HttpServlet {

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        String accion = "";

        if (request.getParameter("accion") != null) {
            accion = request.getParameter("accion");
            //li=listar, le=leer, nu=nuevo; re=registrar,
            //pr=presentar,ac=actualizar
            switch (accion) {
                case "li":
                    this.listarEmpleado(request, response);
                    break;
                case "nu":
                    request.setAttribute("accion", "re");
                    this.presentarFormulario(request, response);
                    break;
                case "re":
                    this.registrarEmpledo(request, response);
                    break;
                case "pr":
                    this.presentarEmpleado(request, response);
                    break;
                case "ac":
                    this.actualizarEmpleado(request, response);
                    break;
                case "eli":
                    this.eliminarEmpleado(request, response);
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

    private void listarEmpleado(HttpServletRequest request, HttpServletResponse response) {
        empleadoDAO dao = new empleadoDAO();
        List<Empleado> empleados = null;

        try {
            empleados = dao.listar();
            request.setAttribute("empleados", empleados);
        } catch (Exception e) {
            request.setAttribute("msje", "No se pudo listar los empleados" + e.getMessage());
        } finally {
            dao = null;
        }
        try {
            this.getServletConfig().getServletContext().getRequestDispatcher("/WEB-INF/Vista/empleados.jsp"
            ).forward(request, response);
        } catch (Exception e) {
            request.setAttribute("msje", "No se pudo realizar la operacion");
        }
    }

    private void presentarFormulario(HttpServletRequest request, HttpServletResponse response) {
        try {
            this.getServletConfig().getServletContext().
                    getRequestDispatcher("/WEB-INF/Vista/empleados.jsp"
                    ).forward(request, response);
        } catch (Exception e) {
            request.setAttribute("msje", "No se pudo realizar la operacion");
        }
    }

    private void registrarEmpledo(HttpServletRequest request, HttpServletResponse response) {
        
        empleadoDAO daoProd;
        Empleado emp = null;

        if (request.getParameter("txtNombres") != null
                && request.getParameter("txtApellidos") != null
                && request.getParameter("cboTipoDoc") != null
                && request.getParameter("txtNumeroDoc") != null
                && request.getParameter("txtDireccion") != null
                && request.getParameter("txtCorreo") != null
                && request.getParameter("txtTelefono") != null
                && request.getParameter("cboCargo") != null
                && request.getParameter("txtFechaNac") != null
                && request.getParameter("txtUser") != null
                && request.getParameter("txtClave") != null) {

            emp = new Empleado();
            emp.setNombre(request.getParameter("txtNombres"));
            emp.setApellidos(request.getParameter("txtApellidos"));
            emp.setTipoDocumento(request.getParameter("cboTipoDoc"));
            emp.setNumeroDocumento(request.getParameter("txtNumeroDoc"));
            emp.setDireccion(request.getParameter("txtDireccion"));
            emp.setEmail(request.getParameter("txtCorreo"));
            emp.setTelefono(request.getParameter("txtTelefono"));
            emp.setCargo(request.getParameter("cboCargo"));
            emp.setFechaNacimiento(request.getParameter("txtFechaNac"));
            emp.setLogin(request.getParameter("txtUser"));
            emp.setClave(request.getParameter("txtClave"));
            if (request.getParameter("chkVigencia") != null) {
                emp.setEstado(true);
            } else {
                emp.setEstado(false);
            }
            daoProd = new empleadoDAO();
            try {
                daoProd.registrar(emp);
                response.sendRedirect("Empleado?accion=li");
            } catch (Exception e) {
                request.setAttribute("msje", "No se pudo registrar el empleado" + e.getMessage());
                request.setAttribute("empleado", emp);
                request.setAttribute("accion", "re");
                this.presentarFormulario(request, response);
            } finally {
            }
        }
    }

    private void presentarEmpleado(HttpServletRequest request, HttpServletResponse response) {
    }

    private void actualizarEmpleado(HttpServletRequest request, HttpServletResponse response) {
    }

    private void eliminarEmpleado(HttpServletRequest request, HttpServletResponse response) {
        empleadoDAO dao;
        Empleado emp;
        if (request.getParameter("cod") != null) {
            emp = new Empleado();
            emp.setCodigo(Integer.parseInt(request.getParameter("cod")));
            dao = new empleadoDAO();
            try {
                dao.eliminar(emp);
                response.sendRedirect("Empleado?accion=li");
            } catch (Exception e) {
                request.setAttribute("msje", "No se pudo eliminar el empleado" + e.getMessage());
            }
        }
    }

}
