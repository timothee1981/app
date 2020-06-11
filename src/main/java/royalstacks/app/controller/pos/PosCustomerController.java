package royalstacks.app.controller.pos;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class PosCustomerController {

    @GetMapping("/pos/customer")
    public final ModelAndView posCustomerControllerHandler() {
        return new ModelAndView("poscustomer");
    }
}
