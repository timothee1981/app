package royalstacks.app.model.DataGenerator;

import org.json.simple.JSONArray;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import royalstacks.app.model.*;
import royalstacks.app.model.repository.AccountRepository;
import royalstacks.app.model.repository.CustomerRepository;
import royalstacks.app.model.repository.EmployeeRepository;
import royalstacks.app.service.AccountService;
import royalstacks.app.service.UserService;

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
    private AccountService accountService;
    @Autowired
    private UserService userService;

    final String customer_data_1 = "src\\main\\resources\\testDataSets\\customer_data_1.json";
    final String customer_data_2 = "src\\main\\resources\\testDataSets\\customer_data_1.json";
    final String customer_data_3 = "src\\main\\resources\\testDataSets\\customer_data_1.json";
    final String customer_data_4 = "src\\main\\resources\\testDataSets\\customer_data_1.json";
    final String companyName1 = "src\\main\\resources\\testDataSets\\companyName1.json";
    final String companyName2 = "src\\main\\resources\\testDataSets\\companyName2.json";

    List<Customer> customers;
    Employee headBusiness;
    List<Account> accounts;

    public Generator() {

    }

    public void GenerateAllDatabaseData() {
        //Generate 4000 customers
        customers = GenerateCustomers();
        //Genereer 2 Employees en sla op
        headBusiness = GenerateHeadbusiness();
        GenerateHeadPrivate();
        //Genereer 4000 accounts
        accounts = GenerateAccounts();
        //Add accountholders
        addAccountHolders();


    }
    private List<Customer> GenerateCustomers(){
        List<Customer> allCustomers = new ArrayList<>();
        //genereer de 1e 1000 customers en sla op
        JSONArray customerJson = Gen.createJsonArrayFromFile(customer_data_1);
        List<Customer> customers = CustomerGenerator.generateCustomers(1000, customerJson);
        allCustomers.addAll(customers);
        customerRepository.saveAll(customers);
        //genereer de 2e 1000 customers en sla op
        customerJson = Gen.createJsonArrayFromFile(customer_data_2);
        customers = CustomerGenerator.generateCustomers(1000, customerJson);
        allCustomers.addAll(customers);
        customerRepository.saveAll(customers);
        //genereer de 3e 1000 customers en sla op
        customerJson = Gen.createJsonArrayFromFile(customer_data_3);
        customers = CustomerGenerator.generateCustomers(1000, customerJson);
        allCustomers.addAll(customers);
        customerRepository.saveAll(customers);
        //genereer de 4e 1000 customers en sla op
        customerJson = Gen.createJsonArrayFromFile(customer_data_4);
        customers = CustomerGenerator.generateCustomers(1000, customerJson);
        allCustomers.addAll(customers);
        customerRepository.saveAll(customers);
        return allCustomers;

    }
    private Employee GenerateHeadbusiness(){
        Employee headBusiness = EmployeeGenerator.headBusinessGenerator();
        employeeRepository.save(headBusiness);
        return headBusiness;
    }
    private Employee GenerateHeadPrivate(){
        Employee headPrivate = EmployeeGenerator.headPrivateGenerator();
        employeeRepository.save(headPrivate);
        return headPrivate;

    }


    private List<Account> GenerateAccounts() {
        List<Account> allAccounts = new ArrayList<>();
        JSONArray companyJson = Gen.createJsonArrayFromFile(companyName1);
        List<Account> businessAccounts = AccountGenerator.businessAccountGenerator(1000, companyJson);
        allAccounts.addAll(businessAccounts);
        accountRepository.saveAll(businessAccounts);
        companyJson = Gen.createJsonArrayFromFile(companyName2);
        businessAccounts = AccountGenerator.businessAccountGenerator(1000, companyJson);
        allAccounts.addAll(businessAccounts);
        accountRepository.saveAll(businessAccounts);
        List<Account> privateAccounts = AccountGenerator.privateAccountGenerator(1000);
        allAccounts.addAll(privateAccounts);
        accountRepository.saveAll(privateAccounts);
        privateAccounts = AccountGenerator.privateAccountGenerator(1000);
        allAccounts.addAll(privateAccounts);
        accountRepository.saveAll(privateAccounts);
        return allAccounts;
    }

    private void addAccountHolders(){
        AccountHolderAdder.addAccountHoldersToAccount(accounts, customers, headBusiness);
        accountRepository.saveAll(accounts);
}
}
