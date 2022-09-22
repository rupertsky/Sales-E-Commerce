package co.edu.unbosque.backAstraBogota.controllers;

import java.util.List;

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

import co.edu.unbosque.backAstraBogota.models.Ventas;
import co.edu.unbosque.backAstraBogota.repositories.VentasDAO;

@RestController
@CrossOrigin(origins = "*", methods = {RequestMethod.POST, RequestMethod.GET,
		     RequestMethod.PUT, RequestMethod.DELETE})
@RequestMapping("/api/ventas")
public class VentasAPI {

	@Autowired
	private VentasDAO ventasdao;

	@PostMapping("/guardar")
	public Ventas guardar(@Validated @RequestBody Ventas ventas) {
		return ventasdao.insert(ventas);
	}
	
	@GetMapping("/listar")
	public List<Ventas> listar(){
		return ventasdao.findAll();
	}
	
	@PutMapping("/actualizar")
    public void actualizar(@RequestBody Ventas ventas) {
        ventasdao.save(ventas);
    }
	
	@DeleteMapping("/eliminar/{id}")
	public void eliminar(@PathVariable String id) {
		ventasdao.deleteById(id);
	}
	
}
