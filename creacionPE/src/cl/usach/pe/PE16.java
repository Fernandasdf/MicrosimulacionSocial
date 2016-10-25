package cl.usach.pe;

import cl.usach.jsim.Mensaje;
import cl.usach.util.General;

public class PE16 extends PE{

	public PE16(String n, Mensaje m) {
		super(n, m);
		// TODO Auto-generated constructor stub
		setLastPe(true);
	}

	@Override
	public double run() {
		 //enviamos a los 3 pe destino dependiendo de x probabilidad
		return General.expn(00.1);
	}

}
