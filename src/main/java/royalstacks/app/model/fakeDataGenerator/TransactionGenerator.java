package royalstacks.app.model.fakeDataGenerator;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.thymeleaf.expression.Lists;
import royalstacks.app.model.Account;
import royalstacks.app.model.Transaction;
import royalstacks.app.model.repository.AccountRepository;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.Month;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Random;

@Service
public class TransactionGenerator {

    @Autowired
    private AccountRepository accountRepository;

    public TransactionGenerator() {
    }

    public List<Transaction> generateTransactions(int amount){
        List<Account>  allAccounts = accountRepository.findAll();
        List<Transaction> transactions = new ArrayList<>();
        for (int i = 0; i < amount; i++) {
            int randomIndex = Gen.generateRandomInt(0, allAccounts.size()-1);
            int randomIndex2 = Gen.generateRandomInt(0, allAccounts.size()-1);
            while (randomIndex == randomIndex2){
                randomIndex2 = Gen.generateRandomInt(0, allAccounts.size()-1);
            }
            int fromAccountId = allAccounts.get(randomIndex).getAccountId();
            int toAccountId = allAccounts.get(randomIndex2).getAccountId();
            Transaction transaction =
                    new Transaction(fromAccountId, toAccountId,randomTransferAmount() ,randomDescription(), randomDateTime());
            transactions.add(transaction);
        }
        return transactions;
    }
    public static BigDecimal randomTransferAmount(){
        final int MIN = 1;
        final int MAX_UNCOMMON = 10000;
        final int MAX_COMMON = 200;
        final int PERCENTAGE_COMMON = 85;
        return Gen.amountGenerator(MIN, MAX_COMMON, MAX_UNCOMMON, PERCENTAGE_COMMON);
    }
    public static LocalDateTime randomDateTime(){
        LocalDateTime start = LocalDateTime.of(2020, Month.FEBRUARY, 1, 9, 0);
        long seconds = ChronoUnit.SECONDS.between(start, LocalDateTime.now());
        LocalDateTime randomDate = start.plusDays(new Random().nextInt((int)seconds+1));
        return randomDate;
    }

    public static String randomDescription(){
        String description = "";
        final int MIN_LETTERS = 1;
        final int MAX_LETTERS = 10;
        final int MIN_WORDS = 0;
        final int MAX_WORDS = 10;
        int letterAmount = Gen.generateRandomInt(MIN_LETTERS, MAX_LETTERS);
        int wordAmount = Gen.generateRandomInt(MIN_WORDS, MAX_WORDS);

        for (int index = 0; index < wordAmount; index++) {
            String word = randomWord(letterAmount) + " ";
            description += word.toLowerCase();
        }
        return description;
    }

    private static String randomWord(int letterAmount) {
        String word = "";
        for (int i = 0; i < letterAmount; i++) {
            char letter = Gen.generateRandomChar('z');
            word += letter;
        }
        return word;
    }


}
