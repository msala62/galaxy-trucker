/*
 * Evento negativo. Ogni giocatore perde 1 giorno di volo per ogni connettore esposto sulla propria nave.
 * Ordine inverso di rotta.*/

package carteAvventura;

import java.util.List;

import componenti.Componente;
import componenti.Connettore;
import game_logic.Giocatore;
import nave.*;

public class PolvereStellare extends Carta {
	private int giorniDaPerdere;

	public PolvereStellare(Livello livello) {
		super(livello, "Polvere Stellare");
		// TODO Auto-generated constructor stub
	}

	@Override
	public String toString() {
		return "Carta: " + getNome() + "\n" + "Livello: " + getLivello() + "\n" + "Giorni da perdere: "
				+ giorniDaPerdere;
	}

	@Override
	public void azione(List<Giocatore> giocatori) {

		System.out.println("Evento: Polvere Stellare");
		System.out.println("Ogni giocatore perderà 1 giorno di volo per ogni connettore esposto sulla propria nave.");
		for (Giocatore giocatore : giocatori) {
			giorniDaPerdere = 0;
			for (int i = 0; i < giocatore.getNave().getPlancia().length; i++) {
				for (int j = 0; j < giocatore.getNave().getPlancia()[0].length; j++) {
					giorniDaPerdere = giorniDaPerdere + latiEsposti(giocatore.getNave(), i, j);

				}
			}

			System.out.println("\n>> Giocatore: " + giocatore.getNome());
			System.out.println("Connettori esposti trovati: " + giorniDaPerdere);
			System.out.println("Perde " + giorniDaPerdere + " giorni di volo.");
			// TODO: Volo.cambiaPosizione(giocatore, giorniDaPerdere , -1)
		}

        System.out.println("Fine effetto Polvere Stellare");

	}

	public int latiEsposti(Nave nave, int i, int j) {
		int lati = 0;
		Componente comp = nave.getPlancia()[i][j].getComponente();
		if (comp == null)
			return 0;
		if (i + 1 >= nave.getPlancia().length || nave.getPlancia()[i + 1][j].getComponente() == null) {
			// giu
			if (comp.getConnettoreGIU() != Connettore.LISCIO)
				lati++;
		}

		if (i - 1 < 0 || nave.getPlancia()[i - 1][j].getComponente() == null) {
			// su
			if (comp.getConnettoreSU() != Connettore.LISCIO)
				lati++;
		}

		if (j + 1 >= nave.getPlancia()[0].length || nave.getPlancia()[i][j + 1].getComponente() == null) {
			// des
			if (comp.getConnettoreDX() != Connettore.LISCIO)
				lati++;
		}
		if (j - 1 < 0 || nave.getPlancia()[i][j - 1].getComponente() == null) {
			// sin
			if (comp.getConnettoreSX() != Connettore.LISCIO)
				lati++;
		}

		return lati;
	}
}
