package cl.usach.jsim;

import cl.usach.pe.PE;
import cz.zcu.fav.kiv.jsim.JSimException;
import cz.zcu.fav.kiv.jsim.JSimHead;
import cz.zcu.fav.kiv.jsim.JSimInvalidParametersException;
import cz.zcu.fav.kiv.jsim.JSimLink;
import cz.zcu.fav.kiv.jsim.JSimProcess;
import cz.zcu.fav.kiv.jsim.JSimSimulation;
import cz.zcu.fav.kiv.jsim.JSimSimulationAlreadyTerminatedException;
import cz.zcu.fav.kiv.jsim.JSimTooManyHeadsException;
import cz.zcu.fav.kiv.jsim.JSimTooManyProcessesException;

public class Procesador extends JSimProcess{

	public JSimHead cola;
	public Router router;
	//public Core cores[];
	//public int nCores;
	
	public Procesador(String name, JSimSimulation sim, Router rtr/*, int nc*/)
			throws JSimSimulationAlreadyTerminatedException,
			JSimInvalidParametersException, JSimTooManyProcessesException, JSimTooManyHeadsException {
		super(name, sim);
		// TODO Auto-generated constructor stub
		cola = new JSimHead("colaProcesador "+name, sim);
		router = rtr;
		/*nCores = nc;
		cores = new Core[nCores];
		for(int i=0; i<nCores;i++)
		{
			cores[i] = new Core(name+" - core"+i, sim);
		}*/
	}
	
	
	protected void life()
	{
		
        try
        {
            JSimLink linkG,linkC;
            PE peG, nextPe;
            this.passivate();//parto con el proceso dormido
            double mTime;
            
            while (true)
            {
            		
            	linkG = cola.first();
            	peG=null;
            	Double tiempoEje=0D;
            	Double tiempoAct=0D;
            	
            	if(linkG==null)
            	{
            		/*duermo el procesador si la cola del procesador esta vacia*/
            		this.message("PROCESADOR ==>"+myParent.getCurrentTime() +" duermo "+getName()+" por que no tengo mensajes que procesar"+" \n");
            		this.passivate();
            	}
            	else
            	{
            		/*ejecuto el pe que se extrajo de la cola de procesador*/
            		linkG.out();
            		peG = (PE)linkG.getData();	
            		tiempoEje=peG.run();
            		tiempoAct = (Double) main.TIEMPO_EJECUCION_PE.get(peG.getName());
            		main.TIEMPO_EJECUCION_PE.put(peG.getName(), tiempoAct + tiempoEje);
	          		hold(tiempoEje); //aqui estaria el core si fuese multicore
	          		this.message("PROCESADOR ==>"+myParent.getCurrentTime()+" "+getName()+" ejecutando PE = " +peG.getName()+", es LASTPE = "+peG.isLastPe()+" \n");	
		           	
	          		
	          		if(peG.isLastPe()==true)
	           		{
	           			/*si es lastpe detengo el procesador, ya que no tiene nada más que hacer*/
	          			this.message("PROCESADOR ==>"+myParent.getCurrentTime() +" duermo "+getName()+" por que llegó al LASTPE"+" \n");
	           			this.passivate();
	           		}
	           		else
	           		{
		           		/*si no es lastpe, agrego a la cola del router y activo el router si esta dormido*/
	           			nextPe = peG.getNextPe();
		           		linkC = new JSimLink(nextPe);
		           		linkC.into(router.cola);
		           		router.memoriaOcupada++;
		           		hold(router.latRecepcionMensaje);
		   				this.message("PROCESADOR ==>"+myParent.getCurrentTime() +" mensaje "+peG.getName()+" no es LASTPE, se agrega a la cola del router NEXTPE = " +nextPe.getName()+" \n");
		           		
		           		if(router.isIdle())
		           		{
		           			this.message("PROCESADOR ==>"+myParent.getCurrentTime() +" activo "+router.getName()+" \n");	
		           			//router.time=0;
		           			router.activate(myParent.getCurrentTime());
		           		}
	           		}
            	}
          	}
            
        } // try
        catch (JSimException e)
        {
            e.printStackTrace();
            e.printComment();
        } // catch
		
	}


	public void setRouter(Router router) {
		this.router = router;
	}

	

}
