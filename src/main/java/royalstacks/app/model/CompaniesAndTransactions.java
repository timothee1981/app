package royalstacks.app.model;

import java.math.BigDecimal;

public class CompaniesAndTransactions implements Comparable<CompaniesAndTransactions> {

    private String companyName;
    private int numberOfTransactions;
    private BigDecimal balance;

    //CONSTRUCTOR
    public CompaniesAndTransactions(String companyName, int numberOfTransactions, BigDecimal balance) {
        this.companyName = companyName;
        this.numberOfTransactions = numberOfTransactions;
        this.balance = balance;
    }

    //GETTERS
    public String getCompanyName() { return companyName; }

    public int getNumberOfTransactions() {
        return numberOfTransactions;
    }

    public BigDecimal getBalance() {
        return balance;
    }

    @Override
    public int compareTo(CompaniesAndTransactions o) {
        return (o.getNumberOfTransactions() - this.getNumberOfTransactions());
    }
}
