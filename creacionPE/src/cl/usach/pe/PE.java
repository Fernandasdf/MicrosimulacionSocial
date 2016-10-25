package cl.usach.pe;

import cl.usach.jsim.Mensaje;

public abstract class PE {
	private String name;
	private Mensaje message;
	private boolean lastPe;
	private PE nextPe;
	
	public PE(String n, Mensaje m){	
		name=n;
		message=m;
		nextPe=null;
		lastPe=false;
	}

	/*este determina el siguiente pe*/
	public abstract double run();

	public String getName() {
		return name;
	}


	public Mensaje getMessage() {
		return message;
	}



	public PE getNextPe() {
		return nextPe;
	}

	protected void setNextPe(PE npe) {
		nextPe = npe;
	}

	public boolean isLastPe() {
		return lastPe;
	}

	protected void setLastPe(boolean lastPe) {
		this.lastPe = lastPe;
	}


	

}
