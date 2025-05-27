package titoli;

import java.util.List;
import game_logic.Giocatore;
import nave.*;

public class Titolo {

	private String titolo;
	private String descrizione;
	private Giocatore proprietario;
	private int creditiPremio = 2;
	private boolean isArgento = false;
	private boolean isGold = false;
	private boolean isOwned = false;

	public Titolo(String titolo, String descrizione) {
		this.titolo = titolo;
		this.descrizione = descrizione;
	}
	
	public int getCreditoPremio() {
		return this.creditiPremio;
	}

	public void passaAGold() {
		this.isGold = true;
		this.creditiPremio = 12;

	}

	public void passaAdArgento() {
		this.isArgento = true;
		this.creditiPremio = 4;

	}

	public Giocatore getProprietario() {
		return proprietario;
	}

	public String stampaTitolo() {
		return titolo + ": " + descrizione;
	}

	public boolean assegnaTitolo(Giocatore giocatore) {
		if (!isOwned)
		{
			this.proprietario = giocatore;
			this.isOwned = true;
			return true;	
		}
		return false;
		
	}

	protected int contatore(Nave nave) {

		return -1;
	}

	public boolean assegnaCrediti() {
		if (proprietario != null) { 
			proprietario.aggiungiCrediti(creditiPremio);
			return true; // Restituisce true se i crediti sono stati assegnati
		}
		return false; // Restituisce false se non c'è un proprietario
	}
	public Giocatore valutazione(List<Giocatore> giocatori)

	{
		if (giocatori.isEmpty()) {
			return null; // Nessun giocatore da valutare
		}
		
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