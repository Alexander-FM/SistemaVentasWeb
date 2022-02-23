var tabla = $("table#tablaProductos");
$(document).ready(function () {
    listarProductos();
});
/**
 * Esta función retorna una lista de productos
 * @returns {List} retorna una lista.
 */
function listarProductos() {
    $.ajax({
        url: "../Producto?accion=li",
        type: 'GET',
        dataType: 'JSON',
        success: function (data) {
            var tpl = "";
            for (var i = 0; i < data.length; i++) {
                tpl += "<tr class=\"text-center\">"
                        + "<td>" + data[i].codigoProducto + "</td>"
                        + "<td>" + data[i].producto + "</td>"
                        + "<td style=\"text-align: center\"><img src='" + data[i].imagen + "' width='50' height='50'></td>"
                        + "<td>" + data[i].descripcion + "</td>"
                        + "<td> S/" + data[i].precioVenta + ".00</td>"
                        + "<td> S/" + data[i].precioCompra + ".00</td>"
                        + "<td>" + data[i].stock + "</td>"
                        + "<td>" + (data[i].estado === true ? '<h5><span class =\"label label-success\">Activa</span></h5>' : '<h5><span class =\"label label-danger\">Inactiva</span></h5>') + "</td>"
                        + "<td>" + data[i].categoria.categoria + "</td>"
                        + "<td>" + data[i].marca.marca + "</td>"
                        + "<td nowrap><button title=\"Editar\" class=\"btn btn-warning\">"
                        + "<span class=\"fa fa-edit\"></span></button> "
                        + "<button title=\"Desactivar\" class=\"btn btn-danger\">"
                        + "<span class=\"fa fa-trash\"></span></button></td>"
                        + "</tr>";
            }
            tabla.find("tbody").html(tpl);
            tabla.dataTable();
        }
    });
}