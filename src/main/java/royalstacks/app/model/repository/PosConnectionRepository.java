package royalstacks.app.model.repository;

import org.springframework.data.repository.CrudRepository;
import royalstacks.app.model.pos.ConnectionRequest;

public interface PosConnectionRepository extends CrudRepository<ConnectionRequest, Integer> {
}
