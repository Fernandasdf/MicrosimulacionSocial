package cl.usach.pe;

import cl.usach.jsim.Mensaje;
import cl.usach.util.General;

public class PE1 extends PE{

	public PE1(String n, Mensaje m) {
		super(n, m);
		// TODO Auto-generated constructor stub
	}

	@Override
	public double run() {
		 //enviamos a los 3 pe destino dependiendo de x probabilidad
		 Double v = Math.random();
		 if(v < 0.35) this.setNextPe(new PE3("PE3", this.getMessage()));
		 else if (v >= 0.35 && v < 0.7)this.setNextPe(new PE3("PE4", this.getMessage()));
		 else this.setNextPe(new PE5("PE5", this.getMessage()));
		 
		 return General.expn(00.2);
	}

}
