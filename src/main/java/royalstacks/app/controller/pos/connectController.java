package royalstacks.app.controller.pos;


import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import royalstacks.app.model.pos.ConnectionRequest;
import royalstacks.app.model.pos.ConnectionRequestData;


@RestController
public class connectController {


    /**
     *  Zenden van volgende curl stuurt een bericht terug:
     *  curl -X POST http://localhost/paymentmachine/connect -H "Content-Type: application/json" -d "{\"account\":\"0123456789\", \"code\":54321}"
     * @param connectionRequestData
     * @return
     */

    @PostMapping("/paymentmachine/connect")
    public String paymentMachineConnectionResult(@RequestBody ConnectionRequestData connectionRequestData){


        System.out.println(connectionRequestData.getAccount());
        System.out.println(connectionRequestData.getCode());
        return "gelukt";
    }

}
