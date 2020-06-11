package royalstacks.app.controller.pos;


import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import royalstacks.app.model.pos.ConnectionRequest;
import royalstacks.app.model.pos.ConnectionRequestData;
import royalstacks.app.model.pos.ConnectionResult;


@RestController
public class connectController {


    /**
     *  Zenden van volgende curl stuurt een bericht terug:
     *  curl -X POST http://localhost/paymentmachine/connect -H "Content-Type: application/json" -d "{\"account\":\"0123456789\", \"code\":54321}"
     * @param connectionRequestData
     * @return ConnectionResult - false / 0 als gefaald
     * - true / id als geslaagd
     */

    @PostMapping("/paymentmachine/connect")
    public ConnectionResult paymentMachineConnectionResult(@RequestBody ConnectionRequestData connectionRequestData){

        ConnectionResult returnValue = checkConnectionResult(connectionRequestData);

        return returnValue;
    }

    private ConnectionResult checkConnectionResult(ConnectionRequestData data) {

        ConnectionResult connectionResult = new ConnectionResult();
        boolean connectionRequestExists = doesConnectionObjectExist(data);
        if(connectionRequestExists){
            connectionResult.succeededConnection();
        } else{
            connectionResult.failedConnection();
            return new ConnectionResult();
        }
        return connectionResult;
    }

    private boolean doesConnectionObjectExist(ConnectionRequestData data) {
        //todo: implement (check in database)
        return false;
    }

}
