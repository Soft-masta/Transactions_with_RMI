package BasedeDatos;

import Servidor.Enterprise;
import Servidor.Operations;
import Servidor.Investor;
import java.sql.*;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Timer;
import java.util.TimerTask;

public class Repository extends Thread {

    public static boolean isTimerActive = false;
    public static ArrayList<Operations> buyQueue = new ArrayList<Operations>();
    public static ArrayList<Operations> sellQueue = new ArrayList<Operations>();
    public static ArrayList<String> activeCompanies = new ArrayList<String>();

    public static int createUser(Investor u) {
        int iRet = -1;
        try {
            Connection con = DBManager.getInstance().getConnection();
            String SQL = "INSERT INTO users (userRFC, name, numOfActions, lastBuyPrice) values(?,?,?,?)";

            PreparedStatement pstmt = con.prepareStatement(SQL);
            pstmt.setString(1, u.getUserRFC());
            pstmt.setString(2, u.getName());
            pstmt.setInt(3, u.getStockNumber());
            pstmt.setDouble(4, u.getLastBuyPrice());

            iRet = pstmt.executeUpdate();

            pstmt.close();
        } catch (SQLException se) {
            System.out.println(se);
        }

        return iRet;
    }

    public static Investor getUser(String userRFC) {
        Investor usr = new Investor();

        try {
            Connection con = DBManager.getInstance().getConnection();
            String SQL = "SELECT * FROM users WHERE userRFC = ? LIMIT 1";

            PreparedStatement pstmt = con.prepareStatement(SQL);
            pstmt.setString(1, userRFC);

            ResultSet rs = pstmt.executeQuery();
            while (rs.next()) {
                usr.setUserRFC(rs.getString("userRFC"));
                usr.setName(rs.getString("name"));
                usr.setStockNumber(rs.getInt("stockNumber"));
                usr.setLastBuyPrice(rs.getDouble("lastBuyPrice"));
            }

            pstmt.close();
        } catch (SQLException se) {
            System.out.println(se);
        }
        return usr;
    }

    public static int createCompany(Enterprise c) {
        int iRet = -1;
        try {
            Connection con = DBManager.getInstance().getConnection();
            String SQL = "INSERT INTO companies (companyRFC, stockNumber, stockValue) values(?,?,?)";

            PreparedStatement pstmt = con.prepareStatement(SQL);
            pstmt.setString(1, c.getCompanyRFC());
            pstmt.setInt(2, c.getStockNumber());
            pstmt.setDouble(3, c.getStockValue());

            iRet = pstmt.executeUpdate();

            pstmt.close();
        } catch (SQLException se) {
            System.out.println(se);
        }

        return iRet;
    }

    public static ArrayList<Enterprise> getAllCompanies() {
        ArrayList<Enterprise> cList = new ArrayList();
        try {
            Connection con = DBManager.getInstance().getConnection();
            String SQL = "SELECT * FROM companies";
            PreparedStatement pstmt = con.prepareStatement(SQL);
            ResultSet rs = pstmt.executeQuery();
            while (rs.next()) {
                Enterprise c = new Enterprise();
                c.setCompanyRFC(rs.getString("companyRFC"));
                c.setStockNumber(rs.getInt("stockNumber"));
                c.setStockValue(rs.getDouble("stockValue"));
                cList.add(c);
            }

        } catch (SQLException se) {
            System.out.println(se);
        }
        return cList;
    }

    public static int addInvestmentToQueue(Operations t) {
        startTimer();
        if (t.getOperatedStocks() < 0) {
            sellQueue.add(t);
        } else {
            buyQueue.add(t);
        }
        if (!activeCompanies.contains(t.getCompanyRFC())) {
            activeCompanies.add(t.getCompanyRFC());
        }
        return 1;
    }

    public static void doInvestments() {
        ArrayList<Operations> buyPerCompany;
        ArrayList<Operations> sellPerCompany;
        Operations lowest;
        Operations highest;

        for (int company = 0; company < activeCompanies.size(); company++) {

            String companyName = activeCompanies.get(company);
            buyPerCompany = new ArrayList<Operations>();
            sellPerCompany = new ArrayList<Operations>();

            for (int sell = 0; sell < sellQueue.size(); sell++) {
                if (sellQueue.get(sell).getCompanyRFC().equals(companyName)) {
                    sellPerCompany.add(sellQueue.get(sell));
                }
            }

            for (int buy = 0; buy < buyQueue.size(); buy++) {
                if (buyQueue.get(buy).getCompanyRFC().equals(companyName)) {
                    buyPerCompany.add(buyQueue.get(buy));
                }
            }
            if (sellQueue.size() > 0) {
                lowest = Collections.min(sellQueue);
                createInvestment(lowest);
                System.out.println("Venta ganadora para " + lowest.getCompanyRFC() + " : " + lowest.getUserRFC() + " a " + lowest.getOperatedStocksPrice());
            }
            if (buyQueue.size() > 0) {
                highest = Collections.max(buyQueue);
                createInvestment(highest);
                System.out.println("Compra ganadora para " + highest.getCompanyRFC() + " : " + highest.getUserRFC() + " a " + highest.getOperatedStocksPrice());

            }
            buyQueue = new ArrayList<Operations>();
            sellQueue = new ArrayList<Operations>();
            activeCompanies = new ArrayList<String>();
        }

    }

