/*
 * Metodi richiesti per questa carta:
 * 1- cambiaPosizione: per cambiare la posizione del giocatore nella plancia volo
 * 2- caricaMerci: per caricare dei merci
 * 3- eleminaMerci: se il giocatore viene sconfiito, deve perdere n numero di merci
 * */


package carteAvventura;

import componenti.*;
import game_logic.Giocatore;
import nave.Nave;

public class Contrabbandieri extends Carta {
	
	private int potenzaRichiesta;
	private int giorniDaPerdere;
	private Pianeta merci;
	private int penalita; //merci da perdere in sconfittta
	private boolean isSconfitto = false;
	
	public boolean getIsSconfitto() {
		return isSconfitto;
	}
	
	public Contrabbandieri(Livello livello, int potenzaRichiesta, int giorniDaPerdere, int penalita
			, int merciVerdi, int merciGialli, int merciRossi, int merciBlu)

	{
		super(livello, "Contrabbandieri");
		merci = new Pianeta(merciVerdi, merciRossi, merciBlu, merciGialli);
		this.potenzaRichiesta = potenzaRichiesta;
		this.giorniDaPerdere = giorniDaPerdere;
		this.penalita = penalita;
	}
	
	@Override
	public String getCartaInfo() {
	    return  getNome() +
	    		"\n Potenza Richiesta:" + potenzaRichiesta +
	    		"\n Giorni Da Perdere: " + giorniDaPerdere +
	    		"\n Penalita(MERCI DA PERDERE):" + penalita +
	    		"\n Merci:" + merci.getMerci();
	}
	
	@Override
	public void azione(Giocatore giocatore) {
		
		if (!isSconfitto)
		{
			if (calcolaPotenzaFuoco(giocatore.getNave()) > potenzaRichiesta)
			{
				isSconfitto = true;
				
				/*Volo.cambiaPosizione(giocatore, giorniDaPerdere,-1)*/ /*Nella classe VOLO dovrebbe essere presente un metodo per
                aggiornare la posizione di un giocatore. Il parametro GIOCATORE
                rappresenta il giocatore da spostare, mentre i GIORNI DA PERDERE
                indicano i passi. Un valore di -1 corrisponde a uno spostamento
                all'indietro, mentre 1 indica uno spostamento in avanti
                (l'implementazione qui va modificata in caso il metodo venga
                programmato in modo diverso).*/ 

				//giocatore.nave.caricaMerci(merci);
				
			}
			
			 else if (calcolaPotenzaFuoco(giocatore.getNave()) < potenzaRichiesta) {}
				//giocatore.nave.eleminaMerci(merci); //il nemico non è sconfitto, penalia= n  merci da perdere
			
		}
		
	}

	public double calcolaPotenzaFuoco(Nave nave) {
		double potenzaFuoco = 0;
		for (int i = 0; i < nave.getPlancia().length; i++)
			for (int j = 0; j < nave.getPlancia()[0].length; j++) {
				if (nave.getPlancia()[i][j].isUtilizzabile() && nave.getPlancia()[i][j].getComponente() != null)
					if (nave.getPlancia()[i][j].getComponente() instanceof Cannone) {
						Cannone p = (Cannone) nave.getPlancia()[i][j].getComponente();
						potenzaFuoco = potenzaFuoco + p.getPotenza();
					}
			}
		return potenzaFuoco;
	}

}
