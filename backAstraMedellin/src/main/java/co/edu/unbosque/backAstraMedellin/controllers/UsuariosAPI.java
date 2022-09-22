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

import co.edu.unbosque.backAstraMedellin.models.Usuarios;
import co.edu.unbosque.backAstraMedellin.repositories.UsuariosDAO;

@RestController
@CrossOrigin(origins = "*", methods = {RequestMethod.POST, RequestMethod.GET,
		     RequestMethod.PUT, RequestMethod.DELETE})
@RequestMapping("/api/usuarios")
public class UsuariosAPI {
	
	@Autowired
	private UsuariosDAO usuariodao;

	@PostMapping("/guardar")
	public Usuarios guardar(@Validated @RequestBody Usuarios usuarios) {
		return usuariodao.insert(usuarios);
	}
	
	@GetMapping("/listar")
	public List<Usuarios> listar(){
		return usuariodao.findAll();
	}
	
	@PutMapping("/actualizar")
    public void actualizar(@RequestBody Usuarios usuarios) {
        usuariodao.save(usuarios);
    }
	
	//@PutMapping("/actualizar/{id}")
	//public void actualizar(@PathVariable String id, @Validated @RequestBody Usuarios usuarios) {
	//	usuariodao.save(usuarios);
	//}
	
	@DeleteMapping("/eliminar/{id}")
	public void eliminar(@PathVariable String id) {
		usuariodao.deleteById(id);
	}
}




