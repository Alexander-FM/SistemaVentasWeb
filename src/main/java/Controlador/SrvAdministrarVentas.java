package Controlador;

import Modelo.Producto;
import Modelo.Venta;
import Modelo.productoDAO;
import Modelo.ventaDAO;
import com.google.gson.Gson;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet(name = "SrvAdministrarVentas", urlPatterns = {"/AdministrarVentas"})
public class SrvAdministrarVentas extends HttpServlet {
  protected void processRequest(HttpServletRequest request, HttpServletResponse response)
      throws ServletException, IOException {
    response.setContentType("application/json;charset=UTF-8");
    String accion = "";

    if (request.getParameter("accion") != null) {
      accion = request.getParameter("accion");
      //li=listar, le=leer, nu=nuevo; re=registrar,
      //pr=presentar,ac=actualizar
      switch (accion) {
        case "listarVentas":
          this.listarVentas(response);
          break;
      }
    } else {
      request.setAttribute("msje", "No se indicó la operación a realizar");
      this.getServletConfig().getServletContext().getRequestDispatcher("/vista/error.jsp"
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

  private void printError(String msjError, HttpServletResponse response) throws IOException {
    response.getWriter().print("{\"msj\": \"" + msjError + "\"}");
  }
  private void listarVentas(HttpServletResponse response) throws IOException {
    PrintWriter out = response.getWriter();
    try {
      ventaDAO dao = new ventaDAO();
      List<Venta> ven = dao.listarVentas();
      Gson gson = new Gson();
      String json = gson.toJson(ven);
      out.print(json);
    } catch (Exception e) {
      this.printError(e.getMessage(), response);
    }
  }
}
