package Servidor;

import java.io.Serializable;


public class Enterprise implements Serializable{
    
    private String companyRFC = "";
    private int stockNumber = 0;
    private Double stockValue = 0.0;

    public Enterprise(String companyRFC, int stockNumber, Double stockValue) {
        this.companyRFC = companyRFC;
        this.stockNumber = stockNumber;
        this.stockValue = stockValue;
    }
    
    public Enterprise() {}

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
