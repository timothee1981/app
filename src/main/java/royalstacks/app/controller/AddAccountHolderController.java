package royalstacks.app.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import royalstacks.app.backingBean.AccountHolderInviteBackingBean;
import royalstacks.app.backingBean.TransactionBackingBean;
import royalstacks.app.model.*;
import royalstacks.app.service.AccountHolderInviteService;
import royalstacks.app.service.AccountService;
import royalstacks.app.service.UserService;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Optional;

@Controller
public class AddAccountHolderController {

    @Autowired
    AccountService accountService;

    @Autowired
    UserService userService;

    @Autowired
    AccountHolderInviteService accountHolderInviteService;


    @GetMapping("/addaccountholder")
    public ModelAndView addAccountHolderHandler(@SessionAttribute("userid") int userId,
                                                @RequestParam(value = "accountNumber", required = false) String accountNumber) {
        // http://localhost/addaccountholder?accountNumber=NL32ROYA0000000019
        // System.out.println(accountNumber);
        ModelAndView mav = new ModelAndView("addaccountholder");

        Optional<Account> optionalAccount = accountService.getAccountByAccountNumber(accountNumber);
        if (optionalAccount.isPresent()) {
            Account anAccount = optionalAccount.get();
            mav.addObject("accountNumber", anAccount.getAccountNumber());
        }
        return mav;
    }


    @PostMapping("/addaccountholder")
    public ModelAndView addAccountHolderHandler(@ModelAttribute AccountHolderInviteBackingBean ibb,
                                                @SessionAttribute("userid") int userId,
                                                Model model) {
        ModelAndView mav = new ModelAndView("addaccountholder");
        User invitee = null;
        Optional<User> optionalUser = userService.findByUsername(ibb.getInviteeUsername());
        Optional<Account> optionalAccount = accountService.getAccountByAccountNumber(ibb.getAccountNumber());
        Account anAccount = optionalAccount.get();
        //check of user bestaat
        if (optionalUser.isPresent()) {
            invitee = optionalUser.get();
        }
        //check op 5 getallen, check op Customer
        if (accountHolderInviteService.isVerificationCodeValid(ibb.getVerificationCode()) && userService.isUserCustomer(invitee) && (!(anAccount.getAccountHolders().contains(invitee)))) {
            ibb.setInviteeUsername(invitee.getUsername());
            ibb.setVerificationCode(ibb.getVerificationCode());
        } else {
            displayMessage("Please enter an existing customer's username (who is not already an account holder for this account) and a five-digit number", mav);
            populateFields(ibb, mav);
            return mav;
        }
        AccountHolderInvite newInvite = ibb.accountHolderInvite((Customer) invitee, anAccount, ibb.getVerificationCode());
        System.out.println(newInvite);
        //hier check of invite al bestaat
        Optional<AccountHolderInvite> existingInvite = accountHolderInviteService.findInviteByAccountAndInvitee(newInvite.getInvitee().getUserid(), newInvite.getAccount().getAccountId());
        System.out.println(existingInvite);
        if (existingInvite.isPresent()){
            AccountHolderInvite updateInvite = existingInvite.get();
            updateInvite.setVerificationCode(newInvite.getVerificationCode());
            accountHolderInviteService.saveAccountHolderInvite(updateInvite);
            displayMessage("Verification code is updated successfully. The new account holder can now add the account using the new verification code.", mav);
        } else {
            accountHolderInviteService.saveAccountHolderInvite(newInvite);
            displayMessage("Invitation sent. The new account holder can now add the account using your verification code.", mav);
        }
        populateFields(ibb, mav);
        return mav;
    }


    private void displayMessage(String message, ModelAndView mav){
        mav.addObject("message", message);
    }


    private void populateFields(AccountHolderInviteBackingBean ibb, ModelAndView mav) {
        //toegevoegd
        mav.addObject("accountNumber",ibb.getAccountNumber());
        mav.addObject("inviteeUsername", ibb.getInviteeUsername());
        mav.addObject("verificationCode", ibb.getVerificationCode());
    }



}
