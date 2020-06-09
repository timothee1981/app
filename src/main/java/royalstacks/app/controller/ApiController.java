package royalstacks.app.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;
import royalstacks.app.model.BusinessAccount;
import royalstacks.app.model.Customer;
import royalstacks.app.model.CustomerAndTransactions;
import royalstacks.app.service.AccountService;
import royalstacks.app.service.CustomerService;
import royalstacks.app.service.UserService;
import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

import java.util.List;

@Controller
public class ApiController {

    @Autowired
    private AccountService accountService;

    @Autowired
    private UserService userService;

    @Autowired
    private CustomerService customerService;

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
    @ResponseBody
    public ModelAndView topTransactionList(){
        Gson gson = new Gson();
        String json = gson.toJson(customerService.findTop10TransactionsOnBusinessAccounts());
        ModelAndView mav = new ModelAndView();
        mav.addObject(json);
        return mav;
    }
}
