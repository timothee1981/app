package royalstacks.app.model.pos;

public class PaymentResult {

    private boolean accountVerified;
    private boolean sufficientBalance;
    private boolean paymentSuccess;
    private String transactionid;


    public PaymentResult(boolean accountVerified, boolean sufficientBalance, boolean paymentSuccess, String transactionid) {
        this.accountVerified = accountVerified;
        this.sufficientBalance = sufficientBalance;
        this.paymentSuccess = paymentSuccess;
        this.transactionid = transactionid;
    }

    public PaymentResult(boolean accountVerified, boolean sufficientBalance, boolean paymentSuccess) {
        this.accountVerified = accountVerified;
        this.sufficientBalance = sufficientBalance;
        this.paymentSuccess = paymentSuccess;
    }

    public PaymentResult() {
    }

    public String getTransactionid() {
        return transactionid;
    }

    public void setTransactionid(String transactionid) {
        this.transactionid = transactionid;
    }

    public boolean isAccountVerified() {
        return accountVerified;
    }

    public void setAccountVerified(boolean accountVerified) {
        this.accountVerified = accountVerified;
    }

    public boolean isSufficientBalance() {
        return sufficientBalance;
    }

    public void setSufficientBalance(boolean sufficientBalance) {
        this.sufficientBalance = sufficientBalance;
    }

    public boolean isPaymentSuccess() {
        return paymentSuccess;
    }

    public void setPaymentSuccess(boolean paymentSuccess) {
        this.paymentSuccess = paymentSuccess;
    }

    @Override
    public String toString() {
        return "PaymentResult{" +
                "accountVerified=" + accountVerified +
                ", sufficientBalance=" + sufficientBalance +
                ", paymentSuccess=" + paymentSuccess +
                ", transactionid='" + transactionid + '\'' +
                '}';
    }
}

