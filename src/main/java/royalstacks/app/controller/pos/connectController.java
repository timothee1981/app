package royalstacks.app.controller.pos;

import com.google.gson.Gson;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import royalstacks.app.model.pos.ConnectionRequestData;


@RestController
public class connectController {


    private ConnectionRequestData connectionRequestData;


    /**
     *  Zenden van volgende curl stuurt een bericht terug:
     *  curl -X POST http://localhost/paymentmachine/connect -H "Content-Type: application/json" -d "{\"number\":\"9\"}"
     * @param number
     * @return
     */

    @PostMapping("/paymentmachine/connect")
    public String paymentMachineConnectionResult(@RequestBody String number){

        System.out.println(number);
        return "gelukt";
    }

}
