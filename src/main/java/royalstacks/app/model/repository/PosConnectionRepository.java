package royalstacks.app.model.repository;

import org.springframework.data.repository.CrudRepository;
import royalstacks.app.model.pos.ConnectionRequest;

import java.util.Optional;

public interface PosConnectionRepository extends CrudRepository<ConnectionRequest, Integer> {
    Optional<Long> getLastId();
}
