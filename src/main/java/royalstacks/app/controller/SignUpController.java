package royalstacks.app.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import royalstacks.app.backingBean.CustomerBackingBean;
import royalstacks.app.model.Customer;
import royalstacks.app.service.CustomerService;
import royalstacks.app.service.UserService;

/**
 * @author Suzanne & Wesley
 */

@Controller
public class SignUpController {

    @Autowired
    private CustomerService customerService;

    @Autowired
    private UserService userService;

    @GetMapping("/signup")
    public ModelAndView signUpHandler(){
        return new ModelAndView("signup");
    }

    @PostMapping("/signup")
    public ModelAndView signUpHandler(@ModelAttribute CustomerBackingBean cbb){
        Customer customer = cbb.customer();
        ModelAndView mav = new ModelAndView("signup");

        if(customerService.isAllInputValid(customer)){
            customerService.saveCustomer(customer);
            populateFields(customer, mav);
            mav.addObject("form", "disabled");
            mav.addObject("notification", "Congratulations! You can now Login");
            System.out.println("**** Customer saved: " + customer);
        } else {
            // zo niet, vul alle velden met input van gebruiker
            mav.addObject("notification", "Sign Up failed: Invalid Field(s)");
            System.out.println("**** No customer saved");
            populateFields(customer, mav);
        }
        return mav;
    }


    private void populateFields(Customer customer, ModelAndView mav) {
        mav.addObject("username", customer.getUsername());
        mav.addObject("password", customer.getPassword());
        mav.addObject("firstName", customer.getFirstName());
        mav.addObject("lastName", customer.getLastName());
        mav.addObject("email", customer.getEmail());
        mav.addObject("postalCode", customer.getPostalCode());
        mav.addObject("city", customer.getCity());
        mav.addObject("phoneNumber", customer.getPhoneNumber());
        mav.addObject("BSN", customer.getBSN());
        mav.addObject("houseNumber", customer.getHouseNumber());
    }

}