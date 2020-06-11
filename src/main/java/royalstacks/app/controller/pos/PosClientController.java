package royalstacks.app.controller.pos;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class PosClientController {

    @GetMapping("/pos/client")
    public final ModelAndView posClientControllerHandler() {
        return new ModelAndView("posclient");
    }
}
