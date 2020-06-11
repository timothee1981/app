package royalstacks.app.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;
import royalstacks.app.model.PosValidator;
import royalstacks.app.model.PosValidatorData;
import royalstacks.app.service.AccountService;
import royalstacks.app.service.PosValidatorService;

import java.net.URI;
import java.net.URISyntaxException;

@RestController
public class PosEmployeeController {

    AccountService accountService;
    PosValidatorService posValidatorService;

    @Autowired
    public PosEmployeeController(AccountService accountService, PosValidatorService posValidatorService) {
        this.accountService = accountService;
        this.posValidatorService = posValidatorService;
    }

    @GetMapping("/pos/employee")
    public ModelAndView posEmployeeHandler() {
        return new ModelAndView("posemployee");
    }

    @PostMapping("/pos/employee")
    public ResponseEntity<PosValidator> create(@ModelAttribute PosValidator posValidator) throws URISyntaxException {
        System.out.println(posValidator);
        if (posValidator == null) {
            return ResponseEntity.notFound().build();
        } else {
            URI uri = ServletUriComponentsBuilder.fromCurrentRequest()
                    .path("/{id}")
                    .buildAndExpand(posValidator.getId())
                    .toUri();

            return ResponseEntity.created(uri)
                    .body(posValidator);
        }
    }
}
