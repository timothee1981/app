package royalstacks.app.model.repository;

import org.json.simple.JSONArray;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;
import royalstacks.app.model.Customer;
import royalstacks.app.model.CustomerGenerator;
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
        String bestandsnaam = "src\\main\\resources\\customer_data_1.json";
        JSONArray jsonArray = CustomerGenerator.createJsonArrayFromFile(bestandsnaam);
        for (int index = 0; index < 20; index++) {
            System.out.println(CustomerGenerator.postalCodeGenerator());

        }

        //CustomerGenerator.printJsonArrayValues(jsonArray);
  /*      List<Customer> customers = CustomerGenerator.printJsonArrayValues(jsonArray);
        for(Customer customer : customers){
            System.out.println(customer);

   */
    }
}

