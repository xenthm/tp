package sales;

public class Sale {
    private int quantitySold = 0;
    private double unitPrice = 0.0;
    private double totalRevenue = 0.0;

    public Sale(int quantitySold, double unitPrice) {
        this.quantitySold = quantitySold;
        this.unitPrice = unitPrice;
        this.totalRevenue = getTotalRevenue();
    }

    public int getQuantitySold() {
        return quantitySold;
    }

    public void setQuantitySold(int quantitySold) {
        this.quantitySold = quantitySold;
    }

    public double getUnitPrice() {
        return unitPrice;
    }

    public void setUnitPrice(double unitPrice) {
        this.unitPrice = unitPrice;
    }
    public double getTotalRevenue() {
        totalRevenue = quantitySold * unitPrice;
        return totalRevenue;
    }

    public boolean isEmpty() {
        return quantitySold == 0 && unitPrice == 0.0;
    }

    @Override
    public String toString() {
        return "[Copies sold: " + quantitySold + ", Unit price: $" + String.format("%.2f", unitPrice)
                + ", Revenue: $" + String.format("%.2f", totalRevenue) + " ]" ;
    }
}
