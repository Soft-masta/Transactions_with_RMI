/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package StockExchange;

import java.io.Serializable;
import java.sql.Date;

/**
 *
 * @author Edwin Fajardo
 */
public class Transaction implements Serializable, Comparable<Transaction> {
    private String userRFC;
    private String companyRFC;
    private int operatedStocks;
    private Double operatedStocksPrice;
    private Double actualStocksPrice;

    public Transaction(String userRFC, String companyRFC, int operatedStocks, Double operatedStocksPrice) {
        this.userRFC = userRFC;
        this.companyRFC = companyRFC;
        this.operatedStocks = operatedStocks;
        this.operatedStocksPrice = operatedStocksPrice;
    }
    
    public Transaction() {}

     @Override
        public int compareTo(Transaction other) {
            return compare(this, other);
        }


        public static int compare(Transaction o1, Transaction o2) {
            if (o1 == o2) {
                return 0;
            } else if (o1 == null) {
                return -1;
            } else if (o2 == null) {
                return 1;
            } else {
                return Double.compare(o1.getOperatedStocksPrice(), o2.getOperatedStocksPrice());
            }
        }
    
    public String getUserRFC() {
        return userRFC;
    }

    public void setUserRFC(String userRFC) {
        this.userRFC = userRFC;
    }

    public String getCompanyRFC() {
        return companyRFC;
    }

    public void setCompanyRFC(String companyRFC) {
        this.companyRFC = companyRFC;
    }

    public int getOperatedStocks() {
        return operatedStocks;
    }

    public void setOperatedStocks(int operatedStocks) {
        this.operatedStocks = operatedStocks;
    }

    public Double getOperatedStocksPrice() {
        return operatedStocksPrice;
    }

    public void setOperatedStocksPrice(Double operatedStocksPrice) {
        this.operatedStocksPrice = operatedStocksPrice;
    }

    public Double getActualStocksPrice() {
        return actualStocksPrice;
    }

    public void setActualStocksPrice(Double actualStocksPrice) {
        this.actualStocksPrice = actualStocksPrice;
    }
    
    
    
    
    
    
    
    

    
    
    
}
