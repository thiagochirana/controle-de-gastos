/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JInternalFrame.java to edit this template
 */
package br.com.controlegastos.front.view;

import java.awt.Color;
import java.awt.Graphics;
import java.beans.PropertyVetoException;
import javax.swing.JInternalFrame;
import javax.swing.JPanel;
import javax.swing.plaf.basic.BasicInternalFrameUI;
import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.data.category.DefaultCategoryDataset;

/**
 *
 * @author Rodrigo
 */
public class TelaControleGrafico extends javax.swing.JInternalFrame {

    /**
     * Creates new form TelaControleGrafico
     */
    public TelaControleGrafico() throws PropertyVetoException {
        super("Gráfico de Barras", true, true, true, true);

        initComponents();
        setBorder(javax.swing.BorderFactory.createEmptyBorder(0, 0, 0, 0));
        BasicInternalFrameUI bi = (BasicInternalFrameUI) this.getUI();
        bi.setNorthPane(null);
        setMaximum(true);

        setSize(910, 520);

        // Criação do painel para exibir o gráfico
        JPanel chartPanel = new JPanel() {
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);

                // Desenha o gráfico de barras
                int[] values = {10, 15, 20, 5};
                String[] labels = {"Item 1", "Item 2", "Item 3", "Item 4"};

                int width = getWidth();
                int height = getHeight();
                int barWidth = width / values.length;

                int maxValue = getMaxValue(values);

                for (int i = 0; i < values.length; i++) {
                    int barHeight = (int) ((double) values[i] / maxValue * height);

                    int x = i * barWidth;
                    int y = height - barHeight;

                    g.setColor(Color.BLUE);
                    g.fillRect(x, y, barWidth, barHeight);

                    g.setColor(Color.BLACK);
                    g.drawString(labels[i], x, height - 5);
                }
            }

            private int getMaxValue(int[] values) {
                int max = Integer.MIN_VALUE;
                for (int value : values) {
                    if (value > max) {
                        max = value;
                    }
                }
                return max;
            }
        };

        // Adiciona o painel do gráfico ao JInternalFrame
        getContentPane().add(chartPanel);
        setVisible(true);

    }

//    public void graficoInternal() {
//        super("Gráfico de Barras", true, true, true, true);
//        setSize(500, 400);
//
//        JInternalFrame internalFrame = new JInternalFrame("Gráfico de Barras", true, true, true, true);
//        internalFrame.setSize(500, 400);
//
//        // Criação do painel para exibir o gráfico
//        JPanel chartPanel = new JPanel() {
//            @Override
//            protected void paintComponent(Graphics g) {
//                super.paintComponent(g);
//
//                // Desenha o gráfico de barras
//                int[] values = {10, 15, 20, 5};
//                String[] labels = {"Item 1", "Item 2", "Item 3", "Item 4"};
//
//                int width = getWidth();
//                int height = getHeight();
//                int barWidth = width / values.length;
//
//                int maxValue = getMaxValue(values);
//
//                for (int i = 0; i < values.length; i++) {
//                    int barHeight = (int) ((double) values[i] / maxValue * height);
//
//                    int x = i * barWidth;
//                    int y = height - barHeight;
//
//                    g.setColor(Color.BLUE);
//                    g.fillRect(x, y, barWidth, barHeight);
//
//                    g.setColor(Color.BLACK);
//                    g.drawString(labels[i], x, height - 5);
//                }
//            }
//
//            private int getMaxValue(int[] values) {
//                int max = Integer.MIN_VALUE;
//                for (int value : values) {
//                    if (value > max) {
//                        max = value;
//                    }
//                }
//                return max;
//            }
//        };
//
//        // Adiciona o painel do gráfico ao JInternalFrame
//        internalFrame.getContentPane().add(chartPanel);
//        internalFrame.setVisible(true);
//
//    }
//    public void criarGrafico() {
//        DefaultCategoryDataset barra = new DefaultCategoryDataset();
//        barra.addValue(200, "Hugo Android", "");
//        barra.addValue(300, "Hugo Iphone", "");
//
//        JFreeChart grafico = ChartFactory.createBarChart("A", "B", "C", barra, PlotOrientation.VERTICAL, true, true, true);
//        ChartPanel painel = new ChartPanel(grafico);
//        add(painel);
//    }
    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        setBorder(null);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 910, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 496, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents


    // Variables declaration - do not modify//GEN-BEGIN:variables
    // End of variables declaration//GEN-END:variables
}
