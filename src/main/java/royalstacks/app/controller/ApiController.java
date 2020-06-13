package royalstacks.app.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import royalstacks.app.model.BusinessAccount;
import royalstacks.app.model.CustomerAndTransactions;
import royalstacks.app.service.*;

import java.util.List;

@Controller
public class ApiController {


    private AccountService accountService;
    private UserService userService;
    private CustomerService customerService;
    private BusinessAccountService businessAccountService;
    private PosService posService;

    @Autowired
    public ApiController(AccountService as, UserService us, CustomerService cs,  BusinessAccountService bs, PosService ps){
        this.accountService = as;
        this.userService = us;
        this.customerService = cs;
        this.businessAccountService = bs;
        this.posService = ps;
    }


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

    @GetMapping("/api/isBusinessAccount")
    @ResponseBody
    public String isBusinessAccountHandler(@RequestParam String accountNumber) {
        return String.valueOf(businessAccountService.findBusinessAccountByAccountNumber(accountNumber).isPresent());

    }

    @GetMapping("/openaccount/v_check")
    @ResponseBody
    public String vatNumberCheckHandler(@RequestParam String vatnumber){
        BusinessAccount businessAccount =  new BusinessAccount();
        businessAccount.setVatNumber(vatnumber);
        return String.valueOf(businessAccount.isVatValid());
    }

    @GetMapping("/api/top10transactions")
    public @ResponseBody List<CustomerAndTransactions> topTransactionList(){
        return customerService.findTop10TransactionsOnBusinessAccounts();
    }

    @GetMapping("/api/getPendingAmount")
    public @ResponseBody String getPendingAmount(int posId) {
        if (posService.findPosByIdentificationNumber(posId).isPresent()) {
            return String.valueOf(posService.findPosByIdentificationNumber(posId).get().getPendingAmount());
        } else {
            return "No payment pending";
        }
    }



    @PostMapping(value = "/api/iban",consumes = "text/plain")
    @ResponseBody
    public String ibanCheckHandler(@RequestBody String iban) throws Exception{
        return String.valueOf(accountService.getAccountByAccountNumber(iban).isPresent());
    }
}
