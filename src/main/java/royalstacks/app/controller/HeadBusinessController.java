package royalstacks.app.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.SessionAttribute;
import org.springframework.web.servlet.ModelAndView;
import royalstacks.app.model.CustomerAndTotalBalance;
import royalstacks.app.service.CustomerService;

import java.util.List;

@Controller
public class HeadBusinessController {

    @Autowired
    private CustomerService customerService;

    @GetMapping("/headbusiness")
    public ModelAndView overviewHandler(@SessionAttribute("userid") int userId ){
        List<CustomerAndTotalBalance> top10BusinessAccounts = customerService.findTop10BusinessAccounts();
        ModelAndView mav = new ModelAndView("headbusiness");
        return mav;
    }

}
