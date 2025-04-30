package game_logic;

import nave.Nave;

import java.util.ArrayList;
import java.util.List;

public class Giocatore {
	public final Nave nave;
	public final String nome;
	private int crediti;
	private List<String> titoli;
	private boolean isLeader;

	public Giocatore(String nome) {
		this.nave = new Nave();
		this.nome = nome;
		this.crediti = 0;
		this.titoli = new ArrayList<String>();
	}

	public Nave getNave() {
		return this.nave;
	}

	@Override
	public String toString() {
		StringBuilder riepilogo = new StringBuilder();
		riepilogo.append("Nome giocatore: " + nome + "\n");
		riepilogo.append("Titoli giocatore: " + "\n");

		for (String titolo : titoli) {
			riepilogo.append(titolo + "\n");
		}

		return riepilogo.toString();
	}

	// GEORGE: Metodo per assegnare crediti ai giocatori
	public void aggiungiCrediti(int creditoDaAggiungere) {
		this.crediti = this.crediti + creditoDaAggiungere;
	}

	public boolean isLeader() {
		return isLeader;
	}

	public void setLeader(boolean isLeader) {
		this.isLeader = isLeader;
	}
}
