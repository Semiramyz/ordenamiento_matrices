package co.edu.unbosque.controller;

import co.edu.unbosque.view.SelectorProgramaGUI;
import co.edu.unbosque.view.BubbleSortGUI;
import javax.swing.*;

public class AplMain {
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            SelectorProgramaGUI selector = new SelectorProgramaGUI();

            selector.btnPoliticos.addActionListener(e -> {
                selector.dispose();
                new PoliticosController();
            });

            selector.btnMatrices.addActionListener(e -> {
                selector.dispose();
                BubbleSortGUI gui = new BubbleSortGUI();
                gui.setVisible(true); // Asegura que la ventana sea visible
                new Controller(gui);
            });
        });
    }
    //aaa
}
