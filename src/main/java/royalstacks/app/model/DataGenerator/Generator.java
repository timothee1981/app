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
    final String companyNAme1 = "src\\main\\resources\\testDataSets\\companyName1.json";
    final String companyNAme2 = "src\\main\\resources\\testDataSets\\companyName2.json";

    public void GenerateAllDatabaseData() {
        JSONArray customerJson = Gen.createJsonArrayFromFile(customer_data_1);
        List<Customer> customers = CustomerGenerator.generateCustomers(1000, customerJson);
        customerRepository.saveAll(customers);
        Employee headBusiness = EmployeeGenerator.headBusinessGenerator();
        Employee headPrivate = EmployeeGenerator.headPrivateGenerator();
        employeeRepository.save(headPrivate);
        employeeRepository.save(headBusiness);
        JSONArray companyJson = Gen.createJsonArrayFromFile(companyNAme1);
        List<Account> businessAccounts = AccountGenerator.businessAccountGenerator(500, companyJson);
        List<Account> privateAccounts = AccountGenerator.privateAccountGenerator(500);
        accountRepository.saveAll(businessAccounts);
        accountRepository.saveAll(privateAccounts);
        AccountHolderAdder.addAccountHoldersToAccount(businessAccounts, customers, headBusiness);
        AccountHolderAdder.addAccountHoldersToAccount(privateAccounts, customers, null);
        accountRepository.saveAll(businessAccounts);
        accountRepository.saveAll(privateAccounts);
    }
}
