<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@page session="true"%>
<%
    if (session.getAttribute("usuario") != null) {
%>
<html>
    <head>
        <%@include file="plus/head.jsp" %>
    </head>
    <body class="hold-transition skin-blue sidebar-mini">
        <div class="wrapper">
            <%@include file="plus/nav.jsp" %>
            <%@include file="plus/aside.jsp" %>
            <!-- Content Wrapper. Contains page content -->
            <div class="content-wrapper">
                <section class="content-header">                   
                    <h1>Nueva Venta</h1>
                    <ol class="breadcrumb">
                        <li><a href="#"><i class="fa fa-dashboard"></i> Ventas</a></li>
                        <li class="active">Nueva Venta</li>
                    </ol>
                </section>
                <section class="content">
                    <!-- Modal para buscar los producto y agregarlos al carrito -->
                    <div class="modal fade" id="buscarproductos">
                        <div class="modal-dialog modal-lg">
                            <div class="modal-content" style="border-radius: 15px">
                                <div class="modal-header">
                                    <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                                        <span aria-hidden="true">&times;</span></button>
                                    <h4 class="modal-title">Buscar Productos</h4>
                                </div>
                                <div class="modal-body">
                                    <div class="table-responsive" >  
                                        <table class="table table table-borderless" id="tablaBuscarProductos" data-page-length='04'>
                                            <thead>
                                                <tr class="success">                                        
                                                    <th class="text-center">Producto</th>                                            
                                                    <th class="text-center">Imagen</th>                                            
                                                    <th class="text-center">Stock</th>
                                                    <th class="text-center">Precio</th>
                                                    <th class="text-center">Marca</th> 
                                                    <th class="text-center">Accion</th>  
                                                </tr>
                                            </thead>
                                            <tbody>
                                                
                                            </tbody>                             
                                        </table>
                                    </div> 
                                </div>                             
                            </div>
                        </div>
                    </div>
                    <!-- ./ Modal para buscar los productos y agregarlos al carrito -->
                    <div class="row">
                        <div class="col-lg-8 col-xs-12">
                            <div class="box box-primary">
                                <div class="box-header with-border">
                                    <h3 class="box-title">Datos de la Venta</h3>
                                    <div class="box-tools pull-right">
                                        <button type="button" class="btn btn-box-tool" data-widget="collapse"><i class="fa fa-minus"></i>
                                        </button>
                                        <button type="button" class="btn btn-box-tool" data-widget="remove"><i class="fa fa-times"></i></button>
                                    </div>
                                </div>
                                <div class="box-body">
                                    <div class="row">
                                        <div class="col-lg-4 col-xs-12">
                                            <div class="form-group">
                                                <label>Cliente</label>
                                                <select class="form-control select2" style="width: 100%;" name="cboCliente">
                                                    <option selected="selected">Cliente Varios</option>                                                  
                                                </select>
                                            </div>
                                        </div>
                                        <div class="col-lg-4 col-xs-12">
                                            <div class="form-group">
                                                <label>Tipo Comprobante</label>
                                                <select class="form-control select2" style="width: 100%;" name="cboTipoComprobante">
                                                    <option selected="selected">Boleta</option>                                                  
                                                </select>
                                            </div>
                                        </div>
                                        <div class="col-lg-4 col-xs-12">
                                            <div class="form-group">
                                                <label for="fecha">Fecha</label>
                                                <input type="text" class="form-control" id="fecha" placeholder="10/07/2022" name="fechaVenta">
                                            </div>
                                        </div>
                                    </div>
                                    <div class="row">
                                        <div class="col-lg-4 col-xs-12">
                                            <div class="form-group">
                                                <label for="serie">Serie</label>
                                                <input type="text" class="form-control" id="serie" placeholder="B001" name="serieVenta">
                                            </div>
                                        </div>
                                        <div class="col-lg-4 col-xs-12">
                                            <div class="form-group">
                                                <label for="correlativo">Correlativo</label>
                                                <input type="text" class="form-control" id="correlativo" placeholder="000001" name="correlativoVenta">
                                            </div>
                                        </div>
                                        <div class="col-lg-4 col-xs-12">
                                            <button style="margin-top: 25px" class="btn btn-primary col-md-12" data-toggle="modal" data-target="#buscarproductos"><i class="fa fa-search"></i> Buscar Producto</button>
                                        </div>
                                    </div>
                                </div>
                                <div class="box-footer clearfix">
                                    <table id="tablaProductos" class="table table-responsive table-bordered table-hover">
                                        <thead>
                                            <tr>
                                                <th class="text-center">Nº</th>
                                                <th class="text-center">Producto</th>
                                                <th class="text-center">Cantidad</th>
                                                <th class="text-center">Precio</th>
                                                <th class="text-center">Total</th>
                                                <th class="text-center">Acciones</th>
                                            </tr>
                                        </thead>
                                        <tbody>                                            
                                        </tbody>
                                    </table>
                                </div>
                            </div>
                        </div>
                        <div class="col-lg-4 col-xs-12">
                            <div class="box box-primary">
                                <div class="box-header with-border">
                                    <h3 class="box-title">Cálculos de la Venta</h3>
                                    <div class="box-tools pull-right">
                                        <button type="button" class="btn btn-box-tool" data-widget="collapse"><i class="fa fa-minus"></i>
                                        </button>
                                        <button type="button" class="btn btn-box-tool" data-widget="remove"><i class="fa fa-times"></i></button>
                                    </div>
                                </div>
                                <div class="box-body">
                                    <div class="col-lg-12 col-xs-12">
                                        <div class="form-group">
                                            <label for="subTotal">Sub-Total</label>
                                            <input type="text" class="form-control" disabled="" id="subTotal" placeholder="S/ 69.32" name="subTotalVenta">
                                        </div>
                                    </div>
                                    <div class="col-lg-12 col-xs-12">
                                        <div class="form-group">
                                            <label for="igv">IGV</label>
                                            <input type="text" class="form-control" disabled="" id="igv" placeholder="18%" name="igv">
                                        </div>
                                    </div>
                                    <div class="col-lg-12 col-xs-12">
                                        <div class="form-group">
                                            <label for="subTotal">Total a Pagar</label>
                                            <input type="text" class="form-control" disabled="" id="subTotal" placeholder="S/ 69.32" name="subTotalVenta">
                                        </div>
                                    </div>
                                </div>
                                <div class="box-footer clearfix">                                  
                                    <div class="col-lg-6 col-xs-12">
                                        <button class="btn btn-success col-md-12">Generar Venta <i class="fa fa-plus"></i></button>
                                    </div>
                                    <div style="" class="col-lg-6 col-md-12">
                                        <button class="btn btn-danger col-md-12">Cancelar Venta <i class="fa fa-close"></i></button>
                                    </div>
                                </div>
                            </div>
                        </div>
                    </div>
                </section>
            </div>
            <%@include file="plus/footer.jsp" %>
        </div>
        <!-- ./wrapper -->
        <!-- REQUIRED JS SCRIPTS -->
        <!-- jQuery 3 -->
        <script src="../bower_components/jquery/dist/jquery.min.js"></script>
        <!-- Bootstrap 3.3.7 -->
        <script src="../bower_components/bootstrap/dist/js/bootstrap.min.js"></script>
        <!-- Datatables -->
        <script src="../bower_components/datatables/jquery.dataTables.js" type="text/javascript"></script>
        <script src="../bower_components/datatables-bs4/js/dataTables.bootstrap4.js" type="text/javascript"></script>
        <!-- Select 2 -->
        <script src="https://cdn.jsdelivr.net/npm/select2@4.1.0-rc.0/dist/js/select2.min.js"></script>
        <!-- SweetAlert -->
        <script src="../plugins/sweetAlert/sweetalert.min.js" type="text/javascript"></script>
        <!-- AdminLTE App -->     
        <script src="../dist/js/adminlte.min.js"></script>
        <!-- Script Productos -->     
        <script src="../js/scriptAdministrarVentas.js"></script>
    </body>
</html>
<%
    } else {
        response.sendRedirect("../index.jsp");
    }
%>