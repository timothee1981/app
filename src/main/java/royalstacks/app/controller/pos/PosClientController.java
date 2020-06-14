package royalstacks.app.controller.pos;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import royalstacks.app.model.pos.PaymentData;
import royalstacks.app.model.pos.PaymentResult;
import royalstacks.app.model.pos.Pos;
import royalstacks.app.service.AccountService;
import royalstacks.app.service.PosService;

import java.math.BigDecimal;
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
    public @ResponseBody ResponseEntity<PaymentResult> StartPosClient(@RequestBody PaymentData pd) {

        System.out.println(pd);

        Optional<Pos> posOptional = posService.findPosByIdentificationNumber(Integer.parseInt(pd.getIdentificationNumber()));
        PaymentResult pr = new PaymentResult();

        if(posOptional.isPresent()) {
            Pos pos = posOptional.get();

            Optional<Integer> accountIdOptional = accountService.getAccountIdByNumberExIban(pd.getAccount());

            if (accountIdOptional.isEmpty()) {
                pr.setAccountVerified(false);
                return new ResponseEntity<>(pr, HttpStatus.OK);
            } else {
                pr.setAccountVerified(true);
                pos.setClientAccountNumber(pd.getAccount());
            }

            if (accountService.getAccountById(accountIdOptional.get()).getBalance().compareTo(pos.getPendingAmount()) < 0) {
                pr.setSufficientBalance(false);
                return new ResponseEntity<>(pr, HttpStatus.OK);
            } else {
                pr.setSufficientBalance(true);
            }

            if (posService.executePosTransaction(pos)) {
                pr.setPaymentSuccess(true);
            } else {
                pr.setPaymentSuccess(false);
            }
            return new ResponseEntity<>(pr, HttpStatus.OK);
        } else {
            pr.setPaymentSuccess(false);
        }
        return new ResponseEntity<>(pr, HttpStatus.OK);
    }
}


