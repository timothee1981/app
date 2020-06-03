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
import royalstacks.app.service.CustomerService;
import royalstacks.app.service.TransactionService;
import royalstacks.app.service.UserService;
import java.util.*;

@Controller
public class TransactionController {

    @Autowired
    AccountService accountService;

    @Autowired
    UserService userService;

    @Autowired
    TransactionService transactionService;

    @Autowired
    CustomerService customerService;


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
            showNotification("Transaction failed: invalid input", mav);
            populateFields(tbb, mav);
            return mav;
        }

        Transaction t = tbb.Transaction();

        // voer transactie uit
        if (transactionService.executeTransaction(t)) {
            showNotification("Money successfully sent", mav);
            // TODO sla transactie op
        } else {
            showNotification("Transaction failed: failed to execute", mav);
            populateFields(tbb, mav);
        }
        return mav;
    }

    /**
     * Haalt alle Accounts van userId op
     * Als accountId meegegeven wordt wordt de bijbehorende Account bovenaan gezet
     */
    @SuppressWarnings("unchecked")
    private void showAccountsOfUserId(Model model, int userId, Integer accountId) {
        Optional<Customer> customerOptional = customerService.findCustomerByUserId(userId);
        if(customerOptional.isPresent()) {
            Customer currentUser = customerOptional.get();
            List<Account> myAccounts = IteratorUtils.toList(currentUser.getAccount().iterator());

            // check of een accountId meegegeven wordt
            if (accountId != null) {
                Account account = accountService.getAccountById(accountId);

                // check of deze account bestaat
                if (account != null) {

                    // check of userId daadwerkelijk holder is van account
                    if (account.getAccountHolders().contains(currentUser)) {

                        // zet account boven aan en haal duplicaat uit lijst
                        myAccounts.remove(accountService.getAccountById(accountId));
                        myAccounts.add(0, accountService.getAccountById(accountId));
                    }
                }
            }
            model.addAttribute("account", myAccounts);
        }
    }

    /**
     * Vul de velden met de ingevoerde waardes. Wordt gebruikt wanneer een error getoond wodt
     */
    private void populateFields(TransactionBackingBean tbb, ModelAndView mav) {
        mav.addObject("toAccountNumber", tbb.getToAccountNumber());
        mav.addObject("amount", tbb.getAmount());
        mav.addObject("description", tbb.getDescription());
    }

    private void showNotification(String notification, ModelAndView mav){
        mav.addObject("notification", notification);
    }
}