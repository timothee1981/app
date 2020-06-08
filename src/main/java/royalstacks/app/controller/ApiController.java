package royalstacks.app.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import royalstacks.app.model.BusinessAccount;
import royalstacks.app.model.Customer;
import royalstacks.app.service.AccountService;
import royalstacks.app.service.CustomerService;
import royalstacks.app.service.UserService;

@Controller
public class ApiController {

    @Autowired
    private AccountService accountService;

    @Autowired
    private UserService userService;

    @Autowired
    private CustomerService customerService;


    @GetMapping("/api/username")
    @ResponseBody
    public String isUsernameUniqueHandler(@RequestParam String username){
        return String.valueOf(userService.findByUsername(username).isEmpty());
    }

    @GetMapping("/api/bsn")
    @ResponseBody
    public String isBSNUniqueHandler(@RequestParam String BSN) {
        return String.valueOf(customerService.findCustomerByBSN(BSN).isEmpty());

    }

    @GetMapping("/openaccount/v_check")
    @ResponseBody
    public String vatNumberCheckHandler(@RequestParam String vatnumber){
        BusinessAccount businessAccount =  new BusinessAccount();
        businessAccount.setVatNumber(vatnumber);
        return String.valueOf(businessAccount.isVatValid());
    }
    @PostMapping(value = "/api/iban",consumes = "text/plain")
    @ResponseBody
    public String ibanCheckHandler(@RequestBody String iban) throws Exception{
        return String.valueOf(accountService.getAccountByAccountNumber(iban).isPresent());
    }
}
