
package Servidor;

import java.io.Serializable;

public class Investor implements Serializable {

    private String userRFC = null;
    private String name = null;
    private int stockNumber = 0;
    private Double lastBuyPrice = 0.0;

    public Investor() {

    }

    public Investor(String userRFC, String name) {
        this.userRFC = userRFC;
        this.name = name;
    }

    public String getUserRFC() {
        return userRFC;
    }

    public void setUserRFC(String userRFC) {
        this.userRFC = userRFC;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getStockNumber() {
        return stockNumber;
    }

    public void setStockNumber(int stockNumber) {
        this.stockNumber = stockNumber;
    }

    public Double getLastBuyPrice() {
        return lastBuyPrice;
    }

    public void setLastBuyPrice(Double lastBuyPrice) {
        this.lastBuyPrice = lastBuyPrice;
    }

}
