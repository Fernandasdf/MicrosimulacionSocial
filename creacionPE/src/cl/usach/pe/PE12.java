package cl.usach.pe;

import cl.usach.jsim.Mensaje;
import cl.usach.util.General;

public class PE12 extends PE{

	public PE12(String n, Mensaje m) {
		super(n, m);
		// TODO Auto-generated constructor stub
	}
	
	@Override
	public double run() {
		// TODO Auto-generated method stub
		 this.setNextPe(new PE16("PE16", this.getMessage()));
			
		return General.expn(00.1);
	}
	
	

}
