package royalstacks.app.service;

import org.springframework.stereotype.Service;

@Service
public class AccountHolderInviteService {

        public boolean isVerificationCodeValid(String verificationCode){
        verificationCode = verificationCode.trim();
        return verificationCode.matches("\\d{5}");
    }

}
