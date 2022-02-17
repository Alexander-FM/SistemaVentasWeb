package Controlador;

import Modelo.Marca;
import Modelo.marcaDAO;
import java.io.IOException;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet(name = "SrvMarca", urlPatterns = {"/Marca"})
public class SrvMarca extends HttpServlet {

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        String accion = "";

        if (request.getParameter("accion") != null) {
            accion = request.getParameter("accion");
            //li=listar, le=leer, nu=nuevo; re=registrar,
            //pr=presentar,ac=actualizar
            switch (accion) {
                case "li":
                    this.listarMarcas(request, response);
                    break;
                case "nu":
                    request.setAttribute("accion", "re");
                    this.presentarFormulario(request, response);
                    break;
                case "re":
                    this.registrarMarca(request, response);
                    break;
                case "pr":
                    this.presentarMarca(request, response);
                    break;
                case "ac":
                    this.actualizarMarca(request, response);
                    break;
                case "eli":
                    this.eliminarMarca(request, response);
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

    private void listarMarcas(HttpServletRequest request, HttpServletResponse response) {
        marcaDAO dao = new marcaDAO();
        List<Marca> marcas = null;

        try {
            marcas = dao.listar();
            request.setAttribute("marcas", marcas);
        } catch (Exception e) {
            request.setAttribute("msje", "No se pudo listar las marcas");
        } finally {
            dao = null;
        }
        try {
            this.getServletConfig().getServletContext().getRequestDispatcher("/WEB-INF/Vista/Marcas.jsp"
            ).forward(request, response);
        } catch (Exception e) {
            request.setAttribute("msje", "No se pudo realizar la operacion");
        }
    }

    private void presentarFormulario(HttpServletRequest request, HttpServletResponse response) {
        try {
            this.getServletConfig().getServletContext().
                    getRequestDispatcher("/WEB-INF/Vista/Marcas.jsp").forward(request, response);
        } catch (Exception e) {
            request.setAttribute("msje", "No se pudo realizar la operacion");
        }
    }

    private void registrarMarca(HttpServletRequest request, HttpServletResponse response) {
        Marca mar = null;
        marcaDAO marDAO;
        if (request.getParameter("Marca") != null) {
            mar = new Marca();
            mar.setMarca(request.getParameter("Marca"));
            if (request.getParameter("chkVigencia") != null) {
                mar.setEstado(true);
            } else {
                mar.setEstado(false);
            }
            marDAO = new marcaDAO();
            try {
                marDAO.registrar(mar);
                response.sendRedirect("Marca?accion=li");
            } catch (Exception e) {
                request.setAttribute("msje", "No se pudo registrar la categoria" + e.getMessage());
                request.setAttribute("marca", mar);
                request.setAttribute("accion", "re");
                this.presentarFormulario(request, response);
            } finally {
            }
        }
    }

    private void presentarMarca(HttpServletRequest request, HttpServletResponse response) {
        marcaDAO dao;
        Marca mar;

        if (request.getParameter("cod") != null) {
            mar = new Marca();
            mar.setCodigo(Integer.parseInt(request.getParameter("cod")));

            dao = new marcaDAO();
            try {
                mar = dao.leer(mar);
                if (mar != null) {
                    request.setAttribute("marca", mar);
                } else {
                    request.setAttribute("msje", "No se encontró la categoria");
                }
            } catch (Exception e) {
                request.setAttribute("msje", "No se pudo acceder a la base de datos");
            }
        } else {
            request.setAttribute("msje", "No se tiene el parámetro necesario");
        }
        request.setAttribute("accion", "ac");
        this.presentarFormulario(request, response);
    }

    private void actualizarMarca(HttpServletRequest request, HttpServletResponse response) {
        marcaDAO marDAO;
        Marca mar = null;

        if (request.getParameter("hCodigo") != null
                && request.getParameter("Marca") != null) {

            mar = new Marca();
            mar.setCodigo(Integer.parseInt(request.getParameter("hCodigo")));//Parametro en el formulario
            mar.setMarca(request.getParameter("Marca"));
            if (request.getParameter("chkVigencia") != null) {
                mar.setEstado(true);
            } else {
                mar.setEstado(false);
            }
            marDAO = new marcaDAO();
            try {
                marDAO.actualizar(mar);
                response.sendRedirect("Marca?accion=li");
            } catch (Exception e) {
                request.setAttribute("msje", "No se pudo actualizar el producto");
                request.setAttribute("marca", mar);
                request.setAttribute("accion", "ac");
                this.presentarFormulario(request, response);
            } finally {
            }
        }
    }

    private void eliminarMarca(HttpServletRequest request, HttpServletResponse response) {
        marcaDAO dao;
        Marca mar;
        if (request.getParameter("cod") != null) {
            mar = new Marca();
            mar.setCodigo(Integer.parseInt(request.getParameter("cod")));
            dao = new marcaDAO();
            try {
                dao.eliminar(mar);
                response.sendRedirect("Marca?accion=li");
            } catch (Exception e) {
                request.setAttribute("msje", "No se pudo eliminar la marca");
            }
        }
    }

}
