var tabla = $("table#tablaAdministrarVentas");
$(document).ready(function () {
  let li_grupoVentas = $('#li_grupoVentas');
  li_grupoVentas.attr('class', 'treeview active menu-open'); //Añadimos la clase
  let li_administrarVentas = $('#li_administrarVentas');
  li_administrarVentas.attr('class', 'active');
  listarVentas();
});
/**
 * Esta función retorna una lista de ventas
 * @returns {List} retorna una lista.
 */
function listarVentas() {
  $.ajax({
    url: "../AdministrarVentas?accion=listarVentas",
    type: 'GET',
    dataType: 'JSON',
    success: function (data) {
      var tpl = "";
      for (var i = 0; i < data.length; i++) {
        tpl += "<tr class=\"text-center\">"
            + "<td>" + data[i].idVenta + "</td>"
            + "<td>" + data[i].tipoComprobante.tipoComprobante + "</td>"
            + "<td>" + data[i].serieComprobante + "</td>"
            + "<td>" + data[i].numeroComprobante + "</td>"
            + "<td>" + data[i].total + "</td>"
            + "<td>" + data[i].impuesto + "</td>"
            + "<td>" + data[i].fecha + "</td>"
            + "<td>" + data[i].empleado.nombre + "</td>"
            + "<td>" + data[i].cliente.nombres + "</td>"
            + "<td>" + (data[i].estado === true ? '<span class =\"label label-success\">No Anulada</span>' : '<span class =\"label label-danger\">Anulada</span>') + "</td>"
            + "<td nowrap><button title=\"Ver Detalle\" class=\"btn btn-info\">"
            + "<span class=\"fa fa-eye\"></span></button> "
            + "<button title=\"Anular Venta\" class=\"btn btn-danger\">"
            + "<span class=\"fa fa-trash\"></span></button></td>"
            + "</tr>";
      }
      tabla.find("tbody").html(tpl);
      tabla.dataTable();
    }
  });
}