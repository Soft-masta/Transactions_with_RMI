package Servidor;

import java.io.Serializable;



public class Operations implements Serializable, Comparable<Operations> {
    private String userRFC;
    private String companyRFC;
    private int operatedStocks;
    private Double operatedStocksPrice;
    private Double actualStocksPrice;

    public Operations(String userRFC, String companyRFC, int operatedStocks, Double operatedStocksPrice) {
        this.userRFC = userRFC;
        this.companyRFC = companyRFC;
        this.operatedStocks = operatedStocks;
        this.operatedStocksPrice = operatedStocksPrice;
    }
    
    public Operations() {}

     @Override
        public int compareTo(Operations other) {
            return compare(this, other);
        }


        public static int compare(Operations o1, Operations o2) {
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
