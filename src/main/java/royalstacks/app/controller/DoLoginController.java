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

    @PostMapping("/doLogin")
    @ResponseBody
    public ModelAndView doLoginHandler(@RequestParam String inputUsername, String inputPassword) {

        //Check if username and password exist and have a value
        boolean usernameHasValue = inputUsernameHasValue(inputUsername);
        boolean passwordHasValue = inputpasswordHasValue(inputPassword);
        if (!(usernameHasValue && passwordHasValue)) {
            ModelAndView mav = new ModelAndView("homepage");
            mav.addObject("username_error", "username is required");
            mav.addObject("password_error", "password is required");
            return mav;
        }

        //Check if username exists in database
        User user = loginService.findByUsername(inputUsername);
        if (user == null) {
            ModelAndView mav = new ModelAndView("homepage");
            mav.addObject("username_error", "username does not exist, please enter a valid username");
            return mav;
        }

        //Check if password of user matches entered value
        if (!user.getPassword().equals(inputPassword)) {
            ModelAndView mav = new ModelAndView("homepage");
            mav.addObject("password_error", "password is not correct, please enter a valid password");
            return mav;
        }

        //redirect user to next page
        if (user instanceof Employee) {
            Employee employee = (Employee) user;
            ModelAndView mav = new ModelAndView("headprivateoverview");
            return mav.addObject(employee);
        } else if (user instanceof Customer) {
            Customer customer = (Customer) user;
            ModelAndView mav = new ModelAndView("myaccounts");
            return mav.addObject(customer);
        } else {
            ModelAndView mav = new ModelAndView("homepage");
            mav.addObject("user_error", "you are neither a customer or employee of Royal Stacks Bank");
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
