package br.com.controlegastos.front.modal;

import br.com.controlegastos.controle.CategoriaGastoController;
import br.com.controlegastos.controle.VeiculoController;
import br.com.controlegastos.entidades.records.DadosCadastroCategoriaGasto;
import br.com.controlegastos.entidades.records.DadosRespostaCadastroCatGasto;





/**
 *
 * @author Thiago Chirana
 */
public class ModalCadastroCategoriaGastos extends javax.swing.JFrame {
    
    ModalMensagem modalMsg = new ModalMensagem();
    
    VeiculoController veiculo = new VeiculoController();
    CategoriaGastoController cat = new CategoriaGastoController();
    
    public ModalCadastroCategoriaGastos() {
        initComponents();
        
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        setTitle("Cadastre uma Categoria");
        setSize(360, 360);
        setLocationRelativeTo(null);
        this.setResizable(false);
        
        setVisible(true);
    }
    
    
    

    
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jTextFieldNomeCategoriaGasto = new javax.swing.JTextField();
        jLabel2 = new javax.swing.JLabel();
        jButtonCadastrarCategoriaGasto = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTextAreaDescricao = new javax.swing.JTextArea();
        jLabel3 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        getContentPane().setLayout(null);

        jPanel1.setBackground(new java.awt.Color(255, 255, 255));

        jLabel1.setFont(new java.awt.Font("Segoe UI", 1, 24)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(0, 51, 204));
        jLabel1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel1.setText("Nova Categoria");

        jTextFieldNomeCategoriaGasto.setBackground(new java.awt.Color(255, 255, 255));
        jTextFieldNomeCategoriaGasto.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jTextFieldNomeCategoriaGasto.setForeground(new java.awt.Color(0, 51, 153));
        jTextFieldNomeCategoriaGasto.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 51, 153)));
        jTextFieldNomeCategoriaGasto.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTextFieldNomeCategoriaGastoActionPerformed(evt);
            }
        });

        jLabel2.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        jLabel2.setForeground(new java.awt.Color(0, 51, 153));
        jLabel2.setText("Categoria");

        jButtonCadastrarCategoriaGasto.setBackground(new java.awt.Color(0, 153, 0));
        jButtonCadastrarCategoriaGasto.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        jButtonCadastrarCategoriaGasto.setForeground(new java.awt.Color(255, 255, 255));
        jButtonCadastrarCategoriaGasto.setText("Cadastrar");
        jButtonCadastrarCategoriaGasto.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jButtonCadastrarCategoriaGastoMouseClicked(evt);
            }
        });

        jTextAreaDescricao.setBackground(new java.awt.Color(255, 255, 255));
        jTextAreaDescricao.setColumns(20);
        jTextAreaDescricao.setForeground(new java.awt.Color(0, 51, 153));
        jTextAreaDescricao.setRows(5);
        jTextAreaDescricao.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 51, 153)));
        jScrollPane1.setViewportView(jTextAreaDescricao);

        jLabel3.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        jLabel3.setForeground(new java.awt.Color(0, 51, 153));
        jLabel3.setText("Descrição");

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(49, 49, 49)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel3)
                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                        .addComponent(jLabel2)
                        .addComponent(jLabel1, javax.swing.GroupLayout.DEFAULT_SIZE, 284, Short.MAX_VALUE)
                        .addComponent(jTextFieldNomeCategoriaGasto)
                        .addComponent(jButtonCadastrarCategoriaGasto, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jScrollPane1)))
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
                .addComponent(jTextFieldNomeCategoriaGasto, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jLabel3)
                .addGap(2, 2, 2)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jButtonCadastrarCategoriaGasto, javax.swing.GroupLayout.PREFERRED_SIZE, 42, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(115, Short.MAX_VALUE))
        );

        getContentPane().add(jPanel1);
        jPanel1.setBounds(-20, -10, 410, 430);

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jTextFieldNomeCategoriaGastoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTextFieldNomeCategoriaGastoActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jTextFieldNomeCategoriaGastoActionPerformed

    private void jButtonCadastrarCategoriaGastoMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jButtonCadastrarCategoriaGastoMouseClicked
        try{
            String nome = jTextFieldNomeCategoriaGasto.getText();
            String descricao = jTextAreaDescricao.getText();
            
            DadosRespostaCadastroCatGasto resp =cat.cadastrarCategoria(new DadosCadastroCategoriaGasto(nome, descricao));
            
            modalMsg.exibirMensagem(resp.resposta(), resp.cadastrou());
            
        } catch (Exception e){
            modalMsg.exibirMensagem(e.getMessage(), false);
        } finally {
            this.dispose();
        }
        
    }//GEN-LAST:event_jButtonCadastrarCategoriaGastoMouseClicked

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
            java.util.logging.Logger.getLogger(ModalCadastroCategoriaGastos.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(ModalCadastroCategoriaGastos.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(ModalCadastroCategoriaGastos.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(ModalCadastroCategoriaGastos.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new ModalCadastroCategoriaGastos().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButtonCadastrarCategoriaGasto;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTextArea jTextAreaDescricao;
    private javax.swing.JTextField jTextFieldNomeCategoriaGasto;
    // End of variables declaration//GEN-END:variables
}
