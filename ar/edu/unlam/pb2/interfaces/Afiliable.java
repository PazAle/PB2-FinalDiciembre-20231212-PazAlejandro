package ar.edu.unlam.pb2.interfaces;

import ar.edu.unlam.pb2.Persona;
import ar.edu.unlam.pb2.exception.CapacidadMaximaAlcanzadaException;
import ar.edu.unlam.pb2.exception.CodigoAfiliadoDuplicadoException;
import ar.edu.unlam.pb2.exception.DniExistenteException;

public interface Afiliable {

	public void afiliar(String codigoAfiliado, Persona persona) throws DniExistenteException, CodigoAfiliadoDuplicadoException, CapacidadMaximaAlcanzadaException;
	public Persona getAfiliado(Integer dni);
	public Persona getAfiliado(String codigoAfiliado);
	public Boolean existeAfiliado(Integer dni);
	public Boolean existeAfiliado(String codigoAfiliado);
	
	
}
