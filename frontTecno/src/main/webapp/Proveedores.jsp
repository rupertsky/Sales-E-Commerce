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
<title>Creacion de Proveedores</title>
</head>
<body>
	<div class="row">
		<div class="ingresar card col-md-3">
			<div class="card-body">
				<h4 class="card-title">Proveedores</h4>
				<h6>En este panel podras
					gestionar los datos de los proveedores del sistema</h6>
				<div>
					<form class="form-sign" method="get" action="Controlador">

						<div class="form-group">
							<input type="hidden" name="menu" value="Proveedores">
							<input type="hidden" name="txtid" value="${proveedorSeleccionado.get_id()}">
							<label>Nit:</label>
							<input type="number" name="txtnit" class="form-control" 
							value="${proveedorSeleccionado.getNit_proveedor()}" minlength="4" maxlength="14" required>
						</div>
						<div class="form-group">
							<label>Nombre:</label> 
							<input type="text" name="txtnombre" class="form-control" 
							value="${proveedorSeleccionado.getNombre_proveedor()}" minlength="4" maxlength="30" required>
						</div>
						<div class="form-group">
							<label>Ciudad:</label> 
							<input type="text" name="txtciudad" class="form-control" 
							value="${proveedorSeleccionado.getCiudad_proveedor()}" minlength="4" maxlength="14" required>
						</div>
						<div class="form-group">
							<label>Dirección:</label> 
							<input type="text" name="txtdireccion" class="form-control" 
							value="${proveedorSeleccionado.getDireccion_proveedor()}" minlength="4" maxlength="14" required>
						</div>
						<div class="form-group">
							<label>Teléfono:</label> 
							<input type="number" name="txttelefono" class="form-control" 
							value="${proveedorSeleccionado.getTelefono_proveedor()}" minlength="4" maxlength="14" required>
						</div>

						<input type="submit" class="btn btn-primary mt-2" name="accion" value="Agregar"> 
						<input type="submit" class="btn btn-success mt-2" name="accion" value="Actualizar">
					</form>
				</div>
			</div>
		</div>
		<div class="lista col-md-9">
			<table class="table">
				<thead class="thead-dark">
					<tr>
						<th scope="col">Nit</th>
						<th scope="col">Nombre</th>
						<th scope="col">Ciudad</th>
						<th scope="col">Dirección</th>
						<th scope="col">Teléfono</th>
					</tr>
				</thead>
				<tbody>
					<c:forEach var="lista" items="${listaProveedores}">
						<tr>
							<td>${lista.getNit_proveedor()}</td>
							<td>${lista.getNombre_proveedor()}</td>
							<td>${lista.getCiudad_proveedor()}</td>
							<td>${lista.getDireccion_proveedor()}</td>
							<td>${lista.getTelefono_proveedor()}</td>						
							<td>
								<a class="btn btn-warning m-2" href="Controlador?menu=Proveedores&accion=Cargar&id=${lista.getNit_proveedor()}">
								Editar</a>
								<a class="btn btn-danger m-2" href="Controlador?menu=Proveedores&accion=Eliminar&id=${lista.get_id()}">
								Eliminar </a>
							</td>
						</tr>
					</c:forEach>
				</tbody>
			</table>
		</div>
	</div>
</body>
</html>