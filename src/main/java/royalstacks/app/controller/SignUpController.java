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
        return mav;
    }

    @PostMapping("/signup")
    public ModelAndView signUpHandler(@ModelAttribute CustomerBackingBean cbb){
        Customer customer = cbb.customer();
        ModelAndView mav = new ModelAndView("signup");
        boolean save = true;
        if(!customer.isPasswordValid()) {
            mav.addObject("password_error", "invalid password");
            save = false;
        }
        if(!customer.isPostalCodeValid()) {
            mav.addObject("postalCode_error", "invalid postalCode");
            save = false;
        }
        if(!customer.isCityValid()) {
            mav.addObject("city_error", "invalid city");
            save = false;
        }
        if(!customer.isSocialSecurityNumberFormatValid()) {
            mav.addObject("ssn_error", "invalid ssn");
            save = false;
        }
        if(!customer.isSocialSecurityNumberUnique()) {
            mav.addObject("ssn_error", "invalid ssn");
            save = false;
        }
        if(!customer.isAddressValid()) {
            mav.addObject("address_error", "invalid address");
            save = false;
        }
        if(save) {
            customerService.saveCustomer(customer);
            mav.addObject("confirmation", "Account successfully created");
        }

        return mav;
    }
}