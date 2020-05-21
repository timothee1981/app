package royalstacks.app.controller;

import org.dom4j.rule.Mode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;
import royalstacks.app.service.LogInService;

@Controller
public class HomepageController {

    @Autowired
    private LogInService loginService;

    @GetMapping("/doLogin")
    public ModelAndView doLoginHandler(){
        String username = "";
        loginService.findUserByUsername(username);
                //daar komt wel/niet een user uit
                //als er een user is, dan kun je de methode gebruiken om password te checken
        //uitvoeren check bestaat username en password in database(getuserbyusername bijv)
        //als die bestaan -> wat voor type user is dit? customer/employee
        //stuur door naar de pagina waar ze heen moeten
        ModelAndView mav = new ModelAndView();
        return mav;
    }
}
