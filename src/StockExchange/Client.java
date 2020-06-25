/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package StockExchange;

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
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author Edwin Fajardo
 */
public class Client {

    static GraphicsConfiguration gc;
    static String username;

    public static void main(String[] args) throws RemoteException, NotBoundException {

        Registry registry = LocateRegistry.getRegistry("127.0.0.1");
        IRemoteController re = (IRemoteController) registry.lookup("User");

        //SE INICIALIZA LA VISTA LOGIN
        VLogin vs = new VLogin();
        vs.setLocation(250, 100);
        vs.setVisible(true);
        JTextField tf = vs.getRfcUsuario();
        JButton btn = vs.getIniciarSesion();
        JLabel lbl = vs.getStatus();
        //> SOLO PARA PRUEBAS
        tf.setText("AA12001082");
        //>

        //Inicialización de vista de inversiones
        VStockList la = new VStockList();
        JTable jt = la.getInvestments();
        DefaultTableModel model = new DefaultTableModel();
        JButton btn2 = la.getTransactions();
        la.setLocation(250, 100);

        //INICIALIZACIÓN DE LA VISTA DE TRANSACCIONES
        VTransactions ta = new VTransactions();
        ta.setDefaultCloseOperation(ta.HIDE_ON_CLOSE);

        ta.setLocation(250, 100);

        //SE OBTIENEN LOS ELEMENTOS
        JTable compsInfo = ta.getCompaniesTable();
        JComboBox compsList = ta.getCompaniesList();
        JTextField numOfActions = ta.getStockNumber();
        JTextField actionsPrice = ta.getStockPrice();
        JButton transact = ta.getTransact();
        DefaultTableModel model2 = new DefaultTableModel();

        //ACtion listener de la primera vista
        btn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    username = tf.getText();

                    //SE BUSCA EL USUARIO Y SE REGRESA SU INFORMACIÓN
                    User usr = re.getUser(username);
                    if (!(usr.getUserRFC() == null)) {
                        lbl.setText("BIENVENIDO!");
                        la.setTitle("Bienvenido " + username);

//                    LISTA TODAS LAS TRANSACCIONES HECHAS POR DETERMINADO USUARIO (userRFC)
                        ArrayList<Transaction> arr = re.getInvestments(usr.getUserRFC());
                        //ALGORITMO PARA CREAR UN ELEMENTO POR EMPRESA
//                      ArrayList<Transaction> arr2 = new ArrayList();
//                        arr.forEach((el) -> {
//                            Transaction tr = el;
//                            String comp1 = el.getCompanyRFC();
//                            int ops = el.getOperatedStocks();
//                            Double opsPrice = el.getOperatedStocksPrice();
//
//                            if (arr2.isEmpty()) {
//                                arr2.add(tr);
//                            } else {
//                                //Se busca en cada elemento de la nueva lista si la empresa ya está listada
//                                arr2.forEach((el2) -> {
//                                    String comp2 = el2.getCompanyRFC();
//                                    int ops2 = el2.getOperatedStocks();
//                                    Double opsPrice2 = el2.getOperatedStocksPrice();
//                                    //Si la empresa ya está agregada a la lista, se suma el número de aciones operadas
//                                    if (comp2.equals(comp1)) {
//                                        el2.setOperatedStocks(ops + ops2);
//                                        el2.setOperatedStocksPrice((opsPrice + opsPrice2) / 2);
//                                    } else if (el2 == arr2.get(arr2.size() - 1)) {
//                                        //Si es el último elemento 
//                                        arr2.add(tr);
//                                    }
//                                });
//                            }
//                        });
//                        
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

                    } else {
                        lbl.setText("NO EXISTES CRACK");
                    }

//                    CREAR NUEVO USUARIO (RFC y Nombre)
//                    User usr = new User("AA12001084", "Hector Burgos");
//                    re.createUser(usr);
//
//                    CREAR NUEVA COMPANIA ("RFC", ACCIONES, PRECIO POR ACCION)
//                    Company comp = new Company("AA30000000", 100, 200.0);
//                    int resp = re.createCompany(comp);
//
//                    CREAR NUEVA TRANSACCIÓN (RFCUsuario, RFCEmpresaa, Acciones [+ es compra, - es venta], precioSugerido)
//                    Transaction tr = new Transaction("AA12001082", "AA10000000", 10, 20.5);
//                    int resp = re.createInvestment(tr);
//
                } catch (Exception ex) {
                    System.out.println(ex);
                }
            }
        });

        btn2.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                ta.setVisible(true);

                ArrayList<Company> arr;
                ArrayList<String> ls = new ArrayList<String>();

                try {
                    //INFORMACIÖN DE EMPRESAS
                    String headers[] = {"Empresa", "Numero de acciones", "Valor de acciones"};
                    model2.setColumnIdentifiers(headers);
                    arr = re.getAllCompanies();

//                    arr.forEach((k) -> {
//                        System.out.println("Comp: " + k.getCompanyRFC());;
//                    });
                    arr.forEach((n) -> {
                        String rfc = n.getCompanyRFC();
                        int actions = n.getStockNumber();
                        Double price = n.getStockValue();
                        Object[] data = {rfc, actions, price};
                        model2.addRow(data);
                        ls.add(rfc);
                    });
                    compsInfo.setModel(model2);

                    //LISTA DE EMPRESAS
                    compsList.setModel(new DefaultComboBoxModel<String>(ls.toArray(new String[0])));

                } catch (RemoteException ex) {
                    Logger.getLogger(Client.class.getName()).log(Level.SEVERE, null, ex);
                }
                //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
                //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
            }
        });

        transact.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    String company = (String) compsList.getSelectedItem();
                    int acts = Integer.parseInt(numOfActions.getText());
                    Double price = Double.parseDouble(actionsPrice.getText());
                    Transaction tr = new Transaction(username, company, acts, price);

                    //System.out.println("Transaction: " + tr.getCompanyRFC() + " " + tr.getUserRFC() + " " + tr.getOperatedStocks() + " " + tr.getOperatedStocksPrice());
                    int res = re.createInvestment(tr);

                    if (res == 1) {
                        System.out.println("EXITO EN LA TRANSACCIÓN");
                    } else {
                        System.out.println("ALGO FALLO EN LA TRANSACCIÓN");
                    }

                    //SE CEREA UNA NUEVA TRANSACTION
                    //PANEL
                    //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
                } catch (RemoteException ex) {
                    Logger.getLogger(Client.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        });

    }

}
