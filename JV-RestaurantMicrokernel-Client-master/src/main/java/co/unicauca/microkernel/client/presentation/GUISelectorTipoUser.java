
package co.unicauca.microkernel.client.presentation;

import co.unicauca.microkernel.common.entities.User;
import co.unicauca.microkernel.core.domain.UserRepository;
import javax.swing.JOptionPane;

/**
 *
 * @author Usuario
 */
public class GUISelectorTipoUser extends javax.swing.JFrame {

    UserRepository service;
    /** Creates new form GUILoguin */
    public GUISelectorTipoUser() {
        initComponents();
        this.service = service;
        setLocationRelativeTo(null);
        setSize(510, 400);
    }

    /** This method is called from within the constructor to
     * initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is
     * always regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jLabel2 = new javax.swing.JLabel();
        jPanel2 = new javax.swing.JPanel();
        jLabel5 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        btn_Cliente = new javax.swing.JButton();
        btn_Administrador = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setBackground(new java.awt.Color(255, 255, 255));
        setUndecorated(true);
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jPanel1.setBackground(new java.awt.Color(255, 255, 255));
        jPanel1.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel2.setBackground(new java.awt.Color(0, 0, 204));
        jLabel2.setFont(new java.awt.Font("Britannic Bold", 1, 24)); // NOI18N
        jLabel2.setForeground(new java.awt.Color(0, 0, 204));
        jLabel2.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel2.setText("¿Cómo desea ingresar?");
        jPanel1.add(jLabel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 90, 300, 34));

        jPanel2.setBackground(new java.awt.Color(0, 51, 255));

        jLabel5.setFont(new java.awt.Font("Blackadder ITC", 0, 36)); // NOI18N
        jLabel5.setForeground(new java.awt.Color(255, 255, 255));
        jLabel5.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel5.setText("Restaurantes en línea");

        org.jdesktop.layout.GroupLayout jPanel2Layout = new org.jdesktop.layout.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
            .add(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .add(jLabel5, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, 287, Short.MAX_VALUE)
                .addContainerGap())
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
            .add(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .add(jLabel5, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );

        jPanel1.add(jPanel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 307, -1));

        jLabel3.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagen/UserLogin.png"))); // NOI18N
        jPanel1.add(jLabel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 170, -1, -1));

        btn_Cliente.setBackground(new java.awt.Color(0, 0, 255));
        btn_Cliente.setFont(new java.awt.Font("Berlin Sans FB Demi", 0, 18)); // NOI18N
        btn_Cliente.setForeground(new java.awt.Color(255, 255, 255));
        btn_Cliente.setText("Cliente");
        btn_Cliente.setBorder(null);
        btn_Cliente.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_ClienteActionPerformed(evt);
            }
        });
        jPanel1.add(btn_Cliente, new org.netbeans.lib.awtextra.AbsoluteConstraints(90, 210, 140, 36));

        btn_Administrador.setBackground(new java.awt.Color(0, 0, 255));
        btn_Administrador.setFont(new java.awt.Font("Berlin Sans FB Demi", 0, 18)); // NOI18N
        btn_Administrador.setForeground(new java.awt.Color(255, 255, 255));
        btn_Administrador.setText("Administrador");
        btn_Administrador.setBorder(null);
        btn_Administrador.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_AdministradorActionPerformed(evt);
            }
        });
        jPanel1.add(btn_Administrador, new org.netbeans.lib.awtextra.AbsoluteConstraints(90, 150, 140, 36));

        getContentPane().add(jPanel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 300, 280));

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btn_ClienteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_ClienteActionPerformed
        //co.unicauca.microkernel.common.entities.User  Usuario = new User(txtUser.getText(), txtPassword.getText());
        //UserRepository repo
        //Aqui vendria el analizar si el usuario existe en el sistema
        /**User varUser = service.findUser(txtUser.getText(),txtPassword.getText());
        if (varUser!=null) {//El usuario fue encontrado
           
        } else {           
            JOptionPane.showMessageDialog(null, "El usuario o contraseña son invalidos");
        } */
    }//GEN-LAST:event_btn_ClienteActionPerformed

    private void btn_AdministradorActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_AdministradorActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_btn_AdministradorActionPerformed

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(GUISelectorTipoUser.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(GUISelectorTipoUser.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(GUISelectorTipoUser.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(GUISelectorTipoUser.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new GUISelectorTipoUser().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btn_Administrador;
    private javax.swing.JButton btn_Cliente;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    // End of variables declaration//GEN-END:variables

}
