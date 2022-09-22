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
<title>Creacion de Productos</title>
</head>
<body>
	<div class="row">
		<div class="ingresar card col-md-3">
			<div class="card-body">
				<h4 class="card-title">Productos</h4>
				<h6>En este panel podras
					gestionar los datos de los productos del sistema</h6>
				<div>
					<form class="form-sign" method="get" action="Controlador">
						<div class="form-group">
							<input type="hidden" name="menu" value="Productos"> 
							<input type="hidden" name="txtid" value="${productoSeleccionado.get_id()}">
							<label>Codigo:</label>
							<input type="number" name="txtcodigo" class="form-control" 
							value="${productoSeleccionado.getCodigo_producto()}" minlength="3" maxlength="14" required>
						</div>
						<div class="form-group">
							<label>Nombre:</label> 
							<input type="text" name="txtnombre" class="form-control" 
							value="${productoSeleccionado.getNombre_producto()}" minlength="6" maxlength="30" required>
						</div>
						<div class="form-group">
							<label>Nit Proveedor:</label> 
							<input type="text" name="txtnit" class="form-control" 
							value="${productoSeleccionado.getNit_proveedor()}" minlength="1" maxlength="14" required>
						</div>
						<div class="form-group">
							<label>Precio Producto:</label> 
							<input type="text" name="txtprecioproducto" class="form-control" 
							value="${productoSeleccionado.getPrecio_compra()}" minlength="4" maxlength="14" required>
						</div>
						<div class="form-group">
							<label>Precio Iva:</label> 
							<input type="text" name="txtiva" class="form-control" 
							value="${productoSeleccionado.getIva_compra()}" minlength="2" maxlength="14" required>
						</div>
						<div class="form-group">
							<label>Precio Venta:</label> 
							<input type="text" name="txtprecioventa" class="form-control" 
							value="${productoSeleccionado.getPrecio_venta()}" minlength="4" maxlength="14" required>
						</div>

						<input type="submit" class="btn btn-primary mt-2" name="accion" value="Agregar"> 
						<input type="submit" class="btn btn-success mt-2" name="accion" value="Actualizar">
					</form>
					<div class="form-group mt-4">
						<form action="http://localhost:8082/api/productos/import" method="post" enctype="multipart/form-data">
							<h4 class="card-title">Importar Archivo CSV:</h4>						
							<input type="file" name="file" class="form-control" id="archivoInput" onchange="return validarExt()">
							<input type="submit" class="btn btn-primary mt-2" value="Cargar">						
						</form>
					</div>
				</div>
			</div>
		</div>
		<div class="lista col-md-9">
			<table class="table">
				<thead class="thead-dark">
					<tr>
						<th scope="col">Código</th>
						<th scope="col">Nombre Producto</th>
						<th scope="col">Nit Proveedor</th>
						<th scope="col">Precio Producto</th>
						<th scope="col">Precio Iva</th>
						<th scope="col">Precio Venta</th>
					</tr>
				</thead>
				<tbody>
					<c:forEach var="lista" items="${listaProductos}">
						<tr>
							<td>${lista.getCodigo_producto()}</td>
							<td>${lista.getNombre_producto()}</td>
							<td>${lista.getNit_proveedor()}</td>
							<td>${lista.getPrecio_compra()}</td>
							<td>${lista.getIva_compra()}</td>	
							<td>${lista.getPrecio_venta()}</td>					
							<td>
								<a class="btn btn-warning m-2" href="Controlador?menu=Productos&accion=Cargar&id=${lista.getCodigo_producto()}">
								Editar</a>
								<a class="btn btn-danger m-2" href="Controlador?menu=Productos&accion=Eliminar&id=${lista.get_id()}">
								Eliminar </a>
							</td>
						</tr>
					</c:forEach>
				</tbody>
			</table>
		</div>
	</div>
	<script src="js/validar.js"></script>
</body>
</html>