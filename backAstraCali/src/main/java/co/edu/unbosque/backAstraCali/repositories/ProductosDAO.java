package co.edu.unbosque.backAstraCali.repositories;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import co.edu.unbosque.backAstraCali.models.Productos;

@Repository
public interface ProductosDAO extends MongoRepository<Productos, String>{

}

