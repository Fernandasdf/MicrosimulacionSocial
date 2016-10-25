package cl.usach.pe;

import cl.usach.jsim.Mensaje;
import cl.usach.util.General;

public class PE9 extends PE{

	public PE9(String n, Mensaje m) {
		super(n, m);
		// TODO Auto-generated constructor stub
	}

	@Override
	public double run() {
		 //enviamos a los 3 pe destino dependiendo de x probabilidad
		 this.setNextPe(new PE14("PE14", this.getMessage()));
		 
		 return General.expn(00.2);
	}

}
