
package Cliente;

import java.awt.Color;
import javax.swing.JButton;
import javax.swing.JTable;

public class InvestorView extends javax.swing.JFrame {


    public InvestorView() {
        initComponents();
        this.getContentPane().setBackground(Color.white);
    }

    public JTable getInvestments() {
        return investments;
    }

    public JButton getTransactions() {
        return transactActions;
    }
    
     public JButton UpdateTransactions() {
        return transactActions1;
    }
    
    
    
    
  
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jScrollPane1 = new javax.swing.JScrollPane();
        investments = new javax.swing.JTable();
        transactActions = new javax.swing.JButton();
        transactActions1 = new javax.swing.JButton();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        investments.setFont(new java.awt.Font("Segoe UI", 0, 13)); // NOI18N
        investments.setModel(new javax.swing.table.DefaultTableModel(
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
        jScrollPane1.setViewportView(investments);

        getContentPane().add(jScrollPane1, new org.netbeans.lib.awtextra.AbsoluteConstraints(50, 80, 750, 360));

        transactActions.setFont(new java.awt.Font("AppleGothic", 0, 13)); // NOI18N
        transactActions.setText("Comprar/vender Acciones");
        transactActions.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                transactActionsActionPerformed(evt);
            }
        });
        getContentPane().add(transactActions, new org.netbeans.lib.awtextra.AbsoluteConstraints(130, 450, 301, -1));

        transactActions1.setFont(new java.awt.Font("AppleGothic", 0, 13)); // NOI18N
        transactActions1.setText("Actualizar");
        transactActions1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                transactActions1ActionPerformed(evt);
            }
        });
        getContentPane().add(transactActions1, new org.netbeans.lib.awtextra.AbsoluteConstraints(440, 450, 301, -1));

        jLabel1.setFont(new java.awt.Font("AppleGothic", 0, 36)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(255, 255, 255));
        jLabel1.setText("Su portafolio de inversiones");
        getContentPane().add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(220, 20, -1, -1));

        jLabel2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/vector-abstract-radial-dots-pattern-halftone-on-blue-gradient-background-technology-digital-concept-futuristic-neon-lighting.jpg"))); // NOI18N
        getContentPane().add(jLabel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 10, 830, 480));

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void transactActionsActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_transactActionsActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_transactActionsActionPerformed

    private void transactActions1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_transactActions1ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_transactActions1ActionPerformed

 
    public static void main(String args[]) {
   
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new InvestorView().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JTable investments;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JButton transactActions;
    private javax.swing.JButton transactActions1;
    // End of variables declaration//GEN-END:variables
}
