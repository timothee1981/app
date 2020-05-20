package royalstacks.app.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class IndexController {

    @GetMapping("/")
    public ModelAndView startHandler() {
        ModelAndView mav = new ModelAndView("homepage");
        return mav;
    }
}
