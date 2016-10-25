package cl.usach.jsim;

import cl.usach.pe.PE;
import cz.zcu.fav.kiv.jsim.JSimException;
import cz.zcu.fav.kiv.jsim.JSimInvalidParametersException;
import cz.zcu.fav.kiv.jsim.JSimLink;
import cz.zcu.fav.kiv.jsim.JSimProcess;
import cz.zcu.fav.kiv.jsim.JSimSimulation;
import cz.zcu.fav.kiv.jsim.JSimSimulationAlreadyTerminatedException;
import cz.zcu.fav.kiv.jsim.JSimTooManyProcessesException;

public class Core extends JSimProcess{
	
	public PE peEjecutar;

	public Core(String name, JSimSimulation sim)
			throws JSimSimulationAlreadyTerminatedException,
			JSimInvalidParametersException, JSimTooManyProcessesException {
		super(name, sim);
		// TODO Auto-generated constructor stub
	}

	protected void life()
	{
		
        try
        {
            this.passivate();
            while (true)
            {
            	hold(peEjecutar.run());
            	this.passivate();
            }
        }
        catch (JSimException e)
        {
        	e.printStackTrace();
        	e.printComment();
        } 
	
	}
	
	
	public void setPeEjecutar(PE pe){
		peEjecutar = pe;
		
	}
	
}
