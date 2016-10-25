package cl.usach.jsim;

import cl.usach.pe.PE;
import cl.usach.util.General;
import cz.zcu.fav.kiv.jsim.JSimException;
import cz.zcu.fav.kiv.jsim.JSimHead;
import cz.zcu.fav.kiv.jsim.JSimInvalidParametersException;
import cz.zcu.fav.kiv.jsim.JSimLink;
import cz.zcu.fav.kiv.jsim.JSimProcess;
import cz.zcu.fav.kiv.jsim.JSimSimulation;
import cz.zcu.fav.kiv.jsim.JSimSimulationAlreadyTerminatedException;
import cz.zcu.fav.kiv.jsim.JSimTooManyHeadsException;
import cz.zcu.fav.kiv.jsim.JSimTooManyProcessesException;

public class Router  extends JSimProcess{
	
	public JSimHead cola;
	public int memoria; //cantidad de elementos que soporta la cola del router
	public int memoriaOcupada; //contadore de elemetos en la cola
	public JSimHead bloques;
	public int anchoBanda; //cantidad de elementos del bloque
	public int anchoBandaOcupado;
	public double frecuenciaTrabajo; //cada cuanto tiempo dispara los bloques a los procesadores
	public double time; //cada cuanto tiempo dispara los bloques a los procesadores
	public double timeFin; //cada cuanto tiempo dispara los bloques a los procesadores
	public double latEnvioBloque;
	public double latRecepcionMensaje;
	public Procesador [] procesadores;

	public Router(String name, JSimSimulation sim, Procesador [] procs)
			throws JSimSimulationAlreadyTerminatedException,
			JSimInvalidParametersException, JSimTooManyProcessesException, JSimTooManyHeadsException {
		super(name, sim);
		
		
		//memoria = 12;
		memoriaOcupada=0;
		anchoBanda = 12;
		anchoBandaOcupado = 0;
		frecuenciaTrabajo = 1.5; //tiempo que se demora en enviarla info de los bloques
		latEnvioBloque = 0.01;
		latRecepcionMensaje = 0.01;		// TODO Auto-generated constructor stub
		cola = new JSimHead("colaRouter", sim);
		procesadores = procs;
		
		timeFin = frecuenciaTrabajo + myParent.getCurrentTime();
	}
	
	
	protected void life()
	{
        try
        {
        	
            JSimLink linkG,linkC;
            //this.passivate();//parto con el proceso dormido
            
            int i=0;
            boolean swSalir=false;
                 
            while (true)
            {  	
            	time = myParent.getCurrentTime();
            	//this.message("ROUTER ==>"+myParent.getCurrentTime()+" *time= "+time+" *timeFin="+timeFin);
            	if(time >= timeFin) /*tiempo de frecuencia mayor o igual frecuenciaTrabajo*/
            	{
            		swSalir=false;
            		/*mensaje generico que muestra como va la ejecución*/
    				this.message("ROUTER ==>"+myParent.getCurrentTime()+" anchobanda ="+anchoBanda +" memoriaOcupada="+memoriaOcupada+" frecuenciaTrabajo="+frecuenciaTrabajo+" time= "+time+" timeFin="+timeFin+" \n"); 
            		for(i=0; i<anchoBanda || swSalir==true; i++)
            		{
            			time = myParent.getCurrentTime();
            			linkG = cola.first();
	           			if(linkG==null)
	           			{
	           				if(i>0)
	           				{
	                    		/*representa la frecuencia de envío del bloque completo por el router*/
	                         	hold(latEnvioBloque);
	           				}
	           				/*si la cola esta vacia me duermo*/
	           				swSalir=true;
                            timeFin = time + frecuenciaTrabajo;
                            this.message("ROUTER ==>"+myParent.getCurrentTime() +" time Inicio="+time+", nuevo limite por la frecuencia timeFin="+timeFin+" \n");
	           				this.message("ROUTER ==>"+myParent.getCurrentTime() +" duermo "+getName()+" por que no tengo mensajes que enviar"+" \n");
	           				this.passivate();
	           			}
	           			else
	           			{
		           			/*extraido el objeto de la cola del router y se lo envio al procesador que corresponda*/
	           				linkG.out();
		                    PE pe = (PE)linkG.getData();
		                    this.memoriaOcupada--;
		    				this.message("ROUTER ==>"+myParent.getCurrentTime()+" obtengo mensaje "+pe.getName()+" de la colaRouter, memoriaOcupada="+memoriaOcupada+" \n");	
		                    
		                   	
			                Integer idProcesador = main.MAP_PE_PROCESADOR.get(pe.getName());
			                linkC = new JSimLink(pe);
			                linkC.into(procesadores[idProcesador].cola);
			                this.message("ROUTER ==>"+myParent.getCurrentTime()+" mensaje "+pe.getName()+" se agrega a la cola de "+procesadores[idProcesador].getName()+" \n");
			                	
			                /*si el procesador destino esta durmiendo lo despierto*/
			                if (procesadores[idProcesador].isIdle())
			                {
			                	this.message("ROUTER ==>"+myParent.getCurrentTime()+" activo "+procesadores[idProcesador].getName()+" \n");
			                	procesadores[idProcesador].activate(myParent.getCurrentTime());
			                }  
	           			}
            		}
            		/*si se completo el ancho de banda y aun quedan elementos en la cola del router*/
            		if(swSalir==false)
            		{
            			time = myParent.getCurrentTime();
            			 timeFin = time + frecuenciaTrabajo;
            			 this.message("ROUTER ==>"+myParent.getCurrentTime() +" time Inicio="+time+", nuevo limite por la frecuencia timeFin="+timeFin+" \n");
                 		/*representa la frecuencia de envío del bloque completo por el router*/
                      	hold(latEnvioBloque);
            		}
            	}
            	else
            	{
            		this.message("ROUTER ==>"+myParent.getCurrentTime() +" duermo "+getName()+" por que aún no se alcanza la frecuencia de transmisión"+" \n");
            		this.passivate();
            	}
              
            } 
        } // try
        catch (JSimException e)
        {
            e.printStackTrace();
            e.printComment();
        } // catch
		
	}

	
	public Boolean anchoBandaLleno()
	{
		return (anchoBanda - anchoBandaOcupado)==0;
		
	}


	public void setProcesadores(Procesador[] procesadores) {
		this.procesadores = procesadores;
	}
	
	
	
}
