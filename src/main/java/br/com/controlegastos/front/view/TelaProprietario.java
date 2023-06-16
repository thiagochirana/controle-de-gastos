/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JInternalFrame.java to edit this template
 */
package br.com.controlegastos.front.view;

import java.awt.Color;
import java.beans.PropertyVetoException;
import javax.swing.plaf.basic.BasicInternalFrameUI;

/**
 *
 * @author Rodrigo
 */
public class TelaProprietario extends javax.swing.JInternalFrame {

    /**
     * Creates new form TelaVeiculo
     */
    public TelaProprietario() throws PropertyVetoException {
        initComponents();

        setBorder(javax.swing.BorderFactory.createEmptyBorder(0, 0, 0, 0));
        BasicInternalFrameUI bi = (BasicInternalFrameUI) this.getUI();
        bi.setNorthPane(null);
        setMaximum(true);

        jLabelProprietario.setOpaque(false);
        jLabelProprietario.setBackground(new Color(0, 0, 0, 0));
        jTextFieldCNH.setOpaque(false);
        jTextFieldCNH.setBackground(new Color(0, 0, 0, 0));
        jTextFieldCPF.setOpaque(false);
        jTextFieldCPF.setBackground(new Color(0, 0, 0, 0));
        jTextFieldDataNascimento.setOpaque(false);
        jTextFieldDataNascimento.setBackground(new Color(0, 0, 0, 0));
        jTextFieldTelefone.setOpaque(false);
        jTextFieldTelefone.setBackground(new Color(0, 0, 0, 0));
        jTextFieldEmail.setOpaque(false);
        jTextFieldEmail.setBackground(new Color(0, 0, 0, 0));
        jTextFieldNome.setOpaque(false);
        jTextFieldNome.setBackground(new Color(0, 0, 0, 0));
        jTextFieldEndereco.setOpaque(false);
        jTextFieldEndereco.setBackground(new Color(0, 0, 0, 0));
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jTextFieldEndereco = new javax.swing.JTextField();
        jTextFieldEmail = new javax.swing.JTextField();
        jTextFieldCPF = new javax.swing.JTextField();
        jTextFieldDataNascimento = new javax.swing.JTextField();
        jTextFieldNome = new javax.swing.JTextField();
        jTextFieldCNH = new javax.swing.JTextField();
        jTextFieldTelefone = new javax.swing.JTextField();
        jLabelProprietario = new javax.swing.JLabel();

        setBorder(null);
        getContentPane().setLayout(null);

        jTextFieldEndereco.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jTextFieldEndereco.setBorder(null);
        getContentPane().add(jTextFieldEndereco);
        jTextFieldEndereco.setBounds(250, 310, 610, 30);

        jTextFieldEmail.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jTextFieldEmail.setBorder(null);
        getContentPane().add(jTextFieldEmail);
        jTextFieldEmail.setBounds(680, 260, 180, 30);

        jTextFieldCPF.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jTextFieldCPF.setBorder(null);
        getContentPane().add(jTextFieldCPF);
        jTextFieldCPF.setBounds(250, 210, 220, 30);

        jTextFieldDataNascimento.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jTextFieldDataNascimento.setBorder(null);
        getContentPane().add(jTextFieldDataNascimento);
        jTextFieldDataNascimento.setBounds(250, 260, 220, 30);

        jTextFieldNome.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jTextFieldNome.setBorder(null);
        getContentPane().add(jTextFieldNome);
        jTextFieldNome.setBounds(250, 160, 220, 30);

        jTextFieldCNH.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jTextFieldCNH.setBorder(null);
        getContentPane().add(jTextFieldCNH);
        jTextFieldCNH.setBounds(680, 160, 180, 30);

        jTextFieldTelefone.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jTextFieldTelefone.setBorder(null);
        getContentPane().add(jTextFieldTelefone);
        jTextFieldTelefone.setBounds(680, 210, 180, 30);

        jLabelProprietario.setIcon(new javax.swing.ImageIcon(getClass().getResource("/br/com/controlegastos/front/image/FundoTelaProprietario.png"))); // NOI18N
        getContentPane().add(jLabelProprietario);
        jLabelProprietario.setBounds(0, -4, 910, 500);

        pack();
    }// </editor-fold>//GEN-END:initComponents


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel jLabelProprietario;
    private javax.swing.JTextField jTextFieldCNH;
    private javax.swing.JTextField jTextFieldCPF;
    private javax.swing.JTextField jTextFieldDataNascimento;
    private javax.swing.JTextField jTextFieldEmail;
    private javax.swing.JTextField jTextFieldEndereco;
    private javax.swing.JTextField jTextFieldNome;
    private javax.swing.JTextField jTextFieldTelefone;
    // End of variables declaration//GEN-END:variables

}