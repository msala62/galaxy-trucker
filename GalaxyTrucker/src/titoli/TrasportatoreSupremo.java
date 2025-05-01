//Trasportatore Supremo: più componenti con almeno un cubetto merce.
package titoli;

import java.util.List;

import componenti.Componente;
import game_logic.Giocatore;
import nave.*;

public class TrasportatoreSupremo extends Titolo {

	public TrasportatoreSupremo() {
		super("Trasportatore Supremo", " Più componenti con merci");
	}

	@Override
	protected int contatore(Nave nave) {
		int count = 0;
		Casella[][] plancia;
		plancia = nave.getPlancia();
		for (int i = 0; i < plancia.length; i++) {
			for (int j = 0; j < plancia[0].length; j++) {
				if (plancia[i][j].isUtilizzabile()) {
					Componente c = plancia[i][j].getComponente();
					if (c.tryGetCargoCorrente() > 0) {
						count++;
					}
				}

			}
		}

		return count;
	}

}
