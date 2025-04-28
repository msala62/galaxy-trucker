//Xenoquartiermastro: somma delle distanze degli alieni dalla cabina più vicina
package titoli;

import java.util.List;

import componenti.CabinaPartenza;
import nave.*;
import game_logic.Giocatore;

public class Xenoquartiermastro extends Titolo {

	public Xenoquartiermastro() {
		super("Xenoquartiermastro","Alieni più lontani dalle cabine.");
	}

	@Override
	protected  int contatore(Nave nave) {
		int count = 0;
		Casella[][] plancia;
		plancia = nave.getPlancia();
		for (int i = 0; i < plancia.length; i++) {
			for (int j = 0; j < plancia[0].length; j++) {
				if (plancia[i][j].isUtilizzabile()) {
					if (plancia[i][j].getComponente() instanceof CabinaPartenza) {
						int distanzaMinima = 999999999;
						for (int x = 0; x < plancia.length; x++) {
							for (int y = 0; y < plancia[0].length; y++) {
								if ((x != i || y != j) && plancia[x][y].getComponente() instanceof CabinaPartenza) {
									int dist = Math.abs(x - i) + Math.abs(y - j);
									if (dist < distanzaMinima) {
										distanzaMinima = dist;
									}
								}
							}
						}
						if (distanzaMinima != 999999999) {
							count += distanzaMinima;
						}
					}
				}
			}
		}
		return count;

	}

}
