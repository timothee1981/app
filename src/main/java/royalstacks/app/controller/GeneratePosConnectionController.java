package royalstacks.app.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import royalstacks.app.model.pos.ConnectionRequest;
import royalstacks.app.service.AccountService;
import royalstacks.app.service.BusinessAccountService;
import royalstacks.app.service.ConnectionRequestService;

import java.util.Optional;
import java.util.Random;

@RestController
public class GeneratePosConnectionController {

    AccountService accountService;
    ConnectionRequestService connectionRequestService;
    BusinessAccountService businessAccountService;

    @Autowired
    public GeneratePosConnectionController(AccountService accountService, ConnectionRequestService connectionRequestService, BusinessAccountService businessAccountService) {
        this.accountService = accountService;
        this.connectionRequestService = connectionRequestService;
        this.businessAccountService = businessAccountService;
    }

    @GetMapping("/pos/employee")
    public final ModelAndView generatePosConnectionHandler() {
        return new ModelAndView("generateposconnection");
    }

    @PostMapping("/pos/employee")
    public final ModelAndView generatePosConnectionPostHandler(@RequestParam String businessAccountIban){
        ModelAndView mav = new ModelAndView("generateposconnection");

        if(businessAccountService.findBusinessAccountByAccountNumber(businessAccountIban).isEmpty()){
            mav.addObject("businessAccountIban", "Ik bel de politie!");
            return mav;
        }

        ConnectionRequest connectionRequest = new ConnectionRequest();
        Optional<ConnectionRequest> crOptional = connectionRequestService.findCustomerRequestByBusinessAccountIban(businessAccountIban);

        int connectionCode = 10000 + new Random().nextInt(90000);

        if(crOptional.isPresent()){
            connectionRequest = crOptional.get();
            connectionRequest.setConnectionCode(connectionCode);
            System.out.println("request: " + connectionRequest);
        } else {
            connectionRequest.setBusinessAccountIban(businessAccountIban);
            connectionRequest.setConnectionCode(connectionCode);
        }
        connectionRequestService.saveConnectionRequest(connectionRequest);
        mav.addObject("businessAccountIban", businessAccountIban);
        mav.addObject("connectionCode", connectionCode);
        return mav;
    }

}
