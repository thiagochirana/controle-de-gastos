/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JInternalFrame.java to edit this template
 */
package br.com.controlegastos.front.view;

import br.com.controlegastos.controle.ProprietarioController;
import br.com.controlegastos.entidades.records.DadosCadastroProprietario;
import br.com.controlegastos.entidades.records.DadosRespostaCadastroProprietario;
import br.com.controlegastos.front.modal.ModalMensagem;
import java.awt.Color;
import java.beans.PropertyVetoException;
import javax.swing.JFormattedTextField;
import javax.swing.plaf.basic.BasicInternalFrameUI;
import javax.swing.text.MaskFormatter;
import javax.swing.text.NumberFormatter;

/**
 *
 * @author Rodrigo, Chirana
 */
public class TelaProprietario extends javax.swing.JInternalFrame {
    
    ModalMensagem msg = new ModalMensagem();
    ProprietarioController prop = new ProprietarioController();

    /**
     * Creates new form TelaVeiculo
     */
    public TelaProprietario() throws PropertyVetoException {
        initComponents();

        formataTextFieldCPF();
        formataTextFieldTelefone();
        estilizaFields();

        setBorder(javax.swing.BorderFactory.createEmptyBorder(0, 0, 0, 0));
        BasicInternalFrameUI bi = (BasicInternalFrameUI) this.getUI();
        bi.setNorthPane(null);
        setMaximum(true);
        
        validaProprietarioExiste();
    }

    private void estilizaFields() {

        jLabelProprietario.setOpaque(false);
        jLabelProprietario.setBackground(new Color(0, 0, 0, 0));
        jFormattedTextFieldCNH.setOpaque(false);
        jFormattedTextFieldCNH.setBackground(new Color(0, 0, 0, 0));
        jComboBoxCategoriaCNH.setOpaque(false);
        jComboBoxCategoriaCNH.setBackground(new Color(0, 0, 0, 0));
        jFormattedTextFieldTelefone.setOpaque(false);
        jFormattedTextFieldTelefone.setBackground(new Color(0, 0, 0, 0));
        jTextFieldEmail.setOpaque(false);
        jTextFieldEmail.setBackground(new Color(0, 0, 0, 0));
        jTextFieldNome.setOpaque(false);
        jTextFieldNome.setBackground(new Color(0, 0, 0, 0));
        jFormattedTextFieldCPF.setOpaque(false);
        jFormattedTextFieldCPF.setBackground(new Color(0, 0, 0, 0));

    }

