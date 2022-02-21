$(document).ready(function () {
    const DOM = {
        mdlRequestPasswordChange: $("#mdlRequestPasswordChange"),
        mdlChangePassword: $("#mdlChangePassword"),
        frmRequestPasswordChange: $("#frmRequestPasswordChange"),
        frmChangePassword: $("#frmChangePassword"),
        frmIdentificar: $("#frmIdentificar"),
        txtLogin: $("#txtLogin"),
        txtClave: $("#txtClave"),
        txtNameUser: $("#txtNameUser")
    };
    DOM.frmIdentificar.on("submit", function (e) {
        e.preventDefault();
        iniciarSesion();
    });
    DOM.frmRequestPasswordChange.on("submit", function (e) {
        e.preventDefault();
        searchEmployeeByUserName();
    });
    DOM.frmChangePassword.on("submit", function (e) {
        e.preventDefault();
        changePassword();
    });
    /**
     * Está función permite identificarse al empleado con sus credenciales para
     * ello verifica en la base de datos si el usuario existe y está con el 
     * estado activo.
     * @returns {Boolean} Retorna un valor booleano, representa si la identifi-
     * cación fue exitosa o no.
     */
    function iniciarSesion() {
        var obj = {
            login: DOM.txtLogin.val(),
            clave: DOM.txtClave.val()};
        $.post("usuarios?accion=ve",
                {datos: JSON.stringify(obj)},
                function (data) {
                    if (data.rpt) {
                        swal('Mensaje del Sistema', data.msj, 'success');
                        setTimeout(function () {
                            window.location.href = "vista/inicio.jsp";
                        }, 1500);
                    } else {
                        swal('Mensaje del Sistema', data.msj, 'error');
                    }
                });
    }
    /**
     * Está función busca el empleado por su user name en la base de datos.
     * @returns {Boolean} Retorna un valor booleano, representa si la busqueda
     * fue correcta o no
     */
    function searchEmployeeByUserName() {
        $.ajax({
            url: "usuarios?accion=solicitarCambioContrasenia",
            type: 'post',
            dataType: 'json',
            data: {login: DOM.txtNameUser.val()},
            success: function (data) {
                if (data.rpta) {
                    swal('Mensaje del Sistema', data.msje, 'success');
                    DOM.mdlRequestPasswordChange.modal('hide');
                    DOM.mdlChangePassword.modal('show');
                    $("#login").val(data.body.login);
                    $("#nombreEmpleado").val(data.body.nombre);
                    DOM.txtNameUser.val("");
                } else {
                    swal('Mensaje del Sistema', data.msje, 'error');
                }
            }, error: function (x, y) {
                swal('Mensaje del Sistema', "No se logro hacer la petición",
                        'error');
                console.log(x.responseText);
            }
        });
    }

    /**
     * Esta función actualiza la contraseña del empleado
     * @returns {Boolean} retorna un valor booleano, representa si la
     * actualización fue exitosa o no.
     */
    function changePassword() {
        const obj = {
            login: $('#login').val(),
            clave: $('#txtPassword').val()
        };
        if ($('#txtPassword').val() === $('#txtPassword2').val()) {
            $.ajax({
                url: "usuarios?accion=cambiarContrasenia",
                type: 'post',
                data: (obj),
                success: function (data) {
                    if (data.rpt) {
                        swal("Mensaje del Sistema", data.msj, "success");
                        DOM.mdlChangePassword.modal('hide');
                        DOM.mdlRequestPasswordChange.modal('hide');
                        $('#txtPassword').val("");
                        $('#txtPassword2').val("");
                    } else {
                        swal("Error", data.msj, "error");
                    }
                }, error: function (x, y) {
                    swal("Error", "No se pudo realizar la operación", "error");
                }
            });
        } else {
            swal("Error", "Las contraseñas no coinciden", "error");
            $('#txtPassword').val("");
            $('#txtPassword2').val("");
        }
    }
});