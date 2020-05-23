package royalstacks.app.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.security.servlet.UserDetailsServiceAutoConfiguration;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import royalstacks.app.backingBean.CustomerBackingBean;
import royalstacks.app.model.Customer;
import royalstacks.app.service.CustomerService;
import royalstacks.app.service.UserService;

@Controller
public class SignUpController {

    @Autowired
    private CustomerService customerService;

    @Autowired
    private UserService userService;

    @GetMapping("/signup")
    // Tijdelijk om verschillende invoerfouten op te vangen. Wordt vervangen door JS
    public ModelAndView signUpHandler(@RequestParam(required=false) boolean invalid,
                                      @RequestParam(required=false) String error){
        ModelAndView mav = new ModelAndView("signup");
        if (invalid) {
            mav.addObject("error", "Error: " + error);
        }
        System.out.println(userService.findById(1));
        return mav;
    }

    @PostMapping("/signup")
    public ModelAndView signUpHandler(@ModelAttribute CustomerBackingBean cbb){
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
        if(!customer.isUsernameUnique()) { save = false; mav.addObject("username_error", "username not unique"); }
        if(!customer.isPasswordValid()) { save = false; mav.addObject("password_error", "invalid password"); }
        if(!customer.isFirstNameValid()) { save = false; mav.addObject("firstName", "invalid first name"); }
        if(!customer.isLastNameValid()) { save = false; mav.addObject("lastName", "invalid last name"); }
        if(!customer.isEmailAddressValid()){ save = false; mav.addObject("emailAddress", "invalid emailAddress"); }
        if(!customer.isPostalCodeValid()) { save = false; mav.addObject("postalCode_error", "invalid postalCode"); }
        if(!customer.isCityValid()) { save = false; mav.addObject("city_error", "invalid city"); }
        if(!customer.isPhoneNumberValid()){ save = false;mav.addObject("phoneNumber", "invalid phoneNumber"); }
        if(!customer.isSocialSecurityNumberFormatValid()) { save = false; mav.addObject("ssn_error", "invalid ssn format"); }
        if(!customer.isSocialSecurityNumberUnique()) { save = false; mav.addObject("ssn_error", "ssn is not unique"); }
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
        mav.addObject("password", customer.getPassword());
        mav.addObject("firstName", customer.getFirstName());
        mav.addObject("lastName", customer.getLastName());
        mav.addObject("postalCode", customer.getPostalCode());
        mav.addObject("city", customer.getCity());
        mav.addObject("socialSecurityNumber", customer.getSocialSecurityNumber());
        mav.addObject("address", customer.getAddress());
    }

}