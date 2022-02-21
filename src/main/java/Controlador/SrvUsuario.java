package Controlador;

import Modelo.Empleado;
import Modelo.empleadoDAO;
import com.google.gson.Gson;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet(name = "SrvUsuario", urlPatterns = {"/usuarios"})
public class SrvUsuario extends HttpServlet {

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("application/json;charset=UTF-8");
        String accion = request.getParameter("accion");
        switch (accion) {
            case "ve":
                this.verificarIdentidad(request, response);
                break;
            case "ce":
                this.cerrarSesion(request, response);
                break;
            case "solicitarCambioContrasenia":
                this.solicitarCambioContrasenia(request, response);
                break;
            case "cambiarContrasenia":
                this.cambiarContrasenia(request, response);
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

    private void printMessage(String msj, boolean rpt, HttpServletResponse response) throws IOException {
        response.getWriter().print("{\"rpt\": " + rpt + ", \"msj\": \"" + msj + "\"}");
    }

    private void verificarIdentidad(HttpServletRequest request, HttpServletResponse response) throws IOException {
        if (request.getParameter("datos") != null) {
            Gson gson = new Gson();
            Empleado emp = gson.fromJson(request.getParameter("datos"), Empleado.class);
            empleadoDAO dao = new empleadoDAO();
            try {
                emp = dao.identificar(emp);
                if (emp == null) {
                    this.printMessage("Usuario y/o contraseña incorrectas o credenciales no válidas", false, response);
                } else {
                    if (!emp.isEstado()) {
                        this.printMessage("Empleado desactivado. Para más información comuníquese "
                                + "con el administrador", false, response);
                    } else {
                        request.getSession().setAttribute("usuario", emp);
                        this.printMessage("Acceso permitido", true, response);
                    }
                }
            } catch (Exception e) {
                this.printMessage(e.getMessage(), false, response);
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

    private void solicitarCambioContrasenia(HttpServletRequest request, HttpServletResponse response) throws IOException {
        empleadoDAO dao;
        Empleado emp;
        Map<String, Object> map = new HashMap<>();
        if (request.getParameter("login") != null) {
            emp = new Empleado();
            emp.setLogin(request.getParameter("login"));
            try {
                dao = new empleadoDAO();
                emp = dao.solicitarCambioContrasenia(emp);
                if (emp != null) {
                    map.put("rpta", true);
                    map.put("msje", "Empleado encontrado correctamente");
                    map.put("body", emp);
                } else {
                    map.put("rpta", false);
                    map.put("msje", "Lo sentimos el empleado no fue encontrado");
                    map.put("body", null);
                }
                String json = new Gson().toJson(map);
                response.getWriter().print(json);
            } catch (Exception e) {
                this.printMessage(e.getMessage(), false, response);
            }
        } else {
            this.printMessage("No se tiene el parámetro del empleado", false, response);
        }
    }

    private void cambiarContrasenia(HttpServletRequest request, HttpServletResponse response) throws IOException {
        if (request.getParameter("login") != null
                && request.getParameter("clave") != null) {
            Empleado emp = new Empleado();
            emp.setLogin(request.getParameter("login"));
            emp.setClave(request.getParameter("clave"));
            try {
                empleadoDAO dao = new empleadoDAO();
                dao.cambiarContrasenia(emp);
                this.printMessage("Datos del empleado actualizado correctamente", true, response);
            } catch (Exception e) {
                this.printMessage(e.getMessage(), false, response);
            }
        } else {
            this.printMessage("Rellene el formulario", false, response);
        }
    }

}
