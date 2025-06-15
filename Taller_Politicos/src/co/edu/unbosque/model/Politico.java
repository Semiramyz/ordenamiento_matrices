package co.edu.unbosque.model;

import java.time.LocalDate;
import java.time.Period;

public class Politico {
    private int id;
    private int dineroRobado;
    private LocalDate fechaNacimiento;

    public Politico(int id, int dineroRobado, LocalDate fechaNacimiento) {
        this.id = id;
        this.dineroRobado = dineroRobado;
        this.fechaNacimiento = fechaNacimiento;
    }

    public int getId() { return id; }
    public void setId(int id) { this.id = id; }
    public int getDineroRobado() { return dineroRobado; }
    public void setDineroRobado(int dineroRobado) { this.dineroRobado = dineroRobado; }
    public LocalDate getFechaNacimiento() { return fechaNacimiento; }
    public void setFechaNacimiento(LocalDate fechaNacimiento) { this.fechaNacimiento = fechaNacimiento; }

    public int getEdad() {
        return Period.between(fechaNacimiento, LocalDate.now()).getYears();
    }

    @Override
    public String toString() {
        return id + " | $" + dineroRobado + " | " + fechaNacimiento;
    }
}
