/*
 * Evento opzionale. Il giocatore può spendere "giorni di volo" per atterrare su un pianeta
	e caricare merci. Un solo giocatore per pianeta, priorità secondo ordine di rotta.
 * Metodi richiesti per questa carta:
 * 1-cambiaPosizione: per cambiare la posizione del giocatore nella plancia volo
 * 2- caricaMerci: per caricare dei merci
 * */

package carteAvventura;

import java.util.*;

import game_logic.Giocatore;

public class Pianeti extends Carta {
	private int giorniDaPerdere; // I giorni di volo da scarifiare per poter caricare dei merci
	private List<Pianeta> pianetiDisponibili; // Lista dei pianeti disponibili sulla carta
	public Pianeti(Livello livello, List<Pianeta> pianeti, int giorniDaPerdere) {
		super(livello, "Pianeti");
		this.giorniDaPerdere = giorniDaPerdere;
		this.pianetiDisponibili = pianeti;

	}

	@Override
	public String getCartaInfo() {
		return getNome() + "\n pianeti Disponibili:" + pianetiDisponibili + "\n Giorni Da Perdere: " + giorniDaPerdere;
	}


	public int getGiorniDaPerdere() {
		return giorniDaPerdere;
	}

	public List<Pianeta> getPianeti() {
		return pianetiDisponibili;
	}

	public void azione(Giocatore giocatore, int pianetaScelta) 
	{
		if (pianetaScelta >= 0 && pianetaScelta < pianetiDisponibili.size()) {
			/* Volo.cambiaPosizione(giocatore, giorniDaPerdere,-1) */ /*
																		 * Nella classe VOLO dovrebbe essere presente un
																		 * metodo per aggiornare la posizione di un
																		 * giocatore. Il parametro GIOCATORE rappresenta
																		 * il giocatore da spostare, mentre i GIORNI DA
																		 * PERDERE indicano i passi. Un valore di -1
																		 * corrisponde a uno spostamento all'indietro,
																		 * mentre 1 indica uno spostamento in avanti
																		 * (l'implementazione qui va modificata in caso
																		 * il metodo venga programmato in modo diverso).
																		 */
			// giocatore.nave.caricaMerci(pianetiDisponibili(pianetaScelta));
			pianetiDisponibili.remove(pianetaScelta);

		}

	}

}
