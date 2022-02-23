<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
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
                    <h1>Gestión Productos</h1>
                    <ol class="breadcrumb">
                        <li><a href="#"><i class="fa fa-dashboard"></i> Nivel</a></li>
                        <li class="active">Productos</li>
                    </ol>
                </section>
                <!-- Main content -->
                <section class="content">
                    <div class="row">
                        <div class="col-xs-10 col-md-5 ">
                            <div class="btn-group pull-left">
                                <button type="button" class="btn btn-success" data-toggle="modal" data-target="#modalproduc">
                                    <i class="fa fa-plus"></i> Nuevo Producto</button>      
                                    <c:if test="${msje != ''}">
                                    <div>${msje}</div>
                                </c:if>
                            </div>
                        </div><br><br>
                        <div class="modal fade" id="modalproduc">
                            <div class="modal-dialog">
                                <div class="modal-content">
                                    <div class="modal-header">
                                        <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                                            <span aria-hidden="true">&times;</span></button>
                                        <h4 class="modal-title">Gestión Productos</h4>
                                    </div>
                                    <form action="Producto?accion=re" method="post" class="form">
                                        <div class="modal-body">
                                            <div class="row">
                                                <div class="col-md-6">
                                                    <div class="form-group">
                                                        <label>Seleccione Categoria</label>
                                                        <select class="form-control" name="cboCategoria">
                                                            <option value="0">Seleccione una Categoria</option>
                                                            <c:forEach var="cat" items="${categorias}" >
                                                                <option value="${cat.codigo}"  
                                                                        <c:if test="${cat.codigo == 
                                                                                      producto.categoria.codigo}">
                                                                              selected
                                                                        </c:if>
                                                                        >${cat.categoria}</option>
                                                            </c:forEach>
                                                        </select>
                                                    </div>
                                                </div>
                                                <div class="col-md-6">
                                                    <div class="form-group">
                                                        <label>Seleccione Marca</label>
                                                        <select class="form-control" name="cboMarca">
                                                            <option value="0">Seleccione una Marca</option>
                                                            <c:forEach var="mar" items="${marcas}">
                                                                <option value="${mar.codigo}"  
                                                                        <c:if test="${mar.codigo == 
                                                                                      producto.marca.codigo}">
                                                                              selected
                                                                        </c:if>
                                                                        >${mar.marca}</option>
                                                            </c:forEach>
                                                        </select>
                                                    </div>
                                                </div>
                                            </div>
                                            <div class="row">
                                                <div class="col-md-6">
                                                    <div class="form-group">
                                                        <label>Codigo Producto</label>
                                                        <div class="input-group">                                               
                                                            <span class="input-group-addon"><i class="fa fa-barcode"></i></span>
                                                            <input type="text" name="codigoProducto" class="form-control" placeholder="Ingrese Codigo Producto">
                                                        </div>
                                                    </div>
                                                </div>
                                                <div class="col-md-6">
                                                    <div class="form-group">
                                                        <label>Producto</label>
                                                        <input type="text" name="producto" class="form-control" placeholder="Producto">
                                                    </div>
                                                </div>
                                            </div>
                                            <div class="row">
                                                <div class="col-md-6">
                                                    <div class="form-group">
                                                        <label>Descripcion</label>                                                      
                                                        <input type="text" name="descripcion" class="form-control" placeholder="Descripcion">                                                       
                                                    </div>
                                                </div>
                                                <div class="col-md-6">
                                                    <div class="form-group">
                                                        <label>Seleccione Unidad Medida</label>                                                    
                                                        <select class="form-control" name="cboUnidadMedida">
                                                            <option value="Decena">Decena</option>
                                                            <option value="Unidad">Unidad</option>
                                                            <option value="Docena">Docena</option>
                                                        </select>                                                       
                                                    </div>
                                                </div>
                                            </div>
                                            <div class="row">
                                                <div class="col-md-6">
                                                    <div class="form-group">
                                                        <label>Precio</label>
                                                        <div class="input-group">                                               
                                                            <span class="input-group-addon"><i class="fa fa-money"></i></span>
                                                            <input name="precio" type="text" class="form-control" placeholder="$ 8.50">
                                                        </div>
                                                    </div>
                                                </div>
                                                <div class="col-md-6">
                                                    <div class="form-group">
                                                        <label>Stock</label> 
                                                        <div class="input-group"> 
                                                            <span class="input-group-addon"><i class=" fa fa-hand-o-right"></i></span>
                                                            <input name="stock" type="text" class="form-control" placeholder="100">
                                                        </div>
                                                    </div>
                                                </div>
                                            </div>                                           
                                            <div class="form-group">                                               
                                                <div class="checkbox">
                                                    <label>
                                                        <input type="checkbox" name="chkVigencia" 
                                                               <c:out value="${producto.estado == false ? 'unchecked' : 'checked'}"
                                                                      default="" />>Activo
                                                    </label>
                                                </div>                                                
                                            </div>                                              
                                        </div>
                                        <div class="modal-footer">
                                            <button type="reset" class="btn btn-danger" data-dismiss="modal"><i class="fa fa-close red"></i> Cancelar</button>
                                            <button type="submit" value="Registrar" class="btn btn-primary"><i class="fa fa-floppy-o"></i> Registrar</button>  
                                        </div>
                                    </form>
                                </div>
                                <!-- /.modal-content -->
                            </div>
                            <!-- /.modal-dialog -->
                        </div>
                        <div class="col-xs-12">
                            <div class="box box-primary">
                                <div class="box-header">
                                    <h3 class="box-title">Listado de Productos</h3>   
                                </div>
                                <div class="box-body">
                                    <table id="tablaProductos" class="table table-responsive table-bordered table-hover">
                                        <thead>
                                            <tr>
                                                <th class="text-center">Codigo Barras</th>
                                                <th class="text-center">Producto</th>
                                                <th class="text-center">Imagen</th>
                                                <th class="text-center">Descripción</th>
                                                <th class="text-center">Precio Venta</th>
                                                <th class="text-center">Precio Compra</th>
                                                <th class="text-center">Stock</th>
                                                <th class="text-center">Estado</th>
                                                <th class="text-center">Categoria</th>
                                                <th class="text-center">Marca</th>
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
        <script src="../js/scriptProductos.js"></script>
    </body>
</html>
