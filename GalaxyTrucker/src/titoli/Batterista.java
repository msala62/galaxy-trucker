//Batterista: più componenti che usano batterie (motori doppi, cannoni doppi, scudi).
package titoli;

import java.util.List;
import componenti.CabinaPartenza;
import componenti.Componente;
import componenti.Connettore;
import game_logic.Giocatore;
import nave.*;

public class Batterista extends Titolo {

	public Batterista() {
		super("Batterista");

	}

	/*
	 * per conteggiare i componenti ai fini dell'assegnazione del titolo di
	 * Batterista: più componenti che usano batterie (motori doppi, cannoni doppi,
	 * scudi).
	 */
	@Override
	public int contatore(Nave nave) {
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

	@Override
	public Giocatore valutazione(List<Giocatore> giocatori)

	{
		Giocatore giocatore;
		Giocatore vincetore = giocatori.getFirst();

		for (int i = 0; i < giocatori.size(); i++) {
			giocatore = giocatori.get(i);
			if (contatore(giocatore.getNave()) > contatore(vincetore.getNave()))
				vincetore = giocatori.get(i);
		}

		return vincetore;
	}

}
