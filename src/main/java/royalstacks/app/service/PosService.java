package royalstacks.app.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import royalstacks.app.model.pos.Pos;
import royalstacks.app.model.repository.PosRepository;

import java.util.Optional;

@Service
public class PosService {

    @Autowired
    PosRepository posRepository;

    public Optional<Pos> findPosByIdentificationNumber(int identificationNumber){
        return posRepository.findPosByIdentificationNumber(identificationNumber);
    }

    public void savePos(Pos pos){
        posRepository.save(pos);
    }
}
