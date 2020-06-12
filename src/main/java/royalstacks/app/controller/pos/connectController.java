
package royalstacks.app.controller.pos;


import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import royalstacks.app.model.pos.ConnectionRequest;
import royalstacks.app.model.pos.ConnectionResult;


@RestController
public class connectController {

/**
     *  Zenden van volgende curl stuurt een bericht terug:
     *  curl -X POST http://localhost/paymentmachine/connect -H "Content-Type: application/json" -d "{\"businessAccountIban\":\"0123456789\", \"connectionCode\":54321}"
     * @param connectionRequest
     * @return ConnectionResult - false / 0 als gefaald
     * - true / id als geslaagd
     */


    @PostMapping("/paymentmachine/connect")
    public ConnectionResult paymentMachineConnectionResult(@RequestBody ConnectionRequest connectionRequest){

        ConnectionResult returnValue = checkConnectionResult(connectionRequest);

        return returnValue;
    }

    private ConnectionResult checkConnectionResult(ConnectionRequest data) {

        ConnectionResult connectionResult = new ConnectionResult();
        boolean connectionRequestExists = doesConnectionObjectExist(data);
        boolean codeMatches = doesCodeMatch(data);
        if(connectionRequestExists && codeMatches){
            connectionResult.succeededConnection();
        } else{
            connectionResult.failedConnection();
            return new ConnectionResult();
        }
        return connectionResult;
    }

    private boolean doesCodeMatch(ConnectionRequest data) {
        //todo: implement (check in database)
        return true;
    }

    private boolean doesConnectionObjectExist(ConnectionRequest data) {
        //todo: implement (check in database)
        return true;
    }

}

