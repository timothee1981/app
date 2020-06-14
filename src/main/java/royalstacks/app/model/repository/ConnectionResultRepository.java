package royalstacks.app.model.repository;

import org.springframework.data.repository.CrudRepository;
import royalstacks.app.model.pos.ConnectionRequest;
import royalstacks.app.model.pos.ConnectionResult;

public interface ConnectionResultRepository extends CrudRepository<ConnectionResult, Integer> {
}
