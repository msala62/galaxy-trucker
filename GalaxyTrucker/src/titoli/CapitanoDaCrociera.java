package titoli;

import java.util.List;
import componenti.*;
import game_logic.Giocatore;
import nave.*;

public class CapitanoDaCrociera extends Titolo {

	public CapitanoDaCrociera() {
		super("Capitano Da Crociera", "Più cabine occupate con vista");
	}

	private boolean conVista(CabinaPartenza cabina) {
		/*
		 * Verifica se è occupata (cioè contiene almeno 1 pedina). Verifica se ha almeno
		 * un lato liscio libero, cioè non tocca un’altra tessera.
		 */

		if (cabina.getEquipaggio() > 0
			&& cabina.getConnettoreDX() == Connettore.LISCIO
			|| cabina.getConnettoreSX() == Connettore.LISCIO
			|| cabina.getConnettoreGIU() == Connettore.LISCIO
			|| cabina.getConnettoreSU() == Connettore.LISCIO)
			return true;
		return false;
		
	}
	

	@Override
	protected int contatore(Nave nave) {
		
		/*
		 * per conteggiare le cabine occupate con vista ai fini dell'assegnazione del
		 * titolo Capitano da Crociera: più cabine occupate con vista.
		 */
		
		int count = 0;
		Casella[][] plancia;
		plancia = nave.getPlancia();
		for (int i = 0; i < plancia.length; i++) {
			for (int j = 0; j < plancia[0].length; j++) {
				if (plancia[i][j].isUtilizzabile()) {
					if (plancia[i][j].getComponente() instanceof CabinaPartenza) {
						CabinaPartenza cabina = (CabinaPartenza) plancia[i][j].getComponente(); // L'equipaggio può essere presente solo nella cabina di partenza, e cabina(sottoclasse di cabina di partenza)
						if (conVista(cabina))
							count++;
						
					}

				}

			}

		}

		return count;

	}
}
