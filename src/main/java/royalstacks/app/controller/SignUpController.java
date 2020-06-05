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

    private String userPassword;

    @Autowired
    private CustomerService customerService;

    @Autowired
    private UserService userService;

    @GetMapping("/signup")
    public ModelAndView signUpHandler(){
        return new ModelAndView("signup");
    }

    @PostMapping("/signup")
    public ModelAndView signUpHandler(@ModelAttribute CustomerBackingBean cbb, @RequestParam String password){
        userPassword = password;
        Customer customer = cbb.customer();
        ModelAndView mav = new ModelAndView("signup");

        if(isAllInputValid(customer)) {
            customerService.saveCustomer(customer);
            populateFields(customer, mav);
            mav.addObject("form", "disabled");
            mav.addObject("confirmation", "Congratulations! You can now Login");
            System.out.println("**** Customer saved: " + customer);
        } else {
            // zo niet, vul alle velden met input van gebruiker
            mav.addObject("error", "Sign Up failed: Invalid Field(s)");
            System.out.println("**** No customer saved");
            populateFields(customer, mav);
        }
        return mav;
    }


    /**
     * Checkt alle velden apart en geeft feedback al deze niet goed ingvuld zijn
     * @param customer
     * @return
     */
    private boolean isAllInputValid(Customer customer) {

        return userService.isUsernameFormatValid(customer.getUsername()) &&
                userService.findByUsername(customer.getUsername()).isEmpty() &&
                userService.isPasswordValid(userPassword) &&
                userService.isFirstNameValid(customer.getFirstName()) &&
                userService.isLastNameValid(customer.getLastName()) &&
                customerService.isEmailValid(customer.getEmail()) &&
                customerService.isPostalCodeValid(customer.getPostalCode()) &&
                customerService.isCityValid(customer.getCity()) &&
                customerService.isPhoneNumberValid(customer.getPhoneNumber()) &&
                customerService.isBSNFormatValid(customer.getBSN()) &&
                customerService.isHouseNumberValid(customer.getHouseNumber());
    }


    private void populateFields(Customer customer, ModelAndView mav) {
        mav.addObject("username", customer.getUsername());
        mav.addObject("password", userPassword);
        mav.addObject("firstName", customer.getFirstName());
        mav.addObject("lastName", customer.getLastName());
        mav.addObject("email", customer.getEmail());
        mav.addObject("postalCode", customer.getPostalCode());
        mav.addObject("city", customer.getCity());
        mav.addObject("phoneNumber", customer.getPhoneNumber());
        mav.addObject("BSN", customer.getBSN());
        mav.addObject("address", customer.getHouseNumber());
    }

}