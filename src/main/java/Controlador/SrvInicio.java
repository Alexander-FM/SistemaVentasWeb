package Controlador;

import Modelo.Producto;
import Modelo.panelAdministrativoDAO;
import com.google.gson.Gson;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @author <a href="">Luigui Alexander Fuentes Medina</a>
 */
@WebServlet(name = "SrvInicio", urlPatterns = {"/panelAdministrativo"})
public class SrvInicio extends HttpServlet {

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("application/json;charset=UTF-8");

        if (request.getParameter("accion") != null) {
            String accion = request.getParameter("accion");
            switch (accion) {
                case "top10ProductosConStockBajo":
                    this.top10ProductosConStockBajo(response);
                    break;
                case "top10UltimosProductosRegistrados":
                    this.top10UltimosProductosRegistrados(response);
                    break;
                case "totalVentasRealizadas":
                    this.totalVentasRealizadas(response);
                    break;
                case "totalComprasRealizadas":
                    this.totalComprasRealizadas(response);
                    break;
                case "totalClientesRegistrados":
                    this.totalClientesRegistrados(response);
                    break;
                case "montoTotalEnCaja":
                    this.montoTotalEnCaja(response);
                    break;
            }
        } else {
            this.printError("No se indico la operacion a realizar", response);
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

    private void printError(String msjError, HttpServletResponse response) throws IOException {
        response.getWriter().print("{\"msj\": \"" + msjError + "\"}");
    }

    private void printMessage(String msj, boolean rpt, HttpServletResponse response) throws IOException {
        response.getWriter().print("{\"rpt\": " + rpt + ", \"msj\": \"" + msj + "\"}");
    }

    /**
     * Devuelve una lista con los 10 productos con bajo stock en formato JSON
     *
     * @param request
     * @param response
     * @throws IOException
     */
    private void top10ProductosConStockBajo(HttpServletResponse response) throws IOException {
        PrintWriter out = response.getWriter();
        try {
            panelAdministrativoDAO dao = new panelAdministrativoDAO();
            List<Producto> pro = dao.listar10ProductosConStockMinimo();
            Gson gson = new Gson();
            String json = gson.toJson(pro);
            out.print(json);
        } catch (Exception e) {
            this.printError(e.getMessage(), response);
        }
    }

    /**
     * Devuelve una lista de los 10 ultimos productos registrados
     *
     * @param request
     * @param response
     * @throws IOException
     */
    private void top10UltimosProductosRegistrados(HttpServletResponse response) throws IOException {
        PrintWriter out = response.getWriter();
        try {
            panelAdministrativoDAO dao = new panelAdministrativoDAO();
            List<Producto> pro = dao.listar10UltimosProductos();
            Gson gson = new Gson();
            String json = gson.toJson(pro);
            out.print(json);
        } catch (Exception e) {
            this.printError(e.getMessage(), response);
        }
    }

    /**
     * Devuelve el total de las ventas realizadas
     *
     * @param request
     * @param response
     * @throws IOException
     */
    private void totalVentasRealizadas(HttpServletResponse response) throws IOException {
        PrintWriter out = response.getWriter();
        try {
            panelAdministrativoDAO dao = new panelAdministrativoDAO();
            int totalVentas = dao.totalVentas();
            Gson gson = new Gson();
            String json = gson.toJson(totalVentas);
            out.print(json);
        } catch (Exception e) {
            this.printError(e.getMessage(), response);
        }
    }

    /**
     * Devuelve el total de las compras realizadas
     *
     * @param response
     * @throws IOException
     */
    private void totalComprasRealizadas(HttpServletResponse response) throws IOException {
        PrintWriter out = response.getWriter();
        try {
            panelAdministrativoDAO dao = new panelAdministrativoDAO();
            int totalCompras = dao.totalCompras();
            Gson gson = new Gson();
            String json = gson.toJson(totalCompras);
            out.print(json);
        } catch (Exception e) {
            this.printError(e.getMessage(), response);
        }
    }

    /**
     * Devuelve el total de Clientes Registrados
     *
     * @param response
     * @throws IOException
     */
    private void totalClientesRegistrados(HttpServletResponse response) throws IOException {
        PrintWriter out = response.getWriter();
        try {
            panelAdministrativoDAO dao = new panelAdministrativoDAO();
            int totalClientes = dao.totalClientes();
            Gson gson = new Gson();
            String json = gson.toJson(totalClientes);
            out.print(json);
        } catch (Exception e) {
            this.printError(e.getMessage(), response);
        }
    }

    /**
     * Devuelve el monto total que hay en caja actualmente.
     *
     * @param response
     * @throws IOException
     */
    private void montoTotalEnCaja(HttpServletResponse response) throws IOException {
        PrintWriter out = response.getWriter();
        try {
            panelAdministrativoDAO dao = new panelAdministrativoDAO();
            double montoCaja = dao.totalCaja();
            Gson gson = new Gson();
            String json = gson.toJson(montoCaja);
            out.print(json);
        } catch (Exception e) {
            this.printError(e.getMessage(), response);
        }
    }

}
