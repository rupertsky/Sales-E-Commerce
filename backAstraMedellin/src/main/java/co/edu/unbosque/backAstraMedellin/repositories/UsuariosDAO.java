package co.edu.unbosque.backAstraMedellin.repositories;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import co.edu.unbosque.backAstraMedellin.models.Usuarios;

@Repository
public interface UsuariosDAO extends MongoRepository<Usuarios, String>{

}
