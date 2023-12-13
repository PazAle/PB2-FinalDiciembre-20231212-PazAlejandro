package ar.edu.unlam.pb2;

import ar.edu.unlam.pb2.enums.Deporte;
import ar.edu.unlam.pb2.enums.Paredes;
import ar.edu.unlam.pb2.enums.Piso;
 
public class CanchaPadel extends Cancha{

	private Paredes paredes;

	public CanchaPadel(Integer numero, Deporte deporte, Double precio, Paredes paredes) {
		super(numero, deporte, precio);
		this.paredes = paredes;
	}

	

	public Paredes getParedes() {
		return paredes;
	}



	public void setParedes(Paredes paredes) {
		this.paredes = paredes;
	}



	@Override
	public void setPrecio(Double precio) {
		// TODO Auto-generated method stub
		
	}
}
