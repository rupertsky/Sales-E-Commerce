<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<link
	href="https://cdn.jsdelivr.net/npm/bootstrap@5.1.0/dist/css/bootstrap.min.css"
	rel="stylesheet"
	integrity="sha384-KyZXEAg3QhqLMpG8r+8fhAXLRk2vvoC2f3B09zVXn8CA5QIVfZOJ3BCsw2P0p/We"
	crossorigin="anonymous">
<meta charset="ISO-8859-1">
<title>Ventas</title>
</head>
<body>
        <div class="row">
            <div class="col-md-5 seccion1">
            <form method="get" action="Controlador">
            <div class="card">
            	<div class="card-body">
            		<div class="form-group d-flex">
            			<div class="col-sm-6 d-flex">
            				<input type="date" name="fecha" class="form-control" value="${fechaSeleccionada}">
            			</div>
            			<div class="col-sm-3">
            				<label>Seleccione la ciudad</label>
            				
            			</div>
            			<div class="col-sm-3">
            				<select name="ciudad" class="form-select">
            					<option>${ciudadSeleccionada}</option>
            					<option value="Bogota">Bogota</option>
            					<option value="Cali">Cali</option>
            					<option value="Medellin">Medellin</option>
            				</select>
            			</div>
            		</div>
            		 
            	</div>
            </div>
                <div class="card">
                    <div class="card-body">
	                    	<div class="logo mt-4 mb-4">
	                    		<img src="img/casconeon.png" class="icon" alt="100" width="100"/>
	                    	</div>
                        <div class="form-group">
	                      <h4 class="card-title" style="color: white;">Datos Clientes</h4>
	                  </div>
                        <input type="hidden" name="menu" value="Ventas">
                        <input type="hidden" name="UsuarioActivo" 
                               value="${usuarioSeleccionado.getCedula_usuario()}">
                        <div class="form-group d-flex">
							<div class="col-sm-6 d-flex">
								<input type="number" name="cedulacliente" class="form-control"
									placeholder="Cedula cliente" required="required"
									value="${clienteSeleccionado.getCedula_cliente()}"> 
									
									<input type="submit" name="accion" value="BuscarCliente"
									class="btn btn-primary">
							</div>
							<div class="col-sm-6">
								<input type="text" name="nombrecliente" readonly="readonly" 
									class="form-control" placeholder="Nombre cliente"
									value="${clienteSeleccionado.getNombre_cliente()}">
							</div>
						</div>
                   </div>
               </div>
               <div class="card mt-2">
                     <div class="card-body">
                         <div class="form-group">
	                      <h4 class="card-title" style="color: white;">Datos Productos</h4>
	                   </div>
                         <div class="form-group d-flex">
                             <div class="col-sm-6 d-flex">
                                 <input type="number" name="codigoproducto" class="form-control" 
                                   placeholder="codigo producto" 
                                   value="${productoSeleccionado.getCodigo_producto()}">
                                 <input type="submit" name="accion" value="BuscarProducto" 
                                    class="btn btn-primary">
	                      </div>
                            <div class="col-sm-6">
	                        <input type="text" name="nombreproducto" class="form-control" 
                                  placeholder="Nombre Producto" 
                                  value="${productoSeleccionado.getNombre_producto()}" readonly="readonly">
	                      </div>
                         	</div>
                          <div class="form-group d-flex">
       	                  <div class="col-sm-6 d-flex">
     	                            <input type="text" name="precioproducto" class="form-control"
                                     placeholder="$ 0000.00" 
                                     value="${productoSeleccionado.getPrecio_venta()}" ><!-- disabled="disabled" -->
   	                         </div>
   	                         <div class="col-sm-3">
	                            <input type="number" name="cantidadproducto" 
                                      class="form-control" placeholder="Cantidad">
   	                         </div>
 	                         <div class="col-sm-3">
	                             <input type="text" name="ivaproducto" class="form-control"
                                      placeholder="Valor iva"
                                      value="${productoSeleccionado.getIva_compra()}" readonly="readonly">
	                         </div>
	                    </div>
                          <div class="form-group d-flex">
	                       <input type="submit" name="accion" value="AgregarProducto"
                                class="btn btn-primary">
                          </div>
                   </div>
                </div>
             </form>
         </div>
      <div class="col-md-7 seccion2">
                <div class="card">
                    <div class="card-header">
                        <div class="form-group row">
                             <label class="col-sm-4 col-form-label numfac">Número de Factura</label>
                             <input class="form-control col-md-4 numfac" type="text" 
                                 name="numerofactura" value="${numerofactura}" readonly="readonly">
                       </div>
                    </div>
				<div class="card-body">
					<table class="table">
						<thead class="thead-dark">
							<tr>
								<th>#</th>
								<th>Codigo</th>
								<th>Producto</th>
								<th>Precio</th>
								<th>Cantidad</th>
								<th>Iva</th>
								<th>Total</th>
							</tr>
						</thead>
						<tbody>
							<c:forEach var="lista" items="${listaventas}">
								<tr>
									<th>${lista.getCodigo_detalle_venta()}</th>
									<td>${lista.getCodigo_producto()}</td>
									<td>${lista.getNombre_producto()}</td><!-- descripcionproducto -->
									<td>${lista.getPrecio_producto()}</td>
									<td>${lista.getCantidad_producto()}</td>
									<td>${lista.getValor_iva()}</td>
									<td>${lista.getValor_venta()}</td>

								</tr>
							</c:forEach>
						</tbody>
					</table>
				</div>
				<div class="card-footer d-flex">
                        <div class="col-md-4">
                            <label class="mt-3">Subtotal</label><br>
                            <label class="mt-3">Iva</label><br>
                            <label class="mt-3">Total a pagar</label>
                        </div>
	                        <div class="col-md-4 totales">    
	                            <input type="text" name="txtsubtotal" class="form-control text-right" 
	                                placeholder="$ 00.000.00" disabled="disabled" 
	                                value="${totalsubtotal}">
	                            <input type="text" name="txttotaliva" class="form-control" 
	                                placeholder="$ 00.000.00" disabled="disabled" 
	                                value="${totaliva}">
	                            <input type="text" name="txttotalapagar" class="form-control" 
	                                placeholder="$ 00.000.00" disabled="disabled"
	                                value="${totalapagar}">
	                         </div>
                        </div>
             </div>
             <div class="card-footer d-flex">
                   <div class="col-md-8">
                     <a class="btn btn-success" onclick="print()" 
                     href="Controlador?menu=Ventas&accion=GenerarVenta&cedulacliente=${clienteSeleccionado.getCedula_cliente()}&UsuarioActivo=${usuarioSeleccionado.getCedula_usuario()}&numerofactura=${numerofactura}">Generar Venta</a>
                     <a class="btn btn-danger" type="button"
                        href="Controlador?menu=Ventas&accion=NuevaVenta">Nueva Venta</a>
                        <script type="text/javascript">
                        	$('form').smockClearForm();
                        </script>
                   </div>
             </div>
         </div>
     </div>
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.1.2/dist/js/bootstrap.bundle.min.js" integrity="sha384-kQtW33rZJAHjgefvhyyzcGF3C5TFyBQBA13V1RKPf4uH+bwyzQxZ6CmMZHmNBEfJ" crossorigin="anonymous"></script>
    <script src="https://code.jquery.com/jquery.js"></script>
	<script src="js/smock.min.js"></script>
     
</body>



</html>

