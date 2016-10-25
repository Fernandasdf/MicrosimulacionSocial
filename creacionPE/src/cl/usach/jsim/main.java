package cl.usach.jsim;

import java.util.ArrayList;
import java.util.Hashtable;
import java.util.Map;

import cz.zcu.fav.kiv.jsim.JSimException;
import cz.zcu.fav.kiv.jsim.JSimSimulation;


public class main {

	 public static Map<String, Integer> MAP_PE_PROCESADOR;
	 public static Map<String, Double> TIEMPO_EJECUCION_PE;
	 
	 public static void setMapPeProcesador(int nPE, int nProcesadores)
	 {
		 int idp=0;
		 for(int i=0;i<nPE; i++)
		 {
			 idp = (idp<nProcesadores)? idp : 0;
			 MAP_PE_PROCESADOR.put("PE"+i , idp);
			 //System.out.println("mensaje ->PE"+i+" PROC"+idp);
			 idp++;
		 }
	 }
	 
	 public static void inicializaTiempoEjecucion(int nPE)
	 {
		 for(int i=0;i<nPE; i++)
		 {
			 TIEMPO_EJECUCION_PE.put("PE"+i , 0D);
		 }
	 }
	 
	 public static void muestraTiempoEjecucion(int nPE,int nProcesadores)
	 {
		 int i=0;
		 Integer idProc=0;
		 String namePe="";
		 String nameProc="";
		 Double sum=0D,tiempo=0D;
		 double[] procs = new double[nProcesadores];
		 
		 System.out.println("\n ======TIEMPO DE EJECUCIÓN DE LOS PE======\n");
		 for(i=0;i<nPE; i++)
		 {
			 namePe="PE"+i;
			 idProc=MAP_PE_PROCESADOR.get(namePe);
			 nameProc="PROC"+idProc;
			 tiempo= (Double)TIEMPO_EJECUCION_PE.get(namePe);
			 procs[idProc]+=tiempo;
			 
			 sum+=tiempo;
			 System.out.println(namePe+" en "+nameProc+" uso "+tiempo+" tiempo");
		 }
		 System.out.println("\n======TIEMPO DE EJECUCIÓN USO POR PROCESADOR POR LOS PE======\n");
		 for(i=0;i<nProcesadores; i++)
		 {
			 nameProc="PROC"+i;
			 tiempo= procs[i];
			 System.out.println(nameProc+ " usado "+tiempo+" tiempo");
		 
		 }
		 
		 System.out.println("\n======TIEMPO TOTAL DE EJECUCIÓN DE LOS PE "+sum+"\n");
		 
	 }
	
	/**
     * @param args the command line arguments
     * @throws InterruptedException 
     */
    public static void main(String[] args) throws InterruptedException {
        JSimSimulation sim = null;
        Productor productor = null;
        Router router =null;
        Procesador procesadores[]; 
        int nPE = 20;
        int nProcesadores=4;
        MAP_PE_PROCESADOR = new Hashtable<String,Integer>();
        TIEMPO_EJECUCION_PE = new Hashtable<String, Double>();
        setMapPeProcesador(nPE,nProcesadores);
        inicializaTiempoEjecucion(nPE);

        try
        {
            sim = new JSimSimulation("Simulation");
            productor = new Productor("productorMensajes", sim, null);
            router = new Router("routerSistema", sim, null);
            productor.setRouter(router);
            procesadores = new Procesador[nProcesadores];

            for(int i=0; i<nProcesadores;i++)
            {
            	procesadores[i]=new Procesador("PROC"+i,sim, null);
            	procesadores[i].setRouter(router);
            	
            }
            router.setProcesadores(procesadores);
            
            sim.message("INICIO DE LA SIMULACIÓN");
            productor.activate(0.0);
            
            while(sim.step() == true  && sim.getCurrentTime() < 100.0)
            	Thread.sleep(10);

            sim.message("FIN!");
            
            muestraTiempoEjecucion(nPE,nProcesadores);
            
        } // try

        catch (JSimException e)
        {
            e.printStackTrace();
            e.printComment(System.err);
        }

        // We must not forget to shut the simulation down!
        finally
        {
            sim.shutdown();
        }
    }
    
}
