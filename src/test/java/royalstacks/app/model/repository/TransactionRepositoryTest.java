package royalstacks.app.model.repository;

import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import royalstacks.app.model.Transaction;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;



@RunWith(SpringRunner.class)
/*@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)*/
@SpringBootTest
class TransactionRepositoryTest {




    @Autowired
    private TransactionRepository transactionRepository;



    @Test
    public void getTransaction() {

        //GIVEN
        //TEST IF WE CAN GET A TRANSACTION FROM MONGO DB, TEST if from accountDB is what we expect
        //Create three transcation Object
        Transaction transaction1 = new Transaction(5,6,300,"HELOOO", null);
        transaction1.setTransactionId("200");
        //SAVE IT INTO DB

        transactionRepository.save(transaction1);
        //WHEN

        //HAAL HET OP
        Optional<Transaction> transactionFound = transactionRepository.findById("200");


        //GET THE FROM_ACOUNTID
        int fromAccountId = 0;
        if(transactionFound.isPresent()){
           fromAccountId = transactionFound.get().getFromAccountId();
        }

        //THEN

        //EXPECTED FromACCOUNTID
        var expected = 5;

        assertThat(fromAccountId).isEqualTo(expected);
        // even better check: check also if they come sorted by date

    }
}