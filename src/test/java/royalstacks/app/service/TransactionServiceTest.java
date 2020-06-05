package royalstacks.app.service;

import org.junit.Before;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;

import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.TestConfiguration;
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

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;


@RunWith(SpringRunner.class)
@SpringBootTest
class TransactionServiceTest {

    @Mock
    TransactionRepository transactionRepository;

    @InjectMocks
    TransactionService transactionService;

    @Before
    public void setUp() {
        Transaction transaction = new Transaction();

/*        Mockito.when(transactionRepository.findById("1")).thenReturn("1");*/
    }

    @Test
    void getTenLastTransaction() {


    }
}