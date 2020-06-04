package royalstacks.app.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import royalstacks.app.backingBean.CustomerBackingBean;
import royalstacks.app.model.Customer;
import royalstacks.app.model.User;
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

        // check of alle velden goed ingevuld zijn
        if(isAllInputValid(customer, mav)) {
            customerService.saveCustomer(customer);
            populateFields(customer, mav);
            mav.addObject("form", "disabled");
            mav.addObject("confirmation", "Congratulations! You can now Login");
            System.out.println("**** Customer saved: " + customer);
        } else {
            // zo niet, vul alle velden met input van gebruiker
            System.out.println("**** No customer saved");
            populateFields(customer, mav);
        }
        return mav;
    }


    /**
     * Checkt alle velden apart en geeft feedback al deze niet goed ingvuld zijn
     * @param customer
     * @param mav
     * @return
     */
    private boolean isAllInputValid(Customer customer, ModelAndView mav) {
        boolean save = true;

        if(!customer.isUsernameFormatValid()) { save = false; showError("invalid format usernameField", mav); }
        if(userService.findByUsername(customer.getUsername()).isPresent()) { save = false; mav.addObject("username", "showUsernameNotAvailable()"); }
        if(!User.isPasswordValid(userPassword)) { save = false; showError("Password must contain 1 lower case letter, 1 upper case letter, 1 number, 1 special character and 10 characters in length", mav); }
        if(!customer.isFirstNameValid()) { save = false; showError("invalid first name", mav);}
        if(!customer.isLastNameValid()) { save = false; showError("invalid last name", mav); }
        if(!customer.isEmailValid()){ save = false; showError("invalid emailField", mav); }
        if(!customer.isPostalCodeValid()) { save = false; showError("invalid postalCodeField", mav); }
        if(!customer.isCityValid()) { save = false; showError("invalid cityField", mav); }
        if(!customer.isPhoneNumberValid()){ save = false; showError("invalid phoneNumberField", mav); }
        if(!customer.isBSNFormatValid() || customerService.findCustomerByBSN(customer.getBSN()).isPresent() ) { save = false; showError("BSNField not available", mav); }
        if(!customer.isHouseNumber()) { save = false; showError("invalid houseNumberField", mav); }

        return save;
    }

    private void showError(String error, ModelAndView mav){
        mav.addObject("error", error);
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