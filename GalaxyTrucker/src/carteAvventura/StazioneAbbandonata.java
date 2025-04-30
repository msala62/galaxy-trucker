/*
 * Evento singolo. Richiede un numero minimo di membri dell'equipaggio per essere sfruttato.
 * Chi aggancia carica merci e perde giorni di volo. Solo un giocatore può sfruttarlo. 
 * 
 * Metodi richiesti per questa carta:
 * 1-cambiaPosizione: per cambiare la posizione del giocatore nella plancia volo
 * 2- caricaMerci: per caricare dei merci
 * */

package carteAvventura;

import java.util.*;
import game_logic.Giocatore;
import merci.*;

public class StazioneAbbandonata extends Carta {

	private int equipaggioRichiesto;
	private int giorniDaPerdere;
	private List<Cargo> cargo;

	public StazioneAbbandonata(Livello livello, List<Cargo> cargo, int giorniDaPerdere, int equipaggioRichiesto) {
		super(livello, "Stazione Abbandonata");
		this.equipaggioRichiesto = equipaggioRichiesto;
		this.giorniDaPerdere = giorniDaPerdere;
		this.cargo = cargo;

	}

	public String getCartaInfo() {
		return getNome() + "Equipaggio Richiesto: " + equipaggioRichiesto + "\n Giorni Da Perdere: " + giorniDaPerdere
				+ "\n Cargo: ";
	}

	@Override
	public void azione(Giocatore giocatore) {

		if (giocatore.nave.getEquipaggioTotale() < equipaggioRichiesto) {
			System.out.println("gioicatore non può attraccare alla stazione");
		} else {

			/* Volo.cambiaPosizione(giocatore, giorniDaPerdere,-1) */
			/*
			 * Nella classe VOLO dovrebbe essere presente un metodo per aggiornare la
			 * posizione di un giocatore. Il parametro GIOCATORE rappresenta il giocatore da
			 * spostare, mentre i GIORNI DA PERDERE indicano i passi. Un valore di -1
			 * corrisponde a uno spostamento all'indietro, mentre 1 indica uno spostamento
			 * in avanti (l'implementazione qui va modificata in caso il metodo venga
			 * programmato in modo diverso).
			 */

			giocatore.nave.caricaCargo(cargo);

		}

	}
}