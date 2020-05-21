package royalstacks.app.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;
import royalstacks.app.backingBean.CustomerBackingBean;
import royalstacks.app.model.Customer;
import royalstacks.app.service.CustomerService;

@Controller
public class SignUpController {

    @Autowired
    private CustomerService customerService;

    @PostMapping("/signup")
    public ModelAndView signUpHandler(@ModelAttribute CustomerBackingBean cbb) {
        System.out.println(cbb);
        ModelAndView mav = new ModelAndView();
        Customer customer = cbb.customer();
        customerService.saveCustomer(customer);
        return mav;
    }
}

