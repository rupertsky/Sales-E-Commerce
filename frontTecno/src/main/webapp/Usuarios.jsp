<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<link
	href="https://cdn.jsdelivr.net/npm/bootstrap@5.1.0/dist/css/bootstrap.min.css"
	rel="stylesheet"
	integrity="sha384-KyZXEAg3QhqLMpG8r+8fhAXLRk2vvoC2f3B09zVXn8CA5QIVfZOJ3BCsw2P0p/We"
	crossorigin="anonymous">
<link rel="stylesheet" href="./css/usuarios.css">
<title>Creacion de Usuario</title>
</head>
<body>
	<div class="row">
		<!-- Seccion 1 -->
		<div class="card col-md-3">
			<div class="card-body">
				<h4 class="card-title">Usuarios</h4>
				<h6>En este panel podras
					gestionar los datos de los usuarios del sistema</h6>
				<div>
					<form class="form-sign needs-validation" id="formulario" method="get" action="Controlador">
						
						<div class="form-group" id="group__cedula">
							<input type="hidden" name="menu" value="Usuarios"> 
							<input type="hidden" name="txtid" value="${usuarioSeleccionado.get_id()}"> 
							<label>Cedula:</label>
							<input type="text" name="txtcedula" class="form-control"
								value="${usuarioSeleccionado.getCedula_usuario()}" minlength="4" maxlength="14" required>
						</div>
						<div class="form-group" id="group__nombre">
							<label>Nombre:</label> 
							<input type="text" name="txtnombre"	class="form-control" 
								value="${usuarioSeleccionado.getNombre_usuario()}" minlength="4" maxlength="30" required>
						</div>
						<div class="form-group" id="group__email">
							<label>Email:</label> 
							<input type="text" name="txtemail" class="form-control"
								value="${usuarioSeleccionado.getEmail_usuario()}" required>
						</div>
						<div class="form-group" id="group__usuario">
							<label>Usuario:</label> 
							<input type="text" name="txtusuario" class="form-control" 
								value="${usuarioSeleccionado.getUsuario()}" minlength="4" maxlength="14" required>
						</div>
						<div class="form-group" id="group__password">
							<label>Password:</label> 
							<input type="password" name="txtpassword" class="form-control"
								value="${usuarioSeleccionado.getPassword()}" minlength="4" maxlength="14" required>
						</div>
						<input type="submit" class="btn btn-primary mt-2" name="accion" value="Agregar"> 
						<input type="submit" class="btn btn-success mt-2" name="accion" value="Actualizar">
					</form>
				</div>
			</div>
		</div>
		<!-- Seccion 2 -->
		<div class="lista col-md-9">
			<table class="table">
				<thead class="thead-dark">
					<tr>
						<th scope="col">Cedula</th>
						<th scope="col">Nombre</th>
						<th scope="col">Email</th>
						<th scope="col">Usuario</th>
						<th scope="col">Password</th>
					</tr>
				</thead>
				<tbody>
					<c:forEach var="lista" items="${listaUsuarios}">
						<tr>
							<td>${lista.getCedula_usuario()}</td>
							<td>${lista.getNombre_usuario()}</td>
							<td>${lista.getEmail_usuario()}</td>
							<td>${lista.getUsuario()}</td>
							<td>${lista.getPassword()}</td>
							<td>
								<a class="btn btn-warning m-2" 
									href="Controlador?menu=Usuarios&accion=Cargar&id=${lista.getCedula_usuario()}">
									Editar
								</a> 
								<a class="btn btn-danger m-2" 
									href="Controlador?menu=Usuarios&accion=Eliminar&id=${lista.get_id()}">
									Eliminar
								</a>
							</td>
						</tr>
					</c:forEach>
				</tbody>
			</table>
		</div>
	</div>
</body>
</html>