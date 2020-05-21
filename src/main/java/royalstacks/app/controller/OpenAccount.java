package royalstacks.app.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import royalstacks.app.model.BusinessAccount;
import royalstacks.app.service.AccountService;

@Controller
public class OpenAccount {

    @Autowired
    private AccountService accountService;

    public OpenAccount() { super();
    }

    @PostMapping("/openaccount")
    public ModelAndView createBusinessAccountHandler(@RequestParam String companyName,
                                                     @RequestParam String kvkNumber,
                                                     @RequestParam String vatNumber,
                                                     @RequestParam String sector) {//
        ModelAndView mav = new ModelAndView("createAccountConfirmation");//maak een nieuwe html view confirmation accout
        //ToDo haal gegevens uit th:fields en sla op in account object  (voeg placeholders toe)
        BusinessAccount businessAccount = new BusinessAccount("123",0.0,companyName,
                kvkNumber,vatNumber,sector);
        //ToDo voer checks uit op gegevens
        //ToDo if(fout){a terug naar pagina met ingevulde velden en foutmeldingin waar foute gegevens zijn ingevuld}
        //ToDo if (correct) sla account op in database
        accountService.saveBusinessAccount(businessAccount);
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
