package royalstacks.app.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import royalstacks.app.model.repository.PosRepository;

import java.util.Optional;

@Service
public class PosService {

    @Autowired
    private PosRepository posRepository;


    public Optional<Integer> getLastPosId(){
        return posRepository.getLastId();
    }

}
