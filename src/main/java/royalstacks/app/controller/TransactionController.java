package royalstacks.app.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;
import royalstacks.app.backingBean.TransactionBackingBean;
import royalstacks.app.model.Account;
import royalstacks.app.model.Transaction;
import royalstacks.app.service.AccountService;

import java.util.Optional;

@Controller
public class TransactionController {

    @Autowired
    AccountService accountService;


    @GetMapping("/transaction")
    public ModelAndView transactionHandler(){
        ModelAndView mav = new ModelAndView("transaction");
        return mav;
    }

    @PostMapping("/transaction")
    public ModelAndView transactionHandler(@ModelAttribute TransactionBackingBean tbb){

        Optional<Account> fromAccountOptional = accountService.getAccountByAccountNumber(tbb.getFromAccountNumber());
        Optional<Account> toAccountOptional = accountService.getAccountByAccountNumber(tbb.getToAccountNumber());

        Account fromAccount;
        Account toAccount;

        ModelAndView mav = new ModelAndView("transaction");

        // checked of bankrekeningnummers bestaan
        if(fromAccountOptional.isEmpty()){
            mav.addObject("notification", "fromAccountNumber is unknown");
            return mav;
        } else {
            fromAccount = fromAccountOptional.get();
            tbb.setFromAccount(fromAccount);
        }
        if(toAccountOptional.isEmpty()){
            mav.addObject("notification", "toAccountNumber is unknown");
            return mav;
        } else {
            toAccount = toAccountOptional.get();
            tbb.setToAccount(toAccount);
        }

        Transaction t = tbb.Transaction();
        System.out.println("transaction: " + t);

        // check of er genoeg geld op staat, zo ja zet geld over
        if(fromAccount.hasSufficientBalance(t.getAmount())){
            fromAccount.subtractAmount(t.getAmount());
            accountService.saveAccount(fromAccount);
            toAccount.addAmount(t.getAmount());
            accountService.saveAccount(toAccount);
            mav.addObject("notification", "Mone");
        }

        return mav;
    }
}
