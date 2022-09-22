<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html lang="es">
<head>
	<meta charset="UTF-8">
	<meta name="viewport" content="width=device-width, initial-scale=1.0">
	<title>Registro Usuario</title>
	<link rel="stylesheet" href="./css/newusuario.css">
	<link rel="icon" href="img/minilogo.ico" type="image/x-icon">
</head>
<body>
	<main class="form">
		<h2>Registro Usuario</h2>
		<form class="formulario" id="formulario" method="get" action="Controlador">
			
			<input type="hidden" name="menu" value="New_usuarios"> 
			<input type="hidden" name="txtid" value="${usuarioSeleccionado.get_id()}">
			<!-- Grupo: Cédula -->
			<div class="formulario__grupo" id="grupo__telefono">
				<label for="telefono" class="formulario__label">Cédula</label>
				<div class="formulario__grupo-input">
					<input type="text" class="formulario__input" name="txtcedula" id="telefono" placeholder="1069235147" value="${usuarioSeleccionado.getCedula_usuario()}">
					<i class="formulario__validacion-estado fas fa-times-circle"></i>
				</div>
				<p class="formulario__input-error">La cédula solo puede contener numeros y el maximo son 14 dígitos.</p>
			</div>

			<!-- Grupo: Nombre -->
			<div class="formulario__grupo" id="grupo__nombre">
				<label for="nombre" class="formulario__label">Nombre</label>
				<div class="formulario__grupo-input">
					<input type="text" class="formulario__input" name="txtnombre" id="nombre" placeholder="invitado" value="${usuarioSeleccionado.getNombre_usuario()}">
					<i class="formulario__validacion-estado fas fa-times-circle"></i>
				</div>
				<p class="formulario__input-error">El usuario tiene que ser de 4 a 16 dígitos y solo puede contener numeros, letras y guion bajo.</p>
			</div>			

			<!-- Grupo: Correo Electronico -->
			<div class="formulario__grupo" id="grupo__correo">
				<label for="correo" class="formulario__label">Correo Electrónico</label>
				<div class="formulario__grupo-input">
					<input type="email" class="formulario__input" name="txtemail" id="correo" placeholder="correo@correo.com" value="${usuarioSeleccionado.getEmail_usuario()}">
					<i class="formulario__validacion-estado fas fa-times-circle"></i>
				</div>
				<p class="formulario__input-error">El correo solo puede contener letras, numeros, puntos, guiones y guion bajo.</p>
			</div>

			<!-- Session: Usuario -->
			<div class="formulario__grupo" id="grupo__usuario">
				<label for="usuario" class="formulario__label">Usuario</label>
				<div class="formulario__grupo-input">
					<input type="text" class="formulario__input" name="txtusuario" id="usuario" placeholder="invitado" value="${usuarioSeleccionado.getUsuario()}">
					<i class="formulario__validacion-estado fas fa-times-circle"></i>
				</div>
				<p class="formulario__input-error">El usuario tiene que ser de 4 a 16 dígitos y solo puede contener numeros, letras y guion bajo.</p>
			</div>

			<!-- Session: Contraseña -->
			<div class="formulario__grupo" id="grupo__password">
				<label for="password" class="formulario__label">Contraseña</label>
				<div class="formulario__grupo-input">
					<input type="password" class="formulario__input" name="txtpassword" id="password" value="${usuarioSeleccionado.getPassword()}">
					<i class="formulario__validacion-estado fas fa-times-circle"></i>
				</div>
				<p class="formulario__input-error">La contraseña tiene que ser de 4 a 12 dígitos.</p>
			</div>

			<!-- Sesssion: Contraseña 2 -->
			<div class="formulario__grupo" id="grupo__password2">
				<label for="password2" class="formulario__label">Repetir Contraseña</label>
				<div class="formulario__grupo-input">
					<input type="password" class="formulario__input" name="password2" id="password2">
					<i class="formulario__validacion-estado fas fa-times-circle"></i>
				</div>
				<p class="formulario__input-error">Ambas contraseñas deben ser iguales.</p>
			</div>						

		

			<div class="formulario__mensaje" id="formulario__mensaje">
				<p><i class="fas fa-exclamation-triangle"></i> <b>Error:</b> Por favor rellena el formulario correctamente. </p>
			</div>

			<div class="formulario__grupo formulario__grupo-btn-enviar">
				<button type="submit" class="formulario__btn" name="accion" value="Agregar">Guardar</button>
				<button type="submit" class="formulario__btn" src="Inicio.jsp">Cancelar</button>
				<a class="formulario__btn" href="Inicio.jsp">Cancelar</a>
				<p class="formulario__mensaje-exito" id="formulario__mensaje-exito">Formulario enviado exitosamente!</p>
			</div>	
			
					
		</form>
	</main>

	<script src="js/newusuario.js"></script>
	
</body>
</html>