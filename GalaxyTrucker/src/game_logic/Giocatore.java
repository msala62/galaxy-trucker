package game_logic;

import nave.Nave;
import nave.NaveLivello1;
import nave.NaveLivello2;
import nave.NaveLivello3;
import planciavolo.Livello1;
import planciavolo.Livello2;
import planciavolo.Livello3;

import java.util.ArrayList;
import java.util.List;

import carteAvventura.Livello;
import componenti.Componente;

public class Giocatore {
	private static int ID_COUNTER = 0;
	
	private final int id;
	private Nave nave = null;
	public final String nome;
	private int crediti;
	private List<String> titoli;
	private List<Componente> componentiPrenotati;
	private boolean isLeader;

	public Giocatore(String nome) {
		this.id = ID_COUNTER;
		this.nome = nome;
		this.crediti = 0;
		this.titoli = new ArrayList<String>();
		this.componentiPrenotati = new ArrayList<Componente>();
		this.isLeader = false;
		
		++ID_COUNTER;
	}
	
	public void InizializzaNave(Livello livello) {
		switch(livello) {
		case Livello.I:
			this.nave = new NaveLivello1();
			break;
		case Livello.II:
			this.nave = new NaveLivello2();
			break;
		case Livello.III:
			this.nave = new NaveLivello3();
			break;
		default:
			break;
		}
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
	
	public int getCrediti() {
		return this.crediti;
	}
	
	public String getNome() {
		return nome;
	}

	public List<Componente> getComponentiPrenotati() {
		return componentiPrenotati;
	}

	public void aggiungiComponentiPrenotati(Componente comp) {
		componentiPrenotati.add(comp);
	}
}
