package royalstacks.app.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttribute;
import org.springframework.web.servlet.ModelAndView;
import royalstacks.app.model.Account;

import java.util.Optional;

public class AcceptAccountHolderInviteController {


    @GetMapping("/acceptaccountholderinvite")
    public ModelAndView acceptAccountHolderInviteHandler(@SessionAttribute("userid") int userId) {
        return new ModelAndView("acceptaccountholderinvite");
    }


}
