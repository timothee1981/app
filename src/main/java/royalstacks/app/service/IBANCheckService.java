package royalstacks.app.service;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigInteger;


@Service
public class IBANCheckService {

    @Autowired
    AccountService accountService;

    public String createNewIban(){
        final String INITIAL_ACCOUNT = "XX00XXX0000000000";
        final String LANDCODE = "NL";
        final String BANKCODE = "ROYA";
        String lastIban;

        if(accountService. retrieveLastIban() == null){
            lastIban = INITIAL_ACCOUNT;
        }
        else {
            lastIban = accountService. retrieveLastIban();
        }
        String newIban = incrementAccountNrByOne(lastIban.substring(8));
        String accountNr11TestProof = makeAccountNr11TestProof(newIban);
        String controlNr = createControlNrFromAccountNr(accountNr11TestProof);
        return  LANDCODE + controlNr + BANKCODE + accountNr11TestProof;
    }

    public String incrementAccountNrByOne(String accountNr){
        BigInteger bigInteger = new BigInteger(accountNr).add(BigInteger.ONE);
        return  String.format("%010d", bigInteger);
    }

    public String createControlNrFromAccountNr(String accountNr){
        final String COUNTRYCODE_NR = "232100";
        final String BANKCODE_NR = "27243410";
        final int HASH_BASE = 98;
        final int HASH_MODULUS = 97;

        BigInteger bigInteger = new BigInteger(BANKCODE_NR + accountNr + COUNTRYCODE_NR);
        BigInteger result = BigInteger.valueOf(HASH_BASE).subtract(bigInteger.mod(BigInteger.valueOf(HASH_MODULUS)));
        return String.format("%02d", result);
    }

    public boolean passed11Test(String accountNr){
        int testSum = 0;

        for (int index = 0; index < accountNr.length(); index++) {
            int singleDigit = Character.getNumericValue(accountNr.charAt(index));
            testSum += (singleDigit * (accountNr.length()-index));
        }
        return testSum%11 == 0;
    }

    public String makeAccountNr11TestProof(String accountNr){
        String newAccountNr = accountNr;
        while(!passed11Test(newAccountNr)){
            newAccountNr = incrementAccountNrByOne(newAccountNr);
        }
        return newAccountNr;
    }
}
