package cl.usach.util;

import java.util.Hashtable;
import java.util.Map;

public final class General {
	/*
	public static final Map<String, Integer> MAP_PE_PROCESADOR;
	static{
		MAP_PE_PROCESADOR = new Hashtable<String,Integer>();
		MAP_PE_PROCESADOR.put("PE0",0);
		MAP_PE_PROCESADOR.put("PE1",1);
		MAP_PE_PROCESADOR.put("PE2",0);
		MAP_PE_PROCESADOR.put("PE3",1);
		MAP_PE_PROCESADOR.put("PE4",0);
		MAP_PE_PROCESADOR.put("PE5",1);
	}*/

	public static Double expn(double theta)
	{
		 return -theta*Math.log(Math.random()); 
	}
	
}
