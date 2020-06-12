package royalstacks.app.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import royalstacks.app.model.BusinessAccount;
import royalstacks.app.model.CompaniesAndTransactions;
import royalstacks.app.service.AccountService;
import royalstacks.app.service.BusinessAccountService;
import royalstacks.app.service.CustomerService;
import royalstacks.app.service.UserService;

import java.util.List;

@Controller
public class ApiController {

    @Autowired
    private AccountService accountService;

    @Autowired
    private UserService userService;

    @Autowired
    private CustomerService customerService;

    @Autowired
    private BusinessAccountService businessAccountService;

    @GetMapping("/api/accountredirect")
    @ResponseBody
    public void accountredirect(@RequestParam String accountnumber){
        System.out.println("hij draait de account-redirect methode");

    }

    @GetMapping("/api/username")
    @ResponseBody
    public String isUsernameUniqueHandler(@RequestParam String username){
        return String.valueOf(userService.findByUsername(username).isEmpty());
    }

    @GetMapping("/api/bsn")
    @ResponseBody
    public String isBSNUniqueHandler(@RequestParam String bsn) {
        return String.valueOf(customerService.findCustomerByBSN(bsn).isEmpty());

    }

    @GetMapping("/openaccount/v_check")
    @ResponseBody
    public String vatNumberCheckHandler(@RequestParam String vatnumber){
        BusinessAccount businessAccount =  new BusinessAccount();
        businessAccount.setVatNumber(vatnumber);
        return String.valueOf(businessAccount.isVatValid());
    }

    @GetMapping("/api/top10transactions")
    public @ResponseBody List<CompaniesAndTransactions> topTransactionList(){
        return businessAccountService.findTop10TransactionsOnBusinessAccounts();
    }

    @PostMapping(value = "/api/iban",consumes = "text/plain")
    @ResponseBody
    public String ibanCheckHandler(@RequestBody String iban) throws Exception{
        return String.valueOf(accountService.getAccountByAccountNumber(iban).isPresent());
    }
}
