package royalstacks.app.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.SessionAttribute;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class HeadBusinessController {

    @GetMapping("/headbusiness")
    public ModelAndView overviewHandler(@SessionAttribute("userid") int userId ){
        ModelAndView mav = new ModelAndView("headbusiness");
        return mav;
    }

}
