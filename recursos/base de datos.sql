use master 
go

create database Sistema
go

use Sistema
go

create table Categoria
(
idCategoria int identity (1,1) primary key,
categoria varchar(255) not null,
estado bit not null
)
go

insert into Categoria values ('Chocolates', 1)
insert into Categoria values ('Bebidas', 1)
insert into Categoria values ('Televisores OLED', 1)
insert into Categoria values ('Televisores QLED', 1)
go

select* from Categoria
go

create table Marca
(
idMarca int identity (1,1) primary key,
marca varchar(255) not null,
estado bit not null
)
go

insert into Marca values ('Donofrio', 1)
insert into Marca values ('Pepsi', 1)
insert into Marca values ('Coca Cola', 1)
insert into Marca values ('Inka Kola', 1)
insert into Marca values ('LG Electronics', 1)
insert into Marca values ('Samsung', 1)
go

select* from Marca
go

create table TipoIdentificacion
(
idTipoIdentificacion int identity (1,1) primary key,
nombreTipoIden varchar(255) not null,
estado bit not null,
)
go

INSERT INTO TIPOIDENTIFICACION VALUES ('DNI', 1), ('RUC', 1)
GO

create table Proveedor
(
idProveedor int identity(1,1) primary key,
razonSocial varchar(255) not null,
idTipoIdentificacion int foreign key references TipoIdentificacion(idTipoIdentificacion),
numeroDocumento char(11) not null,
direccion varchar(255) not null,
telefono char(11) not null,
email varchar(255) not null,
estado bit
)
go

insert into Proveedor values ('Coca Cola Company', 2, '20198745656', 'Lima - San Borja', '074281025', 'cocacola@gmail.com', 1)
insert into Proveedor values ('Donofrio Company', 2, '10496358274', 'Lima - San Isidro', '978456987', 'donofrio@gmail.com', 1)
insert into Proveedor values ('Gloria Company', 2, '20169358247', 'Lima - San Tomas', '654789231', 'gloria@live.com', 1)
insert into Proveedor values ('GN Company', 2, '20485296374', 'Lima - San Felipe', '258963147', 'gn@outlook.com', 1)
insert into Proveedor values ('Amazon', 2, '20485296374', 'EE.UU - New York', '0196325824', 'amazon@amazon.com', 1)
go

select* from Proveedor
go

create table Producto
(
idProducto int identity (1,1) primary key,
codigoProducto varchar(50) null,
producto varchar(255) not null,
descripcion varchar(255) not null,
precioVenta decimal(8,2) not null,
precioCompra decimal(8,2) not null,
stock smallint not null,
estado bit not null,
fechaRegistro date not null,
imagen varchar(500) not null,
idCategoria int foreign key references Categoria(idCategoria),
idMarca int foreign key references Marca(idMarca),
idProveedor int foreign key references Proveedor(idProveedor)
)
go

Insert Into Producto Values('LGOLED2022', 'LG OLED TV', 'LG 65" 4K 120Hz OLED Smart TV', '6000.00', '5000.00', 2, 1, '2022/02/22', 'http://localhost:8080/SistemaVentasWeb/imgProducts/lgOled.png', 3, 5, 1)
Go
select * from Producto

create table Empleado
(
idEmpleado int identity (1,1) primary key,
nombre varchar(255) not null,
apellidos varchar(255) not null,
idTipoIdentificacion int foreign key references TipoIdentificacion(idTipoIdentificacion),
numeroDocumento varchar(100) not null,
direccion varchar(255) not null,
telefono varchar(11) not null,
email varchar(255) not null,
fechaNacimiento date not null,
estado bit,
cargo varchar(100) not null,
userName varchar(50) not null,
clave varbinary(8000) not null
)
go 

INSERT INTO Empleado VALUES ('Jean Carlos', 'Ramos Moncayo', 1, '45685241', 'Chiclayo', '963258741', 'Jean@gmail.com', '1999-05-19', 1, 'Empleado', 'emp01', ENCRYPTBYPASSPHRASE('password', 'Jean'))
INSERT INTO Empleado VALUES ('Misael', 'Tejada Atoche', 1, '78963521', 'Chiclayo', '978564123', 'Misael@gmail.com', '1999-02-19', 1, 'Soporte Tecnico', 'emp02', ENCRYPTBYPASSPHRASE('password', 'Misael'))
INSERT INTO Empleado VALUES ('Ronald', 'Irigoin nunez', 1, '45789632', 'Chiclayo', '951753648', 'Ronald@gmail.com', '1999-04-19', 1, 'Empleado', 'emp03', ENCRYPTBYPASSPHRASE('password', 'Ronald'))
INSERT INTO Empleado VALUES ('Elmer', 'Olivera Yupanqui', 1, '14785256589', 'Chiclayo', '927415838', 'Elmer@gmail.com', '1999-01-19', 1, 'Empleado', 'emp04', ENCRYPTBYPASSPHRASE('password', 'Elmer'))
INSERT INTO Empleado VALUES ('Alexander', 'Fuentes Medina', 1, '78019778', 'Ferrenafe', '917967148', 'Alex@gmail.com', '1999-12-19', 1, 'Administrador', 'admin', ENCRYPTBYPASSPHRASE('password', 'Alexander'))
go

