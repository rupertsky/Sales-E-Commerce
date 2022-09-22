package co.edu.unbosque.backAstraBogota.repositories;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import co.edu.unbosque.backAstraBogota.models.Detalle_venta;

@Repository
public interface Detalle_ventaDAO extends MongoRepository<Detalle_venta, String>{

}
