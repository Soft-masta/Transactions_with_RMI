package Cliente;


import Servidor.Enterprise;
import Servidor.IRemoteController;
import Servidor.Operations;
import Servidor.Investor;
import java.awt.GraphicsConfiguration;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;


public class Client {

    static GraphicsConfiguration gc;
    static String username;

    public static void main(String[] args) throws RemoteException, NotBoundException{

        Registry registry = LocateRegistry.getRegistry("127.0.0.1");
        IRemoteController re = (IRemoteController) registry.lookup("User");

        
        AccesView vs = new AccesView();
        vs.setLocation(250, 100);
        vs.setVisible(true);
        JTextField tf = vs.getRfcUsuario();
        JButton btn = vs.getIniciarSesion();
        JLabel lbl = vs.getStatus();
        
       
        InvestorView la = new InvestorView();
        JTable jt = la.getInvestments();
        DefaultTableModel model = new DefaultTableModel();
        JButton btn2 = la.getTransactions();
        JButton btn3 = la.UpdateTransactions();
        la.setLocation(250, 100);

      
        TransactionsView ta = new TransactionsView();
        ta.setDefaultCloseOperation(ta.HIDE_ON_CLOSE);
        ta.setLocation(250, 100);
        

       
        JTable compsInfo = ta.getCompaniesTable();
        JComboBox compsList = ta.getCompaniesList();
        JTextField numOfActions = ta.getStockNumber();
        JTextField actionsPrice = ta.getStockPrice();
        JButton transact = ta.getTransact();
        JButton btn4 = ta.UpdateTransact();
        DefaultTableModel model2 = new DefaultTableModel();

        
        btn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    username = tf.getText();

                    //Buscar informacion de usuario
                    Investor usr = re.getUser(username);
                    if (!(usr.getUserRFC() == null)) {
                        la.setTitle("Bienvenido " + usr.getName());
                        ta.setTitle(usr.getName());

                        ArrayList<Operations> arr = re.getInvestments(usr.getUserRFC());
                       
                      
                        String headers[] = {"Empresa", "Numero de acciones", "Ultimo precio de compra", "Precio actual"};
                        model.setColumnIdentifiers(headers);

                        arr.forEach((n) -> {
                            String rfc = n.getCompanyRFC();
                            int actions = n.getOperatedStocks();
                            Double price = n.getOperatedStocksPrice();
                            Double aPrice = n.getActualStocksPrice();
                            Object[] data = {rfc, actions, price, aPrice};
                            model.addRow(data);
                        });
                        
                      

                        //PANEL
                        jt.setModel(model);
                        vs.setVisible(false);
                        la.setVisible(true);

                 }
                  

                    

                } catch (Exception ex) {
                    System.out.println(ex);
                }
            }
        });
        
        
        

        btn2.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                ta.setVisible(true);

                ArrayList<Enterprise> arr;
                ArrayList<String> ls = new ArrayList<String>();

                try {
                    //INFORMACIÖN DE EMPRESAS
                    String headers[] = {"Empresa", "Numero de acciones", "Valor de acciones"};
                    model2.setColumnIdentifiers(headers);
                    arr = re.getAllCompanies();

                    arr.forEach((n) -> {
                        String rfc = n.getCompanyRFC();
                        int actions = n.getStockNumber();
                        Double price = n.getStockValue();
                        Object[] data = {rfc, actions, price};
                        model2.addRow(data);
                        ls.add(rfc);
                    });
                    compsInfo.setModel(model2);

                    //Listar empresas
                    compsList.setModel(new DefaultComboBoxModel<String>(ls.toArray(new String[0])));

                } catch (RemoteException ex) {
                    Logger.getLogger(Client.class.getName()).log(Level.SEVERE, null, ex);
                }

            }
        });
        
        
        btn3.addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e) {
                for (int i = 0; i < jt.getRowCount(); i++) {
                              model.removeRow(i);
                              i-=1;
                          }
                Investor usr = null;
                try {
                    usr = re.getUser(username);
                } catch (RemoteException ex) {
                    Logger.getLogger(Client.class.getName()).log(Level.SEVERE, null, ex);
                }
                ArrayList<Operations> arr = null;
                try {
                    arr = re.getInvestments(usr.getUserRFC());
                } catch (RemoteException ex) {
                    Logger.getLogger(Client.class.getName()).log(Level.SEVERE, null, ex);
                }
                arr.forEach((n) -> {
                            String rfc = n.getCompanyRFC();
                            int actions = n.getOperatedStocks();
                            Double price = n.getOperatedStocksPrice();
                            Double aPrice = n.getActualStocksPrice();
                            Object[] data = {rfc, actions, price, aPrice};
                            model.addRow(data);
                        });
                
        }
        });
        
        btn4.addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e) {
                for (int i = 0; i < compsInfo.getRowCount(); i++) {
                              model2.removeRow(i);
                              i-=1;
                          }
                ArrayList<Enterprise> arr;
                ArrayList<String> ls = new ArrayList<String>();

                try {
        
                    arr = re.getAllCompanies();

                    arr.forEach((n) -> {
                        String rfc = n.getCompanyRFC();
                        int actions = n.getStockNumber();
                        Double price = n.getStockValue();
                        Object[] data = {rfc, actions, price};
                        model2.addRow(data);
                        ls.add(rfc);
                    });
                    compsInfo.setModel(model2);

                    //Lista empresas
                    compsList.setModel(new DefaultComboBoxModel<String>(ls.toArray(new String[0])));

                } catch (RemoteException ex) {
                    Logger.getLogger(Client.class.getName()).log(Level.SEVERE, null, ex);
                }

            
                
        }
        });

        transact.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    String company = (String) compsList.getSelectedItem();
                    int acts = Integer.parseInt(numOfActions.getText());
                    Double price = Double.parseDouble(actionsPrice.getText());
                    Operations tr = new Operations(username, company, acts, price);

                    int res = re.createInvestment(tr);

                    if (res == 1) {
                        System.out.println("EXITO EN LA TRANSACCIÓN");
                    } else {
                        System.out.println("ALGO FALLO EN LA TRANSACCIÓN");
                    }
                    JOptionPane.showMessageDialog(null, "Oferta enviada");

       
                } catch (RemoteException ex) {
                    Logger.getLogger(Client.class.getName()).log(Level.SEVERE, null, ex);
                }
                
                
            }
        });

    }

}
