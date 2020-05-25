package royalstacks.app.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import royalstacks.app.model.Account;
import royalstacks.app.model.repository.AccountRepository;

import java.util.Optional;

@Service
public class AccountService {

    private AccountRepository accountRepository;

    @Autowired
    public AccountService(AccountRepository accountRepository){
        super();
        this.accountRepository = accountRepository;
    }

    public <T extends Account> void saveAccount(T account) {
        accountRepository.save(account);}

    public Account findByAccountId(int accountId) {
        Optional<Account> account = accountRepository.findByAccountId(accountId);
        if (account.isPresent()) {
            return account.get();
        } else {
            return null;
        }
    }
    public String getLastAccountNumber(){
        Optional<String> accountNumber = accountRepository.getLastAccountNumber();
        if (accountNumber.isPresent()) {
            return accountNumber.get();
        } else {
            return null;
        }
    }


    public String generateAccountNumber(){
        final String FIRST_ACCOUNT = "NL77ROYA0000000000";
        final String IBAN_START_CODE = "NL77ROYA";
        String lastAccountNr;
        if(getLastAccountNumber() == null){
            lastAccountNr = FIRST_ACCOUNT;
        }
        else {
            lastAccountNr = getLastAccountNumber();
        }
        int newAccountNr =  Integer.parseInt(lastAccountNr.substring(8));
        String newAccountNumber = String.format("%s%010d",IBAN_START_CODE, ++newAccountNr);
        return newAccountNumber;
    }
}
