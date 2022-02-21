package Controlador;

import Modelo.Categoria;
import Modelo.categoriaDAO;
import java.io.IOException;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet(name = "SrvCategoria", urlPatterns = {"/Categoria"})
public class SrvCategoria extends HttpServlet {

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String accion = "";

        if (request.getParameter("accion") != null) {
            accion = request.getParameter("accion");
            //li=listar, le=leer, nu=nuevo; re=registrar,
            //pr=presentar,ac=actualizar
            switch (accion) {
                case "li":
                    this.listarCategorias(request, response);
                    break;
                case "nu":
                    request.setAttribute("accion", "re");
                    this.presentarFormulario(request, response);
                    break;
                case "re":
                    this.registrarCategoria(request, response);
                    break;
                case "pr":
                    this.presentarCategoria(request, response);
                    break;
                case "ac":
                    this.actualizarCategoria(request, response);
                    break;
                case "eli":
                    this.eliminarCategoria(request, response);
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

    private void listarCategorias(HttpServletRequest request, HttpServletResponse response) {
        categoriaDAO dao = new categoriaDAO();
        List<Categoria> categorias = null;

        try {
            categorias = dao.listar();
            request.setAttribute("categorias", categorias);
        } catch (Exception e) {
            request.setAttribute("msje", "No se pudo listar los productos");
        } finally {
            dao = null;
        }
        try {
            this.getServletConfig().getServletContext().getRequestDispatcher("/vista/categorias.jsp"
            ).forward(request, response);
        } catch (Exception e) {
            request.setAttribute("msje", "No se pudo realizar la operacion");
        }
    }

    private void presentarFormulario(HttpServletRequest request, HttpServletResponse response) {
        try {
            this.getServletConfig().getServletContext().
                    getRequestDispatcher("/WEB-INF/Vista/categorias.jsp").forward(request, response);
        } catch (Exception e) {
            request.setAttribute("msje", "No se pudo realizar la operacion");
        }
    }

    private void registrarCategoria(HttpServletRequest request, HttpServletResponse response) {
        Categoria cate = null;
        categoriaDAO cateDAO;
        if (request.getParameter("Categoria") != null) {
            cate = new Categoria();
            cate.setCategoria(request.getParameter("Categoria"));
            if (request.getParameter("chkVigencia") != null) {
                cate.setEstado(true);
            } else {
                cate.setEstado(false);
            }
            cateDAO = new categoriaDAO();
            try {
                cateDAO.registrar(cate);
                response.sendRedirect("Categoria?accion=li");
            } catch (Exception e) {
                request.setAttribute("msje", "No se pudo registrar la categoria" + e.getMessage());
                request.setAttribute("categoria", cate);
                request.setAttribute("accion", "re");
                this.presentarFormulario(request, response);
            } finally {
            }
        }
    }

    private void presentarCategoria(HttpServletRequest request, HttpServletResponse response) {
        categoriaDAO dao;
        Categoria cate;

        if (request.getParameter("cod") != null) {
            cate = new Categoria();
            cate.setCodigo(Integer.parseInt(request.getParameter("cod")));

            dao = new categoriaDAO();
            try {
                cate = dao.leer(cate);
                if (cate != null) {
                    request.setAttribute("categoria", cate);
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

    private void actualizarCategoria(HttpServletRequest request, HttpServletResponse response) {
        categoriaDAO cateDAO;
        Categoria cate = null;

        if (request.getParameter("hCodigo") != null
                && request.getParameter("Categoria") != null) {

            cate = new Categoria();
            cate.setCodigo(Integer.parseInt(request.getParameter("hCodigo")));//Parametro en el formulario
            cate.setCategoria(request.getParameter("Categoria"));
            if (request.getParameter("chkVigencia") != null) {
                cate.setEstado(true);
            } else {
                cate.setEstado(false);
            }
            cateDAO = new categoriaDAO();
            try {
                cateDAO.actualizar(cate);
                response.sendRedirect("Categoria?accion=li");
            } catch (Exception e) {
                request.setAttribute("msje", "No se pudo actualizar el producto");
                request.setAttribute("categoria", cate);
                request.setAttribute("accion", "ac");
                this.presentarFormulario(request, response);
            } finally {
            }
        }
    }

    private void eliminarCategoria(HttpServletRequest request, HttpServletResponse response) {
        categoriaDAO dao;
        Categoria cat;
        if (request.getParameter("cod") != null) {
            cat = new Categoria();
            cat.setCodigo(Integer.parseInt(request.getParameter("cod")));
            dao = new categoriaDAO();
            try {
                dao.eliminar(cat);
                response.sendRedirect("Categoria?accion=li");
            } catch (Exception e) {
                request.setAttribute("msje", "No se pudo eliminar la categoria");
            }           
        }
    }

}
