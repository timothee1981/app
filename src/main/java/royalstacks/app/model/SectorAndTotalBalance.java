package royalstacks.app.model;

public class SectorAndTotalBalance {

    private String sector;
    private double totalBalance;

    //CONSTRUCTOR

    public SectorAndTotalBalance(String sector, double totalBalance) {
        this.sector = sector;
        this.totalBalance = totalBalance;
    }

    //GETTERS

    public String getSector() {
        return sector;
    }

    public double getTotalBalance() {
        return totalBalance;
    }
}
