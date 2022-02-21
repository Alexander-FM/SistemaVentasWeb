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
    <body class="hold-transition skin-blue sidebar-mini" style="font-family: Roboto, sans-serif;">
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
                <section class="content container-fluid">

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
    </body>
</html>
<%
    } else {
        response.sendRedirect("../index.jsp");
    }
%>