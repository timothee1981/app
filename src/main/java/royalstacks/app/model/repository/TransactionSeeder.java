package royalstacks.app.model.repository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;
import royalstacks.app.service.AccountService;

@Component
public class TransactionSeeder implements CommandLineRunner {

    @Autowired
    AccountService accountService;
    @Autowired
    TransactionRepository transactionRepository;

    public TransactionSeeder() {
    }

    @Override
    public void run(String... args) throws Exception {
        transactionRepository.deleteAll();
    }
}
