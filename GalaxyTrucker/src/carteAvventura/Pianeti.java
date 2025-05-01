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
	public String toString() {
		return "Carta:" + getNome() + "\n" + "livello=" + getLivello() + "\n" + "giorniDaPerdere=" + giorniDaPerdere
				+ "\n" + "pianetiDisponibili=" + pianetiDisponibili;
	}

	public int getGiorniDaPerdere() {
		return giorniDaPerdere;
	}

	public List<Pianeta> getPianeti() {
		return pianetiDisponibili;
	}

	@Override
	public void azione(List<Giocatore> giocatori) {
		Scanner scanner = new Scanner(System.in);
		for (Giocatore giocatore : giocatori) {
			if (pianetiDisponibili.isEmpty()) {
				System.out.println("Nessun pianeta disponibile per il giocatore " + giocatore.getNome()); // Tutti i pianeti sono già stati attaccati
				continue;
			}
			System.out.println("\n" + giocatore.getNome() + ", scegli un pianeta su cui atterrare (oppure -1 per passare):");
			for (int i = 0; i < pianetiDisponibili.size(); i++) {
				System.out.println(i + ": " + pianetiDisponibili.get(i));
			}
			int pianetaScelta = scanner.nextInt();
			if (pianetaScelta >= 0 && pianetaScelta < pianetiDisponibili.size()) {
				// TODO:Volo.cambiaPosizione(giocatore, giorniDaPerdere,-1)
				// giocatore.nave.caricaMerci(pianetiDisponibili(pianetaScelta));
				pianetiDisponibili.remove(pianetaScelta);

			}else {
				System.out.println(giocatore.getNome() + " ha deciso di non atterrare.");
			} 

		}

	}

}
