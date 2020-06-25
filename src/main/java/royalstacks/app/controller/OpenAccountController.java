package royalstacks.app.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import royalstacks.app.backingBean.OpenAccountBackingBean;
import royalstacks.app.model.BusinessAccount;
import royalstacks.app.model.Customer;
import royalstacks.app.model.PrivateAccount;
import royalstacks.app.model.repository.EmployeeRepository;
import royalstacks.app.service.AccountService;
import royalstacks.app.service.UserService;

import java.util.Optional;

@Controller
public class OpenAccountController implements GetCustomer{

    @Autowired
    private AccountService accountService;

    @Autowired
    private UserService userService;
    @Autowired
    private EmployeeRepository employeeRepository;

    public OpenAccountController() { super();
    }




    @PostMapping("/openaccount")
    public ModelAndView createAccountHandler(@ModelAttribute ("account") OpenAccountBackingBean bb, @SessionAttribute("userid") int userId) {
        //int userId = (int) model.getAttribute("userid");
        ModelAndView mav2 = new ModelAndView("openaccount");
        Customer accountholder  = getCustomerByUserId(userId);
        if (bb.getAccountType().equals("business"))
            return createBusinessAccount(accountholder, bb,mav2);
        else
            return createPrivateAccount(accountholder, bb,mav2);
    }

    //Do private account method

    private ModelAndView createPrivateAccount(Customer accountholder, OpenAccountBackingBean bb, ModelAndView mav) {
        bb.setAccountNumber(accountService.createNewIban());
        PrivateAccount privateAccount = bb.privateAccount();
        privateAccount.getAccountHolders().add(accountholder);
        accountService.saveAccount(privateAccount);
        mav.addObject("account", bb);
        mav.addObject("form", "disabled");
        mav.addObject("confirmation","Your account is created successfully and your account number is: ");
        return mav;
    }

    //do business account view

    private ModelAndView createBusinessAccount(Customer accountholder, OpenAccountBackingBean bb, ModelAndView mav2) {
        bb.setAccountNumber(accountService.createNewIban());
        BusinessAccount businessAccount = bb.businessAccount();
        businessAccount.getAccountHolders().add((accountholder));
        if(!accountholder.isBusinessAccountHolder()) {
            accountholder.setBusinessAccountHolder(true);
            if(employeeRepository.findAll().iterator().hasNext()) {
                //Er moet geen Account Manager aangemaakt kunnen worden als er geen account manager is.
                accountholder.setAccountManager(employeeRepository.findAll().iterator().next());
            }
        }
        //checken als alle velden valid zijn
        if(isAllInputValid(businessAccount, mav2)) {
            accountService.saveAccount(businessAccount);
            mav2.addObject("account", bb);
            mav2.addObject("form", "disabled");
            mav2.addObject("confirmation","Your account is created successfully and your account number is: ");

            return mav2;
        }
        else
            populateFields(businessAccount,mav2,bb);
            return mav2;

    }


    //populate the field of the view when
    private void populateFields(BusinessAccount businessAccount, ModelAndView mav2,OpenAccountBackingBean bb) {
        mav2.addObject("accountType", bb.getAccountType());
        mav2.addObject("companyName",businessAccount.getCompanyName());
        mav2.addObject("kvkNumber",businessAccount.getKvkNumber());
        mav2.addObject("vatNumber", businessAccount.getVatNumber());
    }

    //check if inputs for business account are valid

    private boolean isAllInputValid(BusinessAccount businessAccount, ModelAndView mav) {
        boolean save = true;
        if (!businessAccount.isCompanyNameFormatValid()) {
            mav.addObject("companyName_error", "we accept only company names with alphanumerical charcaters and + - @ and &");
            save = false;
        } //check kvknumber
        if (!businessAccount.isKvkNameFormatValid()) { //if false save false, full attribuut value
            mav.addObject("kvkNumber_error", "number needs to have 8 digits");
            save = false;
        }
        if (!businessAccount.isVatValid()) { //if false save false, full attribuut value
            mav.addObject("vatNumber_error", "invalid VAT-number");
            save = false;
        }
        return save;
    }


    @Override
    public Customer getCustomerByUserId(int userId) {
        return (Customer) userService.findByUserId(userId);
    }
}
