package royalstacks.app.service;

import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.test.context.junit4.SpringRunner;
import royalstacks.app.model.repository.TransactionRepository;

import static org.junit.jupiter.api.Assertions.*;

@RunWith(SpringRunner.class)
public class TransactionServiceTest {



    @TestConfiguration
    static class TransactionServiceTestContextConfiguration{


        @Bean
        public TransactionService transactionService(){
            return new TransactionService();
        }
    }

    @Autowired
    private TransactionService transactionService;

    @Autowired
    private TransactionRepository transactionRepository;

    @Test
    void getTenLastTransaction() {

        //Create a list of transactions

        //save it into nosqldb

        //retrieve list

        //check first size of list, sould not be greater than 10

        //check that we got the last ten transaction object belonging to account
    }
}