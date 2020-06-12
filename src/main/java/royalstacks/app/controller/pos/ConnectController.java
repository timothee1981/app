
package royalstacks.app.controller.pos;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import royalstacks.app.model.pos.ConnectionRequest;
import royalstacks.app.model.pos.ConnectionResult;
import royalstacks.app.model.repository.ConnectionRequestRepository;
import royalstacks.app.service.PosService;

import java.util.Optional;


@RestController
public class ConnectController {

/**
     *  Zenden van volgende curl stuurt een bericht terug:
     *  curl -X POST http://localhost/paymentmachine/connect -H "Content-Type: application/json" -d "{\"businessAccountIban\":\"0123456789\", \"connectionCode\":54321}"
     * @param connectionRequest
     * @return ConnectionResult - false / 0 als gefaald
     * - true / id als geslaagd
     */

    @Autowired
    PosService posService;

    @Autowired
    ConnectionRequestRepository connectionRequestRepository;

    @PostMapping("/paymentmachine/connect")
    public ConnectionResult paymentMachineConnectionResult(@RequestBody ConnectionRequest connectionRequest){

        ConnectionResult returnValue = checkConnectionResult(connectionRequest);

        return returnValue;
    }

    private ConnectionResult checkConnectionResult(ConnectionRequest data) {

        ConnectionResult connectionResult = new ConnectionResult();
        if(doesConnectionObjectMatch(data)){
            connectionResult = succeededConnection(connectionResult);
        } else{
            connectionResult = failedConnection(connectionResult);
        }
        return connectionResult;
    }



    private boolean doesConnectionObjectMatch(ConnectionRequest data) {
        String ibanRequested = data.getBusinessAccountIban();
        Optional<ConnectionRequest> request = connectionRequestRepository.findCustomerRequestByBusinessAccountIban(ibanRequested);
        if(request.isPresent()){
            // do codes match
            ConnectionRequest myRequest = request.get();
            if(data.getConnectionCode() == myRequest.getConnectionCode() ){
                return true;
            }
        }
        return false;
    }

    private ConnectionResult failedConnection(ConnectionResult connectionResult){
        connectionResult.setSucceeded(false);
        connectionResult.setId(0);
        return connectionResult;
    }


    private ConnectionResult succeededConnection(ConnectionResult connectionResult) {
        connectionResult.setSucceeded(true);
        connectionResult.setId(generate8DigitId());
        return connectionResult;
    }

    private long generate8DigitId() {
        final long INITIAL_ID = 00000000;
        long lastId;

        if(retrieveLastId() == 0){
            lastId = INITIAL_ID;
        }
        else {
            lastId = retrieveLastId();
        }
        return  lastId+1;
    }

    private long retrieveLastId() {
        Optional<Integer> lastId = posService.getLastPosId();
        if (lastId.isPresent()) {
            try{
                int intId = lastId.get();
                return intId;
            } catch (Error e){
                return 0;
            }
        } else {
            return 0;
        }
    }

}

