package co.edu.unbosque.backAstraMedellin.repositories;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import co.edu.unbosque.backAstraMedellin.models.Clientes;

@Repository
public interface ClientesDAO extends MongoRepository<Clientes, String>{

}

