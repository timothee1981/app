package royalstacks.app.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import royalstacks.app.model.PosValidator;
import royalstacks.app.model.repository.PosValidatorRepository;

@Service
public class PosValidatorService {

    @Autowired
    PosValidatorRepository posValidatorRepository;

    public void savePosValidator(PosValidator posValidator) {
        posValidatorRepository.save(posValidator);
    }
}
