package royalstacks.app.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import royalstacks.app.model.Account;
import royalstacks.app.model.repository.AccountRepository;
import royalstacks.app.model.repository.EmployeeRepository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.Set;

@Service
public class AccountService {

    @Autowired
    private AccountRepository accountRepository;
    @Autowired
    private EmployeeRepository employeeRepository;


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
        String newIban = incrementIbanByOne(lastIban);
        String accountNr11TestProof = makeIban11TestProof(newIban);
        String controlNr = createControlNrFromAccountNr(accountNr11TestProof);
        return  LANDCODE + controlNr + BANKCODE + accountNr11TestProof;
    }

    public String incrementIbanByOne(String iban){
        int newAccountNr =  Integer.parseInt(iban.substring(8));
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

    public boolean passed11Test(String iban){
        int testSum = 0;
        String accountNr = iban.substring(8);

        for (int index = 0; index < accountNr.length(); index++) {
            int singleDigit = Character.getNumericValue(accountNr.charAt(index));
            testSum += (singleDigit * (accountNr.length()-index));
        }
        return testSum%11 == 0;
    }

    public String makeIban11TestProof(String newIban){
        String iban = newIban;
        while(!passed11Test(iban)){
           iban = incrementIbanByOne(iban);
        }
        return iban;
    }

    public List<Account> getAllAccounts(){
        List<Account> accounts = (List<Account>) accountRepository.findAll();
        return accounts;
    }





}
