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

    private String tempPassword;

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
        tempPassword = password;
        Customer customer = cbb.customer();
        ModelAndView mav = new ModelAndView("signup");

        // check of alle velden goed ingevuld zijn
        if(isAllInputValid(customer, mav)) {
            customerService.saveCustomer(customer);
            mav.addObject("confirmation", "Account successfully created");
        } else {
            // zo niet, vul alle velden met input van gebruiker
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

        if(!customer.isUsernameFormatValid()) { save = false; mav.addObject("username_error", "invalid format username");}
        if(userService.findByUsername(customer.getUsername()).isPresent()) { save = false; mav.addObject("username_error", "username not unique"); }
        if(!User.isPasswordValid(tempPassword)) { save = false; mav.addObject("password_error", "Password must contain 1 lower case letter, 1 upper case letter, 1 number, 1 special character and 10 characters in length"); }
        if(!customer.isFirstNameValid()) { save = false; mav.addObject("firstName_error", "invalid first name"); }
        if(!customer.isLastNameValid()) { save = false; mav.addObject("lastName_error", "invalid last name"); }
        if(!customer.isEmailValid()){ save = false; mav.addObject("email_error", "invalid email"); }
        if(!customer.isPostalCodeValid()) { save = false; mav.addObject("postalCode_error", "invalid postalCode"); }
        if(!customer.isCityValid()) { save = false; mav.addObject("city_error", "invalid city"); }
        if(!customer.isPhoneNumberValid()){ save = false;mav.addObject("phoneNumber", "invalid phoneNumber"); }
        if(!customer.isBSNFormatValid()) { save = false; mav.addObject("bsn_error", "BSN should contain 9 numbers"); }
        if(customerService.findCustomerByBSN(customer.getBSN()).isPresent()) { save = false; mav.addObject("bsn_error", "bsn is not unique"); }
        if(!customer.isAddressValid()) { save = false; mav.addObject("address_error", "invalid address"); }

        return save;
    }

    /**
     * Vult alle velden in met input van gebruiker
     * @param customer
     * @param mav
     */
    private void populateFields(Customer customer, ModelAndView mav) {
        mav.addObject("username", customer.getUsername());
        mav.addObject("password", tempPassword);
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