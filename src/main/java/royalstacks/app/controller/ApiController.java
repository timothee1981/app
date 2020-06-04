package royalstacks.app.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
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
    public String usernameCheckHandler(@RequestParam String username){
        Customer c = new Customer();
        c.setUsername(username);
        return String.valueOf(c.isUsernameFormatValid() && userService.findByUsername(username).isEmpty());
    }

    @GetMapping("/api/bsn")
    @ResponseBody
    public String BSNCheckHandler(@RequestParam String BSN) {
        Customer c = new Customer();
        c.setBSN(BSN);
        return String.valueOf(c.isBSNFormatValid() && customerService.findCustomerByBSN(BSN).isEmpty());

    }
}