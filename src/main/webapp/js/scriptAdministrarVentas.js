const tabla = $("table#tablaAdministrarVentas"),
        tablaDetalleVenta = $("table#tablaDetalleVenta"),
        tablaBuscarProducto = $("table#tablaBuscarProductos"),
        mdlDetalleVenta = $("#mdlDetalleVenta");
$(document).ready(function () {
    /** Con esto aplicamos la clase active el menu correspondiente */
    let li_grupoVentas = $('#li_grupoVentas');
    li_grupoVentas.attr('class', 'treeview active menu-open'); //Añadimos la clase
    let li_administrarVentas = $('#li_administrarVentas');
    li_administrarVentas.attr('class', 'active');
    /** Llamamos a las funciones*/
    listarVentas();
    listarProductoConStock();
    tabla.on("click", ".btn-info", function () {
        const idVenta = $(this).parents("tr").children()[0].textContent;
        listarDetalleVenta(idVenta);
        mdlDetalleVenta.modal({backdrop: 'static', keyboard: false});
    });
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
            let tpl = "";
            for (var i = 0; i < data.length; i++) {
                tpl += "<tr class=\"text-center\">"
                        + "<td>" + data[i].idVenta + "</td>"
                        + "<td>" + data[i].tipoComprobante.tipoComprobante + "</td>"
                        + "<td>" + data[i].serieComprobante + "</td>"
                        + "<td>" + data[i].numeroComprobante + "</td>"
                        + "<td>S/. " + data[i].total + ".00</td>"
                        + "<td>S/. " + data[i].impuesto + ".00</td>"
                        + "<td>" + data[i].fecha + "</td>"
                        + "<td>" + data[i].empleado.nombre + "</td>"
                        + "<td>" + data[i].cliente.nombres + "</td>"
                        + "<td>" + (data[i].estado === true
                                ? '<span class =\"label label-success\">No Anulada</span>'
                                : '<span class =\"label label-danger\">Anulada</span>')
                        + "</td>"
                        + "<td nowrap><button title=\"Ver Detalle\" class=\"btn btn-info\">"
                        + "<span class=\"fa fa-eye\"></span></button> "
                        + "<button id=\"btnAnularVenta\" onclick=\"anularVenta('" + data[i].idVenta
                        + "')\" title=\"Anular Venta\" class=\"btn btn-danger\">"
                        + "<span class=\"fa fa-trash\"></span></button></td>"
                        + "</tr>";
            }
            tabla.find("tbody").html(tpl);
            tabla.dataTable();
        }
    });
}

/**
 * Este función muestra el detalle de la venta en un modal
 * @param idVenta
 */
function listarDetalleVenta(idVenta) {
    $.ajax({
        url: "../AdministrarVentas?accion=listarDetalleVenta",
        type: 'POST',
        dataType: 'json',
        data: {idVenta: idVenta},
        success: function (data) {
            let tablaA = '';
            data.forEach(dv => {
                tablaA += '<tr class="text-center">';
                tablaA += '<td>' + dv.producto.producto + '</td>';
                tablaA += '<td><img src="' + dv.producto.imagen
                        + '" width="100" height="100"></td>';
                tablaA += '<td>' + dv.cantidad + '</td>';
                tablaA += '<td>S/. ' + dv.precioVenta + '.00</td>';
                tablaA += '<td>S/. ' + dv.precioVenta * dv.cantidad + '.00</td>';
                tablaA += '</tr>';
            });
            tablaDetalleVenta.find("tbody").html(tablaA);
            tablaDetalleVenta.dataTable();
        }
    });
}

/**
 * Esta función retorna una lista con los producto con stock
 * @returns {List} retorna una lista.
 */
function listarProductoConStock() {
    $.ajax({
        url: "../AdministrarVentas?accion=listarProductoConStock",
        type: 'GET',
        dataType: 'JSON',
        success: function (data) {
            let tpl = "";
            for (var i = 0; i < data.length; i++) {
                tpl += "<tr class=\"text-center\">"
                        + "<td>" + data[i].producto + "</td>"
                        + "<td style=\"text-align: center\"><img src='" + data[i].imagen + "' width='50' height='50'></td>"
                        + "<td>" + data[i].stock + "</td>"
                        + "<td>S/. " + data[i].precioVenta + ".00</td>"
                        + "<td>" + data[i].marca.marca + "</td>"
                        + "<td><a href=\"../AdministrarVentas?accion=agregarProductoBoleta&cod=" + data[i].codigo + "\" class=\"btn btn-success\"><i class=\"fa fa-plus\"></i> Agregar</a></td>"
                        + "</tr>";
            }
            tablaBuscarProducto.find("tbody").html(tpl);
            tablaBuscarProducto.DataTable({
                "language": {
                    "lengthMenu": "Mostrar _MENU_ registros por página",
                    "zeroRecords": "Ups! No se encontro nada",
                    "info": "Mostrando página _PAGE_ de _PAGES_",
                    "infoEmpty": "No se encontraron registros",
                    "infoFiltered": "(filtrado de _MAX_ total totales)",
                    "search": "Buscar:",
                    "paginate": {
                        "next": "Anterior",
                        "previous": "Siguiente"
                    }
                }
            });
        }
    });
}

/**
 * Este función anula la venta y actualiza el stock de ese producto.
 * @param idVenta
 */
function anularVenta(idVenta) {
    swal({
        title: "Está seguro que desea anular la venta?",
        text: "Una vez anulada, el stock del producto vendido se actualizará, y no sé podrá deshacer",
        type: "warning",
        showCancelButton: true,
        confirmButtonClass: "btn-danger",
        confirmButtonText: "Si, confirmar!",
        cancelButtonText: "No, cancelar!",
        closeOnConfirm: false,
        closeOnCancel: false
    },
            function (isConfirm) {
                if (isConfirm) {
                    $.ajax({
                        url: "../AdministrarVentas?accion=anularVenta",
                        type: 'POST',
                        dataType: 'json',
                        data: {id: idVenta, cantProd: 3, idProd: 1},
                        success: function (data) {
                            swal("Buen Trabajo !", data.msj, "success");
                            //$('#btnAnularVenta').remove();
                            listarVentas();
                        }
                    });
                } else {
                    swal("Cancelado", "Petición cancelada!", "error");
                }
            });
}
