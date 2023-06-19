package br.com.controlegastos.front.modal;

import br.com.controlegastos.controle.CategoriaGastoController;
import br.com.controlegastos.controle.VeiculoController;
import br.com.controlegastos.entidades.records.DadosCadastroCategoriaGasto;
import br.com.controlegastos.entidades.records.DadosRespostaCadastroCatGasto;
import br.com.controlegastos.entidades.records.DadosRespostaCadastroCategoriaVeiculo;




/**
 *
 * @author Thiago Chirana
 */
public class ModalCadastroCategoria extends javax.swing.JFrame {
    
    ModalMensagem modalMsg = new ModalMensagem();
    
    VeiculoController veiculo = new VeiculoController();
    
    public ModalCadastroCategoria() {
        initComponents();
        
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        setTitle("Cadastre uma Categoria");
        setSize(360, 230);
        setLocationRelativeTo(null);
        this.setResizable(false);
        
        setVisible(true);
    }
    
    
    

    
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jTextFieldNomeCategoria = new javax.swing.JTextField();
        jLabel2 = new javax.swing.JLabel();
        jButtonCadastrarCategoria = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        getContentPane().setLayout(null);

        jPanel1.setBackground(new java.awt.Color(255, 255, 255));

        jLabel1.setFont(new java.awt.Font("Segoe UI", 1, 24)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(0, 51, 204));
        jLabel1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel1.setText("Nova Categoria");

        jTextFieldNomeCategoria.setBackground(new java.awt.Color(255, 255, 255));
        jTextFieldNomeCategoria.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jTextFieldNomeCategoria.setForeground(new java.awt.Color(0, 51, 153));
        jTextFieldNomeCategoria.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 51, 153)));
        jTextFieldNomeCategoria.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTextFieldNomeCategoriaActionPerformed(evt);
            }
        });

        jLabel2.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        jLabel2.setForeground(new java.awt.Color(0, 51, 153));
        jLabel2.setText("Categoria");

        jButtonCadastrarCategoria.setBackground(new java.awt.Color(0, 153, 0));
        jButtonCadastrarCategoria.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        jButtonCadastrarCategoria.setForeground(new java.awt.Color(255, 255, 255));
        jButtonCadastrarCategoria.setText("Cadastrar");
        jButtonCadastrarCategoria.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jButtonCadastrarCategoriaMouseClicked(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(49, 49, 49)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jLabel2)
                    .addComponent(jLabel1, javax.swing.GroupLayout.DEFAULT_SIZE, 284, Short.MAX_VALUE)
                    .addComponent(jTextFieldNomeCategoria)
                    .addComponent(jButtonCadastrarCategoria, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap(77, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(28, 28, 28)
                .addComponent(jLabel1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jLabel2)
                .addGap(3, 3, 3)
                .addComponent(jTextFieldNomeCategoria, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jButtonCadastrarCategoria)
                .addContainerGap(255, Short.MAX_VALUE))
        );

        getContentPane().add(jPanel1);
        jPanel1.setBounds(-20, -10, 410, 430);

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jTextFieldNomeCategoriaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTextFieldNomeCategoriaActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jTextFieldNomeCategoriaActionPerformed

    private void jButtonCadastrarCategoriaMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jButtonCadastrarCategoriaMouseClicked
        try{
            String nome = jTextFieldNomeCategoria.getText();

            DadosRespostaCadastroCategoriaVeiculo resp =veiculo.cadastrarCategoriaVeiculo(nome);
            
            modalMsg.exibirMensagem(resp.mensagem(), resp.cadastrou());
            
        } catch (Exception e){
            modalMsg.exibirMensagem(e.getMessage(), false);
        } finally {
            this.dispose();
        }
        
    }//GEN-LAST:event_jButtonCadastrarCategoriaMouseClicked

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
            java.util.logging.Logger.getLogger(ModalCadastroCategoria.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(ModalCadastroCategoria.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(ModalCadastroCategoria.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(ModalCadastroCategoria.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new ModalCadastroCategoria().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButtonCadastrarCategoria;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JTextField jTextFieldNomeCategoria;
    // End of variables declaration//GEN-END:variables
}
