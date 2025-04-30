
//1Nemici Avanzati (Pirati e Schiavisti)
//Nemici speciali. Sconfitti = si ottengono crediti (non merci). Se sconfitti subisci cannonate (Pirati) o
//perdi membri equipaggio (Schiavisti).
package carteAvventura;

import java.util.List;

import game_logic.Giocatore;

public class Pirati extends Carta {

	private int potenzaRichiesta;
	private int giorniDaPerdere;
	private int creditiDaAquistare;
	private boolean isSconfitto;
	private List<Cannonata> cannonate;

	public Pirati(Livello livello, int potenzaRichiesta, int giorniDaPerdere, int creditiDaAquistare,
			List<Cannonata> cannonate) {
		super(livello, "Pirati");
		this.creditiDaAquistare = creditiDaAquistare;
		this.potenzaRichiesta = potenzaRichiesta;
		this.giorniDaPerdere = giorniDaPerdere;
		this.cannonate = cannonate;
	}

	public String getCartaInfo() {
		return "Potenza Richiesta: " + potenzaRichiesta + ", Giorni Da Perdere: " + giorniDaPerdere
				+ ", Crediti Da Aquistare" + creditiDaAquistare;
	}

	public void azione(List<Giocatore> giocatori) {
		for (int i = 0; i <= giocatori.size(); i++) {
			Giocatore giocatore = giocatori.get(i);
			if (!isSconfitto) {
				if (giocatore.getNave().getPotenzaFuoco() > potenzaRichiesta) {
					// TODO:Volo.cambiaPosizione(giocatore, giorniDaPerdere,-1);
					isSconfitto = true;
					System.out.println("i pirati sono stati sconfitti");
					if (giocatore.isLeader()) {
						giocatore.aggiungiCrediti(creditiDaAquistare);
					}
				} else if (giocatore.getNave().getPotenzaFuoco() < potenzaRichiesta) {
					for (int x = 0; i < cannonate.size(); i++) {
						cannonate.get(x).applicaSu(giocatore);
					}

				}
			}
		}
	}

}
