package royalstacks.app.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import royalstacks.app.model.Customer;
import royalstacks.app.model.Employee;
import royalstacks.app.service.LogInService;

@Controller
public class HomepageController {

    @Autowired
    private LogInService loginService;
    private String username;
    private String password;

    @GetMapping("/doLogin")
    @ResponseBody
    public ModelAndView doLoginHandler(@RequestParam String inputUsername, String inputPassword){
        //Check if username exists in database
        if (loginService.findByUsername(inputUsername) == null){
            return new ModelAndView("homepage");
            //TODO nette melding naar gebruiker dat ingevoerde gebruikersnaam niet bestaat
        }

        //Check if password of user matches entered value
        if (!loginService.findByUsername(inputUsername).getPassword().equals(inputPassword)){
            return new ModelAndView("homepage");
            //TODO nette melding naar gebruiker dat ingevoerde wachtwoord niet hoort bij de user
        }

        //redirect user to next page
        if (loginService.findByUsername(inputUsername) instanceof Employee){
            Employee employee = (Employee)loginService.findByUsername(inputUsername);
            ModelAndView mav = new ModelAndView("headprivateoverview");
            return mav.addObject(employee);
        } else if (loginService.findByUsername(inputUsername) instanceof Customer){
            Customer customer = (Customer)loginService.findByUsername(inputUsername);
            ModelAndView mav = new ModelAndView("myaccounts");
            return mav.addObject(customer);
        } else {
            return new ModelAndView("homepage");
        }

    }
}
