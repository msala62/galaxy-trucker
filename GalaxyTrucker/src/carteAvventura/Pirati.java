
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
	
	@Override
	public String toString() {
		return "Carta: " + getNome() + "\n" +
		       "Livello: " + getLivello() + "\n" +
		       "Potenza richiesta: " + potenzaRichiesta + "\n" +
		       "Giorni da perdere: " + giorniDaPerdere + "\n" +
		       "Crediti da acquistare: " + creditiDaAquistare + "\n" +
		       "Sconfitto: " + isSconfitto + "\n" +
		       "Cannonate: " + cannonate;
	}


	

	@Override
	public void azione(List<Giocatore> giocatori) {
		System.out.println("Evento: Attacco dei Pirati");
        System.out.println("Potenza richiesta per sconfiggere i pirati: " + potenzaRichiesta);
        System.out.println("Chi riesce li sconfigge e guadagna " + creditiDaAquistare + " crediti.");
		for (Giocatore giocatore: giocatori) {
				if (!isSconfitto) {
				if (giocatore.getNave().getPotenzaFuoco() > potenzaRichiesta) {
					// TODO:Volo.cambiaPosizione(giocatore, giorniDaPerdere,-1);
					isSconfitto = true;
                    System.out.println(" I pirati sono stati sconfitti da " + giocatore.getNome() + "!");
					if (giocatore.isLeader()) {
                        System.out.println("Leader riceve " + creditiDaAquistare + " crediti.");
						giocatore.aggiungiCrediti(creditiDaAquistare);
                        System.out.println("Perde " + giorniDaPerdere + " giorni di volo.");
					}
					else {
                        System.out.println("Ma non essendo leader, non ottiene la ricompensa.");
                    }
					
				} else if (giocatore.getNave().getPotenzaFuoco() < potenzaRichiesta) {
					System.out.println("Potenza insufficiente: subisce cannonate!");
					for (Cannonata cannonata: cannonate) {
						cannonata.applicaSu(giocatore);
					}

				} else {
                    System.out.println("Potenza pari: nessun effetto, ma i pirati non sono sconfitti.");
                }
			}
		}
		System.out.println("Fine attacco dei Pirati");
	}

}
