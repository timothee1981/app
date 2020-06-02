package royalstacks.app.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.SessionAttribute;
import org.springframework.web.servlet.ModelAndView;
import royalstacks.app.backingBean.TransactionBackingBean;
import royalstacks.app.model.Account;
import royalstacks.app.model.Customer;
import royalstacks.app.model.Transaction;
import royalstacks.app.service.AccountService;
import royalstacks.app.service.UserService;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Optional;

@Controller
public class TransactionController {

    @Autowired
    AccountService accountService;

    @Autowired
    UserService userService;


    @GetMapping("/transaction")
    public ModelAndView transactionHandler(Model model, @SessionAttribute("userid") int userId){
        ModelAndView mav = new ModelAndView("transaction");

        Customer customer =(Customer) userService.findByUserId(userId);
        Iterator<Account> accounts = customer.getAccount().iterator();
        List<Account> myAccounts = new ArrayList<>();
        while(accounts.hasNext()){
            myAccounts.add(accounts.next());
        }
        System.out.println(myAccounts.toString());
        model.addAttribute("account", myAccounts);
        return mav;
    }


    @PostMapping("/transaction")
    public ModelAndView transactionHandler(@ModelAttribute TransactionBackingBean tbb){

        Optional<Account> fromAccount = accountService.getAccountByAccountNumber(tbb.getFromAccountNumber());
        Optional<Account> toAccount = accountService.getAccountByAccountNumber(tbb.getToAccountNumber());

        ModelAndView mav = new ModelAndView("transaction");

        System.out.println("tbb: " + tbb.getFromAccountNumber());

        // Check of amount groter dan 0 is
        if (tbb.getAmount() <= 0){
            mav.addObject("notification", "Invalid amount");
            populateFields(tbb, mav);
            return mav;
        }

        // Check of bankrekeningnummers bestaan en haal bijbehordende Accounts op
        if (getAccountsByAccountNumbers (fromAccount, toAccount, tbb, mav)){
            // als onbekend, geef error
            return mav;
        }

        // maak een transactie object van de BackingBean
        Transaction t = tbb.Transaction();

        // check of er genoeg geld op staat, zo ja maak het geld over.
        executeTransaction(tbb, t, mav);
        return mav;
    }

    private void executeTransaction(@ModelAttribute TransactionBackingBean tbb, Transaction t, ModelAndView mav) {
        if(t.getFromAccount().hasSufficientBalance(t.getAmount())){

            t.getFromAccount().subtractAmount(t.getAmount());
            accountService.saveAccount(t.getFromAccount());

            t.getToAccount().addAmount(t.getAmount());
            accountService.saveAccount(t.getToAccount());

            mav.addObject("notification", "Money successfully sent");
        } else {
            mav.addObject("notification", "Not enough money brah");
            populateFields(tbb, mav);
        }
    }

    private void populateFields(@ModelAttribute TransactionBackingBean tbb, ModelAndView mav) {
        mav.addObject("toAccountNumber", tbb.getToAccountNumber());
        mav.addObject("amount", tbb.getAmount());
        mav.addObject("description", tbb.getDescription());
    }

    /**
     * Haalt Accounts op die horen bij de AccountNumbers van fromAccountNumber en toAccountNumber.
     * Zet daarna de accounts in de BackingBean zodat ervan een Transaction BackingBean gemaakt kan worden
     */
    private boolean getAccountsByAccountNumbers(@ModelAttribute Optional<Account> fromAccountOptional, Optional<Account> toAccountOptional,
                                                TransactionBackingBean tbb, ModelAndView mav) {
        if(fromAccountOptional.isEmpty()){
            mav.addObject("notification", "fromAccountNumber is unknown");
            populateFields(tbb, mav);
            return true;
        } else {
            Account fromAccount = fromAccountOptional.get();
            tbb.setFromAccount(fromAccount);
        }
        if(toAccountOptional.isEmpty()){
            mav.addObject("notification", "toAccountNumber is unknown");
            populateFields(tbb, mav);
            return true;
        } else {
            Account toAccount = toAccountOptional.get();
            tbb.setToAccount(toAccount);
        }
        return false;
    }
}
