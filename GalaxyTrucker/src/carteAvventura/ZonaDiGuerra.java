//DA CONTINUARE

/*
 * Evento di penalità multipla. Tre prove: minor equipaggio (perdita giorni),
 * minor potenza motrice (perdita membri equipaggio), minor potenza di fuoco (subire cannonate).*/

package carteAvventura;

import java.util.List;
import java.util.Random;

import game_logic.Giocatore;

public class ZonaDiGuerra extends Carta {

	private List<Cannonata> cannonate;
	private Random random = new Random();

	public ZonaDiGuerra(Livello livello, List<Cannonata> cannonate) {
		super(livello, "Zona Di Guerra");
		this.cannonate = cannonate;
		// TODO Auto-generated constructor stub
	}

	@Override
	public String toString() {
		return "Carta: " + getNome() + "\n" + "Livello: " + getLivello() + "\n" + "Cannonate: " + cannonate;
	}

	public void azione(List<Giocatore> giocatori) {
		System.out.println("evento: Zona di Guerra");
		System.out.println(
				"Tre penalità verranno applicate ai giocatori peggiori per: Equipaggio, Potenza Motrice, Potenza di Fuoco.");

		Giocatore gEquipaggio = minorEquipaggio(giocatori);
		System.out.println(">> Giocatore con minor equipaggio: " + gEquipaggio.getNome());
		penalitaMinorEquipaggio(gEquipaggio);

		Giocatore gMotore = minorPotenzaMotrice(giocatori);
		System.out.println(">> Giocatore con minor potenza motrice: " + gMotore.getNome());
		penalitaMinorPotenzaMotrice(gMotore);

		Giocatore gFuoco = minorPotenzaFuoco(giocatori);
		System.out.println(">> Giocatore con minor potenza di fuoco: " + gFuoco.getNome());
		penalitaMinorPotenzaFuoco(gFuoco);
	}

	private void penalitaMinorEquipaggio(Giocatore giocatore) {
		 System.out.println(">> Penalità per minor equipaggio:");
		if (this.livello == Livello.I) {
			System.out.println("Perde 3 giorni di volo.");
			/* Volo.cambiaPosizione(giocatore, 3, -1) */
			// TODO: implementare cambio posizione

		} else if (this.livello == Livello.II) {
			 System.out.println("Subisce cannonate");
			for (Cannonata cannonata : cannonate) {
				cannonata.applicaSu(giocatore);
			}

		} else {
			System.out.println("Perde cargo");
			// giocatore.getNave().eliminaCargo(null)
		}
	}

	private void penalitaMinorPotenzaMotrice(Giocatore giocatore) {
		System.out.println(">> Penalità per minor potenza motrice:");
		if (this.livello == Livello.I) {
			System.out.println("Perde 2 Equipaggi.");
			giocatore.getNave().eliminaEquipaggio(2);
		}

		else if (this.livello == Livello.II) {
			System.out.println("Perde Merci");
			// giocatore.getNave().eliminaCargo(null)

		} else {
			System.out.println("Subisce cannonate");
			for (Cannonata cannonata : cannonate) {
				cannonata.applicaSu(giocatore);
			}
		}
	}

	private void penalitaMinorPotenzaFuoco(Giocatore giocatore) {

		if (this.livello == Livello.I) {
			System.out.println("Subisce cannonate");
			for (Cannonata cannonata : cannonate) {
				cannonata.applicaSu(giocatore);
			}

		} else if (this.livello == Livello.II) {
			System.out.println("Perde 4 giorni di volo.");
			// Volo.cambiaPosizione(giocatore, 4, -1);

		} else {
			System.out.println("Perde 4 Equipaggi.");
			giocatore.getNave().eliminaEquipaggio(4);
		}
	}

	private Giocatore minorEquipaggio(List<Giocatore> giocatori) {
		Giocatore gMinorEquipaggio = giocatori.getFirst();
		for (int i = 0; i < giocatori.size(); i++) {
			if (giocatori.get(i).getNave().getEquipaggioTotale() < gMinorEquipaggio.getNave().getEquipaggioTotale())
				gMinorEquipaggio = giocatori.get(i);

			else if (giocatori.get(i).getNave().getEquipaggioTotale() == gMinorEquipaggio.getNave()
					.getEquipaggioTotale()) {
			} // pareggi si risolvono penalizzando il giocatore più avanti nella rotta:DA FARE
				// DOPO

		}

		return gMinorEquipaggio;
	}

	private Giocatore minorPotenzaMotrice(List<Giocatore> giocatori) {
		Giocatore gMinorPotenzaMotrice = giocatori.getFirst();
		for (int i = 0; i < giocatori.size(); i++) {
			if (giocatori.get(i).getNave().getPotenzaMotrice() < gMinorPotenzaMotrice.getNave().getPotenzaMotrice())
				gMinorPotenzaMotrice = giocatori.get(i);

			else if (giocatori.get(i).getNave().getPotenzaMotrice() == gMinorPotenzaMotrice.getNave()
					.getPotenzaMotrice()) {
				// pareggi si risolvono penalizzando il giocatore più avanti nella rotta:DA FARE
				// DOPO
			}
		}

		return gMinorPotenzaMotrice;
	}

	private Giocatore minorPotenzaFuoco(List<Giocatore> giocatori) {
		Giocatore gMinorPotenzaFuoco = giocatori.getFirst();
		for (int i = 0; i < giocatori.size(); i++) {
			if (giocatori.get(i).getNave().getPotenzaFuoco() < gMinorPotenzaFuoco.getNave().getPotenzaFuoco())
				gMinorPotenzaFuoco = giocatori.get(i);
			else if (giocatori.get(i).getNave().getPotenzaFuoco() < gMinorPotenzaFuoco.getNave().getPotenzaFuoco()) {
				// pareggi si risolvono penalizzando il giocatore più avanti nella rotta:DA FARE
				// DOPO
			}

		}

		return gMinorPotenzaFuoco;

	}

}
