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
import royalstacks.app.service.CustomerService;

@Controller
public class SignUpController {

    @Autowired
    private CustomerService customerService;

    @GetMapping("/signup")
    // Tijdelijk om verschillende invoerfouten op te vangen. Wordt vervangen door JS
    public ModelAndView signUpHandler(@RequestParam(required=false) boolean invalid,
                                      @RequestParam(required=false) String error){
        ModelAndView mav = new ModelAndView("signup");
        if (invalid) {
            mav.addObject("error", "Error: " + error);
        }
        //System.out.println(customerService.findBySocialSecurityNumber("987654321"));
        return mav;
    }

    @PostMapping("/signup")
    public ModelAndView signUpHandler(@ModelAttribute CustomerBackingBean cbb){
        Customer customer = cbb.customer();
        ModelAndView mav = new ModelAndView("signup");
        boolean save = true;

        if(!customer.isUsernameFormatValid()) {
            mav.addObject("username_error", "invalid format username");
            save = false;
        }
        if(!customer.isPasswordValid()) {
            mav.addObject("password_error", "invalid password");
            save = false;
        }
        if(!customer.isFirstNameValid()) {
            mav.addObject("firstName", "invalid first name");
            save = false;
        }
        if(!customer.isLastNameValid()) {
            mav.addObject("lastName", "invalid last name");
            save = false;
        }
        if(!customer.isEmailAddressValid()){
            mav.addObject("emailAddress", "invalid emailAddress");
        }
        if(!customer.isPostalCodeValid()) {
            mav.addObject("postalCode_error", "invalid postalCode");
            save = false;
        }
        if(!customer.isCityValid()) {
            mav.addObject("city_error", "invalid city");
            save = false;
        }
        if(!customer.isPhoneNumberValid()){
            mav.addObject("phoneNumber", "invalid phoneNumber");
        }
        if(!customer.isSocialSecurityNumberFormatValid()) {
            mav.addObject("ssn_error", "invalid ssn format");
            save = false;
        }
        if(!customer.isSocialSecurityNumberUnique()) {
            mav.addObject("ssn_error", "ssn is not unique");
            save = false;
        }
        if(!customer.isAddressValid()) {
            mav.addObject("address_error", "invalid address");
            save = false;
        }

        // als een veld niet goed ingevuld is, vul alle velden met input van gebruiker
        if(!save) {
            populateFields(customer, mav);
        } else {
            // door alle checks heen gekomen: sla gebruiker op en geef bevestiging
            customerService.saveCustomer(customer);
            mav.addObject("confirmation", "Account successfully created");
        }
        return mav;
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