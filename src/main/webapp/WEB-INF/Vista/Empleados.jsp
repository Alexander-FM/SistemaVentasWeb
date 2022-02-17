<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
    <head>
        <meta charset="utf-8">
        <meta http-equiv="X-UA-Compatible" content="IE=edge">
        <title>Sistema Ventas | Empleados</title>
        <!-- Tell the browser to be responsive to screen width -->
        <meta content="width=device-width, initial-scale=1, maximum-scale=1, user-scalable=no" name="viewport">
        <link rel="stylesheet" href="bower_components/bootstrap/dist/css/bootstrap.min.css">
        <!-- Font Awesome -->
        <link rel="stylesheet" href="bower_components/font-awesome/css/font-awesome.min.css">
        <!-- Ionicons -->
        <link rel="stylesheet" href="bower_components/Ionicons/css/ionicons.min.css">
        <!-- Theme style -->
        <link rel="stylesheet" href="dist/css/AdminLTE.min.css">
        <!-- AdminLTE Skins. We have chosen the skin-blue for this starter
              page. However, you can choose any other skin. Make sure you
              apply the skin class to the body tag so the changes take effect. -->
        <link rel="stylesheet" href="dist/css/skins/skin-blue.min.css">

        <!-- HTML5 Shim and Respond.js IE8 support of HTML5 elements and media queries -->
        <!-- WARNING: Respond.js doesn't work if you view the page via file:// -->
        <!--[if lt IE 9]>
        <script src="https://oss.maxcdn.com/html5shiv/3.7.3/html5shiv.min.js"></script>
        <script src="https://oss.maxcdn.com/respond/1.4.2/respond.min.js"></script>
        <![endif]-->

        <!-- Google Font -->
        <link rel="stylesheet"
              href="https://fonts.googleapis.com/css?family=Source+Sans+Pro:300,400,600,700,300italic,400italic,600italic">
    </head>
    <body class="hold-transition skin-blue sidebar-mini">
        <div class="wrapper">

            <!-- Main Header -->
            <header class="main-header">

                <!-- Logo -->
                <a href="index2.html" class="logo">
                    <!-- mini logo for sidebar mini 50x50 pixels -->
                    <span class="logo-mini"><b>B</b>M</span>
                    <!-- logo for regular state and mobile devices -->
                    <span class="logo-lg"><b>Sistema Ventas</b>Mechita</span>
                </a>

                <!-- Header Navbar -->
                <nav class="navbar navbar-static-top" role="navigation">
                    <!-- Sidebar toggle button-->
                    <a href="#" class="sidebar-toggle" data-toggle="push-menu" role="button">
                        <span class="sr-only">Toggle navigation</span>
                    </a>
                    <!-- Navbar Right Menu -->
                    <div class="navbar-custom-menu">
                        <ul class="nav navbar-nav">
                            <!-- User Account Menu -->
                            <li class="dropdown user user-menu">
                                <!-- Menu Toggle Button -->
                                <a href="#" class="dropdown-toggle" data-toggle="dropdown">
                                    <!-- The user image in the navbar-->
                                    <img src="dist/img/avatar3.png" class="user-image"  alt=""/>
                                    <!-- hidden-xs hides the username on small devices so only the image appears. -->
                                    <span class="hidden-xs">${usuario.nombre}</span>
                                </a>
                                <ul class="dropdown-menu">
                                    <!-- The user image in the menu -->
                                    <li class="user-header">
                                        <img src="dist/img/avatar3.png" class="img-circle"  alt=""/>

                                        <p>
                                            ${usuario.nombre} ${usuario.apellidos}
                                            <small>${usuario.cargo}</small>
                                        </p>
                                    </li>
                                    <!-- Menu Footer-->
                                    <li class="user-footer">
                                        <div class="pull-right">
                                            <a data-toggle="modal" href="#logout" class="btn btn-danger btn-flat"><i class="fa fa-power-off"></i>  Salir</a>
                                        </div>
                                    </li>
                                </ul>
                            </li>
                        </ul>
                    </div>
                </nav>
            </header>
            <!-- Ventana Modal del Cerrar Sesion -->                           
            <div class="modal fade in" id="logout" aria-hidden="false">
                <div class="modal-dialog">
                    <div class="modal-content">
                        <div class="modal-header">
                            <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">×</span></button>
                            <h4 class="modal-title"><i class="fa fa-sign-out"> Salir</i></h4>
                        </div>
                        <div class="modal-body">
                            <p>¿Seguro que quieres salir? </p>
                        </div>
                        <div class="modal-footer">
                            <a type="button" class="btn btn-danger" href="usuarios?accion=ce">Si, Salir</a>
                            <button type="button" class="btn btn-default" data-dismiss="modal">Cancelar</button>
                        </div>
                    </div><!-- /.modal-content -->
                </div><!-- /.modal-dialog -->
            </div>
            <!-- Left side column. contains the logo and sidebar -->
            <aside class="main-sidebar">

                <!-- sidebar: style can be found in sidebar.less -->
                <section class="sidebar">

                    <!-- Sidebar user panel (optional) -->
                    <div class="user-panel">
                        <div class="pull-left image">
                            <img src="dist/img/avatar3.png" class="img-circle"  alt=""/>
                        </div>
                        <div class="pull-left info">
                            <p>Bienvenido :</p>                             
                            <small>${usuario.nombre} <i class="fa fa-circle text-success"></i></small><br><br>   
                        </div>
                    </div>

                    <!-- Sidebar Menu -->
                    <ul class="sidebar-menu" data-widget="tree" style="margin-top: 20px;">
                        <!-- Optionally, you can add icons to the links -->
                        <li><a href="#"><i class="fa fa-home"></i> <span>Inicio</span></a></li>
                        <li class="treeview">
                            <a href="#"><i class="glyphicon glyphicon-th-large"></i> <span>Almacen</span>
                                <span class="pull-right-container">
                                    <i class="fa fa-angle-left pull-right"></i>
                                </span>
                            </a>
                            <ul class="treeview-menu">
                                <li><a href="Producto?accion=li"><i class="fa fa-cube"></i> <span>Productos</span></a></li>
                                <li><a href="Categoria?accion=li"><i class="fa fa-bookmark-o"></i> <span>Categorias</span></a></li>
                                <li><a href="Marca?accion=li"><i class="fa fa-tags"></i> <span>Marcas</span></a></li>
                            </ul>
                        </li>
                        <li class="treeview">
                            <a href="#"><i class="glyphicon glyphicon-shopping-cart"></i> <span>Ventas</span>
                                <span class="pull-right-container">
                                    <i class="fa fa-angle-left pull-right"></i>
                                </span>
                            </a>
                            <ul class="treeview-menu">
                                <li><a href="#"><i class="fa fa-plus"></i> <span>Nueva Venta</span></a></li>
                                <li><a href="#"><i class="fa fa-bar-chart-o"></i> <span>Administrar Ventas</span></a></li>
                            </ul>
                        </li>
                        <li class="treeview">
                            <a href="#"><i class="fa fa-truck"></i> <span>Compras</span>
                                <span class="pull-right-container">
                                    <i class="fa fa-angle-left pull-right"></i>
                                </span>
                            </a>
                            <ul class="treeview-menu">
                                <li><a href="Proveedor?accion=li"><i class="fa fa-bank"></i> <span>Proveedores</span></a></li>      
                                <li><a href="#"><i class="fa fa-money"></i> <span>Registrar Compra</span></a></li>
                                <li><a href="#"><i class="fa fa-line-chart"></i> <span>Administrar Compras </span></a></li>
                            </ul>
                        </li>                       
                        <li><a href="#"><i class="fa fa-line-chart"></i> <span>Reportes</span></a></li>
                        <li><a href="Cliente?accion=li"><i class="fa fa-users"></i> <span>Clientes</span></a></li>
                        <li class="active"><a href="Empleado?accion=li"><i class="fa fa-user"></i> <span>Empleados</span></a></li>
                        <li><a href="#"><i class="fa fa-wrench"></i> <span>Soporte</span></a></li>
                        <li><a href="#"><i class="fa fa-cog"></i> <span>Configuracion</span></a></li>
                    </ul>
                    <!-- /.sidebar-menu -->
                </section>
                <!-- /.sidebar -->
            </aside>

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
                                <button type="button" class="btn btn-success" data-toggle="modal" data-target="#modalempleados">
                                    <i class="fa fa-plus"></i> Nuevo Empleado</button>      
                                    <c:if test="${msje != ''}">
                                    <div>${msje}</div>
                                </c:if>
                            </div>
                        </div><br><br>
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
                                                            <input type="text" name="txtApellidos" class="form-control" placeholder="Fuentes Medina">
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
                                                            <input type="text" name="txtDireccion" class="form-control" placeholder="Chiclayo - La Victoria">
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
                                                            <input name="txtCorreo" type="text" class="form-control" placeholder="alexander@gmail.com">
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
                            <div class="box">
                                <div class="box-header">
                                    <h3 class="box-title">Listado de Empleados</h3>   
                                </div>
                                <div class="box-body">
                                    <table id="empleados" class="table table-bordered table-hover">
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
                                        <c:forEach var="emp" items="${empleados}" varStatus="iteracion">
                                            <tbody>
                                                <tr>
                                                    <td>${iteracion.index + 1}</td>
                                                    <td>${emp.getNombreCompleto()}</td>
                                                    <td>${emp.tipoDocumento}</td>
                                                    <td>${emp.numeroDocumento}</td>
                                                    <td>${emp.telefono}</td>
                                                    <td style="text-align: center">${emp.fechaNacimiento}</td>
                                                    <c:if test="${emp.estado == true}">
                                                        <td>
                                                            <span class="badge bg-blue-active">Disponible</span>
                                                        </td>
                                                    </c:if>
                                                    <c:if test="${emp.estado == false}">
                                                        <td>
                                                            <span class="badge bg-red-active">No Disponible</span>
                                                        </td>
                                                    </c:if>
                                                    <td>${emp.cargo}</td>
                                                    <td>${emp.login}</td>
                                                    <td><a href="<c:url value="Empleado">
                                                               <c:param name="accion" value="pr" />
                                                               <c:param name="cod" value="${emp.codigo}" />
                                                           </c:url>"><button class="btn btn-warning" data-toggle="tooltip" title="" data-original-title="Editar">
                                                                <i class="fa fa-pencil"></i></button></a>
                                                        <a href="<c:url value="Empleado">
                                                               <c:param name="accion" value="eli" />
                                                               <c:param name="cod" value="${emp.codigo}" />
                                                           </c:url>"><button class="btn btn-danger" data-toggle="tooltip" title="" data-original-title="Eliminar">
                                                                <i class="fa fa-trash"></i></button></a></td> 
                                                </tr>
                                            </tbody>
                                        </c:forEach>
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
            <footer class="main-footer">
                <!-- To the right -->
                <div class="pull-right hidden-xs">
                    Bodega Mechita
                </div>
                <!-- Default to the left -->
                <strong>Copyright &copy; 2020 <a href="#">Bodega</a>.</strong> Todos los derechos reservados.
            </footer>
            <!-- Add the sidebar's background. This div must be placed
            immediately after the control sidebar -->
            <div class="control-sidebar-bg"></div>
        </div>

        <!-- ./wrapper -->

        <!-- REQUIRED JS SCRIPTS -->

        <!-- jQuery 3 -->
        <script src="bower_components/jquery/dist/jquery.min.js"></script>
        <!-- Bootstrap 3.3.7 -->
        <script src="bower_components/bootstrap/dist/js/bootstrap.min.js"></script>
        <!-- Datatables -->
        <script src="bower_components/datatables.net/js/jquery.dataTables.min.js" type="text/javascript"></script>
        <script src="bower_components/datatables.net-bs/js/dataTables.bootstrap.min.js" type="text/javascript"></script>
        <!-- Slim Scrool -->       
        <script src="bower_components/jquery-slimscroll/jquery.slimscroll.min.js" type="text/javascript"></script>
        <!-- fasclick -->
        <script src="bower_components/fastclick/lib/fastclick.js" type="text/javascript"></script>
        <!-- AdminLTE App -->     
        <script src="dist/js/adminlte.min.js"></script>

        <script>
            $(function () {
                $('#empleados').DataTable({
                    'paging': true,
                    'lengthChange': false,
                    'searching': false,
                    'ordering': true,
                    'info': true,
                    'autoWidth': false
                });
            });
        </script>
    </body>
</html>
