package royalstacks.app.model.repository;

import org.json.simple.JSONArray;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;
import royalstacks.app.model.Account;
import royalstacks.app.model.Customer;
import royalstacks.app.model.fakeDataGenerator.AccountGenerator;
import royalstacks.app.model.fakeDataGenerator.CustomerGenerator;
import royalstacks.app.model.fakeDataGenerator.Gen;
import royalstacks.app.service.AccountService;

import java.util.List;

@Component
public class TransactionSeeder implements CommandLineRunner {

    @Autowired
    AccountService accountService;
    @Autowired
    TransactionRepository transactionRepository;

    public TransactionSeeder() {
    }

    @Override
    public void run(String... args) throws Exception {
        //transactionRepository.deleteAll();
  /*      String personDetailsFile = "src\\main\\resources\\testDataSets\\customer_data_1.json";
        JSONArray personDetails = Gen.createJsonArrayFromFile(personDetailsFile);
        List<Customer> customers = CustomerGenerator.generateCustomerList(personDetails);
*/
        String accountDataFile = "src\\main\\resources\\testDataSets\\companyName1.json";
        JSONArray companyInfo = Gen.createJsonArrayFromFile(accountDataFile);
        List<Account> businessAccounts = AccountGenerator.accountGenerator(30, companyInfo);

        for (int index = 0; index < 100; index++) {
            System.out.println(businessAccounts.get(index).getAccountNumber());

        }

        //CustomerGenerator.printJsonArrayValues(jsonArray);
  /*      List<Customer> customers = CustomerGenerator.printJsonArrayValues(jsonArray);
        for(Customer customer : customers){
            System.out.println(customer);

   */
    }
}

