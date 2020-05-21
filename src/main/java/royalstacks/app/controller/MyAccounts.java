package royalstacks.app.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.ModelAndView;
import royalstacks.app.backingBean.OpenAccountBackingBean;
import royalstacks.app.model.Account;

@Controller
public class MyAccounts {

    public MyAccounts() {super();
    }

    @GetMapping("/openaccount")
    public ModelAndView openAccountHandler(){
        ModelAndView mav = new ModelAndView("openaccount");
        OpenAccountBackingBean bb = new OpenAccountBackingBean();
        mav.addObject("account",bb);
        return mav;
    }
}
