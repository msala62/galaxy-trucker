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

	public String getCartaInfo() {
		return "Equipaggio Da Perdere: " + equipaggioDaPerdere +
			   ", Giorni Da Perdere: " + giorniDaPerdere +
			   ", Crediti Da Acquistare: " + creditiDaAquistare +
			   ", Potenza Richiesta: " + potenzaRichiesta;
	}

	public void azione(List<Giocatore> giocatori) {
		for (int i = 0; i <= giocatori.size(); i++) {
			Giocatore giocatore = giocatori.get(i);

			if (!isSconfitto) {
				if (giocatore.getNave().getPotenzaFuoco() > potenzaRichiesta) {
					// TODO:Volo.cambiaPosizione(giocatore, giorniDaPerdere,-1);
					isSconfitto = true;
					System.out.println("Gli Schiavisti sono stati sconfitti");

					if (giocatore.isLeader()) {
						giocatore.aggiungiCrediti(creditiDaAquistare);
					}

				} else if (giocatore.getNave().getPotenzaFuoco() < potenzaRichiesta) {

					if (giocatore.getNave().getEquipaggioTotale() < equipaggioDaPerdere) {
						System.out.println("Giocatore costretto ad abbandonare la corsa");
						//TODO: giocatore.abbandonaCorsa();
					} else {
						giocatore.getNave().eliminaEquipaggio(equipaggioDaPerdere);
					}
				}
			}
		}
	}
}
