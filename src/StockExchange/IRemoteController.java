/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package StockExchange;

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.ArrayList;

/**
 *
 * @author Edwin Fajardo
 */
interface IRemoteController extends Remote {
  public int createUser(User u) throws RemoteException;
  public User getUser(String userRFC) throws RemoteException;
  public int createCompany(Company c) throws RemoteException;
  public ArrayList<Company> getAllCompanies() throws RemoteException;
  public int createInvestment(Transaction t) throws RemoteException;
  public ArrayList getInvestments(String userRFC) throws RemoteException;
//  public int update(Province p) throws RemoteException;
//  public int delete(Province p) throws RemoteException;
//  public void deleteAll() throws RemoteException;
//  public ArrayList findAll() throws RemoteException;
//  public ArrayList findByName(String criteria) throws RemoteException;
}
