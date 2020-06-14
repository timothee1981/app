package royalstacks.app.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import royalstacks.app.model.Account;
import royalstacks.app.model.Transaction;
import royalstacks.app.model.pos.PaymentData;
import royalstacks.app.model.pos.PaymentResult;
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

    public Optional<PaymentResult> executePosTransaction(Pos pos){

        PaymentResult pr = new PaymentResult(false, false, false);

        System.out.println("execute Postransaction");
        Transaction transaction = new Transaction();
        Optional<Account> accountOptional = accountService.getAccountByAccountNumber(pos.getBusinessAccountNumber());

        if(accountService.getAccountIdByNumberExIban(pos.getClientAccountNumber()).isEmpty()){
            pr.setAccountVerified(false);
            return Optional.of(pr);
        } else {
            pr.setAccountVerified(true);
        }

        int accountId = accountService.getAccountIdByNumberExIban(pos.getClientAccountNumber()).get();

        if (accountService.getAccountById(accountId).getBalance().compareTo(pos.getPendingAmount()) < 0) {
            pr.setSufficientBalance(false);
            return Optional.of(pr);
        } else {
            pr.setSufficientBalance(true);
        }

        if(accountOptional.isEmpty()){
            pr.setPaymentSuccess(false);
            return Optional.of(pr);
        }

        transaction.setFromAccountId(accountId);
        transaction.setAmount(pos.getPendingAmount());
        transaction.setToAccountId(accountOptional.get().getAccountId());
        transaction.setDescription("POS transfer");
        System.out.println(transaction);

        Optional<Transaction> tOptional = transactionService.executeTransaction(transaction);

        if(tOptional.isPresent()){
           pr.setTransactionid(tOptional.get().getTransactionId());
           pr.setPaymentSuccess(true);
           pos.setPendingAmount(null);
           pos.setClientAccountNumber(null);
           posService.savePos(pos);
        }

        return Optional.of(pr);

    }

}
