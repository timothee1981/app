package royalstacks.app.model;

public class SectorAndAverageBalance {

    private String sector;
    private double totalBalance;

    //CONSTRUCTOR

    public SectorAndAverageBalance(String sector, double totalBalance) {
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
