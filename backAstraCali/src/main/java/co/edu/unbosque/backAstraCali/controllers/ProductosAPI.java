package co.edu.unbosque.backAstraCali.controllers;

import java.util.List;
import java.util.Optional;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URI;
import java.util.Scanner;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.bind.annotation.RequestParam;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import co.edu.unbosque.backAstraCali.models.Productos;
import co.edu.unbosque.backAstraCali.repositories.ProductosDAO;

@RestController
@CrossOrigin(origins = "*", methods = {RequestMethod.POST, RequestMethod.GET,
		     RequestMethod.PUT, RequestMethod.DELETE})
@RequestMapping("/api/productos")
public class ProductosAPI {
	
	@Autowired
	private ProductosDAO productodao;

	@PostMapping("/guardar")
	public Productos guardar(@Validated @RequestBody Productos productos) {
		return productodao.insert(productos);
	}
	
	@GetMapping("/listar")
	public List<Productos> listar(){
		return productodao.findAll();
	}
	
	@PutMapping("/actualizar")
    public void actualizar(@RequestBody Productos productos) {
        productodao.save(productos);
    }
	
	@DeleteMapping("/eliminar/{id}")
	public void eliminar(@PathVariable String id) {
		productodao.deleteById(id);
	}
	
	@RequestMapping("/import")
	public ResponseEntity<Void> importarArchivoExcel(@RequestParam("file") MultipartFile file) throws IOException {
		System.out.println("Archivo con extension: " + file.getOriginalFilename());
		if(file.getOriginalFilename().contains(".csv")|| file.getOriginalFilename().contains(".csv")) {
			BufferedReader br = new BufferedReader(new InputStreamReader(file.getInputStream()));
			String linea = null;
			while((linea = br.readLine())!=null) {
				Scanner delimitar = new Scanner(linea);
				delimitar.useDelimiter("\\s*,\\s*");
				
				Productos tempProductos = new Productos();				
				String cod = delimitar.next();
				String nomb = delimitar.next();
				String nit = delimitar.next();
				double precio = delimitar.nextDouble();
				double iva = delimitar.nextDouble();
				double venta = delimitar.nextDouble();
				
				this.productodao.insert(new Productos(cod, nomb, nit, precio, iva, venta));
			}
		}else {
			System.out.println("Archivo no valido");
		}
		return ResponseEntity.status(HttpStatus.FOUND).location(URI.create("http://localhost:8080/frontTecno/Productos.jsp")).build();
	}
}