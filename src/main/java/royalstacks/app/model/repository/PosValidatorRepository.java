package royalstacks.app.model.repository;

import org.springframework.data.repository.CrudRepository;
import royalstacks.app.model.Employee;
import royalstacks.app.model.PosValidator;

public interface PosValidatorRepository extends CrudRepository<PosValidator, Integer> {
}
