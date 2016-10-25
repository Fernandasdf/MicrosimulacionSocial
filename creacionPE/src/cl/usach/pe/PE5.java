package cl.usach.pe;

import cl.usach.jsim.Mensaje;
import cl.usach.util.General;

public class PE5 extends PE{

	public PE5(String n, Mensaje m) {
		super(n, m);
		// TODO Auto-generated constructor stub
	}
	
	@Override
	public double run() {
		// TODO Auto-generated method stub
		 this.setNextPe(new PE9("PE9", this.getMessage()));
		 
		 return General.expn(00.2);
	}

}
