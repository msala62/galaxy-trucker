package titoli;

import java.util.List;
import game_logic.Giocatore;
import nave.*;

public class Titolo {

	private String titolo;
	private String descrizione;
	private Giocatore proprietario;
	private int creditiPremio = 2;
	private boolean isGold = false;
	private boolean isOwned = false;

	public Titolo(String titolo, String descrizione) {
		this.titolo = titolo;
		this.descrizione = descrizione;
	}

	public void passaAGold() {
		this.isGold = true;
		this.creditiPremio = 12;

	}

	public Giocatore getProprietario() {
		return proprietario;
	}
	
	public String  stampaTitolo() {
		return titolo + ": " + descrizione;
	}

	public void assegnaTitolo(Giocatore giocatore) {
		this.proprietario = giocatore;
		this.isOwned = true;

	}

	protected  int contatore(Nave nave) {
		
		return -1;
	}
	public Giocatore valutazione(List<Giocatore> giocatori)

	{
		Giocatore giocatore;
		Giocatore vincetore = giocatori.getFirst();

		for (int i = 0; i < giocatori.size(); i++) {
			giocatore = giocatori.get(i);
			if (contatore(giocatore.getNave()) > contatore(vincetore.getNave())) {
				vincetore = giocatori.get(i);
			}

		}

		return vincetore;
	}
}