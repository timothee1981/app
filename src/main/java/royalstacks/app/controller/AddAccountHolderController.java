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
        ModelAndView mav = new ModelAndView("/addaccountholder");

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
        //TODO checken of user bestaat en een customer is
        if (optionalUser.isPresent()) {
            invitee = optionalUser.get();
        }
        if (accountHolderInviteService.isVerificationCodeValid(ibb.getVerificationCode()) && userService.isUserCustomer(invitee)) {
            ibb.setInviteeUsername(invitee.getUsername());
            ibb.setVerificationCode(ibb.getVerificationCode());
        } else {
            displayMessage("Please enter an existing customer's username and a five-digit number", mav);
            populateFields(ibb, mav);
            return mav;
        }
        AccountHolderInvite newInvite = ibb.accountHolderInvite();
        accountHolderInviteService.saveAccountHolderInvite(newInvite);
        displayMessage("Invitation sent. The new account holder can now add the account using your verification code.", mav);
        return mav;
    }


    private void displayMessage(String message, ModelAndView mav){
        mav.addObject("message", message);
    }


    private void populateFields(AccountHolderInviteBackingBean ibb, ModelAndView mav) {
        mav.addObject("inviteeUsername", ibb.getInviteeUsername());
        mav.addObject("verificationCode", ibb.getVerificationCode());
    }




}
