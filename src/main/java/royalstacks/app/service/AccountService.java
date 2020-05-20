package royalstacks.app.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import royalstacks.app.model.Account;
import royalstacks.app.model.repository.AccountRepository;

@Service
public class AccountService {

    private AccountRepository accountRepository;

    @Autowired
    public AccountService(AccountRepository accountRepository){
        super();
        this.accountRepository = accountRepository;
    }

    public void saveAccount(Account account){
        accountRepository.save(account);
    }
}
