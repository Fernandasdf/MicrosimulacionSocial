package cl.usach.pe;

import cl.usach.jsim.Mensaje;
import cl.usach.util.General;

public class PE13 extends PE{

	public PE13(String n, Mensaje m) {
		super(n, m);
		// TODO Auto-generated constructor stub
	}

	@Override
	public double run() {
		 //enviamos a los 3 pe destino dependiendo de x probabilidad
		this.setNextPe(new PE13("PE17", this.getMessage()));
		
		return General.expn(00.1);
	}

}
