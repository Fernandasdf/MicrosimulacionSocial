package cl.usach.pe;

import cl.usach.jsim.Mensaje;
import cl.usach.util.General;

public class PE3 extends PE{

	public PE3(String n, Mensaje m) {
		super(n, m);
		// TODO Auto-generated constructor stub
	}
	
	@Override
	public double run() {
		// TODO Auto-generated method stub
		 this.setNextPe(new PE7("PE7", this.getMessage()));
		
		 return General.expn(00.1);
	}

}

