package royalstacks.app.controller.pos;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import royalstacks.app.model.pos.PaymentData;
import royalstacks.app.model.pos.PaymentResult;
import royalstacks.app.model.pos.Pos;
import royalstacks.app.service.AccountService;
import royalstacks.app.service.PosService;
import royalstacks.app.service.TransactionService;


import java.util.Optional;

@RestController
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
    public ResponseEntity<PaymentResult> StartPosClient(@RequestBody PaymentData pd) {
        System.out.println(pd.toString());


        Optional<Pos> posOptional = posService.findPosByIdentificationNumber(Integer.parseInt(pd.getIdentificationNumber()));
        PaymentResult pr = new PaymentResult();

        if (posOptional.isPresent()) {
            Pos pos = posOptional.get();
            pos.setPendingAmount(pd.getAmount());
            pos.setClientAccountNumber(pd.getAccount());
            Optional<PaymentResult> prOptional = posService.executePosTransaction(pos);

            if (prOptional.isPresent()) {
                return new ResponseEntity<>(prOptional.get(), HttpStatus.OK);
            }
        }
        return new ResponseEntity<>(pr, HttpStatus.OK);
    }

    @PostMapping(path = "/pos/journal/")
    public final void createJournal(@RequestBody String identificationNumber) {
        // TODO journal post
    }
}


