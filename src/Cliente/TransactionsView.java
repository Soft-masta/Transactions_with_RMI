
package Cliente;

import java.awt.Color;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JTable;
import javax.swing.JTextField;

public class TransactionsView extends javax.swing.JFrame {

    /**
     * Creates new form TransactActions
     */
    public TransactionsView() {
        initComponents();
        this.getContentPane().setBackground(Color.white);
    }

    public JTextField getStockPrice() {
        return actionsPrice;
    }

    public JComboBox<String> getCompaniesList() {
        return companiesList;
    }

    public JTable getCompaniesTable() {
        return companiesTable;
    }

    public JTextField getStockNumber() {
        return numOfActions;
    }

    public JButton getTransact() {
        return transact;
    }
    
     public JButton UpdateTransact() {
        return jButton1;
    }
    
    


    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jScrollPane1 = new javax.swing.JScrollPane();
        companiesTable = new javax.swing.JTable();
        companiesList = new javax.swing.JComboBox<>();
        jLabel1 = new javax.swing.JLabel();
        numOfActions = new javax.swing.JTextField();
        jButton1 = new javax.swing.JButton();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        actionsPrice = new javax.swing.JTextField();
        jLabel4 = new javax.swing.JLabel();
        transact = new javax.swing.JButton();
        jLabel5 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        companiesTable.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4"
            }
        ));
        jScrollPane1.setViewportView(companiesTable);

        getContentPane().add(jScrollPane1, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 0, 690, 260));

        companiesList.setFont(new java.awt.Font("AppleGothic", 0, 18)); // NOI18N
        companiesList.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));
        getContentPane().add(companiesList, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 310, 220, 30));

        jLabel1.setFont(new java.awt.Font("AppleGothic", 0, 18)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(255, 255, 255));
        jLabel1.setText("Seleccione una empresa");
        getContentPane().add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 270, 403, 38));
        getContentPane().add(numOfActions, new org.netbeans.lib.awtextra.AbsoluteConstraints(300, 320, 410, 40));

        jButton1.setText("Actualizar lista");
        getContentPane().add(jButton1, new org.netbeans.lib.awtextra.AbsoluteConstraints(460, 440, 230, 30));

        jLabel2.setFont(new java.awt.Font("AppleGothic", 0, 18)); // NOI18N
        jLabel2.setForeground(new java.awt.Color(255, 255, 255));
        jLabel2.setText("¿Cuantas acciones quiere comprar o vender? ");
        getContentPane().add(jLabel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(320, 270, 403, 40));

        jLabel3.setFont(new java.awt.Font("AppleGothic", 0, 18)); // NOI18N
        jLabel3.setForeground(new java.awt.Color(255, 255, 255));
        jLabel3.setText("Oferte por las acciones");
        getContentPane().add(jLabel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 360, 403, 38));

        actionsPrice.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                actionsPriceActionPerformed(evt);
            }
        });
        getContentPane().add(actionsPrice, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 430, 403, 46));

        jLabel4.setFont(new java.awt.Font("Segoe UI", 0, 13)); // NOI18N
        jLabel4.setForeground(new java.awt.Color(255, 255, 255));
        jLabel4.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel4.setText("Ingrese el número en negativo si es una venta de acciones");
        getContentPane().add(jLabel4, new org.netbeans.lib.awtextra.AbsoluteConstraints(300, 300, 383, 20));

        transact.setFont(new java.awt.Font("AppleGothic", 0, 18)); // NOI18N
        transact.setText("OFERTAR");
        getContentPane().add(transact, new org.netbeans.lib.awtextra.AbsoluteConstraints(440, 370, 250, 60));

        jLabel5.setForeground(new java.awt.Color(255, 255, 255));
        jLabel5.setText("Si desea comprar oferte un precio mayor al actual.");
        getContentPane().add(jLabel5, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 400, 320, 40));

        jLabel6.setForeground(new java.awt.Color(255, 255, 255));
        jLabel6.setText("Si desea vender oferte un precio menor al actual,");
        getContentPane().add(jLabel6, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 380, 310, 40));

        jLabel7.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/vector-abstract-radial-dots-pattern-halftone-on-blue-gradient-background-technology-digital-concept-futuristic-neon-lighting.jpg"))); // NOI18N
        getContentPane().add(jLabel7, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 720, 480));

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void actionsPriceActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_actionsPriceActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_actionsPriceActionPerformed


    public static void main(String args[]) {
     
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new TransactionsView().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JTextField actionsPrice;
    private javax.swing.JComboBox<String> companiesList;
    private javax.swing.JTable companiesTable;
    private javax.swing.JButton jButton1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTextField numOfActions;
    private javax.swing.JButton transact;
    // End of variables declaration//GEN-END:variables
}
