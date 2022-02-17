package Controlador;

import Modelo.Empleado;
import Modelo.empleadoDAO;
import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet(name = "SrvUsuario", urlPatterns = {"/usuarios"})
public class SrvUsuario extends HttpServlet {

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String accion = request.getParameter("accion");
        switch (accion) {
            case "ve":
                this.verificarIdentidad(request, response);
                break;
            case "ce":
                this.cerrarSesion(request, response);
                break;
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

    private void verificarIdentidad(HttpServletRequest request, HttpServletResponse response) {
        empleadoDAO dao;
        Empleado emp;

        if (request.getParameter("txtLogin") != null
                && request.getParameter("txtClave") != null) {

            emp = new Empleado();
            emp.setLogin(request.getParameter("txtLogin"));
            emp.setClave(request.getParameter("txtClave"));

            dao = new empleadoDAO();
            try {
                emp = dao.identificar(emp);
                if (emp != null) {
                    request.getSession().setAttribute("usuario", emp);
                    request.setAttribute("usuario", emp);
                    
                    this.getServletConfig().getServletContext().
                            getRequestDispatcher("/WEB-INF/Vista/Inicio.jsp").forward(request, response);
                } else {                   
                    request.setAttribute("msje", "Credenciales no válidas");
                    this.getServletConfig().getServletContext().getRequestDispatcher("/index.jsp").forward(request, response);
                }
            } catch (Exception e) {
                request.setAttribute("usuario", emp);
                request.setAttribute("msje", e.getMessage());
                try {
                    this.getServletConfig().getServletContext().getRequestDispatcher("/index.jsp").forward(request, response);
                } catch (Exception ex) {
                }
            }
        }
    }

    private void cerrarSesion(HttpServletRequest request, HttpServletResponse response) {
        request.getSession().setAttribute("usuario", null);
        request.getSession().removeAttribute("usuario");
        request.getSession().invalidate();
        try {
            response.sendRedirect("index.jsp");
        } catch (Exception e) {
        }
    }

}
