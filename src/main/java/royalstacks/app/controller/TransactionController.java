package royalstacks.app.controller;

import org.apache.commons.collections.IteratorUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import royalstacks.app.backingBean.TransactionBackingBean;
import royalstacks.app.model.Account;
import royalstacks.app.model.Customer;
import royalstacks.app.model.Transaction;
import royalstacks.app.model.User;
import royalstacks.app.service.AccountService;
import royalstacks.app.service.TransactionService;
import royalstacks.app.service.UserService;

import java.time.LocalDateTime;
import java.util.*;
import java.util.stream.StreamSupport;

@Controller
public class TransactionController {

    @Autowired
    AccountService accountService;

    @Autowired
    UserService userService;

    @Autowired
    TransactionService transactionService;


    /**
     * GetMapping
     * Account van de accountId zal als eerst getoond worden als deze meegegeven wordt
     */
    @GetMapping("/transaction")
    public ModelAndView transactionHandler(@RequestParam (required = false) Integer accountId,
                                           @SessionAttribute("userid") int userId,
                                           Model model) {

        ModelAndView mav = new ModelAndView("transaction");
        showAccountsOfUserId(model, userId, accountId);
        return mav;
    }


    /**
     * PostMapping
     */
    @PostMapping("/transaction")
    public ModelAndView transactionHandler(@ModelAttribute TransactionBackingBean tbb,
                                           @RequestParam (required = false) Integer accountId,
                                           @SessionAttribute("userid") int userId,
                                           Model model) {

        ModelAndView mav = new ModelAndView("transaction");
        showAccountsOfUserId(model, userId, accountId);

        Optional<Account> fromAccountOptional = accountService.getAccountByAccountNumber(tbb.getFromAccountNumber());
        Optional<Account> toAccountOptional = accountService.getAccountByAccountNumber(tbb.getToAccountNumber());

        // Check of alle velden correct ingevuld zijn
        if (fromAccountOptional.isPresent() && toAccountOptional.isPresent() && tbb.getAmount() > 0) {
            // Als alles goed ingevuld is, zet in backing bean
            tbb.setFromAccountId(fromAccountOptional.get().getAccountId());
            tbb.setToAccountId(toAccountOptional.get().getAccountId());
        } else {
            // Zo niet, geef error terug
            mav.addObject("notification", "Transaction failed: invalid input");
            populateFields(tbb, mav);
            return mav;
        }

        Transaction t = tbb.Transaction();

        // voer transactie uit
        if (transactionService.executeTransaction(t)) {
            mav.addObject("notification", "Money successfully sent");
            // TODO sla transactie op
        } else {
            mav.addObject("notification", "Transaction failed: failed to execute");
            populateFields(tbb, mav);
        }
        return mav;
    }

    private void showAccountsOfUserId(Model model, @SessionAttribute("userid") int userId, Integer accountId) {
        Customer customer = (Customer) userService.findByUserId(userId);

        List<Account> myAccounts = IteratorUtils.toList(customer.getAccount().iterator());

        // check of een accountId meegegeven wordt
        if (accountId != null) {
            Account account = accountService.getAccountById(accountId);

            // check of deze account bestaat
            if(account != null){
                Customer c = (Customer) userService.findByUserId(userId);

                // check of userId holder is van account
                if(account.getAccountHolders().contains(c)){

                    // zet account boven aan en haal duplicaat uit lijst
                    myAccounts.add(0, accountService.getAccountById(accountId));
                    myAccounts.remove(accountService.getAccountById(accountId));
                }
            }
        }

        model.addAttribute("account", myAccounts);
    }

    private void populateFields(TransactionBackingBean tbb, ModelAndView mav) {
        mav.addObject("toAccountNumber", tbb.getToAccountNumber());
        mav.addObject("amount", tbb.getAmount());
        mav.addObject("description", tbb.getDescription());
    }
}