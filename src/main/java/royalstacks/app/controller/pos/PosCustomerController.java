package royalstacks.app.controller.pos;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;
import royalstacks.app.model.pos.PendingTransaction;
import royalstacks.app.model.pos.Pos;
import royalstacks.app.service.PendingTransactionService;
import royalstacks.app.service.PosService;

import java.math.BigDecimal;
import java.util.Collection;
import java.util.Map;
import java.util.Optional;

@Controller
public class PosCustomerController {

    PosService posService;
    PendingTransactionService pendingTransactionService;

    @Autowired
    public PosCustomerController(PosService posService, PendingTransactionService pendingTransactionService){
        this.posService = posService;
        this.pendingTransactionService = pendingTransactionService;
    }

    @GetMapping("/pos/customer/{identificationNumber}")
    public final ModelAndView posCustomerControllerHandler(@PathVariable("identificationNumber") int identificationNumber, Model model) {
        Pos pos = new Pos();
        pos.setIdentificationNumber(identificationNumber);
        pos.setIdentificationNumber(identificationNumber);
        pos.setBusinessAccountNumber("NL32ROYA0000000019");
        model.addAttribute("pos", pos);
        posService.savePos(pos);
        return new ModelAndView("poscustomer");
    }

    @PostMapping(path = "/pos/customer/{identificationNumber}", consumes = "application/json", produces = "application/json")
    public final  void createPendingTransaction(@PathVariable("identificationNumber") int identificationNumber, BigDecimal amount, Model model) {
        Optional<Pos> pos = posService.findPosByIdentificationNumber(identificationNumber);

        PendingTransaction pendingTransaction = new PendingTransaction();
        pendingTransaction.setPos(pos.get());
        pendingTransaction.setAmount(amount);
        pendingTransactionService.save(pendingTransaction);
    }
}
