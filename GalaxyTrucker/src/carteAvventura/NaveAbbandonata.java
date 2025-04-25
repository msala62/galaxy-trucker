/* Funzioni richiesti per questa carta: 
 * 1-aggiungicredit(crediti da aggiungere): per ottenere crediti cosmici.
 * 2-eleminaEquipaggio(numero euipaggio da perdere):  sacrificare un certo numero di membri dell'equipaggio
 * 3-cambiaPosizione: per cambiare la posizione del giocatore nella plancia volo
 * 4- getEquipaggio: per poter verificare se il numero di equipaggio richiesto per attivare la carta e disponibile
 * */

package carteAvventura;

import game_logic.Giocatore;

public class NaveAbbandonata extends Carta {
	
	private int equipaggioDaPerdere;
	private int giorniDaPerdere;
	private int creditiDaAquistare;
	private boolean isUsed = false;
	

	public NaveAbbandonata(Livello livello, int equipaggioDaPerdere, int giorniDaPerdere, int creditiDaAquistare) {
		super(livello, "NaveAbbandonata");
		this.creditiDaAquistare = creditiDaAquistare;
		this.equipaggioDaPerdere = equipaggioDaPerdere;
		this.giorniDaPerdere = giorniDaPerdere;
	}
	
	@Override
	public String getCartaInfo() {
	    return  getNome() +
	    		"\n equipaggio Da Perdere:" + equipaggioDaPerdere +
	    		"\n Giorni Da Perdere: " + giorniDaPerdere +
	    		"\n Crediti Da Aquistare" + creditiDaAquistare;
	}
	
	
	@Override
	public void azione(Giocatore giocatore) {

    	if (/*giocatore.nave.getEquipaggio()>= equipaggioDaPerdere &&*/ isUsed == false)
    	{
    		/*Volo.cambiaPosizione(giocatore, giorniDaPerdere,-1)*/	
    		/*Nella classe VOLO dovrebbe essere presente un metodo per
			aggiornare la posizione di un giocatore. Il parametro GIOCATORE
			rappresenta il giocatore da spostare, mentre i GIORNI DA PERDERE
			indicano i passi. Un valore di -1 corrisponde a uno spostamento
			all'indietro, mentre 1 indica uno spostamento in avanti
			(l'implementazione qui va modificata in caso il metodo venga
 			programmato in modo diverso).*/ 
    		
    	    //giocatore.nave.eleminaEquipaggio(equipaggioDaPerdere);
    		/*elemina equipaggio e una funzione per eleminare equipaggio dalla nave*/
    		
    	    //giocatore.aggiungiCrediti(creditiDaAquistare);    	
    		/*
    		 * aggiungiCrediti e una funzione per poter aggiungere crediti ai
				crediti del giocatore che prende come parametro la quantita dei crediti da aggiungere
    		 * */
    		isUsed = true;
    	} else {
    		System.out.println("Equipaggio Insufficiente");
    	}
    	
	};

	
}
