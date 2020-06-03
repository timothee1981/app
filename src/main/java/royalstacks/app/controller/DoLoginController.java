package royalstacks.app.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import royalstacks.app.model.Customer;
import royalstacks.app.model.Employee;
import royalstacks.app.model.User;
import royalstacks.app.service.LogInService;

@Controller
@SessionAttributes("userid")
public class DoLoginController {

    @Autowired
    private LogInService loginService;

    @PostMapping("/doLogin")
    public ModelAndView doLoginHandler(@RequestParam String inputUsername, String inputPassword, Model model){

        //Check if username has a value
        boolean usernameHasValue = inputUsernameHasValue(inputUsername);
        if (!usernameHasValue) {
            ModelAndView mav = new ModelAndView("homepage");
            mav.addObject("username_error", "Username is required");
            return mav;
        }

        //Check if password has a value
        boolean passwordHasValue = inputpasswordHasValue(inputPassword);
        if (!passwordHasValue) {
            ModelAndView mav = new ModelAndView("homepage");
            mav.addObject("password_error", "Password is required");
            return mav;
        }

        //Check if username exists in database
        User user = loginService.findByUsername(inputUsername);
        if (user == null) {
            ModelAndView mav = new ModelAndView("homepage");
            mav.addObject("login_error", "Username and/or password is incorrect");
            return mav;
        }

        //Check if password of user matches entered value
        if ( ! User.checkPassword( inputPassword, user.getPassword() ) ){
            ModelAndView mav = new ModelAndView("homepage");
            mav.addObject("login_error", "Username and/or password is incorrect");
            return mav;
        }

        //add userid to modelSession
        model.addAttribute("userid", user.getUserid());

        //redirect user to next page
        if (user instanceof Employee) {
            Employee employee = (Employee) user;
            ModelAndView mav = new ModelAndView("headprivateoverview");
            mav.addObject(model);
            return mav.addObject(employee);
        } else if (user instanceof Customer) {
            Customer customer = (Customer) user;
            ModelAndView mav = new ModelAndView("redirect:/myaccounts");
            mav.addObject(model);
            return mav.addObject(customer);
        } else {
            ModelAndView mav = new ModelAndView("homepage");
            mav.addObject("user_error", "You are neither a customer or employee of Royal Stacks Bank");
            return mav;
        }

    }


    private boolean inputUsernameHasValue(String inputUsername) {
        boolean inputUserNameHasValue = true;

        if (inputUsername.trim().equals("")) {
            inputUserNameHasValue = false;
        }
        return inputUserNameHasValue;
    }

    private boolean inputpasswordHasValue(String inputPassword) {
        boolean inputPasswordHasValue = true;

        if (inputPassword.trim().equals("")) {
            inputPasswordHasValue = false;
        }
        return inputPasswordHasValue;
    }
}
