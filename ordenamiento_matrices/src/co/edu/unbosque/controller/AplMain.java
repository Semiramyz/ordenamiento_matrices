package co.edu.unbosque.controller;

import co.edu.unbosque.view.BubbleSortGUI;

public class AplMain {
	public static void main(String[] args) {
		BubbleSortGUI b = new BubbleSortGUI();
		Controller c = new Controller(b);

		b.setVisible(true);
	}
}
