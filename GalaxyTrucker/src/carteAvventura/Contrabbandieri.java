/* Nemico. Attacca i giocatori uno per uno. Serve confrontare la potenza di fuoco:
   se vinci, carichi merci; se perdi, perdi merci o batterie. In caso di pareggio, il nemico continua.
 * 
 * Metodi richiesti per questa carta:
 * 1- cambiaPosizione: per cambiare la posizione del giocatore nella plancia volo
 * 2- caricaMerci: per caricare dei merci
 * 3- eleminaMerci: se il giocatore viene sconfiito, deve perdere n numero di merci
 * */


package carteAvventura;

import java.util.List;
import componenti.Cannone;
import game_logic.Giocatore;
import nave.*;
import merci.*;

public class Contrabbandieri extends Carta {
	
	private double potenzaRichiesta;
	private int giorniDaPerdere;
	private List<Cargo> cargo;
	private List<Cargo> cargoDaPerdere;
	private boolean isSconfitto = false;
	
	private boolean getIsSconfitto() {
		return isSconfitto;
	}
	
	public Contrabbandieri(Livello livello, double potenzaRichiesta, int giorniDaPerdere, List<Cargo> cargoDaPerdere
			, List<Cargo> cargo)

	{
		super(livello, "Contrabbandieri");
		this.cargo = cargo;
		this.potenzaRichiesta = potenzaRichiesta;
		this.giorniDaPerdere = giorniDaPerdere;
		this.cargoDaPerdere = cargoDaPerdere;
	}
	
	@Override
	public String getCartaInfo() {
	    return  getNome() +
	    		"\n Potenza Richiesta:" + potenzaRichiesta +
	    		"\n Giorni Da Perdere: " + giorniDaPerdere 
	  //		+"\n Penalita(MERCI DA PERDERE):" 
	 //			+"\n Merci:" + merci.getMerci()
	;
	}
	
	@Override
	public void azione(Giocatore giocatore) {
		
		if (!isSconfitto)
		{
			if (giocatore.getNave().getPotenzaFuoco()> potenzaRichiesta)
			{
				isSconfitto = true;
				
				//TODO:Volo.cambiaPosizione(giocatore, giorniDaPerdere,-1);
				giocatore.nave.caricaCargo(cargo);
				
			}
			
			 else if (giocatore.getNave().getPotenzaFuoco() < potenzaRichiesta) 
			 {
				//giocatore.nave.eleminaMerci(merci); //il nemico non è sconfitto, penalia= n  merci da perdere
			 }	
			
		}
		
	}

		
		 }

