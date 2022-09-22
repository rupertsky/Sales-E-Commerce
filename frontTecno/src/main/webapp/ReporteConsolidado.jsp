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
<title>Reportes Consolidados</title>
</head>
<body>
<!--  -->
                   
            <div class="col-md-7">
            	<div class="card">
            		<div class="card-body">
            			<div class="form-group">
            				<label>Reporte total ventas por ciudad</label>
            			</div>
            			<table class="table">
            			
            				<thead class="thead-dark">
            					<tr>
            						<th scope="col">Ciudad</th>
            						<th scope="col">Valor Total Ventas</th>
            					</tr>
            				</thead>
            				<tbody>
            				
            					<tr>
            						<td>Bogota</td>
            						<td>${totalventabogota}</td>
            					</tr>
            					<tr>	
            						<td>Cali</td>
            						<td>${totalventacali}</td>
            					</tr>
            					<tr>
            						<td>Medellin</td>
            						<td>${totalventamedellin}</td>
            					</tr>
            					<tr>
            						<td style="font-weight:bold">Total Ventas</td>
            						<td>${totalventasgeneral}</td>
            					</tr>
            					
            				
            				</tbody>
            				            				
            			</table>
            		</div>
            	</div>
            </div>
        </body>
        </html>