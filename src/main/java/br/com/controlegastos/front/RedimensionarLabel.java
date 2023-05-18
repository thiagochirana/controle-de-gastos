package br.com.controlegastos.front;

import java.awt.BorderLayout;
import java.awt.Dimension;
import javax.swing.JFrame;
import javax.swing.JLabel;

public class RedimensionarLabel {

    public static void main(String[] args) {
        // Criação da janela
        TelaLogin frame = new TelaLogin();
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        // Definição do layout
        frame.setLayout(new BorderLayout());

        // Criação da label
        JLabel label = new JLabel("Texto da Label");

        // Definição do tamanho desejado inicial
        Dimension tamanhoInicial = new Dimension(200, 100);
        label.setPreferredSize(tamanhoInicial);

        // Adiciona a label ao painel principal do frame
        frame.add(label, BorderLayout.CENTER);

        // Redimensiona a label de acordo com o tamanho da janela
        frame.addComponentListener(new java.awt.event.ComponentAdapter() {
            public void componentResized(java.awt.event.ComponentEvent evt) {
                Dimension tamanhoJanela = frame.getSize();
                label.setSize(tamanhoJanela);
            }
        });

        // Exibição da janela
        frame.pack();
        frame.setVisible(true);
    }
}
