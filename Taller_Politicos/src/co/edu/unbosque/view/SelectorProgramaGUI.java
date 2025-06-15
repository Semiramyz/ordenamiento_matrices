package co.edu.unbosque.view;

import javax.swing.*;
import java.awt.*;

public class SelectorProgramaGUI extends JFrame {
    public JButton btnPoliticos, btnMatrices;

    public SelectorProgramaGUI() {
        setTitle("Seleccionar Programa");
        setSize(350, 150);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setLayout(new FlowLayout());

        btnPoliticos = new JButton("Análisis de Ordenamiento - Políticos");
        btnMatrices = new JButton("Ordenamiento de Matrices");

        add(btnPoliticos);
        add(btnMatrices);

        setVisible(true);
    }
}
