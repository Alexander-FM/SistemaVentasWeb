<%@page contentType="text/html" pageEncoding="UTF-8" %>
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
            <h1>Gestión Empleados</h1>
            <ol class="breadcrumb">
                <li><a href="#"><i class="fa fa-dashboard"></i> Nivel</a></li>
                <li class="active">Empleados</li>
            </ol>
        </section>
        <!-- Main content -->
        <section class="content">
            <div class="row">
                <div class="col-xs-10 col-md-5 ">
                    <div class="btn-group pull-left">
                        <button type="button" class="btn btn-success" data-toggle="modal" data-target="#modalempleados"
                                data-backdrop="static" data-keyboard="false">
                            <i class="fa fa-plus"></i> Nuevo Empleado
                        </button>
                    </div>
                </div>
                <br><br>
                <div class="modal fade" id="modalempleados">
                    <div class="modal-dialog">
                        <div class="modal-content">
                            <div class="modal-header">
                                <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                                    <span aria-hidden="true">&times;</span></button>
                                <h4 class="modal-title">Gestión Empleados</h4>
                            </div>
                            <form action="Empleado?accion=re" method="post" class="form">
                                <div class="modal-body">
                                    <div class="row">
                                        <div class="col-md-6">
                                            <div class="form-group">
                                                <label>Nombres Empleado</label>
                                                <div class="input-group">
                                                    <span class="input-group-addon"><i class="fa fa-user"></i></span>
                                                    <input type="text" name="txtNombres" class="form-control" placeholder="Alexander">
                                                </div>
                                            </div>
                                        </div>
                                        <div class="col-md-6">
                                            <div class="form-group">
                                                <label>Apellidos Empleado</label>
                                                <div class="input-group">
                                                    <span class="input-group-addon"><i class="fa fa-user"></i></span>
                                                    <input type="text" name="txtApellidos" class="form-control"
                                                           placeholder="Fuentes Medina">
                                                </div>
                                            </div>
                                        </div>
                                    </div>
                                    <div class="row">
                                        <div class="col-md-6">
                                            <div class="form-group">
                                                <label>Seleccione Tipo Documento</label>
                                                <select class="form-control" name="cboTipoDoc">
                                                    <option value="DNI">DNI</option>
                                                    <option value="RUC">RUC</option>
                                                    <option value="PASAPORTE">PASAPORTE</option>
                                                    <option value="CARNET DE EXTRANJERIA">CARNET DE EXTRANJERIA</option>
                                                </select>
                                            </div>
                                        </div>
                                        <div class="col-md-6">
                                            <div class="form-group">
                                                <label>Numero Documento</label>
                                                <div class="input-group">
                                                    <span class="input-group-addon"><i class="fa fa-indent"></i></span>
                                                    <input type="text" name="txtNumeroDoc" class="form-control" placeholder="78019778">
                                                </div>
                                            </div>
                                        </div>
                                    </div>
                                    <div class="row">
                                        <div class="col-md-12">
                                            <div class="form-group">
                                                <label>Direccion</label>
                                                <div class="input-group">
                                                    <span class="input-group-addon"><i class="fa fa-home"></i></span>
                                                    <input type="text" name="txtDireccion" class="form-control"
                                                           placeholder="Chiclayo - La Victoria">
                                                </div>
                                            </div>
                                        </div>
                                    </div>
                                    <div class="row">
                                        <div class="col-md-6">
                                            <div class="form-group">
                                                <label>Correo</label>
                                                <div class="input-group">
                                                    <span class="input-group-addon"><i class="fa fa-envelope-o"></i></span>
                                                    <input name="txtCorreo" type="text" class="form-control"
                                                           placeholder="alexander@gmail.com">
                                                </div>
                                            </div>
                                        </div>
                                        <div class="col-md-6">
                                            <div class="form-group">
                                                <label>Telefono</label>
                                                <div class="input-group">
                                                    <span class="input-group-addon"><i class="fa fa-phone"></i></span>
                                                    <input type="text" name="txtTelefono" class="form-control" placeholder="947162276">
                                                </div>
                                            </div>
                                        </div>
                                    </div>
                                    <div class="row">
                                        <div class="col-md-6">
                                            <div class="form-group">
                                                <label>Seleccione un Cargo</label>
                                                <select class="form-control" name="cboCargo">
                                                    <option value="Administrador">Administrador</option>
                                                    <option value="Empleado">Empleado</option>
                                                    <option value="Invitado">Invitado</option>
                                                    <option value="Soporte">Soporte</option>
                                                </select>
                                            </div>
                                        </div>
                                        <div class="col-md-6">
                                            <div class="form-group">
                                                <label>Fecha Nacimiento</label>
                                                <div class="input-group">
                                                    <span class="input-group-addon"><i class=" fa fa-birthday-cake"></i></span>
                                                    <input name="txtFechaNac" type="date" class="form-control" placeholder="2019-12-19">
                                                </div>
                                            </div>
                                        </div>
                                    </div>
                                    <div class="row">
                                        <div class="col-md-6">
                                            <div class="form-group">
                                                <label>Usuario</label>
                                                <div class="input-group">
                                                    <span class="input-group-addon"><i class="fa fa-user"></i></span>
                                                    <input name="txtUser" type="text" class="form-control" placeholder="AlexFume">
                                                </div>
                                            </div>
                                        </div>
                                        <div class="col-md-6">
                                            <div class="form-group">
                                                <label>Clave</label>
                                                <div class="input-group">
                                                    <span class="input-group-addon"><i class="fa fa-user-secret"></i></span>
                                                    <input name="txtClave" type="password" class="form-control" placeholder="*************">
                                                </div>
                                            </div>
                                        </div>
                                    </div>
                                    <div class="form-group">
                                        <div class="checkbox">
                                            <label>
                                                <input type="checkbox" name="chkVigencia"
                                                <c:out value="${empleado.estado == false ? 'unchecked' : 'checked'}"
                                                        default="" />>Disponible
                                            </label>
                                        </div>
                                    </div>
                                </div>
                                <div class="modal-footer">
                                    <button type="reset" class="btn btn-danger" data-dismiss="modal"><i class="fa fa-close red"></i>
                                        Cancelar
                                    </button>
                                    <button type="submit" value="Registrar" class="btn btn-primary"><i class="fa fa-floppy-o"></i> Registrar
                                    </button>
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
                            <h3 class="box-title">Listado de Empleados</h3>
                        </div>
                        <div class="box-body">
                            <table id="tablaEmpleados" class="table table-bordered table-hover">
                                <thead>
                                <tr>
                                    <th>Item</th>
                                    <th>Empleado</th>
                                    <th>Tipo Doc</th>
                                    <th>Numero Doc</th>
                                    <th>Telefono</th>
                                    <th>Fecha Nacimiento</th>
                                    <th>Estado</th>
                                    <th>Cargo</th>
                                    <th>Usuario</th>
                                    <th>Acciones</th>
                                </tr>
                                </thead>
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
<script src="../js/scriptEmpleados.js"></script>
</body>
</html>
<%
    } else {
        response.sendRedirect("../index.jsp");
    }
%>