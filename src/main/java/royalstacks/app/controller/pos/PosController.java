package royalstacks.app.controller.pos;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import royalstacks.app.model.pos.*;
import royalstacks.app.service.ConnectionRequestService;
import royalstacks.app.service.ConnectionResultService;
import royalstacks.app.service.PosService;

import java.util.Optional;

@Controller
public class PosController {

    PosService posService;
    ConnectionResultService connectionResultService;
    ConnectionRequestService connectionRequestService;

    @Autowired
    public PosController(PosService posService, ConnectionResultService connectionResultService, ConnectionRequestService connectionRequestService) {
        this.posService = posService;
        this.connectionResultService = connectionResultService;
        this.connectionRequestService = connectionRequestService;
    }


    @GetMapping("/pos")
    public final ModelAndView posCustomerTestControllerHandler(Model model) {
        Pos dummy = new Pos();
        model.addAttribute(dummy);
        return new ModelAndView("pos");
    }


    @GetMapping("/pos/{identificationNumber}")
    public final ModelAndView posDuoControllerHandler(@PathVariable("identificationNumber") int identificationNumber, Model model) {
        Pos pos = new Pos();
        Optional<Pos> posOptional = posService.findPosByIdentificationNumber(identificationNumber);

        if (posOptional.isPresent()) {
            pos = posOptional.get();
        }

        model.addAttribute("pos", pos);
        return new ModelAndView("pos");
    }

    // curl -H "Content-Type: application/json" -X POST -d '{"id":"0", "businessAccountNumber":"NL32ROYA0000000019", "identificationNumber":"1232", "pendingAmount":"100", "clientAccountNumber":"1111111111"}' http://localhost/pos/client/
    @PostMapping("/pos/client/")
    public @ResponseBody ResponseEntity<PaymentResult> StartPosClient(@RequestBody PaymentData pd) {
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

    @PostMapping("/pos/connect")
    public @ResponseBody ConnectionResult posConnectionResult(@RequestBody ConnectionRequest connectionRequest){

        ConnectionResult returnValue = connectionResultService.checkConnectionResult(connectionRequest);

        if(returnValue.isSucceeded()){
            ConnectionRequest cr = connectionRequestService.findCustomerRequestByBusinessAccountIban(connectionRequest.getBusinessAccountIban()).get();

            Pos pos = new Pos();
            pos.setIdentificationNumber((int) returnValue.getId());
            pos.setBusinessAccountNumber(cr.getBusinessAccountIban());
            posService.savePos(pos);

            connectionRequestService.delete(cr);
        }
        return returnValue;
    }
}
