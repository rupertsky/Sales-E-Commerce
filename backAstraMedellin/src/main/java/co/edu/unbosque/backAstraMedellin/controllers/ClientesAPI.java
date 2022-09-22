package co.edu.unbosque.backAstraMedellin.controllers;

import java.util.List;
import java.util.Optional;

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
import org.springframework.http.ResponseEntity;

import co.edu.unbosque.backAstraMedellin.models.Clientes;
import co.edu.unbosque.backAstraMedellin.repositories.ClientesDAO;

@RestController
@CrossOrigin(origins = "*", methods = {RequestMethod.POST, RequestMethod.GET,
		     RequestMethod.PUT, RequestMethod.DELETE})
@RequestMapping("/api/clientes")
public class ClientesAPI {
	
	@Autowired
	private ClientesDAO clientedao;

	@PostMapping("/guardar")
	public Clientes guardar(@Validated @RequestBody Clientes clientes) {
		return clientedao.insert(clientes);
	}
	
	@GetMapping("/listar")
	public List<Clientes> listar(){
		return clientedao.findAll();
	}
	
	@PutMapping("/actualizar")
    public void actualizar(@RequestBody Clientes clientes) {
        clientedao.save(clientes);
    }
	
	@DeleteMapping("/eliminar/{id}")
	public void eliminar(@PathVariable String id) {
		clientedao.deleteById(id);
	}
}
