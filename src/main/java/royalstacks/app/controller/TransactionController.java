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
import royalstacks.app.service.TransactionService;
import royalstacks.app.service.UserService;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Optional;
import java.util.stream.StreamSupport;

@Controller
public class TransactionController {

    @Autowired
    AccountService accountService;

    @Autowired
    UserService userService;

    @Autowired
    TransactionService transactionService;


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
    public ModelAndView transactionHandler(@ModelAttribute TransactionBackingBean tbb, @SessionAttribute("userid") int userId) {
        ModelAndView mav = new ModelAndView("transaction");

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


    private void populateFields(TransactionBackingBean tbb, ModelAndView mav) {
        mav.addObject("toAccountNumber", tbb.getToAccountNumber());
        mav.addObject("amount", tbb.getAmount());
        mav.addObject("description", tbb.getDescription());
    }
}