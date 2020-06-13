package royalstacks.app.controller.pos;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import royalstacks.app.model.pos.Pos;
import royalstacks.app.service.PosService;

@Controller
public class PosClientController {


    PosService posService;


    @Autowired
    public PosClientController(PosService posService) {
        this.posService = posService;
    }


    @PostMapping("/pos/client/")
    public @ResponseBody String StrtPosClient() {
        return "hoi";
    /*    model.addAttribute(pos);

        if(posService.executePosTransaction(pos)){
            System.out.println("success");

            return new ResponseEntity<>(new PaymentResult(true), HttpStatus.OK);
        } else {
            System.out.println("failt");
            return new ResponseEntity<>(new PaymentResult(false), HttpStatus.OK);
        }
    }*/
    }
}

class PaymentResult {

    private boolean successful;

    public PaymentResult(boolean succesful) {
        this.successful = succesful;
    }

    public boolean isSuccessful() {
        return successful;
    }

    public void setSuccessful(boolean successful) {
        this.successful = successful;
    }
}

