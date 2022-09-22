<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
 <!-- ===== Bootstrap ===== -->
 <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-1BmE4kWBq78iYhFldvKuhfTAU6auU8tT94WrHftjDbrCEXSU1oBoqyl2QvZ6jIW3" crossorigin="anonymous">	
 <!-- ===== CSS ===== -->
 <link rel="stylesheet" href="./css/principal.css">
 <!-- ===== Icono ===== -->    
 <link rel="icon" href="img/minilogo.ico" type="image/x-icon">
<title>Principal</title>
</head>
<body id="body-pd">

	<!-- Sección Menu Sucursal -->
	<nav class="navbar navbar-dark bg-dark">
		<div class="container-fluid">
			<a class="title navbar-brand" href="#">
				<img src="img/logoAstra1.jpg" width="40" height="40" class="d-inline-block align-text-top">
				Cadena de Tiendas "Astra Gaming" - Sucursal: <span id="mensaje"></span>
			</a>
		</div>
	</nav>
	
	<!-- Barra de Menú lateral  -->
	<div class="l-navbar" id="navbar">
            <nav class="nav">
                <div>
                    <div class="nav__brand">
                        <ion-icon name="menu-outline" class="nav__toggle" id="nav-toggle"></ion-icon>
                        <a href="#" class="nav__logo">Astra Gaming</a>
                    </div>
                    <div class="nav__list">
                        <a href="Controlador?menu=Principal" target="myFrame" class="nav__link active" onclick="return validarMenu('home')">
                            <ion-icon name="home-outline" class="nav__icon"></ion-icon>
                            <span class="nav__name">Home</span>
                        </a>
                        <a href="Controlador?menu=Usuarios&accion=Listar" target="myFrame" class="nav__link" onclick="return validarMenu('usuarios')">
                            <ion-icon name="people-outline" class="nav__icon"></ion-icon>
                            <span class="nav__name">Usuarios</span>
                        </a>
                        <a href="Controlador?menu=Clientes&accion=Listar" target="myFrame" class="nav__link" onclick="return validarMenu('clientes')">
                            <ion-icon name="briefcase-outline" class="nav__icon"></ion-icon>                            
                            <span class="nav__name">Clientes</span>
                        </a>
                        <a href="Controlador?menu=Proveedores&accion=Listar" target="myFrame" class="nav__link" onclick="return validarMenu('proveedores')">
                            <ion-icon name="cart-outline" class="nav__icon"></ion-icon>          
                            <span class="nav__name">Proveedores</span>
                        </a>
                        <a href="Controlador?menu=Productos&accion=Listar" target="myFrame" class="nav__link" onclick="return validarMenu('productos')">
                            <ion-icon name="document-text-outline" class="nav__icon"></ion-icon>                           
                            <span class="nav__name">Productos</span>
                        </a>                      
                        <a href="Controlador?menu=Ventas&accion=default&UsuarioActivo=${usuario.getCedula_usuario()}" target="myFrame" class="nav__link" onclick="return validarMenu('ventas')">
                            <ion-icon name="grid-outline" class="nav__icon"></ion-icon>                           
                            <span class="nav__name">Ventas</span>
                        </a>
                        <a href="Controlador?menu=Reportes&accion=default" target="myFrame" class="nav__link" onclick="return validarMenu('reportes')">
                            <ion-icon name="grid-outline" class="nav__icon"></ion-icon>                           
                            <span class="nav__name">Reportes</span>
                        </a>
                        <a href="Controlador?menu=Consolidado&accion=default" target="myFrame" class="nav__link" onclick="return validarMenu('consolidado')">
                            <ion-icon name="grid-outline" class="nav__icon"></ion-icon>                           
                            <span class="nav__name">Consolidado</span>
                        </a>
                    </div>
                </div>

                <a href="Inicio.jsp" class="nav__link">
                    <ion-icon name="log-out-outline" class="nav__icon"></ion-icon>
                    <span class="nav__name">Cerrar Sesión</span>
                </a>   
            </nav>
        </div>
        <div class="m-2" style="height: 800px" >
            <iframe style="height: 100%; width: 100%; border: none" name="myFrame" src="Home.jsp">                
            </iframe>
        </div>        
        <!-- ===== IONICONS ===== -->
        <script src="https://unpkg.com/ionicons@5.1.2/dist/ionicons.js"></script>
        
        <!-- ===== Funciones de JS ===== -->
        <script src="js/principal.js"></script>
        <script src="js/sucursal.js"></script>
</body>
</html>