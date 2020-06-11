package royalstacks.app.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import royalstacks.app.backingBean.AccountDetailsBackingBean;
import royalstacks.app.model.AccountHolderTransaction;
import royalstacks.app.model.*;
import royalstacks.app.service.AccountService;
import royalstacks.app.service.TransactionService;
import royalstacks.app.service.UserService;

import java.time.format.DateTimeFormatter;
import java.util.*;

import static org.springframework.core.annotation.MergedAnnotations.from;

@Controller
public class AccountDetailsController {
    @Autowired
    AccountService accountService;

    @Autowired
    UserService userService;

    @Autowired
    TransactionService transactionService;

    public AccountDetailsController() {
    }

    @GetMapping("/accountdetails")
    public ModelAndView accountDetailsHandler( @SessionAttribute("userid") int userId, @RequestParam(value = "accountNumber",required = false) String accountNumber, @RequestParam(value = "fromAccountNumber",required = false) String fromAccountNumber) {

        ModelAndView mav = new ModelAndView("accountdetails");

        //TU USE IN DROPDWON SELECT EVENTUALLY: DO NO FORGET TO ERASE IT IF ITS NOT USED!!!!!!!!!!!
        List<Account> myAccounts = getAccountsFromUserId(userId);
        mav.addObject("accounts",myAccounts);
        String accountNumberCookie;
        //check if accountnumber belongs to user:
        if(accountNumber!=null) {
             accountNumberCookie = accountNumber;
        }
        else{
            accountNumberCookie = fromAccountNumber;
        }
        for(Account account: myAccounts){
            if(accountNumberCookie.matches(account.getAccountNumber())){
            Account myAccount = getAccountFromAccountNumber(accountNumberCookie);
            populatefields(mav,myAccount);
            return mav;
            }
        }
        //else redirect the user to homepage

        return new ModelAndView("homepage");


    }

    //METHODE DIE VELDEN VULLEN
    private void populatefields(ModelAndView mav,Account myAccount) {

        //check als My account heeft waarde
        if(myAccount != null){
            List<Customer> accountholders = getAccountHolders(myAccount);
            AccountDetailsBackingBean accountDetailsBackingBean = getAccountdetailsbb(myAccount);
            List<AccountHolderTransaction> tenLatestTransactions = getTenLastTransaction(myAccount);
            if (tenLatestTransactions != null) {
                mav.addObject("transactionList", tenLatestTransactions);
            }
            mav.addObject("account",accountDetailsBackingBean);
            mav.addObject("list",accountholders);
        }
    }

    //CREATE ACCOUNT DETAILS BACKING BEAN
    private AccountDetailsBackingBean getAccountdetailsbb(Account myAccount) {
        AccountDetailsBackingBean accountDetailsBackingBean = null;
        if(myAccount instanceof PrivateAccount){
            accountDetailsBackingBean = AccountDetailsBackingBean.createBeanPrivate((PrivateAccount) myAccount);
        }else if (myAccount instanceof BusinessAccount)
            accountDetailsBackingBean = AccountDetailsBackingBean.createBeanBusiness((BusinessAccount) myAccount);
        return accountDetailsBackingBean;
    }

    //CREATE LIST TRANSACTION BACKING BEAN


    //CREATE ONE TRANSACTION BACKING BEAN



    //FILL OBJECT BB last ten transaction WITH CORRECT VALUES



    //METHODE DIE DE TIEN LAATSTE TRANSACTIES OPHAALD

    private List<AccountHolderTransaction> getTenLastTransaction(Account myAccount) {
        List<AccountHolderTransaction> getTenLastTransaction;
        getTenLastTransaction = transactionService.getTenLastTransaction(myAccount.getAccountId());
        if(getTenLastTransaction != null) {
            for (AccountHolderTransaction transaction : getTenLastTransaction) {
                System.out.println(transaction);
            }
            return getTenLastTransaction;
        }
        else return null;
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

    //METHODE DIE HAALT  ACCOUNTS DIE HOREN BIJ HET GEBRUIKER

    private List<Account> getAccountsFromUserId(int userId) {
        Customer customer = (Customer) userService.findByUserId(userId);

        return new ArrayList<>(customer.getAccount());
    }



}

