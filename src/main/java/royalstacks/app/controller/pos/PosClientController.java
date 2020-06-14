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
import royalstacks.app.service.TransactionService;

import java.math.BigDecimal;
import java.util.Optional;

@Controller
public class PosClientController {


    PosService posService;
    AccountService accountService;
    TransactionService transactionService;


    @Autowired
    public PosClientController(PosService posService, AccountService accountService, TransactionService transactionService) {
        this.posService = posService;
        this.accountService = accountService;
        this.transactionService = transactionService;
    }


    // curl -H "Content-Type: application/json" -X POST -d '{"id":"0", "businessAccountNumber":"NL32ROYA0000000019", "identificationNumber":"1232", "pendingAmount":"100", "clientAccountNumber":"1111111111"}' http://localhost/pos/client/
    @PostMapping("/pos/client/")
    public @ResponseBody
    ResponseEntity<PaymentResult> StartPosClient(@RequestBody PaymentData pd) {


        Optional<Pos> posOptional = posService.findPosByIdentificationNumber(Integer.parseInt(pd.getIdentificationNumber()));
        PaymentResult pr = new PaymentResult();

        if (posOptional.isPresent()) {
            Pos pos = posOptional.get();

            pos.setClientAccountNumber(pd.getAccount());

            Optional<PaymentResult> prOptional = posService.executePosTransaction(pos);
            System.out.println(prOptional.get().toString());
            if (prOptional.isPresent()) {
                return new ResponseEntity<>(prOptional.get(), HttpStatus.OK);
            }
        }
        return new ResponseEntity<>(pr, HttpStatus.OK);
    }
}


