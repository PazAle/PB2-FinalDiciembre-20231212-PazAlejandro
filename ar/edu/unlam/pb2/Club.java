package ar.edu.unlam.pb2;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;
import java.util.TreeMap;
import java.util.TreeSet;

import ar.edu.unlam.pb2.enums.Deporte;
import ar.edu.unlam.pb2.enums.Horario;
import ar.edu.unlam.pb2.enums.Paredes;
import ar.edu.unlam.pb2.exception.CanchaNoRegistradaException;
import ar.edu.unlam.pb2.exception.CanchaNoReservadaException;
import ar.edu.unlam.pb2.exception.CapacidadMaximaAlcanzadaException;
import ar.edu.unlam.pb2.exception.CodigoAfiliadoDuplicadoException;
import ar.edu.unlam.pb2.exception.DniExistenteException;
import ar.edu.unlam.pb2.exception.HorarioOcupadoException;
import ar.edu.unlam.pb2.exception.NumeroDeCanchaDuplicadoException;
import ar.edu.unlam.pb2.exception.PersonaNoAfiliadaException;
import ar.edu.unlam.pb2.interfaces.Afiliable;
import ar.edu.unlam.pb2.interfaces.Alquilable;

public class Club implements Afiliable {
	private String nombre;
	private Integer capacidad;

	private Set<Alquilable> canchas;
	private Map<String, Persona> afiliados;

	public Club(String nombre) {
		this.canchas = new HashSet<>();
		this.afiliados = new TreeMap<>();
	}

	public void agregarCancha(Alquilable cancha) throws NumeroDeCanchaDuplicadoException {
		Boolean canchaRegistrada = existeCancha(cancha);
		if(!canchaRegistrada) {
			this.getCanchas().add(cancha);
		} else {
			throw new NumeroDeCanchaDuplicadoException("El número de cancha que desea registrar ya se encuentra utilizado");
		}
	}

	private Boolean existeCancha(Alquilable cancha) {
		Boolean existe = false;
		Integer idCancha = ((Cancha)cancha).getNumero();
		for(Alquilable canchaBuscada: this.getCanchas()) {
			if(((Cancha)canchaBuscada).getNumero().equals(idCancha)){
				existe = true;
			}
		}
		return existe;
	}

	public Alquilable getCancha(Integer numeroCancha) {
		return null;
	}

	public Set<Alquilable> getCanchasDisponibles(Deporte deporte, Horario horario) throws HorarioOcupadoException {
		Set<Alquilable> canchas = new HashSet<>();
		for(Alquilable cancha: this.getCanchas()) {
			if(cancha.estaDisponible(horario)) {
				canchas.add(cancha);
			}
		}
		return canchas;
	}
	
