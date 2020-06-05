package royalstacks.app.model.repository;

import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.AutoConfigureBefore;
import org.springframework.boot.autoconfigure.cache.CacheAutoConfiguration;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.test.context.junit4.SpringRunner;
import royalstacks.app.model.Transaction;
import static org.assertj.core.api.Assertions.*;

import java.util.List;



@RunWith(SpringRunner.class)
@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
public class TransactionRepositoryTest {
    public static final int FROM_ACCOUNT_ID = 1;
    @Autowired
    private TestEntityManager testEntityManager;

    @Autowired
    private TransactionRepository transactionRepository;


    @Test
    public void getTransactionsByFromAccountIdOrToAccountIdOrderByDateDesc() {

        //GIVEN
        //Create three transcation Object
       Transaction transaction1 = new Transaction();
        transaction1.setFromAccountId(FROM_ACCOUNT_ID);

        Transaction transaction2 = new Transaction();
        Transaction transaction3 = new Transaction();
        testEntityManager.persist(transaction1);
        testEntityManager.flush();

                //get those transaction and put it in a list

        //WHEN

        //for now get one transaction
        List<Transaction> transactionFound = transactionRepository.getTransactionsByFromAccountIdOrToAccountIdOrderByDateDesc(FROM_ACCOUNT_ID,FROM_ACCOUNT_ID);
        //check the size -----> put the value of the size int an int variable
        int listsize = transactionFound.size();
        //give an int variable expected

        //THEN
        // result of de test: get the all transaction from a specific account
        // how to check: check the size and compare the actual size of your transaction list
        // even better check: check also if they come sorted by date
        var expected = 1;
        assertThat(listsize).isEqualTo(expected);



    }
}