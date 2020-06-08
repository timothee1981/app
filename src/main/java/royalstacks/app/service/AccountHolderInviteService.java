package royalstacks.app.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import royalstacks.app.model.AccountHolderInvite;
import royalstacks.app.model.Customer;
import royalstacks.app.model.repository.AccountHolderInviteRepository;

import java.util.Optional;

@Service
public class AccountHolderInviteService {

    @Autowired
    AccountHolderInviteRepository accountHolderInviteRepository;

        public void saveAccountHolderInvite(AccountHolderInvite ahi){
        accountHolderInviteRepository.save(ahi);
        System.out.println("Saved to DB");
    }

    public Optional<AccountHolderInvite> findInviteByAccountAndInvitee(int userId, int accountId){
            return accountHolderInviteRepository.findInviteByAccountAndInvitee(userId, accountId);
    }

    public boolean isVerificationCodeValid(String verificationCode){
        verificationCode = verificationCode.trim();
        return verificationCode.matches("\\d{5}");
    }

/*    public boolean isInviteNew(){
MYSQL Query: SELECT * FROM account_holder_invite WHERE account_account_id = 2 AND invitee_userid = 4;
        select * from accountholderinvitation where account_account_id = account.getAccountId AND invitee_userid = user.getUserId;
        if (result == null)
        return true;
        update verification_code with value verificationCode;
    }*/

/*    public void overwriteInvite(String verificationCode){
        if (!isInviteNew){
            UPDATE account_holder_invite SET verification_code = verificationCode WHERE account_account_id = 2 AND invitee_userid = 4;
        }
    }*/

}
