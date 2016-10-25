package cl.usach.pe;

import cl.usach.jsim.Mensaje;
import cl.usach.util.General;

public class PE0 extends PE{

	public PE0(String n, Mensaje m) {
		super(n, m);
		// TODO Auto-generated constructor stub
	}

	@Override
	//RETORNA EL TIEMPO DE EJECUCIÓN
	public double run() {

		 this.setNextPe(new PE2("PE2", this.getMessage()));
		 
		 return General.expn(00.2);
	}

}
