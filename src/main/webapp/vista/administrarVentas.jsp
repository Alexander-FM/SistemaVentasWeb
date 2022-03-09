<%@ page contentType="text/html;charset=UTF-8" language="java" %>
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
            <h1>Gestión Administrar Ventas</h1>
            <ol class="breadcrumb">
                <li><a href="#"><i class="fa fa-dashboard"></i> Ventas</a></li>
                <li class="active">Administrar Ventas</li>
            </ol>
        </section>
        <!-- Main content -->
        <section class="content">
            <div class="row">
                <div class="col-xs-12">
                    <div class="box box-primary">
                        <div class="box-header">
                            <h3 class="box-title">Listado de Ventas</h3>
                        </div>
                        <div class="box-body">
                            <table id="tablaAdministrarVentas" class="table table-bordered table-hover">
                                <thead>
                                <tr>
                                    <th class="text-center">IdVenta</th>
                                    <th class="text-center">TipoComprobante</th>
                                    <th class="text-center">Serie</th>
                                    <th class="text-center">Numero</th>
                                    <th class="text-center">Total</th>
                                    <th class="text-center">Impuesto</th>
                                    <th class="text-center">Fecha</th>
                                    <th class="text-center">Empleado</th>
                                    <th class="text-center">Cliente</th>
                                    <th class="text-center">Estado</th>
                                    <th class="text-center">Acciones</th>
                                </tr>
                                </thead>
                                <tbody>
                                </tbody>
                            </table>
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