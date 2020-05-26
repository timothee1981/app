package royalstacks.app.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import royalstacks.app.model.Customer;
import royalstacks.app.model.Employee;
import royalstacks.app.model.Password;
import royalstacks.app.model.User;
import royalstacks.app.service.LogInService;

@Controller
@SessionAttributes("userid")
public class DoLoginController {

    @Autowired
    private LogInService loginService;

    @PostMapping("/doLogin")
    public ModelAndView doLoginHandler(@RequestParam String inputUsername, String inputPassword, Model model){

        //Check if username and password exist and have a value
        boolean usernameHasValue = inputUsernameHasValue(inputUsername);
        boolean passwordHasValue = inputpasswordHasValue(inputPassword);
        if( ! (usernameHasValue && passwordHasValue ) ){
            return new ModelAndView("homepage");
            //TODO nette melding naar gebruiker dat beide velden gevuld moeten worden
        }

        //Check if username exists in database
        User user = loginService.findByUsername(inputUsername);
        if (user == null){
            return new ModelAndView("homepage");
            //TODO nette melding naar gebruiker dat ingevoerde gebruikersnaam niet bestaat
        }

        //Check if password of user matches entered value
        if ( ! Password.checkPassword( inputPassword, user.getPassword() ) ){
            return new ModelAndView("homepage");
            //TODO nette melding naar gebruiker dat ingevoerde wachtwoord niet hoort bij de user
        }

        //add userid to modelSession
        model.addAttribute("input", user.getUserid());

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


    private boolean inputUsernameHasValue(String inputUsername) {
        boolean inputUserNameHasValue = true;

        if(inputUsername.trim().equals("")){
            inputUserNameHasValue = false;
        }
        return inputUserNameHasValue;
    }

    private boolean inputpasswordHasValue(String inputPassword) {
        boolean inputPasswordHasValue = true;

        if(inputPassword.trim().equals("")){
            inputPasswordHasValue = false;
        }
        return inputPasswordHasValue;
    }
}
