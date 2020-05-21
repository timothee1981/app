package royalstacks.app.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import royalstacks.app.backingBean.OpenAccountBackingBean;
import royalstacks.app.model.BusinessAccount;
import royalstacks.app.model.PrivateAccount;
import royalstacks.app.service.AccountService;

@Controller
public class OpenAccount {

    @Autowired
    private AccountService accountService;

    public OpenAccount() { super();
    }

    @PostMapping("/openaccount")
    public ModelAndView createBusinessAccountHandler(@ModelAttribute ("account") OpenAccountBackingBean bb) {

        ModelAndView mav = new ModelAndView("createAccountConfirmation");

        if(bb.getAccountType().equals("business")) {
            BusinessAccount businessAccount = bb.businessAccount();
            accountService.saveBusinessAccount(businessAccount);
        }
        else if (bb.getAccountType().equals("private")) {
            PrivateAccount privateAccount = bb.privateAccount();
            accountService.savePrivateAccount(privateAccount);
        }
        mav.addObject("account", bb);




        // go To  confirmation page: message+account number, ToMyAccountButton , ToAccountDetails
        return mav;






/*        }

        @PostMapping("/create_person")
        public ModelAndView createPersonHandler(@ModelAttribute CreatePersonBackingBean bbb) {
            ModelAndView mav = new ModelAndView("person_details");
            Person person = bbb.person();
            personService.savePerson(person);
            mav.addObject("name", person.getName());
            mav.addObject("birth", person.getBirth().getDate().getYear());
            mav.addObject("death", person.getDeath().getDate().getYear());
            return mav;
        }
    }*/

    }

}
