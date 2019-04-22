package laboratorio1.dao;

import laboratorio1.model.Client;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ClientRepository extends CrudRepository<Client,Long>{
    
}
