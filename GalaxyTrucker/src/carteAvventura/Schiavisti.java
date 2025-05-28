package carteAvventura;

import java.util.List;
import game_logic.Giocatore;

public class Schiavisti extends Carta {

	private int potenzaRichiesta;
	private int equipaggioDaPerdere;
	private int giorniDaPerdere;
	private int creditiDaAquistare;
	private boolean isSconfitto;

	public Schiavisti(Livello livello, int potenzaRichiesta, int equipaggioDaPerdere, int giorniDaPerdere,
			int creditiDaAquistare) {
		super(livello, "Schiavisti");
		this.potenzaRichiesta = potenzaRichiesta;
		this.creditiDaAquistare = creditiDaAquistare;
		this.equipaggioDaPerdere = equipaggioDaPerdere;
		this.giorniDaPerdere = giorniDaPerdere;
		this.isSconfitto = false;
	}

	@Override
	public String toString() {
		return "Carta: " + getNome() + "\n" +
		       "Livello: " + getLivello() + "\n" +
		       "Potenza richiesta: " + potenzaRichiesta + "\n" +
		       "Equipaggio da perdere: " + equipaggioDaPerdere + "\n" +
		       "Giorni da perdere: " + giorniDaPerdere + "\n" +
		       "Crediti da acquistare: " + creditiDaAquistare + "\n" +
		       "Sconfitto: " + isSconfitto;
	}


	public void azione(List<Giocatore> giocatori) {
		
		System.out.println("Evento: Attacco Schiavisti");
		System.out.println("Potenza richiesta per sconfiggerli: " + potenzaRichiesta);
		System.out.println("Se non sconfitti, i giocatori perdono " + equipaggioDaPerdere + " membri dell'equipaggio.");
		
		for (Giocatore giocatore: giocatori) {
			System.out.println(">> Giocatore: " + giocatore.getNome());
			double potenza = giocatore.getNave().getPotenzaFuoco();
			System.out.println("Potenza di fuoco: " + potenza);
			if (!isSconfitto) {
				if (potenza > potenzaRichiesta) {
					// TODO:Volo.cambiaPosizione(giocatore, giorniDaPerdere,-1);
					System.out.println("Gli Schiavisti sono stati sconfitti da " + giocatore.getNome());
					System.out.println("Perde " + giorniDaPerdere + " giorni di volo.");
					isSconfitto = true;
					if (giocatore.isLeader()) {
						System.out.println("Leader guadagna " + creditiDaAquistare + " crediti.");
						giocatore.aggiungiCrediti(creditiDaAquistare);
					} else {
						System.out.println("Ma non essendo leader, non ottiene la ricompensa.");
					}

				} else if (potenza< potenzaRichiesta) {

					System.out.println("Potenza insufficiente: subisce la perdita di equipaggio!");
					if (giocatore.getNave().getEquipaggio() < equipaggioDaPerdere) {
						System.out.println("Equipaggio insufficiente )");
						System.out.println("Giocatore costretto ad abbandonare la corsa");
						//TODO: giocatore.abbandonaCorsa();
					} else {
						giocatore.getNave().eliminaEquipaggio(equipaggioDaPerdere);
						System.out.println("- Equipaggio perso: " + equipaggioDaPerdere);
					}
				}
			}
		}
		System.out.println("Fine effetto Schiavisti");

	}
}
