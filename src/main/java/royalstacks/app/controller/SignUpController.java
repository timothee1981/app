package royalstacks.app.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;
import royalstacks.app.service.CustomerService;

@Controller
public class SignUpController {

    @Autowired
    private CustomerService customerService;

/*    @PostMapping("signup")
    public ModelAndView signUpHandler(@ModelAttribute CreatePersonBackingBean cbb) {
        ModelAndView mav = new ModelAndView("person_details");
        Person person = bb.person();
        personService.savePerson(person);
        mav.addObject("name", person.getName());
        mav.addObject("birth", person.getBirth().getDate().getYear());
        mav.addObject("death", person.getDeath().getDate().getYear());
        return mav;
    }*/
}

