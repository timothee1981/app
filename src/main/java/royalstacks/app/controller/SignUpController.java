package royalstacks.app.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import royalstacks.app.backingBean.CustomerBackingBean;
import royalstacks.app.model.Customer;
import royalstacks.app.model.User;
import royalstacks.app.service.CustomerService;
import royalstacks.app.service.UserService;

@Controller
public class SignUpController {

    private String userPassword;

    @Autowired
    private CustomerService customerService;

    @Autowired
    private UserService userService;

    @GetMapping("/signup")
    public ModelAndView signUpHandler(){
        ModelAndView mav = new ModelAndView("signup");
        return mav;
    }

    @PostMapping("/signup")
    public ModelAndView signUpHandler(@ModelAttribute CustomerBackingBean cbb, @RequestParam String password){
        userPassword = password;
        Customer customer = cbb.customer();
        System.out.println(customer);
        ModelAndView mav = new ModelAndView("signup");

        // check of alle velden goed ingevuld zijn
        if(isAllInputValid(customer, mav)) {
            customerService.saveCustomer(customer);
            populateFields(customer, mav);
            mav.addObject("form", "disabled");
            mav.addObject("confirmation", "Account successfully created");
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

        if(!customer.isUsernameFormatValid()) { save = false; mav.addObject("error", "invalid format username");}
        if(userService.findByUsername(customer.getUsername()).isPresent()) { save = false; mav.addObject("error", "username not unique"); }
        if(!User.isPasswordValid(userPassword)) { save = false; mav.addObject("error", "Password must contain 1 lower case letter, 1 upper case letter, 1 number, 1 special character and 10 characters in length"); }
        if(!customer.isFirstNameValid()) { save = false; mav.addObject("error", "invalid first name"); }
        if(!customer.isLastNameValid()) { save = false; mav.addObject("error", "invalid last name"); }
        if(!customer.isEmailValid()){ save = false; mav.addObject("error", "invalid email"); }
        if(!customer.isPostalCodeValid()) { save = false; mav.addObject("error", "invalid postalCode"); }
        if(!customer.isCityValid()) { save = false; mav.addObject("error", "invalid city"); }
        if(!customer.isPhoneNumberValid()){ save = false;mav.addObject("error", "invalid phoneNumber"); }
        if(!customer.isBSNFormatValid()) { save = false; mav.addObject("error", "BSN should contain 9 numbers"); }
        if(customerService.findCustomerByBSN(customer.getBSN()).isPresent()) { save = false; mav.addObject("error", "bsn is not unique"); }
        if(!customer.isAddressValid()) { save = false; mav.addObject("error", "invalid address"); }

        return save;
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
        mav.addObject("address", customer.getAddress());
    }

}