select* from empleado
go

create table Cliente
(
idCliente int identity (1,1) primary key,
nombresCompletos varchar(255) not null,
idTipoIdentificacion int foreign key references TipoIdentificacion(idTipoIdentificacion),
numeroDocumento varchar(100) not null,
departamento varchar(255) not null,
provincia varchar(255) not null,
distrito varchar(255) not null,
calle varchar(255) not null,
telefono varchar(11) not null,
email varchar(255) not null,
estado bit 
)
go

insert into Cliente values ('Flor de Maria Medina', 1, '78012585', 'Lambayeque', 'Ferrenafe', 'Ferrenafe', 'Miguel Grau 423', '999234567', 'florcita@gmail.com' ,1)
insert into Cliente values ('Flor de Yadhira Fuentes', 1, '63589741', 'Lambayeque', 'Chiclayo', 'Chiclayo', 'Saenz Pena 741', '978456321', 'yadhira@gmail.com' ,1)
insert into Cliente values ('Yuleisi Fernandez', 1, '45698741', 'Lambayeque', 'Chiclayo', 'Monsefu', 'San Jose 789', '987652344', 'yuleisi@gmail.com' ,1)
insert into Cliente values ('Mary Meono', 1, '23658974', 'Lambayeque', 'Reque', 'Santos', 'Pardo 258', '925471369', 'mary@gmail.com' ,1)
insert into Cliente values ('Juan Carlos', 1, '74852163', 'Lambayeque', 'Olmos', 'Tinajones', 'Miguel Pasco 124', '912365874', 'juanca@gmail.com' ,1)
go 

select* from Cliente
go

create table Venta
(
idVenta int identity (1,1) primary key,
tipoComprobante char(20) not null,
serie tinyint not null,
numero smallint not null,
estado bit not null,
fechaVenta date,
idCliente int foreign key references Cliente(idCliente),
idEmpleado int foreign key references Empleado(idEmpleado)
)
go

insert into venta values ('Boleta', '0001', '0000001', 1, '2019-08-13', 1, 1)
go

select* from venta
go

create table detalleVenta
(
idDetalleVenta int identity(1,1) primary key,
idVenta int foreign key references Venta(idVenta),
idProducto int foreign key references Producto(idProducto),
cantidad smallint not null,
precioVenta smallmoney not null
)
go

insert into detalleVenta values (1, 1, 3, 6000)
go

select* from detalleVenta
go

create table Compra
(
idCompra int identity (1,1) primary key,
idProveedor int foreign key references Proveedor(idProveedor),
serie tinyint not null,
numero smallint not null,
fecha date not null,
tipoComprobante char(20) not null,
impuesto smallint not null
)
go

insert into Compra values (1, '0001', '0000001', '2019-08-14', 'Boleta', 18)
go

select* from Compra
go

create table detalleCompra
(
idDetalleCompra int identity(1,1) primary key,
idCompra int foreign key references Compra(idCompra),
idProducto int foreign key references Producto(idProducto),
cantidad smallint not null,
precioCompra decimal (8,2) not null,
precioVenta decimal (8,2) not null,
)
go

insert into detalleCompra values (1, 1, 5, 5000, 6000)
go

select* from detalleCompra
go

/** 
	Consulta a la base de datos Sistema
*/
-- Total de Empleados
select COUNT(e.idEmpleado) as 'Empleados Registrados' from Empleado e
go
-- Mostrar el año, el mes Y el día de cumpleaños de los empleados
select YEAR(e.fechaNacimiento) as 'AÑO' , MONTH (e.fechaNacimiento) as 'MES', DAY (e.fechaNacimiento) as 'DIA'
from Empleado e
go
-- Mostrar el año y el mes de la fecha de nacimento de los empleados
select e.nombre+' '+e.apellidos as 'Empleado' ,YEAR(GETDATE()) - year(e.fechaNacimiento) as 'edad'
from Empleado e
go
-- Mostrar los empleados que cumplan años hoy
select e.nombre+''+e.apellidos as 'Empleado'
from Empleado e
where MONTH(e.fechaNacimiento) = MONTH(GETDATE())
go
-- Mostrar la contraseña del empleado
SELECT E.idEmpleado, E.nombre, E.apellidos, E.cargo, CONVERT(VARCHAR(MAX), DECRYPTBYPASSPHRASE('password', E.clave)) as [Llave]
FROM Empleado E
WHERE E.estado = 1 AND E.userName = 'admin'
GO
-- CONTAR LA CANTIDAD DE CLIENTES
SELECT COUNT(C.idCliente) AS 'totalClientes' FROM Cliente C
GO
-- CONTAR LA CANTIDAD DE VENTAS
SELECT COUNT(V.idVenta) AS 'totalVentas' FROM Venta V
GO
-- CONTAR LA CANTIDAD DE COMPRAS
SELECT COUNT(C.idCompra) AS 'totalCompras' FROM Compra C
GO
-- TOTAL DE CAJA
SELECT SUM(DV.precioVenta * Dv.cantidad) AS 'totalCaja' FROM detalleVenta DV
GO