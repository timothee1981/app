package royalstacks.app.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import royalstacks.app.model.pos.ConnectionRequest;
import royalstacks.app.model.repository.PosConnectionRepository;

@Service
public class ConnectionRequestService {

    @Autowired
    PosConnectionRepository posConnectionRepository;

    public void savePosValidator(ConnectionRequest connectionRequest) {
        posConnectionRepository.save(connectionRequest);
    }
}
