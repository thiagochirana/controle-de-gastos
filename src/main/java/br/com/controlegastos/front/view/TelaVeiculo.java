/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JInternalFrame.java to edit this template
 */
package br.com.controlegastos.front.view;

import br.com.controlegastos.controle.CategoriaGastoController;
import br.com.controlegastos.controle.MarcaController;
import br.com.controlegastos.controle.ModeloController;
import br.com.controlegastos.controle.VeiculoController;
import br.com.controlegastos.entidades.CategoriaGasto;
import br.com.controlegastos.entidades.Marca;
import br.com.controlegastos.entidades.Modelo;
import br.com.controlegastos.entidades.Veiculo;
import br.com.controlegastos.entidades.records.DadosCadastroVeiculo;
import br.com.controlegastos.entidades.records.DadosRespostaVeiculo;
import br.com.controlegastos.front.modal.ModalCadastroCategoria;
import br.com.controlegastos.front.modal.ModalCadastroMarca;
import br.com.controlegastos.front.modal.ModalCadastroModelo;
import br.com.controlegastos.front.modal.ModalMensagem;
import java.awt.Color;
import java.beans.PropertyVetoException;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.plaf.basic.BasicInternalFrameUI;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author Rodrigo
 */
public class TelaVeiculo extends javax.swing.JInternalFrame {

    MarcaController marca = new MarcaController();
    ModeloController modelo = new ModeloController();
    VeiculoController veiculo = new VeiculoController();
    CategoriaGastoController catGasto = new CategoriaGastoController();
    ModalMensagem msg = new ModalMensagem();

    /**
     * Creates new form TelaVeiculo
     */
    public TelaVeiculo() throws PropertyVetoException {
        initComponents();
        setBorder(javax.swing.BorderFactory.createEmptyBorder(0, 0, 0, 0));
        BasicInternalFrameUI bi = (BasicInternalFrameUI) this.getUI();
        bi.setNorthPane(null);
        setMaximum(true);

        setComboBoxs();
        estilizaFields();
    }

    private void estilizaFields() {

        jComboBoxMarca.setOpaque(false);
        jComboBoxMarca.setBackground(new Color(0, 0, 0, 0));
        jComboBoxCombustivel.setOpaque(false);
        jComboBoxCombustivel.setBackground(new Color(0, 0, 0, 0));
        jComboBoxModelo.setOpaque(false);
        jComboBoxModelo.setBackground(new Color(0, 0, 0, 0));
        jTextFieldPlaca.setOpaque(false);
        jTextFieldPlaca.setBackground(new Color(0, 0, 0, 0));
        jTextFieldQuilometragem.setOpaque(false);
        jTextFieldQuilometragem.setBackground(new Color(0, 0, 0, 0));
        jComboBoxCategoria.setOpaque(false);
        jComboBoxCategoria.setBackground(new Color(0, 0, 0, 0));

    }

    public void setComboBoxs() {
        try {
            setComboBoxMarca();
            setComboBoxModelo();
            setComboBoxCategoria();
            setComboBoxVeiculo();
            setTableVeiculos();
        } catch (Exception e) {
            msg.exibirMensagem(e.getMessage(), false);
        }
    }

    public void setComboBoxMarca() throws Exception {
        jComboBoxMarca.removeAllItems();
        for (Marca m : marca.listarMarcas()) {
            jComboBoxMarca.addItem(m.getIdMarca()+" - "+m.getNome());
        }
    }
    
    public void setComboBoxVeiculo() throws Exception{
        jComboBoxCombustivel.removeAllItems();
        for (String v : veiculo.listarTiposCombustivel()){
            jComboBoxCombustivel.addItem(v);
        }
    }

    public void setComboBoxCategoria() throws Exception {
        try {
            jComboBoxCategoria.removeAllItems();
            for (CategoriaGasto cat : catGasto.listarCategoria()) {
                jComboBoxCategoria.addItem(cat.getIdCategoria() + " - " + cat.getNome());
            }
        } catch (Exception e) {
            msg.exibirMensagem(e.getMessage(), false);
        }
    }

    public void setComboBoxModelo() throws Exception {
        jComboBoxModelo.removeAllItems();
        for (Modelo model : modelo.listaModelos()) {
            jComboBoxModelo.addItem(model.getIdModelo()+" - "+model.getNome());
        }
    }
    
