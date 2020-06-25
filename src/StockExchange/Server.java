/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package StockExchange;

import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;

/**
 *
 * @author Edwin Fajardo
 */
public class Server {
      public static void main(String[] args) {
    try {
      //Create and get reference to rmi registry
      Registry registry = LocateRegistry.createRegistry(1099);
 
      //Instantiate server object
      RemoteObject po = new RemoteObject();
 
      //Register server object
      registry.rebind("User", po);
      System.out.println("¡Servidor de bolsa de valores creado!");
      
    } catch (Exception e) {
      System.out.println(e);
    }
  }
}
