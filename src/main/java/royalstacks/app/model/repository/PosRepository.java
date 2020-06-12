package royalstacks.app.model.repository;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import royalstacks.app.model.pos.Pos;

import java.util.Optional;

public interface PosRepository extends CrudRepository<Pos, Integer> {

    @Query("SELECT p.identificationNumber FROM Pos p WHERE p.identificationNumber = (select max(p.identificationNumber) from p )")
    Optional<Integer> getLastId();
}
