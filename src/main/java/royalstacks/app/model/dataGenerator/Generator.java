package royalstacks.app.model.DataGenerator;

import org.json.simple.JSONArray;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import royalstacks.app.model.*;
import royalstacks.app.model.repository.AccountRepository;
import royalstacks.app.model.repository.CustomerRepository;
import royalstacks.app.model.repository.EmployeeRepository;
import royalstacks.app.model.repository.TransactionRepository;

import java.util.ArrayList;
import java.util.List;

@Service
public class Generator {

    @Autowired
    private CustomerRepository customerRepository;
    @Autowired
    private EmployeeRepository employeeRepository;
    @Autowired
    private AccountRepository accountRepository;
    @Autowired
    TransactionRepository transactionRepository;
    @Autowired
    private TransactionGenerator transactionGenerator;



    final String customer_data_1 = "src/main/resources/testDataSets/customer_data_1.json";
    final String customer_data_2 = "src/main/resources/testDataSets/customer_data_2.json";
    final String customer_data_3 = "src/main/resources/testDataSets/customer_data_3.json";
    final String customer_data_4 = "src/main/resources/testDataSets/customer_data_4.json";
    final String companyName1 = "src/main/resources/testDataSets/companyName1.json";
    final String companyName2 = "src/main/resources/testDataSets/companyName2.json";

    List<Customer> allCustomers;
    Employee headBusiness;
    Employee headPrivate;
    List<Account> allAccounts;

    public Generator() {
        allCustomers = new ArrayList<>();
        allAccounts = new ArrayList<>();
    }

    public void fillDbAllData() {
        fillDbCustomers();
        fillDbHeadbusiness();
        fillDbHeadPrivate();
        fillDbAccounts();
        fillDbAccountholder();
        fillDbtransactions();

    }
    private void fillDbCustomers(){
        fillDbCustomerBatch(customer_data_1);
        fillDbCustomerBatch(customer_data_2);
        fillDbCustomerBatch(customer_data_3);
        fillDbCustomerBatch(customer_data_4);
    }
    private void fillDbHeadbusiness(){
        headBusiness = royalstacks.app.model.dataGenerator.EmployeeGenerator.headBusinessGenerator();
        employeeRepository.save(headBusiness);
    }
    private void fillDbHeadPrivate(){
        headPrivate = royalstacks.app.model.dataGenerator.EmployeeGenerator.headPrivateGenerator();
        employeeRepository.save(headPrivate);
    }
    private void fillDbAccounts() {
        fillDbBusinessAccountBatch(companyName1);
        fillDbBusinessAccountBatch(companyName2);
        fillDbPrivateAccountBatch();
        fillDbPrivateAccountBatch();
    }

    private void fillDbAccountholder(){
        AccountHolderAdder.addAccountHoldersToAccount(allAccounts, allCustomers, headBusiness);
        accountRepository.saveAll(allAccounts);
    }
    private  void fillDbtransactions(){
        List<Transaction> transactions;
        for (int i = 0; i < 1; i++) {
            transactions = transactionGenerator.generateTransactions(1000);
            transactionRepository.saveAll(transactions);
        }
    }
    private void fillDbCustomerBatch(String fileName){
        JSONArray customerJson = royalstacks.app.model.dataGenerator.Gen.createJsonArrayFromFile(fileName);
        List<Customer> customers = CustomerGenerator.generateCustomers(customerJson);
        allCustomers.addAll(customers);
        customerRepository.saveAll(customers);
    }
    private void fillDbBusinessAccountBatch(String fileName){
        JSONArray companyJson = royalstacks.app.model.dataGenerator.Gen.createJsonArrayFromFile(fileName);
        List<Account> businessAccounts = AccountGenerator.businessAccountGenerator(1000, companyJson);
        allAccounts.addAll(businessAccounts);
        accountRepository.saveAll(businessAccounts);
    }
    private void fillDbPrivateAccountBatch(){
        List<Account> privateAccounts = AccountGenerator.privateAccountGenerator(1000);
        allAccounts.addAll(privateAccounts);
        accountRepository.saveAll(privateAccounts);
    }
}
