package royalstacks.app.controller.pos;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.servlet.ModelAndView;
import royalstacks.app.model.pos.Pos;
import royalstacks.app.service.PosService;

import java.math.BigDecimal;
import java.util.Optional;

@Controller
public class PosClientController {


    PosService posService;


    @Autowired
    public PosClientController(PosService posService){
        this.posService = posService;
    }
    @GetMapping("/pos/client")
    public final ModelAndView posClientControllerHandler() {
        return new ModelAndView("posclient");
    }

    @GetMapping("/pos/client/{identificationNumber}")
    public final ModelAndView posCustomerControllerHandler(@PathVariable("identificationNumber") int identificationNumber, Model model) {

        Pos pos;

        Optional<Pos> posOptional = posService.findPosByIdentificationNumber(identificationNumber);
        if(posOptional.isEmpty()){
            return new ModelAndView("posclient");
        } else {
            pos = posOptional.get();
        }

        model.addAttribute("pos", pos);
        return new ModelAndView("posclient");
    }

    @GetMapping("/pos/client/{identificationNumber}/{clientAccountNumber}")
    public final ModelAndView setPosTransferControllerHandler(@PathVariable("clientAccountNumber") String clientAccountNumber, @PathVariable("identificationNumber") int identificationNumber, Model model) {
        Pos pos = new Pos();
        ModelAndView mav = new ModelAndView("posclient");
        pos.setIdentificationNumber(identificationNumber);

        model.addAttribute("pos", pos);

        Optional<Pos> posOptional = posService.findPosByIdentificationNumber(identificationNumber);
        if(posOptional.isEmpty()){
            return new ModelAndView("posclient");
        }
        pos = posOptional.get();
        if(pos.getPendingAmount() == null){
            mav.addObject("notification", "No pending transaction");
            System.out.println("no pending transaction");
            return new ModelAndView("posclient");
        }

        pos.setClientAccountNumber(clientAccountNumber);
        posService.savePos(pos);

        // TODO vervang dit door REST request
        if(posService.executePosTransaction(pos)){
            System.out.println("success");
            mav.addObject("notification", "Transaction executed");
        } else {
            System.out.println("fail");
            mav.addObject("notification", "Transaction failed");
        }
        return mav;
    }
}
