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
import royalstacks.app.service.AccountService;
import royalstacks.app.service.CustomerService;
import royalstacks.app.service.TransactionService;
import java.util.*;

@Controller
public class TransactionController {

    @Autowired
    private AccountService accountService;

    @Autowired
    private TransactionService transactionService;

    @Autowired
    private CustomerService customerService;


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


    public TransactionController() {
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

            // Zet in backingbean zodat een Transaction gemaakt kan worden
            tbb.setFromAccountId(fromAccountOptional.get().getAccountId());
            tbb.setToAccountId(toAccountOptional.get().getAccountId());
        } else {

            showNotification("Transaction failed: invalid input", mav);
            populateFields(tbb, mav);
            return mav;
        }

        if (transactionService.executeTransaction(tbb.transaction())) {
            showNotification("Money successfully sent", mav);
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
    // checked by tom
    private void showAccountsOfUserId(Model model, int userId, Integer accountId) {
        Optional<Customer> customerOptional = customerService.findCustomerByUserId(userId);

        if(customerOptional.isPresent()) {
            Customer currentUser = customerOptional.get();
            List<Account> myAccounts = IteratorUtils.toList(currentUser.getAccount().iterator());

            // accountId wordt meegegeven als de gebruiker vanuit AccountDetails komt
            if (accountId != null) {
                Account account = accountService.getAccountById(accountId);

                // check of account daadwerkelijk bestaat en van de currentUser is
                if (account != null && account.getAccountHolders().contains(currentUser)) {

                    myAccounts.remove(account);
                    myAccounts.add(0, account);
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