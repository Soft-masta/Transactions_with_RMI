/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package StockExchange;

import java.rmi.server.UnicastRemoteObject;
//import IRemoteExchange;
//import Province;
//import ProvinceRepository;
import java.rmi.RemoteException;
import static java.rmi.server.RemoteServer.getClientHost;
import java.rmi.server.ServerNotActiveException;
import java.util.ArrayList;

/**
 *
 * @author Edwin Fajardo
 */
public class RemoteObject extends UnicastRemoteObject implements IRemoteController {

    /**
     *
     */
    private static final long serialVersionUID = 11L;

    public RemoteObject() throws RemoteException {
        super();
    }

    public int createUser(User u) {
        try {
            System.out.println("Invoke save from " + getClientHost());
        } catch (ServerNotActiveException snae) {
            snae.printStackTrace();
        }
        return Repository.createUser(u);
    }

    public User getUser(String userRFC) {
        try {
            System.out.println("Invoke save from " + getClientHost());
        } catch (ServerNotActiveException snae) {
            snae.printStackTrace();
        }

        return Repository.getUser(userRFC);
    }

    public int createCompany(Company c) {
        try {
            System.out.println("Invoke createCompany from " + getClientHost());
        } catch (ServerNotActiveException snae) {
            snae.printStackTrace();
        }
        return Repository.createCompany(c);
    }

    public ArrayList<Company> getAllCompanies() {
        try {
            System.out.println("Invoke listcompanies from " + getClientHost());
        } catch (ServerNotActiveException snae) {
            snae.printStackTrace();
        }
        
        ArrayList<Company> arr = Repository.getAllCompanies();
        
        return arr;
    }

    public int createInvestment(Transaction t) {
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
        ArrayList<Transaction> arr = Repository.getInvestments(userRFC);

        return arr;
    }

//  public int save(Province p) {
//    try {
//      System.out.println("Invoke save from " + getClientHost());
//    } catch (ServerNotActiveException snae) {
//      snae.printStackTrace();
//    }
//    return ProvinceRepository.save(p);
//  }
// 
//  public int update(Province p) {
//    try {
//      System.out.println("Invoke update from " + getClientHost());
//    } catch (ServerNotActiveException snae) {
//      snae.printStackTrace();
//    }
//    return ProvinceRepository.update(p);
//  }
// 
//  public int delete(Province p) {
//    try {
//      System.out.println("Invoke delete from " + getClientHost());
//    } catch (ServerNotActiveException snae) {
//      snae.printStackTrace();
//    }
//    return ProvinceRepository.delete(p);
//  }
// 
//  public void deleteAll() {
//    try {
//      System.out.println("Invoke deleteAll from " + getClientHost());
//    } catch (ServerNotActiveException snae) {
//      snae.printStackTrace();
//    }
//    ProvinceRepository.deleteAll();
//  }
// 
//  public ArrayList findAll() {
//    try {
//      System.out.println("Invoke findAll from " + getClientHost());
//    } catch (ServerNotActiveException snae) {
//      snae.printStackTrace();
//    }
//    return ProvinceRepository.findAll();
//  }
// 
//  public ArrayList findByName(String criteria) {
//    try {
//      System.out.println("Invoke findByName from " + getClientHost());
//    } catch (ServerNotActiveException snae) {
//      snae.printStackTrace();
//    }
//    return ProvinceRepository.findByName(criteria);
//  }
}
