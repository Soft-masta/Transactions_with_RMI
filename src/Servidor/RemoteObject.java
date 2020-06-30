package Servidor;

import BasedeDatos.Repository;
import java.rmi.server.UnicastRemoteObject;
import java.rmi.RemoteException;
import static java.rmi.server.RemoteServer.getClientHost;
import java.rmi.server.ServerNotActiveException;
import java.util.ArrayList;

public class RemoteObject extends UnicastRemoteObject implements IRemoteController {


    private static final long serialVersionUID = 11L;

    public RemoteObject() throws RemoteException {
        super();
    }

    public int createUser(Investor u) {
        try {
            System.out.println("Invoke save from " + getClientHost());
        } catch (ServerNotActiveException snae) {
            snae.printStackTrace();
        }
        return Repository.createUser(u);
    }

    public Investor getUser(String userRFC) {
        try {
            System.out.println("Invoke save from " + getClientHost());
        } catch (ServerNotActiveException snae) {
            snae.printStackTrace();
        }

        return Repository.getUser(userRFC);
    }

    public int createCompany(Enterprise c) {
        try {
            System.out.println("Invoke createCompany from " + getClientHost());
        } catch (ServerNotActiveException snae) {
            snae.printStackTrace();
        }
        return Repository.createCompany(c);
    }

    public ArrayList<Enterprise> getAllCompanies() {
        try {
            System.out.println("Invoke listcompanies from " + getClientHost());
        } catch (ServerNotActiveException snae) {
            snae.printStackTrace();
        }
        
        ArrayList<Enterprise> arr = Repository.getAllCompanies();
        
        return arr;
    }

    public int createInvestment(Operations t) {
        try {
            System.out.println("Invoke createInvestment from " + getClientHost());
        } catch (ServerNotActiveException snae) {
            snae.printStackTrace();
        }
        return Repository.addInvestmentToQueue(t);
    }

    public ArrayList getInvestments(String userRFC) {

        try {
            System.out.println("Invoke listInvestments from " + getClientHost());
        } catch (ServerNotActiveException snae) {
            snae.printStackTrace();
        }

        //ALGORITMO PARA CREAR UN ELEMENTO POR EMPRESA
        ArrayList<Operations> arr = Repository.getInvestments(userRFC);

        return arr;
    }
    
    public ArrayList<String> getWinners(String userRFC) {

        try {
            System.out.println("Invoke winnerCheckout from " + getClientHost());
        } catch (ServerNotActiveException snae) {
            snae.printStackTrace();
        }

        //ALGORITMO PARA CREAR UN ELEMENTO POR EMPRESA
        ArrayList<String> arr = Repository.winnersCheckout(userRFC);
        if (arr.isEmpty()) {
            arr.add("No has ganado ninguna transacción");
        }
        return arr;
    }

}
