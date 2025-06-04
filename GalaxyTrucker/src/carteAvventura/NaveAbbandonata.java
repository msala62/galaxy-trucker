/*  Evento singolo. Il primo giocatore che sceglie può sacrificare membri
 *	dell'equipaggio per ottenere crediti. Costo in giorni di volo. Se nessuno accetta, si passa al successivo.
 *  Metodi richiesti per questa carta: 
 
 * 3-cambiaPosizione: per cambiare la posizione del giocatore nella plancia volo
 * */

package carteAvventura;

import java.util.*;
import game_logic.Giocatore;
import game_logic.LettoreInput;
import planciavolo.PlanciaVolo;

public class NaveAbbandonata extends Carta {

	private int equipaggioDaPerdere;
	private int giorniDaPerdere;
	private int creditiDaAquistare;
	private boolean isUsed = false;

	private static LettoreInput sc = new LettoreInput();
	
	public NaveAbbandonata(Livello livello, int equipaggioDaPerdere, int giorniDaPerdere, int creditiDaAquistare) {
		super(livello, "NaveAbbandonata");
		this.creditiDaAquistare = creditiDaAquistare;
		this.equipaggioDaPerdere = equipaggioDaPerdere;
		this.giorniDaPerdere = giorniDaPerdere;
	}

	@Override
	public String toString() {
		return "Carta: " + getNome() + "\n" + "Livello: " + getLivello() + "\n" + "Equipaggio da perdere: "
				+ equipaggioDaPerdere + "\n" + "Giorni da perdere: " + giorniDaPerdere + "\n"
				+ "Crediti da acquistare: " + creditiDaAquistare + "\n" + "Usata: " + isUsed;
	}

	@Override
	public void azione(List<Giocatore> giocatori, PlanciaVolo plancia) {
		System.out.println("Evento: Nave Abbandonata!");
		System.out.println("Il primo giocatore che accetta può perdere " + equipaggioDaPerdere
				+ " membri dell'equipaggio per ottenere " + creditiDaAquistare + " crediti, ma perderà "
				+ giorniDaPerdere + " giorni di volo.");

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

				if (giocatore.getNave().getEquipaggioTotale() >= equipaggioDaPerdere && isUsed == false) {
					plancia.spostamentoGiocatore(giocatore, -giorniDaPerdere);
					if (giocatore.getNave().eliminaEquipaggio(equipaggioDaPerdere)) {
						isUsed = true;
						giocatore.aggiungiCrediti(creditiDaAquistare);
						System.out.println(giocatore.getNome() + " ha accettato e ottenuto " + creditiDaAquistare + " crediti.");
					} else {
						System.out.println("Errore: non è stato possibile eliminare l'equipaggio.");
					}
				} else {
					System.out.println("Equipaggio Insufficiente");
				}

			}

			// se no
			else {
				System.out.println(giocatore.getNome() + " ha rifiutato l'offerta.");
			}

		}
	};

}
