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
                    <h1>Gestión Categorí­as</h1>
                    <ol class="breadcrumb">
                        <li><a href="#"><i class="fa fa-dashboard"></i> Registros</a></li>
                        <li class="active">Categorí­as</li>
                    </ol>
                </section>
                <!-- Main content -->
                <section class="content">
                    <div class="row">
                        <div class="col-xs-10 col-md-5 ">
                            <div class="btn-group pull-left">
                                <button type="button" class="btn btn-success" data-toggle="modal" data-target="#mdlCategoria">
                                    <i class="fa fa-plus"></i> Nueva Categorí­a</button>
                            </div>
                        </div><br><br>
                        <div class="modal fade" data-backdrop="static" data-keyboard="false" id="mdlCategoria">
                            <input type="hidden" id="idCategoria" value="0">
                            <div class="modal-dialog">
                                <div class="modal-content">
                                    <div class="modal-header">
                                        <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                                            <span aria-hidden="true"><i class="fa fa-close"></i></span></button>
                                        <h4 class="modal-title">Gestión Categorí­as</h4>
                                    </div>
                                    <form id="frmCategorias">
                                        <div class="modal-body">
                                            <div class="row">
                                                <div class="col-md-12">
                                                    <div class="form-group">
                                                        <label>Nombre Categorí­a</label>
                                                        <div class="input-group">                                               
                                                            <span class="input-group-addon"><i class="fa fa-folder"></i></span>
                                                            <input name="txtCategoria" autofocus="" type="text" class="form-control" placeholder="Lacteos">
                                                        </div>
                                                    </div>
                                                </div>
                                            </div>
                                            <div class="form-group">                                               
                                                <div class="checkbox">
                                                    <label>
                                                        <input type="checkbox" name="chkVigencia" checked disabled>
                                                    </label>
                                                </div>                                                
                                            </div>  
                                        </div>
                                        <div class="modal-footer">
                                            <button type="reset" class="btn btn-danger" data-dismiss="modal"><i class="fa fa-close red"></i> Cancelar</button>
                                            <button type="submit" value="btnRegistrar" class="btn btn-primary"><i class="fa fa-floppy-o"></i> Registrar</button>
                                        </div>
                                    </form>
                                </div>
                                <!-- /.modal-content -->
                            </div>
                            <!-- /.modal-dialog -->
                        </div>
                        <div class="col-xs-12">
                            <div class="box">
                                <div class="box-header">
                                    <h3 class="box-title">Listado de Categorí­as</h3>
                                </div>
                                <div class="box-body">
                                    <table id="tablaCategorias" class="table table-bordered table-hover">
                                        <thead>
                                            <tr class="text-center">
                                                <th>Id</th>
                                                <th>Categoria</th>
                                                <th>Estado</th>
                                                <th>Acciones</th>
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
        <!-- SweetAlert -->
        <!-- jQuery 3 -->
        <script src="bower_components/jquery/dist/jquery.min.js"></script>
        <!-- Bootstrap 3.3.7 -->
        <script src="bower_components/bootstrap/dist/js/bootstrap.min.js"></script>
        <!-- AdminLTE App --> 
        <script src="dist/js/adminlte.min.js"></script>
        <!-- Datatables -->
        <script src="bower_components/datatables.net/js/jquery.dataTables.min.js" type="text/javascript"></script>
    </body>
</html>
<%
    } else {
        response.sendRedirect("../index.jsp");
    }
%>