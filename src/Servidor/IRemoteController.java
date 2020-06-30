package Servidor;

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.ArrayList;


public interface IRemoteController extends Remote {
  public int createUser(Investor u) throws RemoteException;
  public Investor getUser(String userRFC) throws RemoteException;
  public int createCompany(Enterprise c) throws RemoteException;
  public ArrayList<Enterprise> getAllCompanies() throws RemoteException;
  public int createInvestment(Operations t) throws RemoteException;
  public ArrayList getInvestments(String userRFC) throws RemoteException;
  public ArrayList<String> getWinners(String userRFC) throws RemoteException;
}
