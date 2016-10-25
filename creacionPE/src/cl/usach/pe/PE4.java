package cl.usach.pe;

import cl.usach.jsim.Mensaje;
import cl.usach.util.General;

public class PE4 extends PE{

	public PE4(String n, Mensaje m) {
		super(n, m);
		// TODO Auto-generated constructor stub
	}
	
	@Override
	public double run() {
		// TODO Auto-generated method stub
		this.setNextPe(new PE8("PE8", this.getMessage()));
		
		return General.expn(00.1);
	}

}

