package Controlador;

import Modelo.Compra;
import Modelo.DetalleVenta;
import Modelo.Producto;
import Modelo.ProductoBoleta;
import Modelo.Venta;
import Modelo.productoDAO;
import Modelo.ventaDAO;
import com.google.gson.Gson;
import java.io.IOException;
import java.io.PrintWriter;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@WebServlet(name = "SrvAdministrarVentas", urlPatterns = {"/AdministrarVentas"})
public class SrvAdministrarVentas extends HttpServlet {
    DecimalFormat df = new DecimalFormat("#.00");
    List<ProductoBoleta> listaProductoBoleta = new ArrayList<>();

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("application/json;charset=UTF-8");
        String accion = "";

        if (request.getParameter("accion") != null) {
            accion = request.getParameter("accion");
            switch (accion) {
                case "listarVentas":
                    this.listarVentas(response);
                    break;
                case "listarDetalleVenta":
                    this.listarDetalleVentas(response, request);
                    break;
                case "anularVenta":
                    this.anularVenta(request, response);
                    break;
                case "listarProductoConStock":
                    this.listarProductoConStock(response);
                    break;
                case "agregarProductoBoleta":
                    this.agregarProductoBoleta(request, response);
                    break;
                case "eliminarProductoBoleta":
                    this.eliminarProductoBoleta(request, response);
                    break;
                case "actualizarCantidad":
                    this.actualizarCantidad(request);
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

    private void printMessage(String msj, boolean rpt, HttpServletResponse response) throws IOException {
        response.getWriter().print("{\"rpt\": " + rpt + ", \"msj\": \"" + msj + "\"}");
    }

    /**
     * Este método lista todas las ventas registradas en la base de datos
     *
     * @param response
     * @throws IOException
     */
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

    /**
     * Este método retorna una lista con el detalle de una venta
     *
     * @param response
     * @param request
     * @throws IOException
     */
    private void listarDetalleVentas(HttpServletResponse response, HttpServletRequest request) throws IOException {
        ventaDAO dao;
        Venta vent;
        List<DetalleVenta> detalles;
        if (request.getParameter("idVenta") != null) {
            vent = new Venta();
            vent.setIdVenta(Integer.parseInt(request.getParameter("idVenta")));
            try {
                dao = new ventaDAO();
                detalles = dao.listarDetallesVentas(vent);
                String json = new Gson().toJson(detalles);
                response.getWriter().print(json);
            } catch (Exception e) {
                this.printMessage(e.getMessage(), false, response);
            }
        } else {
            this.printMessage("No se tiene el parametro de la ventra", false, response);
        }
    }

    /**
     * Este método anular la venta y no sé podrá revertir, además actualizará el
     * stock de los productos por ejemplo: si el producto que ordeno tuvo como
     * stock 10, y la en la venta se llevaron 3 de ese producto, entonces, esa
     * cantidad regresará al stock de los productos.
     *
     * @param request
     * @param response
     */
    private void anularVenta(HttpServletRequest request, HttpServletResponse response) throws IOException {
        Venta venta;
        DetalleVenta detalleVenta;
        if (request.getParameter("id") != null
                && request.getParameter("cantProd") != null
                && request.getParameter("idProd") != null) {
            venta = new Venta();
            venta.setIdVenta(Integer.parseInt(request.getParameter("id")));
            detalleVenta = new DetalleVenta();
            detalleVenta.setCantidad(Integer.parseInt(request.getParameter("cantProd")));
            detalleVenta.setProducto(new Producto());
            detalleVenta.getProducto().setCodigo(Integer.parseInt(request.getParameter("idProd")));
            try {
                ventaDAO dao = new ventaDAO();
                dao.anularVenta(venta);
                dao.actualizarStockProducto(detalleVenta);
                this.printMessage("La anulación de la venta se realizó correctamente, y se actualizó el stock del producto", true, response);
            } catch (Exception e) {
                this.printMessage(e.getMessage(), false, response);
            }
        } else {
            this.printMessage("No se obtuvo los parámetros", false, response);
        }
    }

    private void listarProductoConStock(HttpServletResponse response) throws IOException {
        PrintWriter out = response.getWriter();
        try {
            ventaDAO dao = new ventaDAO();
            List<Producto> pro = dao.listarProductosConStock();
            Gson gson = new Gson();
            String json = gson.toJson(pro);
            out.print(json);
        } catch (Exception e) {
            this.printError(e.getMessage(), response);
        }
    }

    private void agregarProductoBoleta(HttpServletRequest request, HttpServletResponse response) {
        ventaDAO dao;
        int cantidad = 1;
        int pos = 0;
        double total = 0.0;
        int id = Integer.parseInt(request.getParameter("cod"));

        try {
            dao = new ventaDAO();
            if (listaProductoBoleta.size() > 0) {
                for (int i = 0; i < listaProductoBoleta.size(); i++) {
                    if (listaProductoBoleta.get(i).getId() == id) {
                        pos = i;
                    }
                }
                if (listaProductoBoleta.get(pos).getId() == id) {
                    cantidad = listaProductoBoleta.get(pos).getCant() + cantidad;
                    total = listaProductoBoleta.get(pos).getPrecioVenta() * cantidad;
                    listaProductoBoleta.get(pos).setCant(cantidad);
                    listaProductoBoleta.get(pos).setPrecioTotal(total);
                } else {
                    Producto p = dao.listarById(id);
                    ProductoBoleta pb = new ProductoBoleta();
                    pb.setId(id);
                    pb.setProducto(p.getProducto());
                    pb.setCant(cantidad);
                    pb.setPrecioVenta(p.getPrecioVenta());
                    total = cantidad * p.getPrecioVenta();
                    pb.setPrecioTotal(total);
                    listaProductoBoleta.add(pb);
                }
            } else {
                Producto p = dao.listarById(id);
                ProductoBoleta pb = new ProductoBoleta();
                pb.setId(id);
                pb.setProducto(p.getProducto());
                pb.setCant(cantidad);
                pb.setPrecioVenta(p.getPrecioVenta());
                total = cantidad * p.getPrecioVenta();
                pb.setPrecioTotal(total);
                listaProductoBoleta.add(pb);
            }
            listarBoleta(request);
            response.sendRedirect("/SistemaVentasWeb/vista/ventas.jsp");
        } catch (Exception e) {
            System.out.println("No se pudo agregar el producto al carrito: " + e.getLocalizedMessage());
        }

    }

    private void listarBoleta(HttpServletRequest request) {
        HttpSession session = request.getSession();
        double subTotal = 0.0;
        double igv = 0.0;
        double totalPagar;
        for (int i = 0; i < listaProductoBoleta.size(); i++) {
            subTotal = subTotal + listaProductoBoleta.get(i).getPrecioTotal();
        }
        //igv = subTotal * 0.18;
        subTotal = subTotal - igv;
        totalPagar = subTotal;
        session.setAttribute("subTotal", df.format(subTotal));
        session.setAttribute("IGV", df.format(igv));
        session.setAttribute("totalPagar", df.format(totalPagar));
        session.setAttribute("listaProductosBoleta", listaProductoBoleta);
    }

    private void eliminarProductoBoleta(HttpServletRequest request, HttpServletResponse response) throws IOException {
        int id = Integer.parseInt(request.getParameter("id"));
        for (int i = 0; i < listaProductoBoleta.size(); i++) {
            if (listaProductoBoleta.get(i).getId() == id) {
                listaProductoBoleta.remove(i);
            }
        }
        listarBoleta(request);
        response.sendRedirect("/SistemaVentasWeb/vista/ventas.jsp");
    }

    private void actualizarCantidad(HttpServletRequest request) {
        String[] arreglo;
        arreglo = request.getParameterValues("arreglo[]");
        int id = Integer.parseInt(arreglo[0]);
        int cant = Integer.parseInt(arreglo[1]);
        for (int i = 0; i < listaProductoBoleta.size(); i++) {
            if (listaProductoBoleta.get(i).getId() == id) {
                listaProductoBoleta.get(i).setCant(cant);
                double subTotal = listaProductoBoleta.get(i).getPrecioVenta() * cant;
                listaProductoBoleta.get(i).setPrecioTotal(subTotal);
            }
        }
    }
}
