var tablaProductoConBajoStock = $("table#tablaProductosConBajoStock");
$(document).ready(function () {
    let li_inicio = $('#li_inicio');
    li_inicio.attr('class', 'active'); //Añadimos la clase
    top10ProductosConStockBajo();
    top10UltimosProductosRegistrados();
    totalVentasRealizadas();
    totalComprasRealizadas();
    totalClientesRegistrados();
    montoTotalEnCaja();
});
/**
 * Esta función devuelve una lista con los 10 productos con su stock <= 5
 * @returns {List} retorna una lista
 */
function top10ProductosConStockBajo() {
    $.ajax({
        url: "../panelAdministrativo?accion=top10ProductosConStockBajo",
        type: 'GET',
        dataType: 'JSON',
        success: function (data) {
            var tpl = "";
            for (var i = 0; i < data.length; i++) {
                tpl += "<tr>"
                        + "<td>" + data[i].producto + "</td>"
                        + "<td> S/" + data[i].precioCompra + ".00</td>"
                        + "<td> S/" + data[i].precioVenta + ".00</td>"
                        + "<td style=\"color: red;\">" + data[i].stock + "</td>"
                        + "<td><a href=\"#\" title=\"Comprar\" class=\"btn btn-info btn-sm\">"
                        + "<span class=\"fa fa-truck\"></span> Comprar</a></td>"
                        + "</tr>";
            }
            tablaProductoConBajoStock.find("tbody").html(tpl);
        }
    });
}
/*
 * Está función permite listar los 10 ultimos productos registrados
 * @returns {List} retorna una lista
 */
function top10UltimosProductosRegistrados() {
    $.ajax({
        url: "../panelAdministrativo?accion=top10UltimosProductosRegistrados",
        type: 'GET',
        dataType: 'JSON',
        success: function (data) {
            var etiqueta = '';
            for (var i = 0; i < data.length; i++) {
                etiqueta += '<li class="item"><div class="product-img"><img src="' + data[i].imagen + '"'
                        + 'alt="Product Image"></div><div class="product-info"><a class="product-title">'
                        + data[i].producto + '<span class="label label-warning pull-right">S/ '
                        + data[i].precioVenta + '.00</span></a><span class="product-description">'
                        + data[i].descripcion + '</span></div></li>';
            }
            $('.products-list').html(etiqueta);
        }
    });
}
/**
 * Esta función representa el total de ventas realizadas
 * @returns {int} retorna un valor entero, representa el total de ventas 
 * realizadas
 */
function totalVentasRealizadas() {
    var totalVentasRealizadas = '';
    $.get('../panelAdministrativo?accion=totalVentasRealizadas', {}, function (r) {
        totalVentasRealizadas = r;
        $('#totalVentas').html(totalVentasRealizadas);
    });
}
/**
 * Esta función representa el total de compras realizadas
 * @returns {int} retorna un valor entero, representa el total de compras 
 * realizadas
 */
function totalComprasRealizadas() {
    var totalVentasRealizadas = '';
    $.get('../panelAdministrativo?accion=totalComprasRealizadas', {}, function (r) {
        totalVentasRealizadas = r;
        $('#totalCompras').html(totalVentasRealizadas);
    });
}
/**
 * Esta función representa el total de clientes registrados
 * @returns {int} retorna un valor entero, representa el total de clientes 
 * registrados
 */
function totalClientesRegistrados() {
    var totalVentasRealizadas = '';
    $.get('../panelAdministrativo?accion=totalClientesRegistrados', {}, function (r) {
        totalVentasRealizadas = r;
        $('#totalClientes').html(totalVentasRealizadas);
    });
}
/**
 * Esta función devuelve el monto total de caja
 * @returns {double} retorna un valor double, representa el monto total de ventas 
 * realizadas
 */
function montoTotalEnCaja() {
    var totalVentasRealizadas = '';
    $.get('../panelAdministrativo?accion=montoTotalEnCaja', {}, function (r) {
        totalVentasRealizadas = r;
        $('#dineroCaja').html("S/" + totalVentasRealizadas + ".00");
    });
}


