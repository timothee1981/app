package royalstacks.app.controller.pos;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import royalstacks.app.model.pos.Pos;
import royalstacks.app.service.PosService;

import java.math.BigDecimal;
import java.util.Collection;
import java.util.Map;
import java.util.Optional;

@Controller
public class PosCustomerController {

    PosService posService;

    @Autowired
    public PosCustomerController(PosService posService){
        this.posService = posService;
    }

    @GetMapping("/pos/customer/{identificationNumber}")
    public final ModelAndView posCustomerControllerHandler(@PathVariable("identificationNumber") int identificationNumber, Model model) {
        Pos pos = new Pos();
        Optional<Pos> posOptional = posService.findPosByIdentificationNumber(identificationNumber);

        // TODO verwijder tijdelijk automatisch aanmaken POS
        if(posOptional.isEmpty()){
            System.out.println("invalid POS idNumber");
            pos.setIdentificationNumber(identificationNumber);
            pos.setIdentificationNumber(identificationNumber);
            pos.setBusinessAccountNumber("NL32ROYA0000000019");
            posService.savePos(pos);
        } else {
            pos = posOptional.get();
        }

        model.addAttribute("pos", pos);
        return new ModelAndView("poscustomer");
    }

    @GetMapping("/pos/customer/{identificationNumber}/{pendingAmount}")
    public final ModelAndView setPendingAmountControllerHandler(@PathVariable("pendingAmount") BigDecimal pendingAmount, @PathVariable("identificationNumber") int identificationNumber, Model model) {
        System.out.println(pendingAmount);
        Pos pos = new Pos();
        Optional<Pos> posOptional = posService.findPosByIdentificationNumber(identificationNumber);
        if(posOptional.isEmpty()){
            return new ModelAndView("poscustomer");
        } else {
         pos = posOptional.get();
         pos.setPendingAmount(pendingAmount);
         posService.savePos(pos);
        }

        model.addAttribute("pos", pos);
        model.addAttribute("status", "status1");

        return new ModelAndView("posclient");
    }

    @PostMapping(path = "/pos/customer/{identificationNumber}", consumes = "application/json", produces = "application/json")
    public final  void createPendingTransaction(@PathVariable("identificationNumber") int identificationNumber, BigDecimal amount, Model model) {
        Optional<Pos> pos = posService.findPosByIdentificationNumber(identificationNumber);
    }
}
