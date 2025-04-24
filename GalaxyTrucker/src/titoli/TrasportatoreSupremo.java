//Trasportatore Supremo: più componenti con almeno un cubetto merce.
package titoli;

import java.util.List;

import componenti.Componente;
import game_logic.Giocatore;
import nave.*;

public class TrasportatoreSupremo extends Titolo {

	public TrasportatoreSupremo() {
		super("Trasportatore Supremo");
	}

	@Override
	public int contatore(Nave nave) {
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

	@Override
	public Giocatore valutazione(List<Giocatore> giocatori)

	{
		Giocatore giocatore;
		Giocatore vincetore = giocatori.getFirst();

		for (int i = 0; i < giocatori.size(); i++) {
			giocatore = giocatori.get(i);
			if (contatore(giocatore.getNave()) > contatore(vincetore.getNave())) {
				vincetore = giocatori.get(i);
			}

		}

		return vincetore;
	}

}
