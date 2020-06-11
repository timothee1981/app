package royalstacks.app.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;
import royalstacks.app.model.pos.ConnectionRequest;
import royalstacks.app.service.AccountService;
import royalstacks.app.service.ConnectionRequestService;

import java.net.URI;
import java.net.URISyntaxException;

@RestController
public class GeneratePosConnectionController {

    AccountService accountService;
    ConnectionRequestService connectionRequestService;

    @Autowired
    public GeneratePosConnectionController(AccountService accountService, ConnectionRequestService connectionRequestService) {
        this.accountService = accountService;
        this.connectionRequestService = connectionRequestService;
    }

    @GetMapping("/pos/employee")
    public ModelAndView posEmployeeHandler() {
        return new ModelAndView("generateposconnection");
    }

    @PostMapping("/pos/employee")
    public ResponseEntity<ConnectionRequest> create(@ModelAttribute ConnectionRequest connectionRequest) throws URISyntaxException {
        System.out.println(connectionRequest);
        if (connectionRequest == null) {
            return ResponseEntity.notFound().build();
        } else {
            URI uri = ServletUriComponentsBuilder.fromCurrentRequest()
                    .path("/{id}")
                    .buildAndExpand(connectionRequest.getId())
                    .toUri();

            return ResponseEntity.created(uri)
                    .body(connectionRequest);
        }
    }
}
