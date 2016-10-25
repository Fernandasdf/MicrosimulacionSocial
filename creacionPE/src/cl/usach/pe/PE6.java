package cl.usach.pe;

import cl.usach.jsim.Mensaje;
import cl.usach.util.General;

public class PE6 extends PE{
	public PE6(String n, Mensaje m) {
		super(n, m);
		// TODO Auto-generated constructor stub
		
	}
	
	@Override
	public double run() {

		 this.setNextPe(new PE10("PE10", this.getMessage()));
		 
		 return General.expn(00.2);
	}

}
