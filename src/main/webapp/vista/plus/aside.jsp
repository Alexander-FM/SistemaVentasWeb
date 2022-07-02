<aside class="main-sidebar">
    <!-- sidebar: style can be found in sidebar.less -->
    <section class="sidebar">
        <!-- Sidebar user panel (optional) -->
        <div class="user-panel">
            <div class="pull-left image">
                <img src="../dist/img/avatar3.png" class="img-circle"  alt=""/>
            </div>
            <div style="margin-top: 12px" class="pull-left info">
                <p>Hola, ${usuario.nombre}</p>
            </div>
        </div>
        <!-- Sidebar Menu -->
        <ul class="sidebar-menu" data-widget="tree" style="margin-top: 20px;">
            <!-- Optionally, you can add icons to the links -->
            <li id="li_inicio"><a href="../vista/inicio.jsp"><i class="fa fa-home"></i><span>Inicio</span></a></li>
            <li id="li_grupoRegistros" class="treeview">
                <a href="#"><i class="glyphicon glyphicon-th-large"></i><span>Registros</span>
                    <span class="pull-right-container"><i class="fa fa-angle-left pull-right"></i></span>
                </a>
                <ul class="treeview-menu">
                    <li id="li_productos"><a href="../vista/productos.jsp"><i class="fa fa-cube"></i><span>Productos</span></a></li>
                    <li id="li_categorias"><a href="../vista/categorias.jsp"><i class="fa fa-bookmark-o"></i><span>Categorias</span></a></li>
                    <li id="li_marcas"><a href="../vista/marcas.jsp"><i class="fa fa-tags"></i><span>Marcas</span></a></li>
                </ul>
            </li>
            <li id="li_grupoVentas" class="treeview">
                <a href="#"><i class="glyphicon glyphicon-shopping-cart"></i><span>Ventas</span>
                    <span class="pull-right-container"><i class="fa fa-angle-left pull-right"></i></span>
                </a>
                <ul class="treeview-menu">
                    <li id="li_nuevaVenta"><a href="../vista/ventas.jsp"><i class="fa fa-plus"></i><span>Nueva Venta</span></a></li>
                    <li id="li_clientes"><a href="../vista/clientes.jsp"><i class="fa fa-users"></i><span>Clientes</span></a></li>
                    <li id="li_administrarVentas"><a href="../vista/administrarVentas.jsp"><i class="fa fa-bar-chart-o"></i><span>Administrar Ventas</span></a></li>
                </ul>
            </li>
            <li class="treeview">
                <a href="#"><i class="fa fa-truck"></i><span>Compras</span>
                    <span class="pull-right-container"><i class="fa fa-angle-left pull-right"></i></span>
                </a>
                <ul class="treeview-menu">
                    <li><a href="#"><i class="fa fa-money"></i><span>Registrar Compra</span></a></li>
                    <li><a href="../vista/proveedores.jsp"><i class="fa fa-bank"></i><span>Proveedores</span></a></li>
                    <li><a href="#"><i class="fa fa-line-chart"></i><span>Administrar Compras </span></a></li>
                </ul>
            </li>
            <li id="li_empleados"><a href="../vista/empleados.jsp"><i class="fa fa-user"></i><span>Empleados</span></a></li>
            <li class="treeview">
                <a href="#"><i class="fa fa-line-chart"></i><span>Reportes</span>
                    <span class="pull-right-container"><i class="fa fa-angle-left pull-right"></i></span>
                </a>
                <ul class="treeview-menu">
                    <li><a href="#"><i class="fa fa-bar-chart"></i><span>Reporte Ventas</span></a></li>
                    <li><a href="#"><i class="fa fa-bar-chart"></i><span>Reportes Compras</span></a></li>
                </ul>
            </li>
            <li><a href="#"><i class="fa fa-cog"></i><span>Configuracion</span></a></li>
        </ul>
        <!-- /.sidebar-menu -->
    </section>
    <!-- /.sidebar -->
</aside>
