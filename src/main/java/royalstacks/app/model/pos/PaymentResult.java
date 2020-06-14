package royalstacks.app.model.pos;

public class PaymentResult {

    private boolean accountVerified;
    private boolean sufficientBalance;
    private boolean paymentSuccess;


    public PaymentResult(boolean accountVerified, boolean sufficientBalance, boolean paymentSuccess) {
        this.accountVerified = accountVerified;
        this.sufficientBalance = sufficientBalance;
        this.paymentSuccess = paymentSuccess;
    }

    public PaymentResult() {
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
}

