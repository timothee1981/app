package royalstacks.app.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import royalstacks.app.model.Account;
import royalstacks.app.model.BusinessAccount;
import royalstacks.app.model.PrivateAccount;
import royalstacks.app.model.repository.BusinessAccountRepository;
import royalstacks.app.model.repository.PrivateAccountRepository;

@Service
public class AccountService {

    private PrivateAccountRepository privateAccountRepository;
    private BusinessAccountRepository businessAccountRepository;

    @Autowired
    public AccountService(PrivateAccountRepository privateAccountRepository,
                          BusinessAccountRepository businessAccountRepository){
        super();
        this.privateAccountRepository = privateAccountRepository;
        this.businessAccountRepository = businessAccountRepository;
    }

    public void savePrivateAccount(PrivateAccount privateAccount){
        privateAccountRepository.save(privateAccount);
    }

    public void saveBusinessAccount(BusinessAccount businessAccount){businessAccountRepository.save(businessAccount);}
}
