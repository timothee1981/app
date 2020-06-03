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

        // maak Optional<Account> van ingevoerde accountNummers en geef deze terug aan de BackingBean
        tbb.setFromAccount(accountService.getAccountByAccountNumber(tbb.getFromAccountNumber()));
        tbb.setToAccount(accountService.getAccountByAccountNumber(tbb.getToAccountNumber()));

        // Check of Transaction valid is, als dat niet zo is is de backingbean leeg
        if(tbb.Transaction().isEmpty()) {
            mav.addObject("notification", "Invalid Transaction");
            populateFields(tbb, mav);
            return mav;
        }

        // Check of fromAccount daadwerkelijk van de ingelogde gebruiker is
        if(!tbb.Transaction().get().getFromAccount().getAccountHolders().contains(userService.findByUserId(userId))){
            mav.addObject("notification", "YOU'RE STEALING MONEY! WE'RE CALLING THE POLICE!");
            populateFields(tbb, mav);
            return mav;
        }

        Transaction t = tbb.Transaction().get();

        t.getFromAccount().subtractAmount(t.getAmount());
        accountService.saveAccount(t.getFromAccount());

        t.getToAccount().addAmount(t.getAmount());
        accountService.saveAccount(t.getToAccount());

        mav.addObject("notification", "Money successfully sent");
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

    }

    private void populateFields(TransactionBackingBean tbb, ModelAndView mav) {
        mav.addObject("toAccountNumber", tbb.getToAccountNumber());
        mav.addObject("amount", tbb.getAmount());
        mav.addObject("description", tbb.getDescription());
    }
}