    public void setTableVeiculos() throws Exception{
        List<Veiculo> veiculos = veiculo.listarVeiculos(true);
        
        DefaultTableModel modelTable = new DefaultTableModel();
        modelTable.setNumRows(0);
        
        
        for (Veiculo v : veiculos){
            String[] cell = new String[7];
            
            cell[0] = v.getIdVeiculo()+"";
            cell[1] = v.getPlaca();
            cell[2] = v.getModeloId()+"";
            cell[3] = "MARCA AQUI";
            cell[4] = v.getCategoriaVeiculo();
            cell[5] = v.getQuilometragem()+"";
            cell[6] = v.getTipoCombustivel();
            
            modelTable.addRow(cell);
        }
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jComboBoxCombustivel = new javax.swing.JComboBox<>();
        jLabelAddCombustivel = new javax.swing.JLabel();
        jComboBoxModelo = new javax.swing.JComboBox<>();
        jScrollPaneVeiculos = new javax.swing.JScrollPane();
        jTableVeiculos = new javax.swing.JTable();
        jComboBoxMarca = new javax.swing.JComboBox<>();
        jComboBoxCategoria = new javax.swing.JComboBox<>();
        jLabelExcluirVeiculo = new javax.swing.JLabel();
        jLabelCadastrarVeiculo = new javax.swing.JLabel();
        jLabelAddCategoria = new javax.swing.JLabel();
        jLabelAddMarca = new javax.swing.JLabel();
        jLabelAddModelo = new javax.swing.JLabel();
        jTextFieldQuilometragem = new javax.swing.JTextField();
        jTextFieldPlaca = new javax.swing.JTextField();
        jLabelFundoVeiculo = new javax.swing.JLabel();

        setBorder(null);
        getContentPane().setLayout(null);

        getContentPane().add(jComboBoxCombustivel);
        jComboBoxCombustivel.setBounds(206, 146, 180, 22);

        jLabelAddCombustivel.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabelAddCombustivelMouseClicked(evt);
            }
        });
        getContentPane().add(jLabelAddCombustivel);
        jLabelAddCombustivel.setBounds(400, 140, 0, 40);

        getContentPane().add(jComboBoxModelo);
        jComboBoxModelo.setBounds(540, 148, 160, 20);

        jTableVeiculos.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null}
            },
            new String [] {
                "#", "Placa", "Modelo", "Marca", "Categoria", "Quilometragem", "Combustível"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jScrollPaneVeiculos.setViewportView(jTableVeiculos);

        getContentPane().add(jScrollPaneVeiculos);
        jScrollPaneVeiculos.setBounds(22, 182, 860, 260);

        jComboBoxMarca.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jComboBoxMarcaMouseClicked(evt);
            }
        });
        getContentPane().add(jComboBoxMarca);
        jComboBoxMarca.setBounds(540, 106, 160, 22);

        jComboBoxCategoria.setToolTipText("");
        jComboBoxCategoria.setName(""); // NOI18N
        jComboBoxCategoria.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jComboBoxCategoriaActionPerformed(evt);
            }
        });
        getContentPane().add(jComboBoxCategoria);
        jComboBoxCategoria.setBounds(540, 65, 160, 22);

        jLabelExcluirVeiculo.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabelExcluirVeiculoMouseClicked(evt);
            }
        });
        getContentPane().add(jLabelExcluirVeiculo);
        jLabelExcluirVeiculo.setBounds(570, 450, 150, 30);

        jLabelCadastrarVeiculo.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabelCadastrarVeiculoMouseClicked(evt);
            }
        });
        getContentPane().add(jLabelCadastrarVeiculo);
        jLabelCadastrarVeiculo.setBounds(730, 450, 150, 30);

        jLabelAddCategoria.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabelAddCategoriaMouseClicked(evt);
            }
        });
        getContentPane().add(jLabelAddCategoria);
        jLabelAddCategoria.setBounds(720, 60, 30, 30);

        jLabelAddMarca.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabelAddMarcaMouseClicked(evt);
            }
        });
        getContentPane().add(jLabelAddMarca);
        jLabelAddMarca.setBounds(720, 100, 30, 30);

        jLabelAddModelo.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabelAddModeloMouseClicked(evt);
            }
        });
        getContentPane().add(jLabelAddModelo);
        jLabelAddModelo.setBounds(720, 140, 30, 30);

        jTextFieldQuilometragem.setBorder(null);
        jTextFieldQuilometragem.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTextFieldQuilometragemActionPerformed(evt);
            }
        });
        jTextFieldQuilometragem.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                jTextFieldQuilometragemKeyTyped(evt);
            }
        });
        getContentPane().add(jTextFieldQuilometragem);
        jTextFieldQuilometragem.setBounds(205, 105, 180, 25);

        jTextFieldPlaca.setBorder(null);
        getContentPane().add(jTextFieldPlaca);
        jTextFieldPlaca.setBounds(205, 65, 180, 25);

        jLabelFundoVeiculo.setIcon(new javax.swing.ImageIcon(getClass().getResource("/br/com/controlegastos/front/image/FundoTelaVeiculo.png"))); // NOI18N
        getContentPane().add(jLabelFundoVeiculo);
        jLabelFundoVeiculo.setBounds(0, -1, 910, 500);

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jLabelAddCategoriaMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabelAddCategoriaMouseClicked
        // TODO add your handling code here:

        try {
            ModalCadastroCategoria cat = new ModalCadastroCategoria();
        } catch (Exception ex) {
            msg.exibirMensagem(ex.getMessage(), false);
        } finally {
            setComboBoxs();
        }

    }//GEN-LAST:event_jLabelAddCategoriaMouseClicked

    private void jLabelAddMarcaMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabelAddMarcaMouseClicked
        // TODO add your handling code here:
        try {
            ModalCadastroMarca modal = new ModalCadastroMarca();
        } catch (Exception ex) {
            msg.exibirMensagem(ex.getMessage(), false);
        } finally {
            setComboBoxs();
        }
    }//GEN-LAST:event_jLabelAddMarcaMouseClicked

    private void jLabelAddModeloMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabelAddModeloMouseClicked
        // TODO add your handling code here:

        try {
            ModalCadastroModelo modalModelo = new ModalCadastroModelo();
        } catch (Exception e) {
            msg.exibirMensagem(e.getMessage(), false);
        } finally {
            setComboBoxs();
        }


    }//GEN-LAST:event_jLabelAddModeloMouseClicked

    private void jLabelCadastrarVeiculoMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabelCadastrarVeiculoMouseClicked
        
        String[] categoria = jComboBoxCategoria.getSelectedItem().toString().split(" - ");
        String[] modelo = jComboBoxModelo.getSelectedItem().toString().split(" - ");
        
        boolean temPlaca = !jTextFieldPlaca.getText().isEmpty();
        String placa = jTextFieldPlaca.getText();
        String tipoCombustivel = jComboBoxCombustivel.getSelectedItem().toString();
        float quilometragem = Float.parseFloat(jTextFieldQuilometragem.getText());
        String idCategoria = categoria[0];
        long idModelo = Long.parseLong(modelo[0]);
        
        DadosCadastroVeiculo dados = new DadosCadastroVeiculo(
                temPlaca,
                placa,
                null,
                tipoCombustivel,
                quilometragem,
                idCategoria,
                idModelo,
                1
        );
        
        DadosRespostaVeiculo resp;
        try {
            resp = veiculo.cadastrarVeiculo(dados);
            msg.exibirMensagem(resp.mensagem(), resp.acaoFoiRealizada());
        } catch (Exception ex) {
            msg.exibirMensagem(ex.getMessage(), false);
        }
        
        setComboBoxs();
    }//GEN-LAST:event_jLabelCadastrarVeiculoMouseClicked

    private void jLabelExcluirVeiculoMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabelExcluirVeiculoMouseClicked
        // TODO add your handling code here:


    }//GEN-LAST:event_jLabelExcluirVeiculoMouseClicked

    private void jComboBoxMarcaMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jComboBoxMarcaMouseClicked
        // TODO add your handling code here:
        setComboBoxs();
    }//GEN-LAST:event_jComboBoxMarcaMouseClicked

    private void jLabelAddCombustivelMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabelAddCombustivelMouseClicked
        // TODO add your handling code here:
        
    }//GEN-LAST:event_jLabelAddCombustivelMouseClicked

    private void jComboBoxCategoriaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jComboBoxCategoriaActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jComboBoxCategoriaActionPerformed

    private void jTextFieldQuilometragemActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTextFieldQuilometragemActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jTextFieldQuilometragemActionPerformed

    private void jTextFieldQuilometragemKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jTextFieldQuilometragemKeyTyped
        // TODO add your handling code here:char c = evt.getKeyChar();
        char c = evt.getKeyChar();
        
        if(!Character.isDigit(c)){
            evt.consume();
        }
    }//GEN-LAST:event_jTextFieldQuilometragemKeyTyped

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JComboBox<String> jComboBoxCategoria;
    private javax.swing.JComboBox<String> jComboBoxCombustivel;
    private javax.swing.JComboBox<String> jComboBoxMarca;
    private javax.swing.JComboBox<String> jComboBoxModelo;
    private javax.swing.JLabel jLabelAddCategoria;
    private javax.swing.JLabel jLabelAddCombustivel;
    private javax.swing.JLabel jLabelAddMarca;
    private javax.swing.JLabel jLabelAddModelo;
    private javax.swing.JLabel jLabelCadastrarVeiculo;
    private javax.swing.JLabel jLabelExcluirVeiculo;
    private javax.swing.JLabel jLabelFundoVeiculo;
    private javax.swing.JScrollPane jScrollPaneVeiculos;
    private javax.swing.JTable jTableVeiculos;
    private javax.swing.JTextField jTextFieldPlaca;
    private javax.swing.JTextField jTextFieldQuilometragem;
    // End of variables declaration//GEN-END:variables
}
