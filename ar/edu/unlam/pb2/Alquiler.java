package ar.edu.unlam.pb2;

import ar.edu.unlam.pb2.enums.Horario;

public class Alquiler {

	private Horario horario;
	private Persona responsable;
	private Double senia;

	public Alquiler(Horario horario, Persona responsable, Double senia) {
		this.horario = horario;
		this.responsable = responsable;
		this.senia = senia;
	}

	public Horario getHorario() {
		return horario;
	}

	public void setHorario(Horario horario) {
		this.horario = horario;
	}

	public Persona getResponsable() {
		return responsable;
	}

	public void setResponsable(Persona responsable) {
		this.responsable = responsable;
	}

	public Double getSenia() {
		return senia;
	}

	public void setSenia(Double senia) {
		this.senia = senia;
	}
	
	
}
