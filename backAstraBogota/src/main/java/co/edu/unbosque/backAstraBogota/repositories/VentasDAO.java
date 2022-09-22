package co.edu.unbosque.backAstraBogota.repositories;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import co.edu.unbosque.backAstraBogota.models.Ventas;

@Repository
public interface VentasDAO extends MongoRepository<Ventas, String> {

}
