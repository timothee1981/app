package royalstacks.app.model.fakeDataGenerator;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import royalstacks.app.model.*;
import royalstacks.app.service.AccountService;

import java.lang.reflect.Array;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class AccountGenerator {




    public static List<Account> accountGenerator(int amount, JSONArray jsonArray) {
        Iterator<JSONObject> iterator = jsonArray.iterator();
        List<Account> accounts = new ArrayList<>();
        String accountNr = "0000000000";
        int i = 0;
        while(iterator.hasNext() && i < amount){
            JSONObject company = iterator.next();
            String companyName = company.get("companyName").toString();
            String kvkNumber = kvkNumberGenerator();
            String vatNumber = vatNumberGenerator();
            Sector sector = sectorGenerator();
            System.out.println(sector);
            BigDecimal balance = balanceGenerator();
            String iban = ibanGenerator(accountNr);
            accountNr = iban.substring(8);
            BusinessAccount businessAccount = new BusinessAccount(iban, balance, companyName, kvkNumber, vatNumber, sector);
            System.out.println(businessAccount);
            accounts.add(businessAccount);
            i++;
        }
            return accounts;
    }
    public static String kvkNumberGenerator(){
        final int KVK_MIN = 1;
        final int KVK_MAX = 99999999;
        int kvkNumber = Gen.generateRandomInt(KVK_MIN, KVK_MAX);
        return String.format("%08d", kvkNumber);
    }
    public static String vatNumberGenerator(){
        final String COUNTRY_CODE = "NL";
        final char VAT_LETTER = 'B';
        final int MIN_VAT = 1;
        final int MAX_START_NUMBER = 99999999;
        final int MAX_END_NUMBER = 99;
        int startNumber = Gen.generateRandomInt(MIN_VAT, MAX_START_NUMBER);
        int endNumber = Gen.generateRandomInt(MIN_VAT, MAX_END_NUMBER);
        return String.format("%s%08d%c%02d", COUNTRY_CODE, startNumber, VAT_LETTER, endNumber);
    }
    public static Sector sectorGenerator(){
        Sector[] sectors = Sector.values();
        return (Sector)Array.get(sectors,Gen.generateRandomInt(0, sectors.length));
    }
    public static BigDecimal balanceGenerator(){
        final int MIN = 0;
        final int MAX_UNCOMMON = 10000000;
        final int MAX_COMMON = 5000;
        final int PERCENTAGE_COMMON = 85;
        if(Gen.generateRandomTrueFalse(PERCENTAGE_COMMON)){
            double randomBalance =(Gen.generateRandomInt(MIN, MAX_COMMON*100))/100.0;
            return BigDecimal.valueOf(randomBalance);
        }
        else {
            double randomBalance = (Gen.generateRandomInt(MIN, MAX_UNCOMMON * 100)) / 100.0;
            return BigDecimal.valueOf(randomBalance);
        }
    }
    public static String ibanGenerator(String previousAccountNr){
        final String LANDCODE = "NL";
        final String BANKCODE = "ROYA";
        AccountService accountService = new AccountService();
        String accountNr = accountService.incrementAccountNrByOne(previousAccountNr);
        accountNr = accountService.makeAccountNr11TestProof(accountNr);
        String controlNr = accountService.createControlNrFromAccountNr(accountNr);
        return  LANDCODE + controlNr + BANKCODE + accountNr;


    }

}
