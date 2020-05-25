package royalstacks.app.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;
import royalstacks.app.backingBean.OpenAccountBackingBean;
import royalstacks.app.model.BusinessAccount;
import royalstacks.app.model.PrivateAccount;
import royalstacks.app.service.AccountService;

@Controller
public class OpenAccountController {

    @Autowired
    private AccountService accountService;

    public OpenAccountController() { super();
    }

    @PostMapping("/openaccount")
    public ModelAndView createAccountHandler(@ModelAttribute ("account") OpenAccountBackingBean bb) {

        ModelAndView mav = new ModelAndView("createAccountConfirmation");
        ModelAndView mav2 = new ModelAndView("openaccount");
        if (bb.getAccountType().equals("business"))
            return createBusinessAccount(bb,mav,mav2);
        else
            return createPrivateAccount(bb,mav);
    }

    //Do private account method
    private ModelAndView createPrivateAccount(OpenAccountBackingBean bb, ModelAndView mav) {
        bb.setAccountNumber(accountService.generateAccountNumber());
        PrivateAccount privateAccount = bb.privateAccount();
        accountService.saveAccount(privateAccount);
        mav.addObject("account", bb);
        return mav;
    }

    //do business account view
    private ModelAndView createBusinessAccount(OpenAccountBackingBean bb, ModelAndView mav, ModelAndView mav2) {
        bb.setAccountNumber(accountService.generateAccountNumber());
        BusinessAccount businessAccount = bb.businessAccount();
        //checken als alle velden valid zijn
        if(isAllInputValid(businessAccount, mav2)) {
            accountService.saveAccount(businessAccount);
            mav.addObject("account", bb);
            return mav;
        }
        else
            populateFields(businessAccount,mav2);
            return mav2;

    }


    //populate the field of the view when
    private void populateFields(BusinessAccount businessAccount, ModelAndView mav2) {

        mav2.addObject("companyName",businessAccount.getCompanyName());
        mav2.addObject("kvkNumber",businessAccount.getKvkNumber());
        mav2.addObject("vatNumber", businessAccount.getVatNumber());
    }

    //check if inputs for business account are valid

    private boolean isAllInputValid(BusinessAccount businessAccount, ModelAndView mav) {
        boolean save = true;
        if (!businessAccount.isCompanyNameFormatValid()) {
            mav.addObject("companyName_error", "invalid name");
            save = false;
        } //check kvknumber
        if (!businessAccount.isKvkNameFormatValid()) { //if false save false, full attribuut value
            mav.addObject("kvkNumber_error", "number needs to have 8 digits");
            save = false;
        }
        //check Vat number

        return save;
    }




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
