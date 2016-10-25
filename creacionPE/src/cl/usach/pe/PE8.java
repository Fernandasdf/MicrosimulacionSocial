package cl.usach.pe;

import cl.usach.jsim.Mensaje;
import cl.usach.util.General;

public class PE8 extends PE{

	public PE8(String n, Mensaje m) {
		super(n, m);
		// TODO Auto-generated constructor stub
	}
	
	@Override
	public double run() {
		// TODO Auto-generated method stub
		this.setNextPe(new PE13("PE13", this.getMessage()));
		
		return General.expn(00.1);
	}

}

