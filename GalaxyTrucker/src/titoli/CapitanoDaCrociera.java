package titoli;
import java.util.List;
import componenti.CabinaPartenza;
import componenti.Connettore;
import game_logic.Giocatore;
import nave.Casella;
import nave.Nave;
public class CapitanoDaCrociera extends Titolo {

	public CapitanoDaCrociera() {
		super("Capitano Da Crociera");
	}

	/*
	 * per conteggiare le cabine occupate con vista ai fini dell'assegnazione del
	 * titolo Capitano da Crociera: più cabine occupate con vista.
	 */

	@Override
	public int contatore(Nave nave) {
		int count = 0;
		Casella[][] plancia;
		plancia = nave.getPlancia();
		for (int i = 0; i < plancia.length; i++) {
			for (int j = 0; j < plancia[0].length; j++) {

				if (plancia[i][j].isUtilizzabile()) {
					if (plancia[i][j].getComponente() instanceof CabinaPartenza) {

						CabinaPartenza c = (CabinaPartenza) plancia[i][j].getComponente(); // L'equipaggio può
																							// essere presente solo
																							// nella cabina di
																							// partenza

						if (c.getEquipaggio() > 0 && c.getConnettoreDX() == Connettore.LISCIO
								|| c.getConnettoreSX() == Connettore.LISCIO || c.getConnettoreGIU() == Connettore.LISCIO
								|| c.getConnettoreSU() == Connettore.LISCIO)
							count++;
						/*
						 * Verifica se è occupata (cioè contiene almeno 1 pedina). Verifica se ha almeno
						 * un lato liscio libero, cioè non tocca un’altra tessera.
						 */

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
