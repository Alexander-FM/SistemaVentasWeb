var tabla = $("table#tablaProductos"),
        cboProveedor = $("#cboProveedor"),
        cboCategoria = $("#cboCategoria"),
        cboMarca = $("#cboMarca");
$(document).ready(function () {
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
            //$('#combo_proveedorAc').html(combo_proveedores);//For Update
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
            //$('#combo_categoriaAc').html(combo_categorias);//FOR UPDATE
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
            //$('#combo_marcaAc').html(combo_marcas);//FOR UPDATE
        }
    });
}