//Mastro Ingegnere: più componenti totali sulla nave.
package titoli;

import java.util.List;

import componenti.Componente;
import game_logic.Giocatore;
import nave.Casella;
import nave.Nave;

public class MastroIngegnere extends Titolo {

	public MastroIngegnere() {
		super("Mastro Ingegnere");
		// TODO Auto-generated constructor stub
	}

	/*
	 * per conteggiare i componenti ai fini dell'assegnazione del titolo di Mastro
	 * Ingegnere: maggiore è il numero totale di componenti sulla nave.
	 */
	@Override
	public int contatore(Nave nave) {
		int count = 0;
		Casella[][] plancia;
		plancia = nave.getPlancia();
		for (int i = 0; i < plancia.length; i++) {
			for (int j = 0; j < plancia[0].length; j++) {
				if (plancia[i][j].isUtilizzabile()) {
					Componente c = plancia[i][j].getComponente();
					if (c != null)
						count++;
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
