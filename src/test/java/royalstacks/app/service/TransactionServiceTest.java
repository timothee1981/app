package royalstacks.app.service;

import org.junit.Before;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;

import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Bean;
import org.springframework.test.context.junit4.SpringRunner;

import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.boot.test.context.SpringBootTest;
import royalstacks.app.model.Employee;
import royalstacks.app.model.Transaction;
import royalstacks.app.model.repository.AccountRepository;

import royalstacks.app.model.repository.TransactionRepository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;


@RunWith(MockitoJUnitRunner.class)
@SpringBootTest
class TransactionServiceTest {




    @Mock
    TransactionRepository transactionRepository;

    @InjectMocks
    TransactionService transactionService;




    @Test //TEST TO DETERMINE IF IF GET AN ACTUAL LIST OF TEN TRANSACTION
    void getTenTransaction() {
        List<Transaction>  transactions = new ArrayList<>();
        for(int index = 0; index < 20; index ++){
            Transaction transaction = new Transaction();
            transactions.add(transaction);
        }


        Mockito.when(transactionRepository.getTransactionsByFromAccountIdOrToAccountIdOrderByDateDesc(1,1)).thenReturn(transactions);


        List<Transaction> transactionListActual = transactionService.getTenLastTransaction(1);
        int transactionListSizeActual = transactionListActual.size();
        int transactionListSizeExpected = 10;

        assertEquals(transactionListSizeExpected,transactionListSizeActual);

        //check if size of transactionlist is actually 10
    }
}