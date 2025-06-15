package co.edu.unbosque.view;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;

public class PoliticosGUI extends JFrame {

    public JButton btnGenerar, btnOrdenar;
    public JComboBox<String> comboAlgoritmo, comboTipoArreglo;
    public JTable tablaPoliticos;
    public JLabel lblComparaciones, lblIntercambios, lblTiempo;
    public DefaultTableModel modeloTabla;
    public JSpinner spinnerCantidad;

    public PoliticosGUI() {
        setTitle("Análisis de Ordenamiento - Políticos");
        setSize(800, 500);
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        setLocationRelativeTo(null);
        setLayout(new BorderLayout());

        JPanel panelTop = new JPanel();

        spinnerCantidad = new JSpinner(new SpinnerNumberModel(10, 1, 1000, 1));
        panelTop.add(new JLabel("Cantidad de Políticos:"));
        panelTop.add(spinnerCantidad);

        btnGenerar = new JButton("Generar Datos");
        comboAlgoritmo = new JComboBox<>(new String[]{
            "Bubble Sort", "Selection Sort", "Insertion Sort", "Merge Sort", "Quick Sort"
        });
        comboTipoArreglo = new JComboBox<>(new String[]{
            "Desordenado", "Parcialmente Ordenado", "Orden Inverso"
        });
        btnOrdenar = new JButton("Ordenar");

        panelTop.add(btnGenerar);
        panelTop.add(comboAlgoritmo);
        panelTop.add(comboTipoArreglo);
        panelTop.add(btnOrdenar);
        add(panelTop, BorderLayout.NORTH);

        modeloTabla = new DefaultTableModel(new String[]{"ID", "Dinero Robado", "Fecha Nacimiento", "Edad"}, 0);
        tablaPoliticos = new JTable(modeloTabla);
        add(new JScrollPane(tablaPoliticos), BorderLayout.CENTER);

        JPanel panelBottom = new JPanel();
        lblComparaciones = new JLabel("Comparaciones: ");
        lblIntercambios = new JLabel("Intercambios: ");
        lblTiempo = new JLabel("Tiempo: ");
        panelBottom.add(lblComparaciones);
        panelBottom.add(lblIntercambios);
        panelBottom.add(lblTiempo);
        add(panelBottom, BorderLayout.SOUTH);

        setVisible(true);
    }
}
