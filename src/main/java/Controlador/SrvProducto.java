package Controlador;

import Modelo.Categoria;
import Modelo.Marca;
import Modelo.Producto;
import Modelo.Proveedor;
import Modelo.categoriaDAO;
import Modelo.marcaDAO;
import Modelo.productoDAO;
import Modelo.proveedorDAO;
import com.google.gson.Gson;
import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.sql.Date;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.Calendar;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;

@WebServlet(name = "SrvProducto", urlPatterns = {"/Producto"})
@MultipartConfig
public class SrvProducto extends HttpServlet {

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("application/json;charset=UTF-8");
        String accion = "";

        if (request.getParameter("accion") != null) {
            accion = request.getParameter("accion");
            //li=listar, le=leer, nu=nuevo; re=registrar,
            //pr=presentar,ac=actualizar
            switch (accion) {
                case "li":
                    this.listarProductos(response);
                    break;
                case "liCategorias":
                    this.listarCategorias(response);
                    break;
                case "liMarcas":
                    this.listarMarcas(response);
                    break;
                case "liProveedores":
                    this.listarProveedores(response);
                    break;
                case "re":
                    this.registrarProducto(request, response);
                    break;
                case "pr":
                    this.presentarProducto(request, response);
                    break;
                case "ac":
                    this.actualizarProducto(request, response);
                    break;
                case "eli":
                    this.eliminarProducto(request, response);
                    break;
            }
        } else {
            request.setAttribute("msje", "No se indic?? la operaci??n a realizar");
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

    private void listarProductos(HttpServletResponse response) throws IOException {
        PrintWriter out = response.getWriter();
        try {
            productoDAO dao = new productoDAO();
            List<Producto> pro = dao.listar();
            Gson gson = new Gson();
            String json = gson.toJson(pro);
            out.print(json);
        } catch (Exception e) {
            this.printError(e.getMessage(), response);
        }
    }

    private void listarCategorias(HttpServletResponse response) throws IOException {
        PrintWriter out = response.getWriter();
        try {
            categoriaDAO dao = new categoriaDAO();
            List<Categoria> cat = dao.listar();
            Gson gson = new Gson();
            String json = gson.toJson(cat);
            out.print(json);
        } catch (Exception e) {
            this.printError(e.getMessage(), response);
        }
    }

    private void listarMarcas(HttpServletResponse response) throws IOException {
        PrintWriter out = response.getWriter();
        try {
            marcaDAO dao = new marcaDAO();
            List<Marca> mar = dao.listar();
            Gson gson = new Gson();
            String json = gson.toJson(mar);
            out.print(json);
        } catch (Exception e) {
            this.printError(e.getMessage(), response);
        }
    }

    private void listarProveedores(HttpServletResponse response) throws IOException {
        PrintWriter out = response.getWriter();
        try {
            proveedorDAO dao = new proveedorDAO();
            List<Proveedor> prov = dao.listarProveedoresActivos();
            Gson gson = new Gson();
            String json = gson.toJson(prov);
            out.print(json);
        } catch (Exception e) {
            this.printError(e.getMessage(), response);
        }
    }

    private void registrarProducto(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        productoDAO daoProd;
        Producto prod = null;
        if (request.getParameter("codigoProducto") != null
                && request.getParameter("producto") != null
                && request.getParameter("descripcion") != null
                && request.getParameter("precioVenta") != null
                && request.getParameter("precioCompra") != null
                && request.getParameter("stock") != null
                && request.getParameter("cboCategoria") != null
                && request.getParameter("cboMarca") != null
                && request.getParameter("cboProveedor") != null) {

            prod = new Producto();
            prod.setCodigoProducto(request.getParameter("codigoProducto"));
            prod.setProducto(request.getParameter("producto"));
            prod.setDescripcion(request.getParameter("descripcion"));
            prod.setPrecioVenta(Double.parseDouble(request.getParameter("precioVenta")));
            prod.setPrecioCompra(Double.parseDouble(request.getParameter("precioCompra")));
            prod.setStock(Integer.parseInt(request.getParameter("stock")));
            prod.setCategoria(new Categoria());
            prod.getCategoria().setCodigo(Integer.parseInt(request.getParameter("cboCategoria")));
            prod.setMarca(new Marca());
            prod.getMarca().setCodigo(Integer.parseInt(request.getParameter("cboMarca")));
            prod.setProveedor(new Proveedor());
            prod.getProveedor().setCodigo(Integer.parseInt(request.getParameter("cboProveedor")));
            prod.setFechaRegistro(Date.valueOf(LocalDate.now()));
            //Guardar Imagen
            Part part = request.getPart("imagen");//Nombre de nuestro input de tipo file.
            String nombreArchivo = Paths.get(part.getSubmittedFileName()).getFileName().toString(); //Conseguir el nombre del archivo
            //Ruta donde se guarda la imagen
            File file = new File("C:\\SistemaVentasWeb\\src\\main\\webapp\\imgProducts\\" + nombreArchivo);
            Files.copy(part.getInputStream(), file.toPath(), StandardCopyOption.REPLACE_EXISTING);
            prod.setImagen("http://localhost:8080/SistemaVentasWeb/imgProducts/" + nombreArchivo);
            if (request.getParameter("chkVigencia") != null) {
                prod.setEstado(true);
            } else {
                prod.setEstado(false);
            }
            daoProd = new productoDAO();
            try {
                daoProd.registrar(prod);
                response.sendRedirect("vista/productos.jsp");
            } catch (Exception e) {
                request.setAttribute("msje", "No se pudo registrar el producto" + e.getMessage());
            }
        } else {
            request.setAttribute("msje", "Complete los campos");
        }
    }

    private void presentarProducto(HttpServletRequest request, HttpServletResponse response) throws IOException {
        productoDAO dao;
        Producto pro;
        if (request.getParameter("id") != null) {
            pro = new Producto();
            pro.setCodigo(Integer.parseInt(request.getParameter("id")));
            try {
                dao = new productoDAO();
                pro = dao.leer(pro);
                String json = new Gson().toJson(pro);
                response.getWriter().print(json);
            } catch (Exception e) {
                this.printMessage(e.getMessage(), false, response);
            }
        } else {
            this.printMessage("No se tiene el parametro del producto", false, response);
        }
    }

    private void actualizarProducto(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        productoDAO daoProd;
        Producto prod = null;
        if (request.getParameter("codigoProductoAc") != null
                && request.getParameter("productoAc") != null
                && request.getParameter("descripcionAc") != null
                && request.getParameter("precioVentaAc") != null
                && request.getParameter("precioCompraAc") != null
                && request.getParameter("stockAc") != null
                && request.getParameter("cboCategoriaAc") != null
                && request.getParameter("cboMarcaAc") != null
                && request.getParameter("cboProveedorAc") != null) {

            prod = new Producto();
            prod.setCodigo(Integer.parseInt(request.getParameter("idProd")));
            prod.setCodigoProducto(request.getParameter("codigoProductoAc"));
            prod.setProducto(request.getParameter("productoAc"));
            prod.setDescripcion(request.getParameter("descripcionAc"));
            prod.setFechaRegistro(Date.valueOf(request.getParameter("fechaProd")));
            prod.setPrecioVenta(Double.parseDouble(request.getParameter("precioVentaAc")));
            prod.setPrecioCompra(Double.parseDouble(request.getParameter("precioCompraAc")));
            prod.setStock(Integer.parseInt(request.getParameter("stockAc")));
            prod.setCategoria(new Categoria());
            prod.getCategoria().setCodigo(Integer.parseInt(request.getParameter("cboCategoriaAc")));
            prod.setMarca(new Marca());
            prod.getMarca().setCodigo(Integer.parseInt(request.getParameter("cboMarcaAc")));
            prod.setProveedor(new Proveedor());
            prod.getProveedor().setCodigo(Integer.parseInt(request.getParameter("cboProveedorAc")));
            //Actualizar Imagen
            if (request.getParameter("chkConservarImagen") == null) {
                Part part = request.getPart("imagenAc");//Nombre de nuestro input de tipo file.
                String nombreArchivo = Paths.get(part.getSubmittedFileName()).getFileName().toString(); //Conseguir el nombre del archivo
                //Ruta donde se guarda la imagen
                File file = new File("C:\\SistemaVentasWeb\\src\\main\\webapp\\imgProducts\\" + nombreArchivo);
                Files.copy(part.getInputStream(), file.toPath(), StandardCopyOption.REPLACE_EXISTING);
                prod.setImagen("http://localhost:8080/SistemaVentasWeb/imgProducts/" + nombreArchivo);
            } else {
                /** En caso de que no se actualice la imagen del producto, setearemos la imagen por defecto*/
                prod.setImagen(request.getParameter("txtConservarImagen"));

            }
            if (request.getParameter("chkVigenciaAc") != null) {
                prod.setEstado(true);
            } else {
                prod.setEstado(false);
            }
            daoProd = new productoDAO();
            try {
                daoProd.actualizar(prod);
                response.sendRedirect("vista/productos.jsp");
            } catch (Exception e) {
                request.setAttribute("msje", "No se pudo registrar el producto" + e.getMessage());
            }
        } else {
            request.setAttribute("msje", "Complete los campos");
        }
    }

    private void eliminarProducto(HttpServletRequest request, HttpServletResponse response) {
        productoDAO dao;
        Producto prod;
        if (request.getParameter("cod") != null) {
            prod = new Producto();
            prod.setCodigo(Integer.parseInt(request.getParameter("cod")));
            dao = new productoDAO();
            try {
                dao.eliminar(prod);
                printMessage("Producto eliminado correctamente", true, response);
            } catch (Exception e) {
                request.setAttribute("msje", "No se pudo eliminar el producto" + e.getMessage());
            }
        }
    }

}
