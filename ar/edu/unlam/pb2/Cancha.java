package ar.edu.unlam.pb2;

import java.util.HashSet;
import java.util.Set;

import ar.edu.unlam.pb2.enums.Deporte;
import ar.edu.unlam.pb2.enums.Horario;
import ar.edu.unlam.pb2.enums.Piso;
import ar.edu.unlam.pb2.exception.HorarioOcupadoException;
import ar.edu.unlam.pb2.interfaces.Alquilable;

public abstract class Cancha implements Alquilable {

	private Integer numero;
	private Set<Alquiler> alquileres;
	private Deporte deporte;
	private Double precio;

	public Cancha(Integer numero, Deporte deporte, Double precio) {
		this.numero = numero;
		this.deporte = deporte;
		this.precio = precio;
		this.alquileres = new HashSet<>();
	}

	public void alquilar(Horario horario, Persona afiliado, Double senia) throws HorarioOcupadoException {
		Boolean estaDisponible = estaDisponible(horario);
		if(estaDisponible) {
			this.getAlquileres().add(new Alquiler(horario, afiliado, senia));
		}
	}

	public Boolean estaDisponible(Horario horario) throws HorarioOcupadoException {
		Boolean estaDisponible = true;
		for(Alquiler alquiler: this.getAlquileres()) {
			if(alquiler.getHorario().equals(horario)) {
				throw new HorarioOcupadoException("El horario solicitado no está disponible");
			}
		}
		return estaDisponible;
	}

	public Integer getNumero() {
		return numero;
	}

	public void setNumero(Integer numero) {
		this.numero = numero;
	}

	public Set<Alquiler> getAlquileres() {
		return alquileres;
	}

	public void setAlquileres(Set<Alquiler> alquileres) {
		this.alquileres = alquileres;
	}

	public Deporte getDeporte() {
		return deporte;
	}

	public void setDeporte(Deporte deporte) {
		this.deporte = deporte;
	}

	public Double getPrecio() {
		return precio;
	}

	public void setPrecio(Double precio) {
		this.precio = precio;
	}

	public Boolean estaReservada(Horario horario) {
		Boolean estaReservada = false;
		for(Alquiler alquiler: this.getAlquileres()) {
			if(alquiler.getHorario().equals(horario)) {
				estaReservada = true;
			}
		}
		return estaReservada;
	}

	
}
