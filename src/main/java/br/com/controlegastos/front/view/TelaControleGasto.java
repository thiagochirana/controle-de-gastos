/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JInternalFrame.java to edit this template
 */
package br.com.controlegastos.front.view;

import java.awt.Color;
import java.awt.event.KeyEvent;
import java.beans.PropertyVetoException;
import java.text.NumberFormat;
import java.util.Calendar;
import java.util.Locale;
import javax.swing.plaf.basic.BasicInternalFrameUI;

/**
 *
 * @author Rodrigo
 */
public class TelaControleGasto extends javax.swing.JInternalFrame {

    /**
     * Creates new form TelaControleGasto
     */
    public TelaControleGasto() throws PropertyVetoException {
        initComponents();

        estilizaFields();
        preparaDatePicker();

        setBorder(javax.swing.BorderFactory.createEmptyBorder(0, 0, 0, 0));
        BasicInternalFrameUI bi = (BasicInternalFrameUI) this.getUI();
        bi.setNorthPane(null);
        setMaximum(true);

    }

    private void estilizaFields() {

        jComboBoxBuscarVeiculo.setOpaque(false);
        jComboBoxBuscarVeiculo.setBackground(new Color(0, 0, 0, 0));
        jComboBoxTipoGasto.setOpaque(false);
        jComboBoxTipoGasto.setBackground(new Color(0, 0, 0, 0));
        jTextFieldValorGasto.setOpaque(false);
        jTextFieldValorGasto.setBackground(new Color(0, 0, 0, 0));
        jScroolPaneGasto.setOpaque(false);
        jScroolPaneGasto.setBackground(new Color(0, 0, 0, 0));

    }

    private void formataTextFieldValor() {

        String texto = jTextFieldValorGasto.getText().replaceAll("[^\\d]", "");

        try {
            long numero = Long.parseLong(texto);

            // Criar um objeto NumberFormat para formatar o valor em moeda
            NumberFormat numberFormat = NumberFormat.getCurrencyInstance(new Locale("pt", "BR"));

            // Configurar o símbolo da moeda para R$
            numberFormat.setCurrency(java.util.Currency.getInstance("BRL"));

            // Obter o valor em moeda formatado
            String textoFormatado = numberFormat.format(numero / 100.0);

            jTextFieldValorGasto.setText(textoFormatado);

        } catch (NumberFormatException e) {
            jTextFieldValorGasto.setText("");
        }
    }

