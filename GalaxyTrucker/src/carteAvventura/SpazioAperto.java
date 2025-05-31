/*
 * Evento positivo. Ogni giocatore dichiara la sua potenza motrice, paga batterie se
 * necessario e avanza sulla rotta. Permette di sorpassare.
 *
 * Metodi richiesti per questa carta:
 * 1- cambiaPosizione: per cambiare la posizione del giocatore nella plancia volo
*/
package carteAvventura;

import java.util.List;

import componenti.*;
import game_logic.Giocatore;
import nave.Nave;
import planciavolo.PlanciaVolo;

public class SpazioAperto extends Carta {

	public SpazioAperto(Livello livello) {
		super(livello, "SpazioAperto");
	}

	@Override
	public void azione(List<Giocatore> giocatori, PlanciaVolo plancia) {
		 System.out.println(" Carta Avventura: Spazio Aperto");
		    System.out.println("Ogni giocatore dichiara la potenza motrice e avanza sulla rotta.");

		for (Giocatore giocatore : giocatori) {
			System.out.println("==================== Giocatore: " + giocatore.getNome()+"========================");
	        int potenza = giocatore.getNave().getPotenzaMotrice();
	        
			if (potenza < 1) {
				System.out.println("  Potenza motrice: " + potenza);
	            System.out.println("  Risultato: potenza insufficiente. Il giocatore è costretto ad abbandonare la corsa.");
	            //TODO: metodo per abbandonare cara
			} else {
				System.out.println("  Potenza motrice dichiarata: " + potenza);
	            System.out.println("  Il giocatore avanza di " + (int) potenza + " spazi vuoti sulla rotta.");
	            plancia.spostamentoGiocatore(giocatore, giocatore.getNave().getPotenzaMotrice());
			}
		}
	}

}
