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
        LocalDateTime timestamp = LocalDateTime.now();
        double amount = 129.95;
        Account accountFrom = new PrivateAccount(accountService.createNewIban(), 1500);
        Account accountTo = new PrivateAccount(accountService.createNewIban(), 0);
        String description = "First transaction test";
        Transaction transaction1 = new Transaction(1, 2, amount, description, timestamp);

        LocalDateTime timestamp2 = LocalDateTime.now().minusMonths(1);
        double amount2 = 99;
        Account accountFrom2 = new PrivateAccount(accountService.createNewIban(), 999);
        Account accountTo2 = new PrivateAccount(accountService.createNewIban(), 99);
        String description2 = "Second transaction test";
        Transaction transaction2 = new Transaction(3, 4, amount2, description2, timestamp2);

        //drop all transactions
        transactionRepository.deleteAll();

        //add transactions to db
        List<Transaction> transactions = new ArrayList<>();
        transactions.add(transaction1);
        transactions.add(transaction2);
        transactionRepository.saveAll(transactions);





    }
}
