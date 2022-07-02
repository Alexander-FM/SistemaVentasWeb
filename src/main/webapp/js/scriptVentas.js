$(document).ready(function () {
    /** Con esto aplicamos la clase active el menu correspondiente */
    let li_grupoRegistros = $('#li_grupoVentas');
    li_grupoRegistros.attr('class', 'treeview active menu-open'); //Añadimos la clase
    let li_productos = $('#li_nuevaVenta');
    li_productos.attr('class', 'active');
    $('.select2').select2();
});
