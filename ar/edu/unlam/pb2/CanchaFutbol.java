package ar.edu.unlam.pb2;

import ar.edu.unlam.pb2.enums.Capacidad;
import ar.edu.unlam.pb2.enums.Deporte;
import ar.edu.unlam.pb2.enums.Piso;

public class CanchaFutbol extends Cancha{
	
	private Capacidad capacidad;
	private Piso piso;

	public CanchaFutbol(Integer numero, Deporte deporte, Double precio, Capacidad capacidad, Piso piso) {
		super(numero, deporte, precio);
		this.capacidad = capacidad;
		this.piso = piso;
	}

	public Capacidad getCapacidad() {
		return capacidad;
	}

	public void setCapacidad(Capacidad capacidad) {
		this.capacidad = capacidad;
	}

	public Piso getPiso() {
		return piso;
	}

	public void setPiso(Piso piso) {
		this.piso = piso;
	}

	
}
