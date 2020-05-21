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
        String error;

        if(!customer.isPasswordValid()){
            error = "password invalid";
        } else if(!customer.isPostalCodeValid()) {
            error = "postalCode invalid";
        } else if(!customer.isCityValid()) {
            error = "city invalid";
        } else if(!customer.isSocialSecurityNumberFormatValid()) {
            error = "SSN format invalid";
        } else if(!customer.isSocialSecurityNumberUnique()) {
            error = "SSN not unique";
        } else if(!customer.isAddressValid()) {
            error = "adress invalid";
        } else {
            customerService.saveCustomer(customer);
            return new ModelAndView("signupconfirmation");
        }

        ModelAndView mav = new ModelAndView("signup");
        mav.addObject("invalid", true);
        mav.addObject("error", error);
        return mav;
    }

    @GetMapping("/signupconfirmation")
    public ModelAndView signUpConfirmationHandler(){
        return new ModelAndView("signupconfirmation");
    }
}