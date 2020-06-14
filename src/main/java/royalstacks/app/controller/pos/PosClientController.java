package royalstacks.app.controller.pos;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import royalstacks.app.model.Account;
import royalstacks.app.model.pos.Pos;
import royalstacks.app.service.AccountService;
import royalstacks.app.service.PosService;

import java.util.Optional;

@Controller
public class PosClientController {


    PosService posService;
    AccountService accountService;



    @Autowired
    public PosClientController(PosService posService, AccountService accountService ) {
        this.posService = posService;
        this.accountService = accountService;
    }


    // curl -H "Content-Type: application/json" -X POST -d '{"id":"0", "businessAccountNumber":"NL32ROYA0000000019", "identificationNumber":"1232", "pendingAmount":"100", "clientAccountNumber":"1111111111"}' http://localhost/pos/client/
    @PostMapping("/pos/client/")
    public @ResponseBody ResponseEntity<PaymentResult> StartPosClient(@RequestBody Pos pos) {
        PaymentResult pr = new PaymentResult();

        Optional<Integer> accountIdOptional = accountService.getAccountIdByNumberExIban(pos.getClientAccountNumber());
        if(accountIdOptional.isEmpty()) {
            pr.setAccountVerified(false);
            return new ResponseEntity<>(pr, HttpStatus.OK);
        } else {
            pr.setAccountVerified(true);
        }

        if(accountService.getAccountById(accountIdOptional.get()).getBalance().compareTo(pos.getPendingAmount() ) < 0){
            pr.setSufficientBalance(false);
            return new ResponseEntity<>(pr, HttpStatus.OK);
        } else {
            pr.setSufficientBalance(true);
        }

        if(posService.executePosTransaction(pos)) {
            pr.setPaymentSuccess(true);
        } else {
            pr.setPaymentSuccess(false);
        }
        return new ResponseEntity<>(pr,HttpStatus.OK);
    }
}

class PaymentResult {

    private boolean accountVerified;
    private boolean sufficientBalance;
    private boolean paymentSuccess;


    public PaymentResult(boolean accountVerified, boolean sufficientBalance, boolean paymentSuccess) {
        this.accountVerified = accountVerified;
        this.sufficientBalance = sufficientBalance;
        this.paymentSuccess = paymentSuccess;
    }

    public PaymentResult() {
    }

    public boolean isAccountVerified() {
        return accountVerified;
    }

    public void setAccountVerified(boolean accountVerified) {
        this.accountVerified = accountVerified;
    }

    public boolean isSufficientBalance() {
        return sufficientBalance;
    }

    public void setSufficientBalance(boolean sufficientBalance) {
        this.sufficientBalance = sufficientBalance;
    }

    public boolean isPaymentSuccess() {
        return paymentSuccess;
    }

    public void setPaymentSuccess(boolean paymentSuccess) {
        this.paymentSuccess = paymentSuccess;
    }
}

