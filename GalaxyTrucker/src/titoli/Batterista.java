//Batterista: più componenti che usano batterie (motori doppi, cannoni doppi, scudi).
package titoli;

import java.util.List;
import componenti.*;
import game_logic.Giocatore;
import nave.*;

public class Batterista extends Titolo {

	public Batterista() {
		super("Batterista", "Più componenti che usano batterie");
	}

	/*
	 * per conteggiare i componenti ai fini dell'assegnazione del titolo di
	 * Batterista: più componenti che usano batterie (motori doppi, cannoni doppi,
	 * scudi).
	 */
	@Override
	protected int contatore(Nave nave) {
		int count = 0;
		for (int i = 0; i < nave.getPlancia().length; i++) {
			for (int j = 0; j < nave.getPlancia()[0].length; j++) {
				Casella[][] plancia;
				plancia = nave.getPlancia();
				if (plancia[i][j].isUtilizzabile()) {
					Componente c = plancia[i][j].getComponente();
					if (c.getRichiedeBatterie()) {
						count++;
					}
				}
			}
		}
		return count;
	}

}
