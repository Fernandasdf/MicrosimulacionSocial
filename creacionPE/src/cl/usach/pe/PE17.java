package cl.usach.pe;

import cl.usach.jsim.Mensaje;
import cl.usach.util.General;

public class PE17 extends PE{
	public PE17(String n, Mensaje m) {
		super(n, m);
		// TODO Auto-generated constructor stub
		setLastPe(true);
	}
	
	@Override
	public double run() {
		// TODO Auto-generated method stub
		return General.expn(00.1);
	}

}
