<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@page session="true" %>
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
                <!-- Content Header (Page header) -->
                <section class="content-header">
                    <h1>
                        Página Principal
                    </h1>
                    <ol class="breadcrumb">
                        <li><a href="#"><i class="fa fa-dashboard"></i> Página Principal</a></li>
                        <li class="active">Inicio</li>
                    </ol>
                </section>
                <!-- Main content -->
                <section class="content">
                    <div class="row">
                        <div class="col-lg-3 col-xs-6">
                            <div class="small-box bg-aqua">
                                <div class="inner">
                                    <h3 id="totalVentas">150</h3>
                                    <p>Ventas realizadas</p>
                                </div>
                                <div style="margin-top: 15px" class="icon">
                                    <i class="ion ion-bag"></i>
                                </div>
                                <a href="#" class="small-box-footer">Más Información <i class="fa fa-arrow-circle-right"></i></a>
                            </div>
                        </div>

                        <div class="col-lg-3 col-xs-6">
                            <div class="small-box bg-green">
                                <div class="inner">
                                    <h3 id="totalCompras">120</h3>
                                    <p>Compras realizadas</p>
                                </div>
                                <div style="margin-top: 15px" class="icon">
                                    <i class="fa fa-truck"></i>
                                </div>
                                <a href="#" class="small-box-footer">Más Información <i class="fa fa-arrow-circle-right"></i></a>
                            </div>
                        </div>

                        <div class="col-lg-3 col-xs-6">
                            <div class="small-box bg-yellow">
                                <div class="inner">
                                    <h3 id="totalClientes">10</h3>
                                    <p>Clientes Registrados</p>
                                </div>
                                <div style="margin-top: 15px" class="icon">
                                    <i class="ion ion-person"></i>
                                </div>
                                <a href="#" class="small-box-footer">Más Información <i class="fa fa-arrow-circle-right"></i></a>
                            </div>
                        </div>

                        <div class="col-lg-3 col-xs-6">
                            <div class="small-box bg-red">
                                <div class="inner">
                                    <h3 id="dineroCaja">S/ 3040.00</h3>
                                    <p>Dinero de Caja</p>
                                </div>
                                <div style="margin-top: 15px" class="icon">
                                    <i class="fa fa-money"></i>
                                </div>
                                <a href="#" class="small-box-footer">Más Información <i class="fa fa-arrow-circle-right"></i></a>
                            </div>
                        </div>
                    </div> 
                    <div class="row">
                        <div class="col-lg-8 col-xs-12">
                            <div class="box box-primary">
                                <div class="box-header with-border">
                                    <h3 class="box-title">Productos sobre el stock mínimo</h3>
                                    <div class="box-tools pull-right">
                                        <button type="button" class="btn btn-box-tool" data-widget="collapse"><i class="fa fa-minus"></i>
                                        </button>
                                        <button type="button" class="btn btn-box-tool" data-widget="remove"><i class="fa fa-times"></i></button>
                                    </div>
                                </div>
                                <div class="box-body">
                                    <div class="table-responsive">
                                        <table id="tablaProductosConBajoStock" class="table no-margin">
                                            <thead>
                                                <tr>
                                                    <th>Producto</th>
                                                    <th>Precio Compra</th>
                                                    <th>Precio Venta</th>
                                                    <th>Stock</th>
                                                    <th>Acciones</th>
                                                </tr>
                                            </thead>
                                            <tbody>
                                            </tbody>
                                        </table>
                                    </div>
                                </div>
                                <div class="box-footer clearfix">
                                    <a href="#" class="btn btn-sm btn-primary btn-flat pull-left">Comprar ahora</a>
                                    <a href="#" class="btn btn-sm btn-default btn-flat pull-right">Ver todas las compras</a>
                                </div>
                            </div>
                        </div>
                        <div class="col-lg-4 col-xs-12">
                            <div class="box box-success">
                                <div class="box-header with-border">
                                    <h3 class="box-title">Productos recientemente agregados</h3>
                                    <div class="box-tools pull-right">
                                        <button type="button" class="btn btn-box-tool" data-widget="collapse"><i class="fa fa-minus"></i></button>
                                        <button type="button" class="btn btn-box-tool" data-widget="remove"><i class="fa fa-times"></i></button>
                                    </div>
                                </div>
                                <div class="box-body">
                                    <ul class="products-list product-list-in-box">
                                        <li class="item">
                                            <div class="product-img">
                                                <img src="../imagenes/image_not_found.png" alt="Product Image">
                                            </div>
                                            <div class="product-info">
                                                <a id="nombreProducto" class="product-title">LG OLED TV
                                                    <span id="precioProducto" class="label label-warning pull-right">$1800</span>
                                                </a>
                                                <span id="descripcionProducto" class="product-description">
                                                    Samsung 65" 4K 120Hz OLED Smart TV.
                                                </span>
                                            </div>
                                        </li>
                                    </ul>
                                </div>
                                <div class="box-footer text-center">
                                    <a href="#" class="uppercase">Ver todos los productos</a>
                                </div>
                            </div>
                        </div>
                    </div>
                </section>
                <!-- /.content -->
            </div>
            <!-- /.content-wrapper -->
            <!-- Main Footer -->
            <%@include file="plus/footer.jsp" %>
            <!-- /.Main footer -->
        </div>
        <!-- ./wrapper -->
        <!-- REQUIRED JS SCRIPTS -->
        <!-- jQuery 3 -->
        <script src="../bower_components/jquery/dist/jquery.min.js"></script>
        <!-- Bootstrap 3.3.7 -->
        <script src="../bower_components/bootstrap/dist/js/bootstrap.min.js"></script>
        <!-- AdminLTE App -->
        <script src="../dist/js/adminlte.min.js"></script>
        <!-- Script Inicio -->
        <script src="../js/scriptInicio.js"></script>
    </body>
</html>
<%
    } else {
        response.sendRedirect("../index.jsp");
    }
%>