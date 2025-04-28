package titoli;

import java.util.List;

import componenti.Componente;
import componenti.Connettore;
import game_logic.Giocatore;
import nave.Casella;
import nave.Nave;

public class MastroCorridoista extends Titolo {

	public MastroCorridoista() {
		super("MastroCorridoista", "Catena più lunga di corridoi");
	}

	private boolean isComponenteCorridoio(Componente componente) {
		int count = 0;
		if (componente.getConnettoreDX() != Connettore.LISCIO)
			count++;
		if (componente.getConnettoreSX() != Connettore.LISCIO)
			count++;
		if (componente.getConnettoreGIU() != Connettore.LISCIO)
			count++;
		if (componente.getConnettoreSU() != Connettore.LISCIO)
			count++;

		if (count != 0 && count <= 2)
			return true;

		return false;
	}


	private int ricercaProfondita(Casella[][] plancia, boolean[][] visitato, int i, int j) {

		if (i < 0 || i >= plancia.length || j < 0 || j >= plancia[0].length)
			return 0;
		if (visitato[i][j])
			return 0;
		if (!plancia[i][j].isUtilizzabile() || plancia[i][j].getComponente() == null
				|| !isComponenteCorridoio(plancia[i][j].getComponente()))
			return 0;
		visitato[i][j] = true;
		int lunghezza = 1;
		lunghezza += ricercaProfondita(plancia, visitato, i + 1, j);
		lunghezza += ricercaProfondita(plancia, visitato, i - 1, j);
		lunghezza += ricercaProfondita(plancia, visitato, i, j + 1);
		lunghezza += ricercaProfondita(plancia, visitato, i, j - 1);

		return lunghezza;
	}

	@Override
	protected  int contatore(Nave nave) {
		int lunghezzaMassima = 0;
		Casella[][] plancia = nave.getPlancia();
		boolean[][] visitato = new boolean[plancia.length][plancia[0].length];
		for (int i = 0; i < plancia.length; i++) {
			for (int j = 0; j < plancia[0].length; j++) {
				if (plancia[i][j].isUtilizzabile() && plancia[i][j].getComponente() != null
						&& isComponenteCorridoio(plancia[i][j].getComponente())) {
					int lunghezza = ricercaProfondita(plancia, visitato, i, j);
					if (lunghezza > lunghezzaMassima)
						lunghezzaMassima = lunghezza;
				}
			}
		}
		return lunghezzaMassima;
	}

}