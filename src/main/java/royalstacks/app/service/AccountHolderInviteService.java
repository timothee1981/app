package royalstacks.app.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import royalstacks.app.model.AccountHolderInvite;
import royalstacks.app.model.Transaction;
import royalstacks.app.model.repository.TransactionRepository;

@Service
public class AccountHolderInviteService {

    @Autowired
    TransactionRepository transactionRepository;

    public void saveAccountHolderInvite(AccountHolderInvite ahi){
        AccountHolderInviteRepository.save(ahi);
        System.out.println("Saved to DB");
    }

    public boolean isVerificationCodeValid(String verificationCode){
        verificationCode = verificationCode.trim();
        return verificationCode.matches("\\d{5}");
    }

}
