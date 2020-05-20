package royalstacks.app.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
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
    public ModelAndView doLoginHandler(@RequestParam String username){
//        if (loginService.findUserByUsername(username) == null){
//            return new ModelAndView("homepage");
//            //TODO nette melding naar gebruiker dat ingevoerde gebruikersnaam niet bestaat
//        }
        //als er een user is, dan kun je de methode gebruiken om password te checken

        //uitvoeren check bestaat username en password in database(getuserbyusername bijv)


        if (loginService.findUserByUsername(username) instanceof Employee){
            Employee employee = (Employee)loginService.findUserByUsername(username);
            ModelAndView mav = new ModelAndView("headprivateoverview");
            return mav.addObject(employee);
        } else if (loginService.findUserByUsername(username) instanceof Customer){
            Customer customer = (Customer)loginService.findUserByUsername(username);
            ModelAndView mav = new ModelAndView("myaccounts");
            return mav.addObject(customer);
        } else {
            return new ModelAndView("homepage");
        }


    }
}
