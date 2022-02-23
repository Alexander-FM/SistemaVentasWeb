<%@page contentType="text/html" pageEncoding="UTF-8"%>
<html>
    <head>
        <meta charset="utf-8">
        <meta http-equiv="X-UA-Compatible" content="IE=edge">
        <title>Sistema Ventas | Iniciar Sesión</title>
        <link rel="icon" href="imagenes/perfil.png">
        <!-- Tell the browser to be responsive to screen width -->
        <meta content="width=device-width, initial-scale=1, maximum-scale=1, user-scalable=no" name="viewport">
        <!-- Bootstrap 3.3.7 -->
        <link href="bower_components/bootstrap/dist/css/bootstrap.min.css" rel="stylesheet" type="text/css"/>
        <!-- Font Awesome -->
        <link href="bower_components/font-awesome/css/font-awesome.min.css" rel="stylesheet" type="text/css"/>
        <!-- Ionicons -->
        <link href="bower_components/Ionicons/css/ionicons.min.css" rel="stylesheet" type="text/css"/>
        <!-- Theme style -->
        <link href="dist/css/AdminLTE.min.css" rel="stylesheet" type="text/css"/>
        <!-- iCheck -->
        <link href="plugins/iCheck/square/blue.css" rel="stylesheet" type="text/css"/>
        <!-- Sweet Alert -->
        <link href="plugins/sweetAlert/sweetalert.css" rel="stylesheet" type="text/css"/>
        <!-- Google Font -->
        <link rel="stylesheet" href="https://fonts.googleapis.com/css?family=Source+Sans+Pro:300,400,600,700,300italic,400italic,600italic">
    </head>
    <body class="hold-transition login-page" style="font-family: Roboto, sans-serif;">
        <div class="login-box">
            <div class="login-logo">
                <a href="#"><b>SISTEMA </b>VENTAS</a>
            </div>
            <!-- /.login-logo -->
            <div class="login-box-body" style="border-radius: 15px">
                <p class="login-box-msg">Iniciar Sesión</p>
                <form id="frmIdentificar">
                    <div class="form-group has-feedback">
                        <input type="text" class="form-control" required="" id="txtLogin" placeholder="Usuario">
                        <span class="glyphicon glyphicon-user form-control-feedback"></span>
                    </div>
                    <div class="form-group has-feedback">
                        <input type="password" class="form-control" required="" id="txtClave" placeholder="Password">
                        <span class="glyphicon glyphicon-lock form-control-feedback"></span>
                    </div>
                    <div class="row">
                        <div class="col-xs-7">
                            <div class="checkbox icheck">
                                <label>
                                    <input type="checkbox"> Recuérdame
                                </label>
                            </div>
                        </div>
                        <!-- /.col -->
                        <div class="col-xs-5">
                            <button type="submit" class="btn btn-primary btn-block btn-flat">Iniciar Sesión</button>
                        </div>
                        <!-- /.col -->
                    </div>
                </form>
                <a href="#mdlRequestPasswordChange" data-toggle="modal" data-backdrop="static" data-keyboard="false">Olvidé mi contraseña</a><br>
            </div>
            <!-- /.login-box-body -->
        </div>
        <!-- Modal para solicitud de cambio de contraseña -->
        <div class="modal fade" id="mdlRequestPasswordChange">
            <div class="modal-dialog">
                <div class="modal-content" style="border-radius: 15px">
                    <div class="modal-header">
                        <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                            <span aria-hidden="true"><i class="fa fa-close"></i></span>
                        </button>
                        <h4 class="modal-title">Solicitud para cambio de contraseña</h4>
                    </div>
                    <div class="modal-body">
                        <form id="frmRequestPasswordChange">
                            <div class="row">
                                <div class="col-md-10">
                                    <div class="form-group">
                                        <label>Ingresa el nombre de usuario</label>
                                        <div class="input-group">
                                            <span class="input-group-addon"><i class="fa fa-user-circle"></i></span>
                                            <input id="txtNameUser" type="text" class="form-control">
                                        </div>
                                    </div>
                                </div>
                                <div class="col-md-2">
                                    <button id="btnChangePass" type="submit" style="margin-top: 25px" class="btn btn-primary pull-right">Siguiente
                                        <i class="fa fa-chevron-right"></i></button>
                                </div>
                            </div>
                        </form>
                    </div>
                </div><!-- /.modal-content -->
            </div><!-- /.modal-dialog -->
        </div>
        <!-- Modal para actualizar contraseña -->
        <div class="modal fade" id="mdlChangePassword">
            <div class="modal-dialog">
                <div class="modal-content" style="border-radius: 15px">
                    <div class="modal-header">
                        <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                            <span aria-hidden="true"><i class="fa fa-close"></i></span>
                        </button>
                        <h4 class="modal-title">Ingrese su nueva contraseña</h4>
                    </div>
                    <div class="modal-body">
                        <input type="hidden" id="login"><!-- Guardamos el login en un input de tipo de hidden -->
                        <p>Hola, <b style="color: #0b3e6f" id="nombreEmpleado">Luigui Alexander Fuentes Medina</b>, estás a solo un paso de su nueva contraseña, recupere su
                            contraseña ahora.</p>
                        <form id="frmChangePassword">
                            <div class="form-group">
                                <label>Ingresa tu nueva contraseña</label>
                                <div class="input-group">
                                    <span class="input-group-addon"><i class="fa fa-lock"></i></span>
                                    <input id="txtPassword" required type="password" class="form-control">
                                </div>
                            </div>
                            <div class="form-group">
                                <label>Confirma tu contraseña</label>
                                <div class="input-group">
                                    <span class="input-group-addon"><i class="fa fa-lock"></i></span>
                                    <input id="txtPassword2" required type="password" class="form-control">
                                </div>
                            </div>
                            <div class="row">
                                <div class="col-12">
                                    <button id="btnSaveChange" type="submit" class="btn btn-success pull-right" style="margin-right: 16px">
                                        Cambiar Contraseña <i class="fa fa-chevron-right"></i>
                                    </button>
                                </div>
                            </div>
                        </form>
                    </div>
                </div><!-- /.modal-content -->
            </div><!-- /.modal-dialog -->
        </div>
        <!-- jQuery 3 -->
        <script src="bower_components/jquery/dist/jquery.min.js" type="text/javascript"></script>
        <!-- Bootstrap 3.3.7 -->
        <script src="bower_components/bootstrap/dist/js/bootstrap.min.js" type="text/javascript"></script>
        <!-- iCheck -->
        <script src="plugins/iCheck/icheck.min.js" type="text/javascript"></script>
        <!-- Sweet Alert -->
        <script src="plugins/sweetAlert/sweetalert.js" type="text/javascript"></script>
        <!-- Script session -->
        <script src="js/scriptSesion.js" type="text/javascript"></script>
        <script>
            $(function () {
                $('input').iCheck({
                    checkboxClass: 'icheckbox_square-blue',
                    radioClass: 'iradio_square-blue',
                    increaseArea: '20%' /* optional */
                });
            });
        </script>
    </body>
</html>