package ar.edu.unlam.pb2.test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.TreeSet;

import org.junit.Test; 

import ar.edu.unlam.pb2.Cancha;
import ar.edu.unlam.pb2.CanchaFutbol;
import ar.edu.unlam.pb2.CanchaPadel;
import ar.edu.unlam.pb2.Club;
import ar.edu.unlam.pb2.Persona;
import ar.edu.unlam.pb2.enums.Capacidad;
import ar.edu.unlam.pb2.enums.Deporte;
import ar.edu.unlam.pb2.enums.Horario;
import ar.edu.unlam.pb2.enums.Paredes;
import ar.edu.unlam.pb2.enums.Piso;
import ar.edu.unlam.pb2.exception.CanchaNoRegistradaException;
import ar.edu.unlam.pb2.exception.CanchaNoReservadaException;
import ar.edu.unlam.pb2.exception.CapacidadMaximaAlcanzadaException;
import ar.edu.unlam.pb2.exception.CodigoAfiliadoDuplicadoException;
import ar.edu.unlam.pb2.exception.DniExistenteException;
import ar.edu.unlam.pb2.exception.HorarioOcupadoException;
import ar.edu.unlam.pb2.exception.NumeroDeCanchaDuplicadoException;
import ar.edu.unlam.pb2.exception.PersonaNoAfiliadaException;
import ar.edu.unlam.pb2.interfaces.Alquilable;

public class TestCases {
	/*
	 * Se pide realizar un sistema para Club Deportivo UNLAM. El club cuenta con
	 * capacidad para 500 afiliados No pueden existir varios afiliados con el mismo
	 * codigo, o mismo DNI. Los afiliados pueden aprovechar las instalaciones para
	 * jugar Padel o Futbol. Para ello es necesario alquilar una cancha. Se debe
	 * abonar entre el 20 y el 80 % del costo del alquiler de la cancha, a modo de
	 * reserva. Solo un afiliado al club puede alquilarlas. Al finalizar el horario,
	 * el afiliado debe abonar el saldo restante y la cancha pasa a estar disponible
	 * en ese horario. Las canchas de Padel pueden ser de piso sintetico o cemento,
	 * y pueden tener paredes vidriadas o de cemento Las canchas de futbol pueden
	 * ser de piso sintetico o cemento, y su capacidad para 5, 7 u 11 jugadores por
	 * equipo. No se debe permitir alquilar una cancha en un horario no disponible.
	 * 
	 */