    private void formataTextFieldTelefone() {

        try {
            MaskFormatter telefoneFormatter = new MaskFormatter("(##)#####-####");
            JFormattedTextField jFormattedTextFieldTelefone = new JFormattedTextField(telefoneFormatter);
            // Configurar o tamanho máximo do campo
            jFormattedTextFieldTelefone.setColumns(14);

            // Restringir a entrada apenas para números
            jFormattedTextFieldTelefone.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.NumberFormatter()));
        } catch (java.text.ParseException ex) {
            ex.printStackTrace();
        }

    }

    private void formataTextFieldCPF() {

        try {
            MaskFormatter cpfFormatter = new MaskFormatter("###.###.###-##");
            JFormattedTextField jFormattedTextFieldCPF = new JFormattedTextField(cpfFormatter);
            // Configurar o tamanho máximo do campo
            jFormattedTextFieldCPF.setColumns(14);

            // Restringir a entrada apenas para números
            jFormattedTextFieldCPF.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.NumberFormatter()));
        } catch (java.text.ParseException ex) {
            ex.printStackTrace();
        }

    }

    private void formataTextFieldCNH() {

        try {

            MaskFormatter maskFormatter = new MaskFormatter("##########");
            maskFormatter.setValidCharacters("0123456789");

            JFormattedTextField textField = new JFormattedTextField(new NumberFormatter());
            textField.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(maskFormatter));
        } catch (java.text.ParseException ex) {
            ex.printStackTrace();
        }
    }

    //
    public boolean validaProprietarioExiste(){
        try{
            boolean valida = prop.verificaSePropriatarioExiste();
            if (!valida){
                msg.exibirMensagem("Por favor preencha os campos com sua informação", false);
            }
            return valida;
        } catch (Exception e){
            msg.exibirMensagem(e.getMessage(), false);
            return false;
        } 
    }
    
    
    public void cadastrarProprietario(DadosCadastroProprietario dados){
        try{
            DadosRespostaCadastroProprietario resp = prop.cadastrarProprietario(dados);
            msg.exibirMensagem(resp.mensagem(), resp.cadastrou());
        } catch(Exception e){
            msg.exibirMensagem(e.getMessage(), false);
        }
    }
            
    
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jFormattedTextFieldCPF = new javax.swing.JFormattedTextField();
        jFormattedTextFieldCNH = new javax.swing.JFormattedTextField();
        jLabelButtonSalvar = new javax.swing.JLabel();
        jTextFieldEmail = new javax.swing.JTextField();
        jTextFieldNome = new javax.swing.JTextField();
        jLabelButtonEditar = new javax.swing.JLabel();
        jComboBoxCategoriaCNH = new javax.swing.JComboBox<>();
        jFormattedTextFieldTelefone = new javax.swing.JFormattedTextField();
        jLabelProprietario = new javax.swing.JLabel();

        setBorder(null);
        getContentPane().setLayout(null);

        jFormattedTextFieldCPF.setBorder(null);
        try {
            jFormattedTextFieldCPF.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.MaskFormatter("###.###.###-##")));
        } catch (java.text.ParseException ex) {
            ex.printStackTrace();
        }
        getContentPane().add(jFormattedTextFieldCPF);
        jFormattedTextFieldCPF.setBounds(190, 149, 220, 20);

        jFormattedTextFieldCNH.setBorder(null);
        try {
            jFormattedTextFieldCNH.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.MaskFormatter("##########")));
        } catch (java.text.ParseException ex) {
            ex.printStackTrace();
        }
        getContentPane().add(jFormattedTextFieldCNH);
        jFormattedTextFieldCNH.setBounds(640, 103, 190, 16);

        jLabelButtonSalvar.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabelButtonSalvarMouseClicked(evt);
            }
        });
        getContentPane().add(jLabelButtonSalvar);
        jLabelButtonSalvar.setBounds(730, 440, 150, 40);

        jTextFieldEmail.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jTextFieldEmail.setBorder(null);
        getContentPane().add(jTextFieldEmail);
        jTextFieldEmail.setBounds(645, 197, 185, 25);

        jTextFieldNome.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jTextFieldNome.setBorder(null);
        getContentPane().add(jTextFieldNome);
        jTextFieldNome.setBounds(192, 100, 215, 25);

        jLabelButtonEditar.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabelButtonEditarMouseClicked(evt);
            }
        });
        getContentPane().add(jLabelButtonEditar);
        jLabelButtonEditar.setBounds(570, 440, 150, 40);

        jComboBoxCategoriaCNH.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "A", "B", "AB", "C", "D", "E" }));
        getContentPane().add(jComboBoxCategoriaCNH);
        jComboBoxCategoriaCNH.setBounds(640, 150, 190, 22);

        jFormattedTextFieldTelefone.setBorder(null);
        try {
            jFormattedTextFieldTelefone.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.MaskFormatter("(##)#####-####")));
        } catch (java.text.ParseException ex) {
            ex.printStackTrace();
        }
        getContentPane().add(jFormattedTextFieldTelefone);
        jFormattedTextFieldTelefone.setBounds(190, 198, 220, 20);

        jLabelProprietario.setIcon(new javax.swing.ImageIcon(getClass().getResource("/br/com/controlegastos/front/image/FundoTelaProprietario.png"))); // NOI18N
        getContentPane().add(jLabelProprietario);
        jLabelProprietario.setBounds(0, -4, 910, 500);

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jLabelButtonEditarMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabelButtonEditarMouseClicked
        // TODO add your handling code here:


    }//GEN-LAST:event_jLabelButtonEditarMouseClicked

    private void jLabelButtonSalvarMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabelButtonSalvarMouseClicked
        try{
            String nome = jTextFieldNome.getText();
            String cpf = jFormattedTextFieldCPF.getText().replace(".","").replace("-","");
            String telefone = jFormattedTextFieldTelefone.getText().replace("(","").replace(")","").replace(" ","").replace("-","");
            String cnh = jFormattedTextFieldCNH.getText();
            String catCNH = jComboBoxCategoriaCNH.getSelectedItem().toString();
            String email = jTextFieldEmail.getText();
            
            cadastrarProprietario(new DadosCadastroProprietario(
                    cpf,
                    nome,
                    telefone,
                    email,
                    cnh,
                    catCNH
            ));
            
        } catch(Exception e){
            msg.exibirMensagem(e.getMessage(), false);
        }
    }//GEN-LAST:event_jLabelButtonSalvarMouseClicked


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JComboBox<String> jComboBoxCategoriaCNH;
    private javax.swing.JFormattedTextField jFormattedTextFieldCNH;
    private javax.swing.JFormattedTextField jFormattedTextFieldCPF;
    private javax.swing.JFormattedTextField jFormattedTextFieldTelefone;
    private javax.swing.JLabel jLabelButtonEditar;
    private javax.swing.JLabel jLabelButtonSalvar;
    private javax.swing.JLabel jLabelProprietario;
    private javax.swing.JTextField jTextFieldEmail;
    private javax.swing.JTextField jTextFieldNome;
    // End of variables declaration//GEN-END:variables

}
