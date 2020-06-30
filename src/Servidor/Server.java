package Servidor;

import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;

public class Server {
      public static void main(String[] args) {
    try {
      
      Registry registry = LocateRegistry.createRegistry(1099);
 
      
      RemoteObject po = new RemoteObject();
 
     
      registry.rebind("User", po);
      System.out.println("¡Servidor de bolsa de valores creado!");
      
    } catch (Exception e) {
      System.out.println(e);
    }
  }
}