	@Test
	public void queSePuedaAgregarUnaCanchaDePadel() {
		String nombreClub = "Club Deportivo UNLaM";
		Integer idCancha = 1;
		Deporte deporte = Deporte.PADEL;
		Double precioCancha = 1000.00;
		Paredes paredesCancha = Paredes.VIDRIADA;
		
		Club clubDeportivoUNLaM = new Club(nombreClub);
		Cancha canchaPadel = new CanchaPadel(idCancha, deporte, precioCancha, paredesCancha);
		
		try {
			clubDeportivoUNLaM.agregarCancha(canchaPadel);
		} catch (NumeroDeCanchaDuplicadoException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		Integer valorEsperado = 1;
		Integer valorObtenido = clubDeportivoUNLaM.getCanchas().size();
		
		assertEquals(valorEsperado, valorObtenido);
	}

	@Test(expected = NumeroDeCanchaDuplicadoException.class)
	public void queAlIntentarAgregarUnaCanchaConUnNumeroExistenteSeLanceUnaNumeroDeCanchaDuplicadoException() throws NumeroDeCanchaDuplicadoException {
		String nombreClub = "Club Deportivo UNLaM";
		Integer idCancha = 1;
		Deporte deporte = Deporte.PADEL;
		Double precioCancha = 1000.00;
		Paredes paredesCancha = Paredes.VIDRIADA;
		
		Club clubDeportivoUNLaM = new Club(nombreClub);
		Cancha canchaPadel = new CanchaPadel(idCancha, deporte, precioCancha, paredesCancha);
		
		try {
			clubDeportivoUNLaM.agregarCancha(canchaPadel);
			clubDeportivoUNLaM.agregarCancha(canchaPadel);
		} catch (NumeroDeCanchaDuplicadoException e) {
			// TODO Auto-generated catch block
			System.out.println(e.getMessage());
			throw new NumeroDeCanchaDuplicadoException();
		}
		
		Integer valorEsperado = 1;
		Integer valorObtenido = clubDeportivoUNLaM.getCanchas().size();
		
	}

	@Test
	public void queSePuedaConsultarLasCanchasDePadelDisponibles() {
		String nombreClub = "Club Deportivo UNLaM";
		Integer id1 = 1, id2 = 2, id3 =3, id4 =4;
		Deporte deporte = Deporte.PADEL;
		Double precioCancha = 1000.00;
		Paredes paredesCancha = Paredes.VIDRIADA;
		
		Club clubDeportivoUNLaM = new Club(nombreClub);
		Cancha canchaPadel1 = new CanchaPadel(id1, deporte, precioCancha, paredesCancha);
		Cancha canchaPadel2 = new CanchaPadel(id2, deporte, precioCancha, paredesCancha);
		Cancha canchaPadel3 = new CanchaPadel(id3, deporte, precioCancha, paredesCancha);
		Cancha canchaPadel4 = new CanchaPadel(id4, deporte, precioCancha, paredesCancha);
		
		try {
			clubDeportivoUNLaM.agregarCancha(canchaPadel1);
			clubDeportivoUNLaM.agregarCancha(canchaPadel2);
			clubDeportivoUNLaM.agregarCancha(canchaPadel3);
			clubDeportivoUNLaM.agregarCancha(canchaPadel4);
			
			
		} catch (NumeroDeCanchaDuplicadoException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			
		}
		Set<Alquilable> canchasDisponibles = new HashSet<>();
		try {
			canchasDisponibles = clubDeportivoUNLaM.getCanchasDisponibles(deporte, Horario.BLOQUE1);
		} catch (HorarioOcupadoException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		Integer valorEsperado = 4;
		Integer valorObtenido = canchasDisponibles.size();
		
		assertEquals(valorEsperado, valorObtenido);
	}

	@Test
	public void queUnaPersonaSePuedaAfiliarAlClub() {
		String nombreClub = "Club Deportivo UNLaM", nombrePersona = "Alejandro", apellidoPersona = "Paz", codigoAfiliado = "AA001";
		Integer id1 = 1, id2 = 2, id3 =3, id4 =4, dni = 34567754;
		Deporte deporte = Deporte.PADEL;
		Double precioCancha = 1000.00;
		Boolean estaDisponible = null;
		Paredes paredesCancha = Paredes.VIDRIADA;
		
		Club clubDeportivoUNLaM = new Club(nombreClub);
		Cancha canchaPadel1 = new CanchaPadel(id1, deporte, precioCancha, paredesCancha);
		Persona alejandro = new Persona(dni, nombrePersona, apellidoPersona);
		
		try {
			clubDeportivoUNLaM.afiliar(codigoAfiliado, alejandro);
			clubDeportivoUNLaM.agregarCancha(canchaPadel1);
			
			
		} catch (NumeroDeCanchaDuplicadoException | DniExistenteException |
				CodigoAfiliadoDuplicadoException | CapacidadMaximaAlcanzadaException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			
		}
		
		Integer valorEsperado = 1;
		Integer valorObtenido = clubDeportivoUNLaM.getAfiliados().size();
		
		assertEquals(valorEsperado, valorObtenido);
	}

	@Test
	public void queUnAfiliadoPuedaAlquilarUnaCanchaDePadel() {
		String nombreClub = "Club Deportivo UNLaM", nombrePersona = "Alejandro", apellidoPersona = "Paz", codigoAfiliado = "AA001";
		Integer id1 = 1, id2 = 2, id3 =3, id4 =4, dni = 34567754;
		Deporte deporte = Deporte.PADEL;
		Double precioCancha = 1000.00, senia = 500.00;
		Paredes paredesCancha = Paredes.VIDRIADA;
		Horario horarioReservado = Horario.BLOQUE1;

		Club clubDeportivoUNLaM = new Club(nombreClub);
		Cancha canchaPadel1 = new CanchaPadel(id1, deporte, precioCancha, paredesCancha);
		Persona alejandro = new Persona(dni, nombrePersona, apellidoPersona);
		
		try {
			clubDeportivoUNLaM.afiliar(codigoAfiliado, alejandro);
			clubDeportivoUNLaM.agregarCancha(canchaPadel1);
			
			clubDeportivoUNLaM.alquilarCancha(horarioReservado, alejandro, canchaPadel1, senia);
			
		} catch (NumeroDeCanchaDuplicadoException | DniExistenteException | HorarioOcupadoException |
				PersonaNoAfiliadaException | CanchaNoRegistradaException | CodigoAfiliadoDuplicadoException |
				CapacidadMaximaAlcanzadaException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			
		}
		
		Integer valorEsperado = 1;
		Integer valorObtenido = canchaPadel1.getAlquileres().size();
		
		assertEquals(valorEsperado, valorObtenido);
	}

	@Test
	public void queSePuedaAveriguarCuantoDebePagarPorLaCanchaDeFutbol11AlquiladaConsiderandoLaSenia() {
		String nombreClub = "Club Deportivo UNLaM", nombrePersona = "Alejandro", apellidoPersona = "Paz", codigoAfiliado = "AA001";
		Integer id1 = 1, id2 = 2, id3 =3, id4 =4, dni = 34567754;
		Deporte deporte = Deporte.FUTBOL;
		Double precioCancha = 10000.00, senia = 5000.00;
		Piso piso = Piso.SINTETICO;
		Capacidad capacidad = Capacidad.ONCE;
		Horario horarioReservado = Horario.BLOQUE2;
		Double restaPagar = 0.0;

		Club clubDeportivoUNLaM = new Club(nombreClub);
		Cancha canchaFutbol11 = new CanchaFutbol(id1, deporte, precioCancha, capacidad, piso);
		Persona alejandro = new Persona(dni, nombrePersona, apellidoPersona);
		
		try {
			clubDeportivoUNLaM.afiliar(codigoAfiliado, alejandro);
			clubDeportivoUNLaM.agregarCancha(canchaFutbol11);
			
			clubDeportivoUNLaM.alquilarCancha(horarioReservado, alejandro, canchaFutbol11, senia);
			restaPagar = clubDeportivoUNLaM.verificarCuandoRestaAbonarPorUnaCanchaReservada(canchaFutbol11, horarioReservado);
		} catch (NumeroDeCanchaDuplicadoException | DniExistenteException | HorarioOcupadoException |
				PersonaNoAfiliadaException | CanchaNoRegistradaException | CanchaNoReservadaException |
				CodigoAfiliadoDuplicadoException | CapacidadMaximaAlcanzadaException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			
		}
		
		Double valorEsperado = 5000.00;
		Double valorObtenido = restaPagar;
		
		assertEquals(valorEsperado, valorObtenido);
	}
	

	/*@Test
	public void queSePuedaObtenerUnMapaConLosTotalesRecaudadosPorCadaCancha() {
		// TODO: para la key utilizar el siguiente formato:
		// Futbol: FUTBOL-11-1 (Deporte-Capacidad-numero)
		// Padel: PADEL-VIDRIADA-1 (Deporte-Paredes-numero)
		String nombreClub = "Club Deportivo UNLaM", nombrePersona = "Alejandro", apellidoPersona = "Paz";
		Integer id1 = 1, id2 = 2, id3 =3, id4 =4, id5 = 5, id6 = 6, id7 = 7, dni= 87329487;
		Deporte deporte = Deporte.PADEL;
		Double precioCanchaPadel = 1000.00, precioCanchaFutbol = 10000.00, seniaFutbol = 5000.00, seniaPadel = 500.0;
		Paredes paredesCancha = Paredes.VIDRIADA;
		Piso piso = Piso.SINTETICO;
		Capacidad capacidad = Capacidad.ONCE;
		Horario horarioReservado = Horario.BLOQUE2;
		
		
		Persona alejandro = new Persona(dni, nombrePersona, apellidoPersona);
		
		Club clubDeportivoUNLaM = new Club(nombreClub);
		Cancha canchaPadel1 = new CanchaPadel(id1, deporte, precioCanchaPadel, paredesCancha);
		Cancha canchaPadel2 = new CanchaPadel(id2, deporte, precioCanchaPadel, paredesCancha);
		Cancha canchaPadel3 = new CanchaPadel(id3, deporte, precioCanchaPadel, paredesCancha);
		Cancha canchaPadel4 = new CanchaPadel(id4, deporte, precioCanchaPadel, paredesCancha);
		
		Cancha canchaFutbol1 = new CanchaFutbol(id5, deporte, precioCanchaFutbol, capacidad, piso);
		Cancha canchaFutbol2 = new CanchaFutbol(id6, deporte, precioCanchaFutbol, capacidad, piso);
		Cancha canchaFutbol3 = new CanchaFutbol(id7, deporte, precioCanchaFutbol, capacidad, piso);
		
		try {
			clubDeportivoUNLaM.agregarCancha(canchaPadel1);
			clubDeportivoUNLaM.agregarCancha(canchaPadel2);
			clubDeportivoUNLaM.agregarCancha(canchaPadel3);
			clubDeportivoUNLaM.agregarCancha(canchaPadel4);
			clubDeportivoUNLaM.agregarCancha(canchaFutbol1);
			clubDeportivoUNLaM.agregarCancha(canchaFutbol2);
			clubDeportivoUNLaM.agregarCancha(canchaFutbol3);
			
			clubDeportivoUNLaM.alquilarCancha(horarioReservado, alejandro, canchaFutbol1, seniaFutbol);
			clubDeportivoUNLaM.alquilarCancha(horarioReservado, alejandro, canchaFutbol2, seniaFutbol);
			clubDeportivoUNLaM.alquilarCancha(horarioReservado, alejandro, canchaFutbol3, seniaFutbol);
			
			clubDeportivoUNLaM.alquilarCancha(horarioReservado, alejandro, canchaPadel1, seniaPadel);
			clubDeportivoUNLaM.alquilarCancha(horarioReservado, alejandro, canchaPadel2, seniaPadel);
			clubDeportivoUNLaM.alquilarCancha(horarioReservado, alejandro, canchaPadel3, seniaPadel);
			
		} catch (NumeroDeCanchaDuplicadoException | HorarioOcupadoException | PersonaNoAfiliadaException |
				CanchaNoRegistradaException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			
		}
		Map<String, Double> totalesPorCancha = clubDeportivoUNLaM.obtenerRecaudacionPoTipoDeCancha();

		
	}*/

	@Test
	public void queSePuedanObtenerLasPersonasAfiliadasAlClubOrdenadasPorApellidoAscendente() {
		String nombreClub = "Club Deportivo UNLaM", nombrePersona = "Alejandro", apellidoPersona = "Paz", codigoAfiliado = "AA001",
				codigoAfiliado2 = "AB002", codigoAfiliado3 = "AC005",
				nombrePersona2 = "Pedro", apellidoPersona2 = "Araucaria", nombrePersona3 = "Brenda", apellidoPersona3 = "Benitez";
		Integer id1 = 1, id2 = 2, id3 =3, id4 =4, dni = 34567754, dni2 = 47234923, dni3 = 23423498;
		Deporte deporte = Deporte.PADEL;
		Double precioCancha = 1000.00;
		Boolean estaDisponible = null;
		Paredes paredesCancha = Paredes.VIDRIADA;
		
		Club clubDeportivoUNLaM = new Club(nombreClub);
		Cancha canchaPadel1 = new CanchaPadel(id1, deporte, precioCancha, paredesCancha);
		Persona alejandro = new Persona(dni, nombrePersona, apellidoPersona);
		Persona pedro = new Persona(dni2, nombrePersona2, apellidoPersona2);
		Persona brenda = new Persona(dni3, nombrePersona3, apellidoPersona3);
		
		try {
			clubDeportivoUNLaM.afiliar(codigoAfiliado2, pedro);
			clubDeportivoUNLaM.afiliar(codigoAfiliado3, brenda);
			clubDeportivoUNLaM.afiliar(codigoAfiliado, alejandro);
			clubDeportivoUNLaM.agregarCancha(canchaPadel1);
			
			
		} catch (NumeroDeCanchaDuplicadoException | DniExistenteException |
				CodigoAfiliadoDuplicadoException | CapacidadMaximaAlcanzadaException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			
		}
		Set<Persona> afiliados = new TreeSet<>();
		afiliados = clubDeportivoUNLaM.obtenerColeccionDeAfiliadosOrdenados();
		List<Persona> afiliadosOrdenados = new ArrayList<>(afiliados);
		
		assertEquals(afiliadosOrdenados.get(0), alejandro);
		assertEquals(afiliadosOrdenados.get(1), brenda);
		assertEquals(afiliadosOrdenados.get(2), pedro);
	}

}