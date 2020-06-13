package royalstacks.app.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import royalstacks.app.model.Account;
import royalstacks.app.model.Transaction;
import royalstacks.app.model.pos.Pos;
import royalstacks.app.model.repository.PosRepository;

import java.util.Objects;
import java.util.Optional;

@Service
public class PosService {

    @Autowired
    PosRepository posRepository;

    @Autowired
    TransactionService transactionService;

    @Autowired
    AccountService accountService;

    @Autowired
    PosService posService;

    private Transaction transaction;

    public Optional<Pos> findPosByIdentificationNumber(int identificationNumber){
        return posRepository.findPosByIdentificationNumber(identificationNumber);
    }

    public void savePos(Pos pos){
        posRepository.save(pos);
    }

    public Optional<Integer> getLastPosId(){
        return posRepository.getLastId();
    }

    public boolean executePosTransaction(Pos pos){
        System.out.println("execute Postransaction");
        Transaction transaction = new Transaction();
        Optional<Account> accountOptional = accountService.getAccountByAccountNumber(pos.getBusinessAccountNumber());
        if(accountOptional.isEmpty()){
            return false;
        }
        if(accountService.getAccountIdByNumberExIban(pos.getClientAccountNumber()).isEmpty()){
            return false;
        }
        int accountId = accountService.getAccountIdByNumberExIban(pos.getClientAccountNumber()).get();
        transaction.setFromAccountId(accountId);
        transaction.setAmount(pos.getPendingAmount());
        transaction.setToAccountId(accountOptional.get().getAccountId());
        transaction.setDescription("POS transfer");
        System.out.println(transaction);

        transactionService.executeTransaction(transaction);
        pos.setPendingAmount(null);
        pos.setClientAccountNumber(null);
        posService.savePos(pos);

        return true;
    }

}
