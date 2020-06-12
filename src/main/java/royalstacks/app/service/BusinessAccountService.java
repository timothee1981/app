package royalstacks.app.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import royalstacks.app.model.*;
import royalstacks.app.model.repository.BusinessAccountRepository;
import royalstacks.app.model.repository.TransactionRepository;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

@Service
public class BusinessAccountService {

    @Autowired
    private BusinessAccountRepository businessAccountRepository;

    @Autowired
    private TransactionRepository transactionRepository;

    public List<SectorAndAverageBalance> findSectorAndAverageBalance(){
        List<Object[]> results = businessAccountRepository.findSectorAndAverageBalance();
        List<SectorAndAverageBalance> sectorAndAverageBalances = new ArrayList<>();

        for (Object[] result : results) {
            sectorAndAverageBalances.add(
                    new SectorAndAverageBalance(
                            Sector.valueOf((String) result[0]),
                            (double) result[1]
                    )
            );
        }
        return sectorAndAverageBalances;

    }


    public Optional<BusinessAccount> findByAccountId(int accountId) {
        return businessAccountRepository.findByAccountId(accountId);
    }

    public List<CompaniesAndTransactions> findTop10TransactionsOnBusinessAccounts(){
        List<Object[]> results = businessAccountRepository.findCompaniesAndBusinessAccounts();
        List<CompaniesAndTransactions> companiesAndTransactions = new ArrayList<>();

        for (Object[] result : results) {
            List<Transaction> transactionList = transactionRepository
                    .getTransactionsByFromAccountIdOrToAccountIdOrderByDateDesc((int)result[2], (int)result[2]);

            companiesAndTransactions.add(
                    new CompaniesAndTransactions(
                            (String) result [0],
                            transactionList.size(),
                            (BigDecimal) result [3]
                    )
            );
        }
        Collections.sort(companiesAndTransactions);
        return companiesAndTransactions.subList(0,10);
    }
}
