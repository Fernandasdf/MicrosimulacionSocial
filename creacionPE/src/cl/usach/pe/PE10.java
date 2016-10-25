package cl.usach.pe;

import cl.usach.jsim.Mensaje;
import cl.usach.util.General;

public class PE10 extends PE{
	public PE10(String n, Mensaje m) {
		super(n, m);
		// TODO Auto-generated constructor stub
	}
	
	@Override
	public double run() {
		 this.setNextPe(new PE11("PE11", this.getMessage()));
		 
		 return General.expn(00.2);
	}

}
