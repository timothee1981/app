package royalstacks.app.service;

import org.assertj.core.internal.cglib.asm.$AnnotationVisitor;
import org.junit.Before;
import org.junit.internal.runners.model.EachTestNotifier;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;

import org.mockito.Mockito;
import org.mockito.invocation.InvocationOnMock;
import org.mockito.stubbing.Answer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Bean;
import org.springframework.data.mongodb.core.mapping.TextScore;
import org.springframework.test.context.junit4.SpringRunner;

import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.boot.test.context.SpringBootTest;
import royalstacks.app.model.*;
import royalstacks.app.model.repository.AccountRepository;

import royalstacks.app.model.repository.TransactionRepository;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;


@RunWith(MockitoJUnitRunner.class)
@SpringBootTest
class TransactionServiceTest {

    AccountRepository accountRepository;

    @Mock
    TransactionRepository transactionRepository;


    private AccountService accountService = Mockito.mock(AccountService.class);

    @InjectMocks
    TransactionService transactionService;
    private Object Account;

    @Test
    void saveTransaction() {
    }

    @Test
    void executeTransaction() {
    }




    @Test //TEST TO DETERMINE IF IF GET AN ACTUAL LIST OF TEN TRANSACTION
    void getTenTransaction() {
        List<Transaction>  transactions = new ArrayList<>();
        for(int index = 0; index < 20; index ++){
            Transaction transaction = new Transaction();
            transactions.add(transaction);
        }


        Mockito.when(transactionRepository.getTransactionsByFromAccountIdOrToAccountIdOrderByDateDesc(1,1)).thenReturn(transactions);


        List<AccountHolderTransaction> transactionListActual = transactionService.getTenLastTransaction(1);
        int transactionListSizeActual = transactionListActual.size();
        int transactionListSizeExpected = 10;

        assertEquals(transactionListSizeExpected,transactionListSizeActual);

        //check if size of transactionlist is actually 10
    }

// TEST TO SEE IF AMOUNT IS CORRECTLY DEBITET OR CREDITET
    @Test
    void DebitorOrCredit(){
        //SET UP

        /*PrivateAccount account = new PrivateAccount("NL79ROYA1111111111",new BigDecimal(111));
        int acountid = 1;
        account.setAccountId(1);
        Transaction transaction = new Transaction(null,0,0,null,"", LocalDateTime.parse("1986-04-08T12:30:00"));
        //SO IT SHOULD BE A CREDIT

        transaction.setToAccountId(1);
        transaction.setAmount(new BigDecimal(500));
        Mockito.when(accountService.getAccountById(1)).thenReturn(account);
        AccountHolderTransaction accountHolderTransaction =  transactionService.getTransaction(transaction, acountid);

        String amountExpected = " + 500" ;
        BigDecimal amountActual = accountHolderTransaction.getAmount();
        assertEquals(amountExpected,amountActual);
        Transaction transaction2 = new Transaction(null,0,0,new BigDecimal(500),"", LocalDateTime.parse("1986-04-08T12:30:00"));

        //THIS TIME SHOULD BE A DEBIT

        transaction2.setFromAccountId(1);
        AccountHolderTransaction accountHolderTransaction2 =  transactionService.getTransaction(transaction2, acountid);
        String amountExpected1 = " - 500";
        BigDecimal amountActual2 = accountHolderTransaction2.getAmount();
        assertEquals(amountExpected1,amountActual2);*/

    }






}