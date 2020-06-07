package royalstacks.app.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import royalstacks.app.backingBean.CustomerBackingBean;
import royalstacks.app.model.Customer;
import royalstacks.app.service.CustomerService;

import java.util.logging.Level;
import java.util.logging.Logger;

@Controller
public class SignUpController {

    private static final String SIGN_UP_SUCCESS= "Congratulations! You can now Login";
    private static final String SIGN_UP_FAILED= "Sign Up failed: Invalid Field(s)";

    private static final Logger LOGGER = Logger.getLogger(SignUpController.class.getName());

    private CustomerService customerService;
    private ModelAndView mav;
    private Customer customer;

    @Autowired
    public SignUpController(CustomerService cs){
        this.customerService = cs;
    }

    @GetMapping("/signup")
    public final ModelAndView signUpHandler(){
        return new ModelAndView("signup");
    }

    @PostMapping("/signup")
    public final ModelAndView signUpHandler(@ModelAttribute CustomerBackingBean cbb){

        this.mav = new ModelAndView("signup");
        this.customer = cbb.customer();

        if(customerService.isAllInputValid(this.customer)){
            customerService.saveCustomer(this.customer);
            populateFields();
            disableForm();
            notification(SIGN_UP_SUCCESS);
            LOGGER.log(Level.INFO, "**** Customer saved");
        } else {
            populateFields();
            notification(SIGN_UP_FAILED);
            LOGGER.log(Level.SEVERE,"**** No customer saved");
        }
        return mav;
    }

    private void disableForm(){
        this.mav.addObject("form", "disabled");
    }

    private void notification(String notification){
        this.mav.addObject("notification", notification);
    }

    private void populateFields() {
        this.mav.addObject("username", this.customer.getUsername());
        this.mav.addObject("password", this.customer.getPassword());
        this.mav.addObject("firstName", this.customer.getFirstName());
        this.mav.addObject("lastName", this.customer.getLastName());
        this.mav.addObject("email", this.customer.getEmail());
        this.mav.addObject("postalCode", this.customer.getPostalCode());
        this.mav.addObject("city", this.customer.getCity());
        this.mav.addObject("phoneNumber", this.customer.getPhoneNumber());
        this.mav.addObject("BSN", this.customer.getBSN());
        this.mav.addObject("houseNumber", this.customer.getHouseNumber());
    }

}