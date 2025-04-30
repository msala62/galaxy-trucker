//DA CONTINUARE

/*
 * Evento di penalità multipla. Tre prove: minor equipaggio (perdita giorni),
 * minor potenza motrice (perdita membri equipaggio), minor potenza di fuoco (subire cannonate).*/

package carteAvventura;

import java.util.List;
import java.util.Random;

import game_logic.Giocatore;

public class ZonaDiGuerra  extends Carta {

	
	private List<Cannonata> cannonate;
	private Random random = new Random();
	
	public ZonaDiGuerra(Livello livello, List<Cannonata> cannonate) {
		super(livello, "Zona Di Guerra");
		this.cannonate = cannonate;
		// TODO Auto-generated constructor stub
	}

    public void azione(List<Giocatore> giocatori) {
    	
    	penalitaMinorEquipaggio (minorEquipaggio(giocatori));
    	penalitaMinorPotenzaMotrice(minorPotenzaMotrice(giocatori)); 
		penalitaMinorPotenzaFuoco(minorPotenzaFuoco(giocatori));
	}
    
    private void penalitaMinorEquipaggio(Giocatore giocatore)
    {
    	if (this.livello == Livello.I)
		{
    		/* Volo.cambiaPosizione(giocatore, 3, -1) */
    		// TODO: implementare cambio posizione
    		
		} else if (this.livello == Livello.II)
		{
			
		} else {
			//giocatore.getNave().eliminaCargo(null)
		}
    }
    
    private void penalitaMinorPotenzaMotrice(Giocatore giocatore)
    {
    	if (this.livello == Livello.I)
		{
    		giocatore.getNave().eliminaEquipaggio(2);
		}
    	
    	else if (this.livello == Livello.II)
		{
			//giocatore.getNave().eliminaCargo(null)
			
		} else {
			//giocatore.getNave().eliminaCargo(null)
		}
    }
	
    private void penalitaMinorPotenzaFuoco(Giocatore giocatore)
    {
    	
    	if (this.livello == Livello.I)
		{
    		for (int i=0 ; i<cannonate.size(); i++)
    		{
    			int dado = random.nextInt(12)+1;
    			if (cannonate.get(i).getDimensione() == Cannonata.Dimensione.LEGGERA)
    			{
    				
    			}
    			else if (cannonate.get(i).getDimensione() == Cannonata.Dimensione.LEGGERA)
    			{
    				
    				//TODO:giocatore.getNave().distruggiComponente(i, j);
    			}	
    		}
    		

		} else if (this.livello == Livello.II)
		{
			//giocatore.getNave().eliminaCargo(null)
			
		} else {
			//giocatore.getNave().eliminaCargo(null)
		}
    }
    
    private Giocatore minorEquipaggio(List<Giocatore> giocatori) {
    	Giocatore  gMinorEquipaggio= giocatori.getFirst();
    	for(int i = 0 ; i<giocatori.size();i++)
		{
			if (giocatori.get(i).getNave().getEquipaggioTotale()<gMinorEquipaggio.getNave().getEquipaggioTotale())
				gMinorEquipaggio=giocatori.get(i);
			
			else if (giocatori.get(i).getNave().getEquipaggioTotale()==gMinorEquipaggio.getNave().getEquipaggioTotale())
			{}	// pareggi si risolvono penalizzando il giocatore più avanti nella rotta:DA FARE DOPO 

		}
    	
    	return gMinorEquipaggio;
	}

    
	private Giocatore minorPotenzaMotrice(List<Giocatore> giocatori) {
		Giocatore  gMinorPotenzaMotrice= giocatori.getFirst();
    	for(int i = 0 ; i<giocatori.size();i++)
		{
			if (giocatori.get(i).getNave().getPotenzaMotrice()<gMinorPotenzaMotrice.getNave().getPotenzaMotrice())
				gMinorPotenzaMotrice=giocatori.get(i); 
		
			else if (giocatori.get(i).getNave().getPotenzaMotrice()==gMinorPotenzaMotrice.getNave().getPotenzaMotrice())
			{
			// pareggi si risolvono penalizzando il giocatore più avanti nella rotta:DA FARE DOPO
			}
		} 
    	
    	return gMinorPotenzaMotrice;
	}
	
   private Giocatore minorPotenzaFuoco(List<Giocatore> giocatori) {
	   Giocatore  gMinorPotenzaFuoco= giocatori.getFirst();
   	for(int i = 0 ; i<giocatori.size();i++)
		{
			if (giocatori.get(i).getNave().getPotenzaFuoco()<gMinorPotenzaFuoco.getNave().getPotenzaFuoco())
				gMinorPotenzaFuoco=giocatori.get(i);
			else if (giocatori.get(i).getNave().getPotenzaFuoco()<gMinorPotenzaFuoco.getNave().getPotenzaFuoco())
			{
				// pareggi si risolvono penalizzando il giocatore più avanti nella rotta:DA FARE DOPO
			}

		}
   	
   	return gMinorPotenzaFuoco;
	   
	}

	
}
