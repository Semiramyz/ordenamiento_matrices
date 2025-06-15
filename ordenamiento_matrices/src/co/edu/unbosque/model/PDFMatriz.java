package co.edu.unbosque.model;

import javax.swing.*;

import com.itextpdf.text.BaseColor;
import com.itextpdf.text.Chunk;
import com.itextpdf.text.Document;
import com.itextpdf.text.Element;
import com.itextpdf.text.Font;
import com.itextpdf.text.FontFactory;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.Phrase;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;

import java.io.File;
import java.io.FileOutputStream;
import java.util.Map;

public class PDFMatriz {

	public void generarReporte(Map<String, long[]> resultados) {
		if (resultados == null || resultados.isEmpty()) {
			JOptionPane.showMessageDialog(null, "No hay datos de análisis para generar el PDF.", "Advertencia",
					JOptionPane.WARNING_MESSAGE);
			return;
		}

		JFileChooser selectorArchivo = new JFileChooser();
		selectorArchivo.setDialogTitle("Guardar Reporte de Análisis");
		selectorArchivo.setSelectedFile(new File("ReporteAnalisisAlgoritmos.pdf"));

		int resultado = selectorArchivo.showSaveDialog(null);

		if (resultado == JFileChooser.APPROVE_OPTION) {
			String ruta = selectorArchivo.getSelectedFile().getAbsolutePath();
			if (!ruta.toLowerCase().endsWith(".pdf")) {
				ruta += ".pdf";
			}

			generarInterno(ruta, resultados);
		}
	}

	private void generarInterno(String ruta, Map<String, long[]> resultados) {
		Document doc = new Document();
		try {
			PdfWriter.getInstance(doc, new FileOutputStream(ruta));
			doc.open();

			Font tituloFont = FontFactory.getFont(FontFactory.HELVETICA_BOLD, 18);
			Paragraph titulo = new Paragraph("Análisis de Algoritmos de Ordenamiento", tituloFont);
			titulo.setAlignment(Element.ALIGN_CENTER);
			doc.add(titulo);
			doc.add(Chunk.NEWLINE);

			PdfPTable tabla = new PdfPTable(4);
			tabla.setWidthPercentage(100);
			tabla.setWidths(new int[] { 4, 4, 4, 4 });

			agregarCeldaEncabezado(tabla, "Algoritmo");
			agregarCeldaEncabezado(tabla, "Comparaciones");
			agregarCeldaEncabezado(tabla, "Intercambios");
			agregarCeldaEncabezado(tabla, "Tiempo (ms)");

			for (Map.Entry<String, long[]> entry : resultados.entrySet()) {
				tabla.addCell(entry.getKey());
				tabla.addCell(String.valueOf(entry.getValue()[0]));
				tabla.addCell(String.valueOf(entry.getValue()[1]));
				tabla.addCell(String.valueOf(entry.getValue()[2]));
			}

			doc.add(tabla);
			doc.close();

			JOptionPane.showMessageDialog(null, "PDF generado exitosamente:\n" + ruta, "Éxito",
					JOptionPane.INFORMATION_MESSAGE);
		} catch (Exception e) {
			e.printStackTrace();
			JOptionPane.showMessageDialog(null, "Error al generar el PDF:\n" + e.getMessage(), "Error",
					JOptionPane.ERROR_MESSAGE);
		}
	}

	private void agregarCeldaEncabezado(PdfPTable tabla, String texto) {
		Font font = FontFactory.getFont(FontFactory.HELVETICA_BOLD);
		PdfPCell celda = new PdfPCell(new Phrase(texto, font));
		celda.setBackgroundColor(BaseColor.LIGHT_GRAY);
		celda.setHorizontalAlignment(Element.ALIGN_CENTER);
		tabla.addCell(celda);
	}
}
