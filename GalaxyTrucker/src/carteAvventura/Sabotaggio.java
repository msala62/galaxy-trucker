package carteAvventura;

import java.util.List;
import java.util.Random;

import game_logic.Giocatore;
import planciavolo.PlanciaVolo;

public class Sabotaggio extends Carta {
	int tentativi = 0;
	boolean colpito = false;
	private Random random = new Random();

	public Sabotaggio(Livello livello) {
		super(livello, "Sabotaggio");
	}

	@Override
	public void azione(List<Giocatore> giocatori, PlanciaVolo plancia) {
		Giocatore gMinorEquipaggio = minorEquipaggio(giocatori);
		System.out.println(" Carta Avventura: Sabotaggio");
	    System.out.println(" Il giocatore con meno equipaggio subisce la distruzione di un componente scelto casualmente (tiro di dadi)");
		System.out.println(">> Giocatore con meno equipaggio: " + gMinorEquipaggio.getNome()
				+ " (equipaggio: " + gMinorEquipaggio.getNave().getEquipaggioTotale() + ")");

		while (tentativi < 3 && !colpito) {
			int i = random.nextInt(12);
			int j = random.nextInt(12);
			System.out.println("Tentativo " + (tentativi + 1) + ": controllo cella [" + i + "," + j + "]...");

			if (gMinorEquipaggio.getNave().getPlancia()[i][j].isUtilizzabile()
					&& gMinorEquipaggio.getNave().getPlancia()[i][j].getComponente() != null) {
				System.out.println(">> Componente trovato in [" + i + "," + j + "]: "
						+ gMinorEquipaggio.getNave().getPlancia()[i][j].getComponente().getClass().getSimpleName());
				gMinorEquipaggio.getNave().eliminaComponente(i, j);
				System.out.println(">> Componente distrutto.");
				colpito = true;
			} else {
				System.out.println(">> Nessun componente valido trovato in [" + i + "," + j + "].");
				tentativi++;
			}
		}

		if (!colpito) {
			System.out.println(">> Sabotaggio fallito: nessun componente colpito dopo 3 tentativi.");
		}
	}

	private Giocatore minorEquipaggio(List<Giocatore> giocatori) {
		Giocatore gMinorEquipaggio = giocatori.getFirst();
		for (int i = 0; i < giocatori.size(); i++) {
			int equipaggioAttuale = giocatori.get(i).getNave().getEquipaggioTotale();
			int equipaggioMin = gMinorEquipaggio.getNave().getEquipaggioTotale();

			if (equipaggioAttuale < equipaggioMin) {
				gMinorEquipaggio = giocatori.get(i);
			} else if (equipaggioAttuale == equipaggioMin) {
				// TODO: gestire il pareggio in base alla posizione sulla rotta
			}
		}
		return gMinorEquipaggio;
	}
}
