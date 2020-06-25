/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package StockExchange;

import java.io.Serializable;

/**
 *
 * @author Edwin Fajardo
 */
public class Company implements Serializable{
    
    private String companyRFC = "";
    private int stockNumber = 0;
    private Double stockValue = 0.0;

    public Company(String companyRFC, int stockNumber, Double stockValue) {
        this.companyRFC = companyRFC;
        this.stockNumber = stockNumber;
        this.stockValue = stockValue;
    }
    
    public Company() {}

    public String getCompanyRFC() {
        return companyRFC;
    }

    public void setCompanyRFC(String companyRFC) {
        this.companyRFC = companyRFC;
    }

    public int getStockNumber() {
        return stockNumber;
    }

    public void setStockNumber(int stockNumber) {
        this.stockNumber = stockNumber;
    }

    public Double getStockValue() {
        return stockValue;
    }

    public void setStockValue(Double stockValue) {
        this.stockValue = stockValue;
    }
    
    
    

    
    
    
}
