/*
 * Funzioni richiesti per questa carta:
 * 1-cambiaPosizione: per cambiare la posizione del giocatore nella plancia volo
 * 2- caricaMerci: per caricare dei merci
 * */


package carteAvventura;

import game_logic.Giocatore;

public class StazioneAbbandonata extends Carta {
	
	private int equipaggioRichiesto;
	private int giorniDaPerdere;
	private Pianeta merci;
	
	
	public StazioneAbbandonata(Livello livello, int equipaggioRichiesto,int giorniDaPerdere,
     int merciVerdi, int merciGialli, int merciRossi, int merciBlu) {
		super(livello, "Stazione Abbandonata");
		this.equipaggioRichiesto = equipaggioRichiesto;
		this.giorniDaPerdere = giorniDaPerdere;
		merci = new Pianeta(merciVerdi, merciRossi, merciBlu, merciGialli);
		
				}
	
	
	public String getCartaInfo() {
	    return getNome() +
	    		"Equipaggio Richiesto: " + equipaggioRichiesto +
	    		"\n Giorni Da Perdere: " + giorniDaPerdere
	    		+ "\n Merci: " + merci.getMerci() ;
	}
	
	@Override
    public void azione(Giocatore giocatore) {
		
		//if(giocatore.nave.getEquipaggio()<equipaggioRichiesto)
		{
			System.out.println("gioicatore non può attraccare alla stazione");
		}
		//else {
			
			/*Volo.cambiaPosizione(giocatore, giorniDaPerdere,-1)*/	
    		/*Nella classe VOLO dovrebbe essere presente un metodo per
			aggiornare la posizione di un giocatore. Il parametro GIOCATORE
			rappresenta il giocatore da spostare, mentre i GIORNI DA PERDERE
			indicano i passi. Un valore di -1 corrisponde a uno spostamento
			all'indietro, mentre 1 indica uno spostamento in avanti
			(l'implementazione qui va modificata in caso il metodo venga
 			programmato in modo diverso).*/ 
			
			// giocatore.nave.caricaMerci(merci);
			
			
			
		}

	}

