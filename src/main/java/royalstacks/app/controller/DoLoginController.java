package royalstacks.app.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import royalstacks.app.model.Customer;
import royalstacks.app.model.Employee;
import royalstacks.app.model.User;
import royalstacks.app.service.LogInService;

@Controller
public class DoLoginController {

    @Autowired
    private LogInService loginService;
    private String username;
    private String password;

    @PostMapping("/doLogin")
    @ResponseBody
    public ModelAndView doLoginHandler(@RequestParam String inputUsername, String inputPassword){
        //Check if username exists in database
        User user = loginService.findByUsername(inputUsername);
        if (user == null){
            return new ModelAndView("homepage");
            //TODO nette melding naar gebruiker dat ingevoerde gebruikersnaam niet bestaat
        }

        //Check if password of user matches entered value
        if (!user.getPassword().equals(inputPassword)){
            return new ModelAndView("homepage");
            //TODO nette melding naar gebruiker dat ingevoerde wachtwoord niet hoort bij de user
        }

        //redirect user to next page
        if (user instanceof Employee){
            Employee employee = (Employee) user;
            ModelAndView mav = new ModelAndView("headprivateoverview");
            return mav.addObject(employee);
        } else if (user instanceof Customer){
            Customer customer = (Customer) user;
            ModelAndView mav = new ModelAndView("myaccounts");
            return mav.addObject(customer);
        } else {
            return new ModelAndView("homepage");
            //TODO nette melding naar gebruiker dat hij geen employee of customer is
        }

    }
}