    public static void startTimer() {
        if (!isTimerActive) {
            System.out.println("timer creado");
            isTimerActive = true;
            Timer timer = new Timer();
            timer.schedule(new TimerTask() {
                @Override
                public void run() {
                    doInvestments();
                    timer.cancel();
                    isTimerActive = false;
                    System.out.println("Timer finalizado");
                }
            }, 1 * 60 * 1000);
        }
    }

//NUEVA TRANSACCIÖN
    public static int createInvestment(Operations t) {

        int iRet = -1;
        try {

            Connection con = DBManager.getInstance().getConnection();

            //Actualizar acciones de la compañia
            String SQL = "UPDATE companies SET stockNumber =  stockNumber - ? WHERE companyRFC = ?";
            PreparedStatement pstmt = con.prepareStatement(SQL);
            pstmt.setInt(1, t.getOperatedStocks());
            pstmt.setString(2, t.getCompanyRFC());
            iRet = pstmt.executeUpdate();
            

            if (iRet == 1) {
                //se hace la transacción si se cuenta con las acciones suficientes
                SQL = "INSERT INTO transactions (userRFC, companyRFC, operatedStocks, operatedStocksPrice) values(?,?,?,?)";
                PreparedStatement pstmt2 = con.prepareStatement(SQL);
                pstmt2.setString(1, t.getUserRFC());
                pstmt2.setString(2, t.getCompanyRFC());
                pstmt2.setInt(3, t.getOperatedStocks());
                pstmt2.setDouble(4, t.getOperatedStocksPrice());
                iRet = pstmt2.executeUpdate();

                //Se actualiza los datos para el inversor 
                SQL = "UPDATE users SET stockNumber =  stockNumber + ?, lastBuyPrice = ? WHERE userRFC = ?";
                PreparedStatement pstmt3 = con.prepareStatement(SQL);
                pstmt3.setInt(1, t.getOperatedStocks());
                pstmt3.setDouble(2, t.getOperatedStocksPrice());
                pstmt3.setString(3, t.getUserRFC());
                iRet = pstmt3.executeUpdate();
                
                //Actualizar precio de la acción
                SQL = "UPDATE companies SET stockValue =  stockValue = ? WHERE companyRFC = ?";
                PreparedStatement pstmt4 = con.prepareStatement(SQL);
                pstmt4.setDouble(1, t.getOperatedStocksPrice());
                pstmt4.setString(2, t.getCompanyRFC());
                iRet = pstmt4.executeUpdate();
                
                SQL = "UPDATE companies SET stockValue =  stockValue + ? WHERE companyRFC = ?";
                PreparedStatement pstmt5 = con.prepareStatement(SQL);
                pstmt5.setDouble(1, t.getOperatedStocksPrice());
                pstmt5.setString(2, t.getCompanyRFC());
                iRet = pstmt5.executeUpdate();
                
            }

        } catch (SQLException se) {
            System.out.println(se);
        }

        return iRet;
    }

    public static ArrayList getInvestments(String userRFC) {
        ArrayList<Operations> arr = new ArrayList();
        try {
            Connection con = DBManager.getInstance().getConnection();
            String SQL = "SELECT t.companyRFC, t.operatedStocks, t.operatedStocksPrice, c.stockValue FROM transactions as t "
                    + " INNER JOIN companies as c ON t.companyRFC = c.companyRFC WHERE userRFC = ?";
            PreparedStatement pstmt = con.prepareStatement(SQL);
            pstmt.setString(1, userRFC);

            ResultSet rs = pstmt.executeQuery();
            while (rs.next()) {
                Operations tr = new Operations();
                tr.setUserRFC(userRFC);
                tr.setCompanyRFC(rs.getString("companyRFC"));
                tr.setOperatedStocks(rs.getInt("operatedStocks"));
                tr.setOperatedStocksPrice(rs.getDouble("operatedStocksPrice"));
                tr.setActualStocksPrice(rs.getDouble("stockValue"));
                arr.add(tr);
            }

            pstmt.close();
        } catch (SQLException se) {
            System.out.println(se);
        }

        return arr;
    }

}
