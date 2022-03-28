var tabla = $("table#tablaProductos"),
        cboProveedor = $("#cboProveedor"),
        cboCategoria = $("#cboCategoria"),
        cboMarca = $("#cboMarca");
$(document).ready(function () {
    /** Con esto aplicamos la clase active el menu correspondiente */
    let li_grupoRegistros = $('#li_grupoRegistros');
    li_grupoRegistros.attr('class', 'treeview active menu-open'); //Añadimos la clase
    let li_productos = $('#li_productos');
    li_productos.attr('class', 'active');
    tabla.on("click", ".btn-danger", function () {
        var id = $(this).parents("tr").children()[0].textContent;
        eliminar(id);
    });
    tabla.on("click", ".btn-warning", function () {
        var id = $(this).parents("tr").children()[0].textContent;
        leerProducto(id);
        $('#modal-actualizarProductos').modal({backdrop: 'static', keyboard: false});
    });
    listarProductos();
    listarCategorias();
    listarMarcas();
    listarProveedores();
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
                        + "<td>" + data[i].codigo + "</td>"
                        + "<td>" + data[i].codigoProducto + "</td>"
                        + "<td>" + data[i].producto + "</td>"
                        + "<td style=\"text-align: center\"><img src='" + data[i].imagen + "' width='50' height='50'></td>"
                        + "<td>" + data[i].descripcion + "</td>"
                        + "<td> S/" + data[i].precioVenta + ".00</td>"
                        + "<td> S/" + data[i].precioCompra + ".00</td>"
                        + "<td>" + data[i].stock + "</td>"
                        + "<td>" + (data[i].estado === true ? '<span class =\"label label-success\">Activa</span>' : '<span class =\"label label-danger\">Inactiva</span>') + "</td>"
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
/**
 * Esta funcion devuelve una lista de proveedores
 * @returns {List} retorna una lista, representa a los proveedores
 */
function listarProveedores() {
    $.ajax({
        url: "../Producto?accion=liProveedores",
        type: 'GET',
        dataType: 'JSON',
        success: function (data) {
            var combo_proveedores = '';
            for (var i = 0; i < data.length; i++) {
                combo_proveedores += '<option value="' + data[i].codigo + '">' + data[i].razonSocial + '</option>';
            }
            cboProveedor.html(combo_proveedores);
            $('#cboProveedorAc').html(combo_proveedores);//For Update
        }
    });
}
/**
 * Esta funcion devuelve una lista de categorias
 * @returns {List} retorna una lista, representa a los categorias
 */
function listarCategorias() {
    $.ajax({
        url: "../Producto?accion=liCategorias",
        type: 'GET',
        dataType: 'JSON',
        success: function (data) {
            var combo_categoria = '';
            for (var i = 0; i < data.length; i++) {
                combo_categoria += '<option value="' + data[i].codigo + '">' + data[i].categoria + '</option>';
            }
            cboCategoria.html(combo_categoria);
            $('#cboCategoriaAc').html(combo_categoria);//FOR UPDATE
        }
    });
}
/**
 * Esta funcion devuelve una lista de marcas
 * @returns {List} retorna una lista, representa a los marcas
 */
function listarMarcas() {
    $.ajax({
        url: "../Producto?accion=liMarcas",
        type: 'GET',
        dataType: 'JSON',
        success: function (data) {
            var combo_marcas = '';
            for (var i = 0; i < data.length; i++) {
                combo_marcas += '<option value="' + data[i].codigo + '">' + data[i].marca + '</option>';
            }
            cboMarca.html(combo_marcas);
            $('#cboMarcaAc').html(combo_marcas);//FOR UPDATE
        }
    });
}
function eliminar(idTemp) {
    swal({
        title: "Esta seguro de eliminar?",
        text: "Una vez desactivado ya no estará disponible!",
        type: "warning",
        showCancelButton: true,
        confirmButtonClass: "btn-danger",
        confirmButtonText: "Si, eliminar!",
        cancelButtonText: "No, cancelar!",
        closeOnConfirm: false,
        closeOnCancel: false
    },
            function (isConfirm) {
                if (isConfirm) {
                    $.ajax({
                        url: "../Producto?accion=eli",
                        type: 'POST',
                        dataType: 'json',
                        data: {cod: idTemp},
                        success: function (data) {
                            swal("Desactivado!", data.msj, "success");
                            listarProductos();
                        }
                    });
                } else {
                    swal("Cancelado", "Petición cancelada!", "error");
                }
            });
}

function formaterFecha(timestamp) {
    var datetime = new Date(timestamp);
    var dia = datetime.getDate();
    var mes = datetime.getMonth() + 1;
    var anio = datetime.getFullYear();

    var fecha = concatenarCero([dia, mes, anio]);
    var fecha_string = fecha[2] + '-' + fecha[1] + '-' + fecha[0];
    //Esto es para concatenar las horas, en mi caso, no lo haré, porque solo
    //necesito la fecha.
    var horas = datetime.getHours();
    var minutos = datetime.getMinutes();
    var segundos = datetime.getSeconds();

    var hora = concatenarCero([horas, minutos, segundos]);
    var hora_string = hora[0] + ':' + hora[1] + ':' + hora[2];
    //Retorno solo la fecha formateada.
    return fecha_string;
}

function concatenarCero(numeros) {
    for (var i = 0; i < numeros.length; i++) {
        if (numeros[i] < 10)
            numeros[i] = "0" + numeros[i];
    }
    return numeros;
}

function leerProducto(idTemp) {
    $.ajax({
        url: "../Producto?accion=pr",
        type: 'POST',
        dataType: 'json',
        data: {id: idTemp},
        success: function (data) {
            $('#idProd').val(data.codigo);
            $('#fechaProd').val(formaterFecha(data.fechaRegistro));
            $('#codigoProductoAc').val(data.codigoProducto);
            $('#productoAc').val(data.producto);
            $('#descripcionAc').val(data.descripcion);
            $('#precioVentaAc').val(data.precioVenta);
            $('#precioCompraAc').val(data.precioCompra);
            $('#stockAc').val(data.stock);
            $('#cboCategoriaAc').val(data.categoria.codigo);
            $('#cboMarcaAc').val(data.marca.codigo);
            $('#cboProveedorAc').val(data.proveedor.codigo);
            $('#imagenProdAc').attr('src', data.imagen);
            $('#chkVigenciaAc').prop('checked', data.estado);
            $('#txtConservarImagen').val(data.imagen);
        }
    });
}
function cambiarPropiedad() {
    var uploadImg = $('#imagenAc'),
            chkConservarImagen = $('#chkConservarImagen');
    if ((chkConservarImagen).is(':checked')) {
        uploadImg.attr('disabled', '');
        uploadImg.removeAttr('required');
    } else {
        uploadImg.removeAttr('disabled');
        uploadImg.attr('required', '');
    }
}