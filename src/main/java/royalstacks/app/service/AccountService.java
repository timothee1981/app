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
    public String retrieveLastIban(){
        Optional<String> accountNumber = accountRepository.getLastAccountNumber();
        if (accountNumber.isPresent()) {
            return accountNumber.get();
        } else {
            return null;
        }
    }

    public String createNewIban(){
        final String INITIAL_ACCOUNT = "XX00XXX0000000000";
        final String LANDCODE = "NL";
        final String BANKCODE = "ROYA";
        String lastIban;

        if(retrieveLastIban() == null){
            lastIban = INITIAL_ACCOUNT;
        }
        else {
            lastIban = retrieveLastIban();
        }
        String newAccountNr = createNewAccountNrFromLastIban(lastIban);
        String controlNr = createControlNrFromAccountNr(newAccountNr);
        return  LANDCODE + controlNr + BANKCODE + newAccountNr;
    }

    public String createNewAccountNrFromLastIban(String lastIban){
        int newAccountNr =  Integer.parseInt(lastIban.substring(8));
        String newAccountNumber = String.format("%010d", ++newAccountNr);
        return newAccountNumber;
    }

    public String createControlNrFromAccountNr(String accountNr){
    final String CONTROLNUMBER_HASH = "232100";
    final int HASH_BASE = 98;
    final int HASH_MODULUS = 97;
    int controlNrInt;
    String controlNrString = accountNr + CONTROLNUMBER_HASH;
    controlNrInt = HASH_BASE-(Integer.parseInt(controlNrString)%HASH_MODULUS);
    return String.format("%02d", controlNrInt);
    }
}
