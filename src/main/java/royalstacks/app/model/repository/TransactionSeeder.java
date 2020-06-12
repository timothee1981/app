package royalstacks.app.model.repository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;
import royalstacks.app.model.Account;
import royalstacks.app.model.PrivateAccount;
import royalstacks.app.model.Transaction;
import royalstacks.app.service.AccountService;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

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
