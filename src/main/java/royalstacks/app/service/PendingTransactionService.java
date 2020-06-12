package royalstacks.app.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import royalstacks.app.model.pos.PendingTransaction;
import royalstacks.app.model.repository.PendingTransactionRepository;

@Service
public class PendingTransactionService {

    @Autowired
    PendingTransactionRepository pendingTransactionRepository;

    public void save(PendingTransaction pendingTransaction){
        pendingTransactionRepository.save(pendingTransaction);

    }
}
