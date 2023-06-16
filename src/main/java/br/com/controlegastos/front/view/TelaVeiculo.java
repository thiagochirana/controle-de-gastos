/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JInternalFrame.java to edit this template
 */
package br.com.controlegastos.front.view;

import java.beans.PropertyVetoException;
import javax.swing.plaf.basic.BasicInternalFrameUI;

/**
 *
 * @author Rodrigo
 */
public class TelaVeiculo extends javax.swing.JInternalFrame {

    /**
     * Creates new form TelaVeiculo
     */
    public TelaVeiculo() throws PropertyVetoException {
        initComponents();
        setBorder(javax.swing.BorderFactory.createEmptyBorder(0, 0, 0, 0));
        BasicInternalFrameUI bi = (BasicInternalFrameUI)this.getUI();
        bi.setNorthPane(null);
        setMaximum(true);        
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabelVeiculo = new javax.swing.JLabel();

        setBorder(null);
        getContentPane().setLayout(null);

        jLabelVeiculo.setIcon(new javax.swing.ImageIcon(getClass().getResource("/br/com/controlegastos/front/image/FundoTelaVeiculo.png"))); // NOI18N
        getContentPane().add(jLabelVeiculo);
        jLabelVeiculo.setBounds(0, -1, 910, 500);

        pack();
    }// </editor-fold>//GEN-END:initComponents

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel jLabelVeiculo;
    // End of variables declaration//GEN-END:variables
}