    private void preparaDatePicker() {

        Calendar cal = Calendar.getInstance();
        jDateChooserDataGasto.setMaxSelectableDate(cal.getTime());
        jDateChooserDataGasto.getDateEditor().setEnabled(false);

        jDateChooserDataInicial.setMaxSelectableDate(cal.getTime());
        jDateChooserDataInicial.getDateEditor().setEnabled(false);
        
        jDateChooserDataFinal.setMaxSelectableDate(cal.getTime());
        jDateChooserDataFinal.getDateEditor().setEnabled(false);

    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jDateChooserDataGasto = new com.toedter.calendar.JDateChooser();
        jDateChooserDataInicial = new com.toedter.calendar.JDateChooser();
        jLabelBuscarGasto = new javax.swing.JLabel();
        jTextFieldValorGasto = new javax.swing.JTextField();
        jDateChooserDataFinal = new com.toedter.calendar.JDateChooser();
        jScroolPaneGasto = new javax.swing.JScrollPane();
        jTableGastos = new javax.swing.JTable();
        jLabelExcluirGasto = new javax.swing.JLabel();
        jLabelSalvarGasto = new javax.swing.JLabel();
        jLabelAddTipoGasto = new javax.swing.JLabel();
        jComboBoxBuscarVeiculo = new javax.swing.JComboBox<>();
        jComboBoxTipoGasto = new javax.swing.JComboBox<>();
        jLabelFundoGastos = new javax.swing.JLabel();

        setBorder(null);
        getContentPane().setLayout(null);

        jDateChooserDataGasto.setForeground(new java.awt.Color(0, 102, 255));
        jDateChooserDataGasto.setDateFormatString("dd/MM/yyyy");
        jDateChooserDataGasto.setFocusCycleRoot(true);
        getContentPane().add(jDateChooserDataGasto);
        jDateChooserDataGasto.setBounds(660, 160, 155, 22);

        jDateChooserDataInicial.setForeground(new java.awt.Color(0, 102, 255));
        jDateChooserDataInicial.setDateFormatString("dd/MM/yyyy");
        jDateChooserDataInicial.setFocusCycleRoot(true);
        getContentPane().add(jDateChooserDataInicial);
        jDateChooserDataInicial.setBounds(542, 105, 155, 22);

        jLabelBuscarGasto.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabelBuscarGastoMouseClicked(evt);
            }
        });
        getContentPane().add(jLabelBuscarGasto);
        jLabelBuscarGasto.setBounds(830, 140, 40, 40);

        jTextFieldValorGasto.setBorder(null);
        jTextFieldValorGasto.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                jTextFieldValorGastoKeyReleased(evt);
            }
            public void keyTyped(java.awt.event.KeyEvent evt) {
                jTextFieldValorGastoKeyTyped(evt);
            }
        });
        getContentPane().add(jTextFieldValorGasto);
        jTextFieldValorGasto.setBounds(189, 103, 190, 16);

        jDateChooserDataFinal.setForeground(new java.awt.Color(0, 102, 255));
        jDateChooserDataFinal.setDateFormatString("dd/MM/yyyy");
        jDateChooserDataFinal.setFocusCycleRoot(true);
        getContentPane().add(jDateChooserDataFinal);
        jDateChooserDataFinal.setBounds(480, 160, 155, 22);

        jTableGastos.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Veículo", "Valor", "Tipo de Gasto", "Data"
            }
        ));
        jTableGastos.setGridColor(new java.awt.Color(153, 255, 0));
        jScroolPaneGasto.setViewportView(jTableGastos);

        getContentPane().add(jScroolPaneGasto);
        jScroolPaneGasto.setBounds(22, 192, 860, 250);

        jLabelExcluirGasto.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabelExcluirGastoMouseClicked(evt);
            }
        });
        getContentPane().add(jLabelExcluirGasto);
        jLabelExcluirGasto.setBounds(570, 450, 150, 30);

        jLabelSalvarGasto.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabelSalvarGastoMouseClicked(evt);
            }
        });
        getContentPane().add(jLabelSalvarGasto);
        jLabelSalvarGasto.setBounds(730, 450, 150, 30);

        jLabelAddTipoGasto.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabelAddTipoGastoMouseClicked(evt);
            }
        });
        getContentPane().add(jLabelAddTipoGasto);
        jLabelAddTipoGasto.setBounds(715, 60, 35, 35);

        jComboBoxBuscarVeiculo.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));
        getContentPane().add(jComboBoxBuscarVeiculo);
        jComboBoxBuscarVeiculo.setBounds(190, 70, 190, 22);

        jComboBoxTipoGasto.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));
        getContentPane().add(jComboBoxTipoGasto);
        jComboBoxTipoGasto.setBounds(540, 70, 160, 22);

        jLabelFundoGastos.setIcon(new javax.swing.ImageIcon(getClass().getResource("/br/com/controlegastos/front/image/FundoTelaGastos.png"))); // NOI18N
        getContentPane().add(jLabelFundoGastos);
        jLabelFundoGastos.setBounds(0, -20, 910, 540);

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jLabelAddTipoGastoMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabelAddTipoGastoMouseClicked
        // TODO add your handling code here:


    }//GEN-LAST:event_jLabelAddTipoGastoMouseClicked

    private void jLabelExcluirGastoMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabelExcluirGastoMouseClicked
        // TODO add your handling code here:


    }//GEN-LAST:event_jLabelExcluirGastoMouseClicked

    private void jLabelSalvarGastoMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabelSalvarGastoMouseClicked
        // TODO add your handling code here:


    }//GEN-LAST:event_jLabelSalvarGastoMouseClicked

    private void jTextFieldValorGastoKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jTextFieldValorGastoKeyTyped
        // TODO add your handling code here:

        char c = evt.getKeyChar();
        if (!Character.isDigit(c) && c != KeyEvent.VK_BACK_SPACE) {
            evt.consume();
        }

    }//GEN-LAST:event_jTextFieldValorGastoKeyTyped

    private void jTextFieldValorGastoKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jTextFieldValorGastoKeyReleased
        // TODO add your handling code here:

        formataTextFieldValor();

    }//GEN-LAST:event_jTextFieldValorGastoKeyReleased

    private void jLabelBuscarGastoMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabelBuscarGastoMouseClicked
        // TODO add your handling code here:
        
        
        
    }//GEN-LAST:event_jLabelBuscarGastoMouseClicked


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JComboBox<String> jComboBoxBuscarVeiculo;
    private javax.swing.JComboBox<String> jComboBoxTipoGasto;
    private com.toedter.calendar.JDateChooser jDateChooserDataFinal;
    private com.toedter.calendar.JDateChooser jDateChooserDataGasto;
    private com.toedter.calendar.JDateChooser jDateChooserDataInicial;
    private javax.swing.JLabel jLabelAddTipoGasto;
    private javax.swing.JLabel jLabelBuscarGasto;
    private javax.swing.JLabel jLabelExcluirGasto;
    private javax.swing.JLabel jLabelFundoGastos;
    private javax.swing.JLabel jLabelSalvarGasto;
    private javax.swing.JScrollPane jScroolPaneGasto;
    private javax.swing.JTable jTableGastos;
    private javax.swing.JTextField jTextFieldValorGasto;
    // End of variables declaration//GEN-END:variables

}
