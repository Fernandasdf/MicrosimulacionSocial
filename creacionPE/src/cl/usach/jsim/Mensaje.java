package cl.usach.jsim;

public class Mensaje {
	
	public int tamano;
	public String contenido;
	
	public Mensaje(int tamano, String contenido) {
		super();
		this.tamano = tamano;
		this.contenido = contenido;
	}
	public int getTamano() {
		return tamano;
	}
	public void setTamano(int tamano) {
		this.tamano = tamano;
	}
	public String getContenido() {
		return contenido;
	}
	public void setContenido(String contenido) {
		this.contenido = contenido;
	}

	
	
}
