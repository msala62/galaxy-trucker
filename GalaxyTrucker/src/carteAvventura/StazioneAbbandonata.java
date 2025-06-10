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
import game_logic.LettoreInput;
import merci.*;
import planciavolo.PlanciaVolo;

public class StazioneAbbandonata extends Carta {

	private int equipaggioRichiesto;
	private int giorniDaPerdere;
	private List<Cargo> cargo;
	private boolean isUsed = false;

	private static LettoreInput sc = new LettoreInput();
	
	public StazioneAbbandonata(Livello livello, List<Cargo> cargo, int giorniDaPerdere, int equipaggioRichiesto) {
		super(livello, "Stazione Abbandonata");
		this.equipaggioRichiesto = equipaggioRichiesto;
		this.giorniDaPerdere = giorniDaPerdere;
		this.cargo = cargo;

	}

	@Override
	public String toString() {
		return "Carta: " + getNome() + "\n" + "Livello: " + getLivello() + "\n" + "Equipaggio richiesto: "
				+ equipaggioRichiesto + "\n" + "Giorni da perdere: " + giorniDaPerdere + "\n" + "Cargo: " + cargo;
	}

	@Override
	public void azione(List<Giocatore> giocatori, PlanciaVolo plancia) {
		
		System.out.println("======================= Evento: Stazione Abbandonata! =======================");
		System.out.println("È possibile recuperare: " + cargo + ".");
		System.out.println("L'operazione richiede un equipaggio di almeno " + equipaggioRichiesto + " membri e costerà " + giorniDaPerdere + " giorni di volo.");
		for (Giocatore giocatore : giocatori) {
			
			if (isUsed)
				break;

			System.out.println("==================== Giocatore: " + giocatore.getNome()+"========================");
			System.out.print("Vuoi accettare l'offerta? (s/n): ");
			String risposta = sc.leggiString().trim().toLowerCase();

			// verifica input
			while (!risposta.equals("s") && !risposta.equals("n")) {
				System.out.print("Risposta non valida. Digita 's' per sì o 'n' per no: ");
				risposta = sc.leggiString().trim().toLowerCase();
			}

			// se si
			if (risposta.equals("s")) {
				if (giocatore.getNave().getEquipaggioTotale() < equipaggioRichiesto) {
					System.out.println("gioicatore non può attraccare alla stazione"); //equipaggio minore di quello richiesto
				} else {
					plancia.spostamentoGiocatore(giocatore, -giorniDaPerdere);
					giocatore.getNave().caricaCargo(cargo);
					isUsed=true;
					System.out.println(giocatore.getNome() + " ha accettato e ottenuto " + cargo + " cargo.");

				}

			}

			// se no
			else {
				System.out.println(giocatore.getNome() + " ha rifiutato l'offerta.");
			}

		}
	}
}