package titoli;

import java.util.List;
import game_logic.Giocatore;
import nave.*;

public class Titolo {

	private String titolo;
	private Giocatore proprietario;
	private int creditiPremio = 2;
	private boolean isGold = false;
	private boolean isOwned = false;

	public Titolo(String titolo) {
		this.titolo = titolo;
	}

	public void passaAGold() {
		this.isGold = true;
		this.creditiPremio = 12;

	}

	public Giocatore getProprietario() {
		return proprietario;
	}

	public void assegnaTitolo(Giocatore giocatore) {
		if (!this.isOwned) {
			this.proprietario = giocatore;
			this.isOwned = true;

		} else
			System.out.println("titolo già attribuito a un altro giocatore");
	}

	public int contatore(Nave nave) {
		int count = 0;
		return count;
	}

	public Giocatore valutazione(List<Giocatore> giocatori) {

		return null;
	}
}