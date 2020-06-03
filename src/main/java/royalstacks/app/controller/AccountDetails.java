package royalstacks.app.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import royalstacks.app.model.*;
import royalstacks.app.service.AccountService;
import royalstacks.app.service.UserService;

import java.util.*;

@Controller
public class AccountDetails {
    @Autowired
    AccountService accountService;

    @Autowired
    UserService userService;

    public AccountDetails() {
    }

    @GetMapping("/accountdetails")
    public ModelAndView accountDetailsHandler( @SessionAttribute("userid") int userId, @RequestParam(value = "accountNumber",required = false) String accountNumber) {

        ModelAndView mav = new ModelAndView("/accountdetails");
        List<Account> myAccounts = getAccountsFromUserId(userId);

        //TODO DONT YOU FORGET ABOUT DATE AND TIME

         Account myAccount = getAccountFromAccountNumber(accountNumber);

         populatefields(mav,myAccount);

        //TODO: get transactions corresponding to this account from nosql DB en show only ten last transactio
        mav.addObject("accounts",myAccounts);
       // mav.addObject("accountNumber", accountNumber);

        return mav;


    }

    //METHODE DIE VELDEN VULLEN
    private void populatefields(ModelAndView mav,Account myAccount) {

        //check als My account heeft waarde
        if(myAccount != null){
            List<Customer> accountholders = getAccountHolders(myAccount);
            mav.addObject("accountNumber",myAccount.getAccountNumber());
            mav.addObject("balance", myAccount.getBalance());
            mav.addObject("list",accountholders);
        }
    }

    //METHODE DIE LIJST MET ACCOUNT HOLDERS TERUG GEEFT

    private List<Customer> getAccountHolders(Account myAccount) {
        return new ArrayList<>(myAccount.getAccountHolders());

    }

    //METHODE DIE JUISTE ACCOUNT TERUG GEEFT

    private Account getAccountFromAccountNumber(String accountNumber) {
        Account myAccount = null;
        Optional<Account> account = accountService.getAccountByAccountNumber(accountNumber);
        if(account.isPresent()){
            myAccount = account.get();
        }

        return myAccount;
    }

    //METHODE DIE DE DROPDOWN VULT MET ACCOUNTS DIE HOREN BIJ HET GEBRUIKER

    private List<Account> getAccountsFromUserId(int userId) {
        List<Account> myAccounts = new ArrayList<>();
        Customer customer = (Customer) userService.findByUserId(userId);
        Iterator<Account> accounts = customer.getAccount().iterator();
        while(accounts.hasNext()){
            myAccounts.add(accounts.next());
        }

        return myAccounts;
    }


    //METHODE DIE ACCOUNT TYPE TERUG GEEFT, MAAR DIE IS WAARSCHIJNLIJK NIET NODIG


    private String getAccountType (Account account){
        String accountType = null;
        if (account instanceof BusinessAccount)
            return accountType = "Business Account";
        else
            return accountType = "Private Account";
        }



}

