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
    public ModelAndView transactionHandler(Model model, @SessionAttribute("userid") int userId) {
        ModelAndView mav = new ModelAndView("transaction");

        List<Account> myAccounts = getAccountsFromUserId(userId);
        model.addAttribute("account", myAccounts);
        return mav;
    }

    /**
     * Haalt alle accounts die horen bij de userId
     *
     * @param userId
     * @return
     */
    private List<Account> getAccountsFromUserId(int userId) {
        Customer customer = (Customer) userService.findByUserId(userId);
        Iterator<Account> accounts = customer.getAccount().iterator();
        List<Account> myAccounts = new ArrayList<>();
        while (accounts.hasNext()) {
            myAccounts.add(accounts.next());
        }
        return myAccounts;
    }

    /**
     * PostMapping
     *
     * @param tbb
     * @return
     */
    @PostMapping("/transaction")
    public ModelAndView transactionHandler(@ModelAttribute TransactionBackingBean tbb) {

        ModelAndView mav = new ModelAndView("transaction");

        // Check of amount groter dan 0 is
        if (tbb.getAmount() <= 0) {
            mav.addObject("notification", "Invalid amount");
            populateFields(tbb, mav);
            return mav;
        }

        tbb.Transaction()

/*        Optional<Account> fromAccountOptional = accountService.getAccountByAccountNumber(tbb.getFromAccountNumber());
        Optional<Account> toAccountOptional = accountService.getAccountByAccountNumber(tbb.getToAccountNumber());

        // Check of accounts bestaan
        if (fromAccountOptional.isPresent()) {
            Account fromAccount = fromAccountOptional.get();
            tbb.setFromAccount(fromAccount);
        } else {
            mav.addObject("notification", "fromAccountNumber is unknown");
            populateFields(tbb, mav);
            return mav;
        }
        if (toAccountOptional.isPresent()) {
            Account toAccount = toAccountOptional.get();
            tbb.setToAccount(toAccount);
        } else {
            mav.addObject("notification", "toAccountNumber is unknown");
            populateFields(tbb, mav);
            return mav;
        }

        // maak een transactie object van de BackingBean
        Transaction t = tbb.Transaction();*/

        // check of er genoeg geld op staat, zo ja maak het geld over.
        executeTransaction(tbb, t, mav);

        // TODO sla transactie op

        return mav;
    }

    /**
     * Voert transa
     *
     * @param tbb
     * @param t
     * @param mav
     */
    private void executeTransaction(@ModelAttribute TransactionBackingBean tbb, Transaction t, ModelAndView mav) {
        if (t.getFromAccount().hasSufficientBalance(t.getAmount())) {

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

    private void populateFields(TransactionBackingBean tbb, ModelAndView mav) {
        mav.addObject("toAccountNumber", tbb.getToAccountNumber());
        mav.addObject("amount", tbb.getAmount());
        mav.addObject("description", tbb.getDescription());
    }
}