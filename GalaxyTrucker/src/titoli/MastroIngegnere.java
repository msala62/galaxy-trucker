//Mastro Ingegnere: più componenti totali sulla nave.
package titoli;

import java.util.List;
import componenti.*;
import game_logic.Giocatore;
import nave.*;

public class MastroIngegnere extends Titolo {

	public MastroIngegnere() {
		super("Mastro Ingegnere","Più componenti sulla nave");
		// TODO Auto-generated constructor stub
	}

	/*
	 * per conteggiare i componenti ai fini dell'assegnazione del titolo di Mastro
	 * Ingegnere: maggiore è il numero totale di componenti sulla nave.
	 */
	@Override
	protected int contatore(Nave nave) {
		int count = 0;
		Casella[][] plancia;
		plancia = nave.getPlancia();
		for (int i = 0; i < plancia.length; i++) {
			for (int j = 0; j < plancia[0].length; j++) {
				if (plancia[i][j].isUtilizzabile()) {
					Componente c = plancia[i][j].getComponente();
					if (c != null) {
						count++;
					}
				}
			}
		}

		return count;
	}
}
