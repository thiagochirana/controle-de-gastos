package br.com.controlegastos.front.modal;

import br.com.controlegastos.controle.MarcaController;
import br.com.controlegastos.controle.ModeloController;
import br.com.controlegastos.entidades.Marca;
import br.com.controlegastos.entidades.records.DadosCadastroModelo;
import br.com.controlegastos.entidades.records.DadosRespostaCadastroModelo;

/**
 *
 * @author Thiago Chirana
 */
public class ModalCadastroModelo extends javax.swing.JFrame {

    MarcaController marca = new MarcaController();
    ModeloController mod = new ModeloController();
    ModalMensagem modalMsg = new ModalMensagem();
    
    public ModalCadastroModelo() {
        initComponents();
        
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        setTitle("Cadastre um Modelo");
        setSize(360, 290);
        setLocationRelativeTo(null);
        this.setResizable(false);
        setComboBoxMarca();
        setVisible(true);
    }
    
    public void setComboBoxMarca(){
        try{
            jComboBoxMarca.removeAllItems();
            for (Marca m : marca.listarMarcas()){
                jComboBoxMarca.addItem(m.getIdMarca() +" - "+m.getNome());
            }
        } catch(Exception e) {
            modalMsg.exibirMensagem(e.getMessage(), false);
        }
              
    }

    
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jTextFieldNomeModelo = new javax.swing.JTextField();
        jLabel2 = new javax.swing.JLabel();
        jButtonCadastrarModelo = new javax.swing.JButton();
        jComboBoxMarca = new javax.swing.JComboBox<>();
        jLabel3 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        getContentPane().setLayout(null);

        jPanel1.setBackground(new java.awt.Color(255, 255, 255));

        jLabel1.setFont(new java.awt.Font("Segoe UI", 1, 24)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(0, 51, 204));
        jLabel1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel1.setText("Novo Modelo");

        jTextFieldNomeModelo.setBackground(new java.awt.Color(255, 255, 255));
        jTextFieldNomeModelo.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jTextFieldNomeModelo.setForeground(new java.awt.Color(0, 51, 153));
        jTextFieldNomeModelo.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 51, 153)));
        jTextFieldNomeModelo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTextFieldNomeModeloActionPerformed(evt);
            }
        });

        jLabel2.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        jLabel2.setForeground(new java.awt.Color(0, 51, 153));
        jLabel2.setText("Modelo");

        jButtonCadastrarModelo.setBackground(new java.awt.Color(0, 153, 0));
        jButtonCadastrarModelo.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        jButtonCadastrarModelo.setForeground(new java.awt.Color(255, 255, 255));
        jButtonCadastrarModelo.setText("Cadastrar");
        jButtonCadastrarModelo.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jButtonCadastrarModeloMouseClicked(evt);
            }
        });

        jComboBoxMarca.setBackground(new java.awt.Color(255, 255, 255));
        jComboBoxMarca.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jComboBoxMarca.setForeground(new java.awt.Color(0, 51, 153));

        jLabel3.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        jLabel3.setForeground(new java.awt.Color(0, 51, 153));
        jLabel3.setText("Marca");

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(49, 49, 49)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jLabel2)
                    .addComponent(jLabel1, javax.swing.GroupLayout.DEFAULT_SIZE, 297, Short.MAX_VALUE)
                    .addComponent(jTextFieldNomeModelo)
                    .addComponent(jButtonCadastrarModelo, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jLabel3)
                    .addComponent(jComboBoxMarca, javax.swing.GroupLayout.Alignment.TRAILING, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap(64, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(28, 28, 28)
                .addComponent(jLabel1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jLabel2)
                .addGap(3, 3, 3)
                .addComponent(jTextFieldNomeModelo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel3)
                .addGap(9, 9, 9)
                .addComponent(jComboBoxMarca, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jButtonCadastrarModelo)
                .addContainerGap(185, Short.MAX_VALUE))
        );

        getContentPane().add(jPanel1);
        jPanel1.setBounds(-20, -10, 410, 430);

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jTextFieldNomeModeloActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTextFieldNomeModeloActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jTextFieldNomeModeloActionPerformed

    private void jButtonCadastrarModeloMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jButtonCadastrarModeloMouseClicked
        try{
            String nomeModelo = jTextFieldNomeModelo.getText();
            String[] marcaSelecionada = jComboBoxMarca.getSelectedItem().toString().split(" - ");

            DadosCadastroModelo dados = new DadosCadastroModelo(
                    nomeModelo,
                    null,
                    Long.parseLong(marcaSelecionada[0]),
                    marcaSelecionada[1]
            );

            DadosRespostaCadastroModelo resp = mod.cadastrarModelo(dados);
            
            modalMsg.exibirMensagem(resp.mensagem(), resp.cadastrou());
            
        } catch (Exception e){
            modalMsg.exibirMensagem(e.getMessage(), false);
        } finally {
            this.dispose();
        }
            
    }//GEN-LAST:event_jButtonCadastrarModeloMouseClicked

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
            java.util.logging.Logger.getLogger(ModalCadastroModelo.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(ModalCadastroModelo.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(ModalCadastroModelo.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(ModalCadastroModelo.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new ModalCadastroModelo().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButtonCadastrarModelo;
    private javax.swing.JComboBox<String> jComboBoxMarca;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JTextField jTextFieldNomeModelo;
    // End of variables declaration//GEN-END:variables
}
