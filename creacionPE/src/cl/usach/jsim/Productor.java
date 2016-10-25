package cl.usach.jsim;

import cl.usach.pe.PE;
import cl.usach.pe.PE1;
import cl.usach.util.General;
import cz.zcu.fav.kiv.jsim.JSimInvalidParametersException;
import cz.zcu.fav.kiv.jsim.JSimLink;
import cz.zcu.fav.kiv.jsim.JSimProcess;
import cz.zcu.fav.kiv.jsim.JSimSecurityException;
import cz.zcu.fav.kiv.jsim.JSimSimulation;
import cz.zcu.fav.kiv.jsim.JSimSimulationAlreadyTerminatedException;
import cz.zcu.fav.kiv.jsim.JSimTooManyProcessesException;

public class Productor extends JSimProcess{
	
	public Router router;
	public double intervaloActualizacion;
	//public JSimHead colaSistema;

	public Productor(String name, JSimSimulation sim, Router r)
			throws JSimSimulationAlreadyTerminatedException,
			JSimInvalidParametersException, JSimTooManyProcessesException {
		super(name, sim);
		// TODO Auto-generated constructor stub
		router = r;
		intervaloActualizacion = 50;
	}
	
	
	protected void life()
	{
       	try 
       	{
			JSimLink linkG;
			PE peG;
			double time, timeFin;
			time = 0;
			timeFin = 0;
			
        	while (true)
        	{
        		/*verifico el tiempo designado para generar mensajes de tipo PE0*/
        		time = myParent.getCurrentTime();
        		if(time >= timeFin)
        		{
        			/*genero mensaje de tipo PEO y lo agrego a la cola del router*/
        			hold (General.expn(0.5));
                    peG = new PE1("PE0",new Mensaje(1,"texto")); 
    				this.message("PRODUCTOR ==>"+myParent.getCurrentTime() +" mensaje "+peG.getName()+" creado \n");	
                    
    				linkG = new JSimLink(peG);
    				linkG.into(router.cola);
    				router.memoriaOcupada++;
    				hold(router.latRecepcionMensaje);
    				this.message("PRODUCTOR ==>"+myParent.getCurrentTime() +" mensaje "+peG.getName()+" enviado a la cola del router \n");
                	
    				time = myParent.getCurrentTime();
                    timeFin = intervaloActualizacion + time;
        		}
        		
        		/*genero mensaje del tipo PE1 y lo agrego a la cola del router*/
				hold (General.expn(0.5));
                peG = new PE1("PE1",new Mensaje(1,"texto")); 
				linkG = new JSimLink(peG);
				this.message("PRODUCTOR ==>"+myParent.getCurrentTime() +" mensaje "+peG.getName()+" creado \n");	

				linkG.into(router.cola);
				router.memoriaOcupada++;
				hold(router.latRecepcionMensaje);
				this.message("PRODUCTOR ==>"+myParent.getCurrentTime() +" mensaje "+peG.getName()+" enviado a la cola del router \n");
				
				/*activo el router si esta dormido*/
				if (router.isIdle())
				{
					this.message("PRODUCTOR ==>"+myParent.getCurrentTime()+" activo "+router.getName()+" \n");	
					//router.time=0;
					router.activate(myParent.getCurrentTime());
    				
				}
				

        	}
		} catch (JSimSecurityException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (JSimInvalidParametersException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} 
		
	}
	

	public void setRouter(Router router) {
		this.router = router;
	}

	
}
