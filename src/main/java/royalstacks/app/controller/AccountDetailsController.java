package royalstacks.app.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import royalstacks.app.backingBean.AccountDetailsBackingBean;
import royalstacks.app.backingBean.LastTenTransactionBackingBean;
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
    public ModelAndView accountDetailsHandler( @SessionAttribute("userid") int userId, @RequestParam(value = "accountNumber",required = false) String accountNumber) {

        ModelAndView mav = new ModelAndView("/accountdetails");

        //TU USE IN DROPDWON SELECT EVENTUALLY: DO NO FORGET TO ERASE IT IF ITS NOT USED!!!!!!!!!!!
        List<Account> myAccounts = getAccountsFromUserId(userId);
        mav.addObject("accounts",myAccounts);


        //TODO DONT YOU FORGET ABOUT DATE AND TIME: BAAACCCKING BEAAAAANNN

         Account myAccount = getAccountFromAccountNumber(accountNumber);


         populatefields(mav,myAccount);

        //TODO: get transactions corresponding to this account from nosql DB en show only ten last transaction




        return mav;


    }

    //METHODE DIE VELDEN VULLEN
    private void populatefields(ModelAndView mav,Account myAccount) {

        //check als My account heeft waarde
        if(myAccount != null){
            List<Customer> accountholders = getAccountHolders(myAccount);
            AccountDetailsBackingBean accountDetailsBackingBean = getAccountdetailsbb(myAccount);
            List<Transaction> tenLatestTransactions = getTenLastTransaction(myAccount);
            if (tenLatestTransactions != null && !tenLatestTransactions.isEmpty()) {
                List<LastTenTransactionBackingBean> lttb = setupListLastTenTransaction(tenLatestTransactions, myAccount);
                mav.addObject("transactionList", lttb);
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
    private List<LastTenTransactionBackingBean> setupListLastTenTransaction(List<Transaction> tenLatestTransactions, Account account) {
        List<LastTenTransactionBackingBean> lttb = new ArrayList<>();


        for (Transaction transaction : tenLatestTransactions) {
            LastTenTransactionBackingBean lasttentbt = getTransactionBackingBean(transaction, account);
                lttb.add(lasttentbt);
            }

            return lttb;

    }

    //CREATE ONE TRANSACTION BACKING BEAN

    private LastTenTransactionBackingBean getTransactionBackingBean(Transaction transaction, Account account) {
        LastTenTransactionBackingBean lasttentbt = null;
        Account accountFrom;


        //ACCOUNT DEBITET
        if(transaction.getFromAccountId() == account.getAccountId()) {
            accountFrom = accountService.getAccountById(transaction.getToAccountId());
            lasttentbt = fillBackingBeanWithCorrectCalue(accountFrom,transaction);
            lasttentbt.setAmount(" - " + transaction.getAmount());

         //ACCOUNT CREDITET

        }else if((transaction.getToAccountId() == account.getAccountId())){
            accountFrom = accountService.getAccountById(transaction.getFromAccountId());
            lasttentbt = fillBackingBeanWithCorrectCalue(accountFrom,transaction);
            lasttentbt.setAmount(" + " + transaction.getAmount());

        }
        return lasttentbt;
    }

    //FILL OBJECT BB WITH CORRECT VALUES

    private LastTenTransactionBackingBean fillBackingBeanWithCorrectCalue(Account accountFrom, Transaction transaction) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
        String datetime = transaction.getDate().format(formatter);
        String name = getAccountHolders(accountFrom).get(0).getLastName();
        String bankaccount = accountFrom.getAccountNumber();
        String description = transaction.getDescription();


        return new LastTenTransactionBackingBean(datetime,name,bankaccount,description,null);
    }


    //METHODE DIE DE TIEN LAATSTE TRANSACTIES OPHAALD

    private List<Transaction> getTenLastTransaction(Account myAccount) {
        List<Transaction> getTenLastTransaction;
        getTenLastTransaction = transactionService.getTenLastTransaction(myAccount.getAccountId());
        if(!getTenLastTransaction.isEmpty()) {
            for (Transaction transaction : getTenLastTransaction) {
                System.out.println(transaction);
            }
            return getTenLastTransaction;

        }else
            return null;
    }


    //Use the transaction backing bean


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


    //METHODE DIE ACCOUNT TYPE TERUG GEEFT, MAAR DIE IS WAARSCHIJNLIJK NIET NODIG ALS BACKING BEAN WERKT LETS SEE


    private String getAccountType (Account account){
        String accountType = null;
        if (account instanceof BusinessAccount)
            return accountType = "Business Account";
        else
            return accountType = "Private Account";
        }



}

