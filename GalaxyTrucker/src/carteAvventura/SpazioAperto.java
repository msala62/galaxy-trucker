/*
 * Evento positivo. Ogni giocatore dichiara la sua potenza motrice, paga batterie se
 * necessario e avanza sulla rotta. Permette di sorpassare.
 *
 * Metodi richiesti per questa carta:
 * 1- cambiaPosizione: per cambiare la posizione del giocatore nella plancia volo
*/
package carteAvventura;

import componenti.*;
import game_logic.Giocatore;
import nave.Nave;

public class SpazioAperto extends Carta {

	public SpazioAperto(Livello livello) {
		super(livello, "SpazioAperto");
	}

	@Override
	public void azione(Giocatore giocatore) {

		if (giocatore.getNave().getPotenzaMotrice()< 1) {
			System.out.println("giocatore costretto ad abbandonare la corsa​");
		}
		// else {
		/* Volo.cambiaPosizione(giocatore, giocatore.nave.getPotenzaMotrice , 1) */
		/*
		 * Nella classe VOLO dovrebbe essere presente un metodo per aggiornare la
		 * posizione di un giocatore. Il parametro GIOCATORE rappresenta il giocatore da
		 * spostare, mentre i GIORNI DA PERDERE indicano i passi. Un valore di -1
		 * corrisponde a uno spostamento all'indietro, mentre 1 indica uno spostamento
		 * in avanti (l'implementazione qui va modificata in caso il metodo venga
		 * programmato in modo diverso).
		 */

	}

	
}
