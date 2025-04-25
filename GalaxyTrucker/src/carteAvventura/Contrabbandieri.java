/*
 * Funzioni richiesti per questa carta:
 * 1- cambiaPosizione: per cambiare la posizione del giocatore nella plancia volo
 * 2- caricaMerci: per caricare dei merci
 * 3- eleminaMerci: se il giocatore viene sconfiito, deve perdere n numero di merci
 * 4-getPotenzaDiFuoco
 * 
 * attribuiti richiesti:
 * 1- giocatore.nave.potenzaDiFuoco = la potenza di fuoco della nave
 * */


package carteAvventura;

import game_logic.Giocatore;

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
			//if (giocatore.nave.getPotenzaDiFuoco() > potenzaRichiesta)
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
			// else if ( giocatore.nave.getPotenzaDiFuoco() == potenzaRichiesta) 
			{} //Nessun effetto per quel giocatore, ma il nemico non è sconfitto
			
			// else if (giocatore.nave.getPotenzaDiFuoco() < potenzaRichiesta) 
				//giocatore.nave.eleminaMerci(merci); //il nemico non è sconfitto, penalia= n  merci da perdere
			
		}
		
	}

}