	public Map<String, Double> obtenerTotalesPorCancha(){
		return null;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public Integer getCapacidad() {
		return capacidad;
	}

	public void setCapacidad(Integer capacidad) {
		this.capacidad = capacidad;
	}

	public Set<Alquilable> getCanchas() {
		return canchas;
	}

	public void setCanchas(Set<Alquilable> canchas) {
		this.canchas = canchas;
	}

	public Map<String, Persona> getAfiliados() {
		return afiliados;
	}

	public void setAfiliados(Map<String, Persona> afiliados) {
		this.afiliados = afiliados;
	}


	private void afiliarPersona(String codigo, Persona persona) {
		this.getAfiliados().put(codigo, persona);
		
	}

	private Boolean personaAfiliada(Persona persona) {
		Boolean estaAfiliada = false;
		if(this.getAfiliados().containsValue(persona)) {
			estaAfiliada = true;
		}
		return estaAfiliada;
	}

	public void alquilarCancha(Horario horario, Persona persona, Cancha cancha, Double senia) throws HorarioOcupadoException, PersonaNoAfiliadaException, CanchaNoRegistradaException{
		Boolean estaAfiliado = personaAfiliada(persona);
		Boolean existeCancha = existeCancha(cancha);
		if(estaAfiliado && existeCancha) {
			cancha.alquilar(horario, persona, senia);
		} else if(!estaAfiliado) {
			throw new PersonaNoAfiliadaException("La persona no está afiliada al club, por lo tanto, no puede alquilar una cancha");
		} else if(!existeCancha) {
			throw new CanchaNoRegistradaException("La cancha solicitada no existe");
		}
		
	}

	public Set<Persona> obtenerColeccionDeAfiliadosOrdenados() {
		Set<String> claves = this.getAfiliados().keySet();
		Set<Persona> personas = new TreeSet<>();
		
		for(String clave: claves) {
			personas.add(this.getAfiliados().get(clave));
		}
		return personas;
	}

	public Double verificarCuandoRestaAbonarPorUnaCanchaReservada(Cancha cancha, Horario horario) throws HorarioOcupadoException, CanchaNoReservadaException {
		Boolean estaReservada = cancha.estaReservada(horario);
		Double saldoAPagar = 0.0;
		if(estaReservada) {
			saldoAPagar = calcularSaldoAPagar(cancha, horario);
		} else {
			throw new CanchaNoReservadaException("La cancha no está reservada");
		}
		return saldoAPagar;
	}

	private Double calcularSaldoAPagar(Cancha cancha, Horario horario) {
		Double saldo = 0.0;
		for(Alquiler alquiler: cancha.getAlquileres()) {
			if(alquiler.getHorario().equals(horario)) {
				saldo = cancha.getPrecio() - alquiler.getSenia();
			}
		}
		return saldo;
	}

	public Map<String, Double> obtenerRecaudacionPoTipoDeCancha() {
	/*	Map<String, Double> totales = new HashMap<>();
		Double total = 0.0;
		String nombre = null;
		for(Alquilable cancha: this.getCanchas()) {
			total = obtenerTotalPorCancha(cancha);
			if(cancha instanceof CanchaFutbol) {
				totales.put(((Cancha)cancha).getDeporte() + ((CanchaFutbol)cancha).getCapacidad() + ((Cancha)cancha).getNumero(), total);
			}
			
		}
		*/
		return null;
	}

	private Double obtenerTotalPorCancha(Alquilable cancha) {
		Double total = 0.0;
		for(Alquiler alquiler: ((Cancha)cancha).getAlquileres()) {
			total += alquiler.getSenia();
		}
		return total;
	}

	@Override
	public void afiliar(String codigo, Persona persona)
			throws DniExistenteException, CodigoAfiliadoDuplicadoException, CapacidadMaximaAlcanzadaException {
		Boolean personaAfiliada = personaAfiliada(persona);
		Boolean codigoUtilizado = codigoUtilizado(codigo);
		Boolean capacidadMaximaAlcanzada = capacidadMaximaAlcanzada();
		if(personaAfiliada) {
			throw new DniExistenteException("El DNI de la persona que intenta agregar, ya se encuentra registrado");
		} else if(codigoUtilizado) {
			throw new CodigoAfiliadoDuplicadoException("El código que intenta utilizar ya está utilizado por otro afiliado");
		} else if(capacidadMaximaAlcanzada) {
			throw new CapacidadMaximaAlcanzadaException("Se alcanzó la capacidad máxima del Club");
		} else {
			afiliarPersona(codigo, persona);
		}
		
	}

	private Boolean capacidadMaximaAlcanzada() {
		Boolean capacidadMaximaAlcanzada = false;
		if(this.getAfiliados().size() == 500) {
			capacidadMaximaAlcanzada = true;
		}
		return capacidadMaximaAlcanzada;
	}

	private Boolean codigoUtilizado(String codigo) {
		Boolean codigoUtilizado = false;
		if(this.getAfiliados().containsKey(codigo)) {
			codigoUtilizado = true;
		}
		return codigoUtilizado;
	}

	@Override
	public Persona getAfiliado(Integer dni) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Persona getAfiliado(String codigoAfiliado) {
		Persona persona = null;
		if(this.getAfiliados().get(codigoAfiliado) != null) {
			persona = this.getAfiliados().get(codigoAfiliado);
		}
		return persona;
	}

	@Override
	public Boolean existeAfiliado(Integer dni) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Boolean existeAfiliado(String codigoAfiliado) {
		Boolean existe = false;
		if(this.getAfiliados().get(codigoAfiliado) != null) {
			existe = true;
		}
		return existe;
	}
	
	
}
