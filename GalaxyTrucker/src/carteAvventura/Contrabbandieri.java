/* Nemico. Attacca i giocatori uno per uno. Serve confrontare la potenza di fuoco:
   se vinci, carichi merci; se perdi, perdi merci o batterie. In caso di pareggio, il nemico continua.
 * 
 * Metodi richiesti per questa carta:
 * 1- cambiaPosizione: per cambiare la posizione del giocatore nella plancia volo
 * 2- caricaMerci: per caricare dei merci
 * 3- eleminaMerci: se il giocatore viene sconfiito, deve perdere n numero di merci
 * */

package carteAvventura;

import java.util.List;
import game_logic.Giocatore;
import planciavolo.PlanciaVolo;
import merci.*;

public class Contrabbandieri extends Carta {

	private double potenzaRichiesta;
	private int giorniDaPerdere;
	private List<Cargo> cargo;
	private int cargoDaPerdere;
	private boolean isSconfitto = false;



	public Contrabbandieri(Livello livello, double potenzaRichiesta, int giorniDaPerdere, int cargoDaPerdere,
			List<Cargo> cargo)

	{
		super(livello, "Contrabbandieri");
		this.cargo = cargo;
		this.potenzaRichiesta = potenzaRichiesta;
		this.giorniDaPerdere = giorniDaPerdere;
		this.cargoDaPerdere = cargoDaPerdere;
	}

	@Override
	public String toString() {
		return "Carta: " + getNome() + "\n" + "Livello: " + getLivello() + "\n" + "Potenza richiesta: "
				+ potenzaRichiesta + "\n" + "Giorni da perdere: " + giorniDaPerdere + "\n" + "Cargo da caricare: "
				+ cargo + "\n" + "Cargo da perdere: " + cargoDaPerdere + "\n" + "Sconfitto: " + isSconfitto;
	}

	@Override
	public void azione(List<Giocatore> giocatori, PlanciaVolo plancia) {
		System.out.println("Evento: Contrabbandieri");
		System.out.println("Potenza richiesta per sconfiggerli: " + potenzaRichiesta);
		for (Giocatore giocatore : giocatori) {
			if (isSconfitto)
				return;
			
			double potenza = giocatore.getNave().getPotenzaFuoco();
			System.out.println("==================== Giocatore: " + giocatore.getNome()+"========================");
			System.out.println("  Potenza di fuoco: " + potenza);


			if (potenza > potenzaRichiesta) {
				isSconfitto = true;
				plancia.spostamentoGiocatore(giocatore, -giorniDaPerdere);
				giocatore.getNave().caricaCargo(cargo);
				System.out.println("  Risultato: VITTORIA");
				System.out.println("  Il giocatore sconfigge i contrabbandieri e carica le seguenti merci: " + cargo);
				System.out.println("  Perde " + giorniDaPerdere + " giorni di volo.");

			} else if (potenza < potenzaRichiesta)

			{
				System.out.println("  Risultato: SCONFITTA");
				System.out.println("  Il giocatore perde le seguenti merci: " + cargoDaPerdere);
				giocatore.getNave().eliminaCargo(cargoDaPerdere); //il nemico non è sconfitto, penalita= n merci da perdere
			} else {
				System.out.println("  Risultato: PAREGGIO");
				System.out.println("  Nessuna conseguenza per il giocatore, ma i contrabbandieri non sono sconfitti.");
			}

		}

	}

}
