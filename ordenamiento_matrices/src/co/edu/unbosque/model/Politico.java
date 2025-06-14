package co.edu.unbosque.model;

import java.time.LocalDate;
import java.time.Period;
import java.time.format.DateTimeFormatter;

public class Politico {
	private int id;
	private int dineroRobado;
	private LocalDate fechaNacimiento;

	public Politico(int id, int dineroRobado, LocalDate fechaNacimiento) {
		this.id = id;
		this.dineroRobado = dineroRobado;
		this.fechaNacimiento = fechaNacimiento;
	}

	public int getId() {
		return id;
	}

	public int getDineroRobado() {
		return dineroRobado;
	}

	public LocalDate getFechaNacimiento() {
		return fechaNacimiento;
	}

	public int getEdad() {
		return Period.between(this.fechaNacimiento, LocalDate.now()).getYears();
	}

	@Override
	public String toString() {
		return String.format("[ID:%02d $:%d Edad:%d (%s)]", id, dineroRobado, getEdad(),
				fechaNacimiento.format(DateTimeFormatter.ofPattern("dd-MM-yyyy")));
	}
}
