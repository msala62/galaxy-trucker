
/*
 * Evento di danno. Vengono lanciati dadi per determinare impatti su righe/colonne. Piccoli meteoriti
 * si bloccano con scudi; grandi devono essere abbattuti con cannoni, altrimenti distruggono pezzi.
 * 
 * */
package carteAvventura;

import java.util.List;
import java.util.Random;

import componenti.Componente;
import componenti.Connettore;
import game_logic.Giocatore;
import nave.*;

public class PioggiaDiMeteoriti extends Carta {

	private List<Meteorite> meteorite;
	private Random random = new Random();

	public PioggiaDiMeteoriti(Livello livello, List<Meteorite> Meteorite) {
		super(livello, "Pioggia Di Meteoriti");
		this.meteorite = Meteorite;
	}

	private boolean verificaPlanciaDimensioneVSDado(int dado, Nave nave, Meteorite meteorite) {
		if (meteorite.getDirezione() == MeteoriteDirezione.FRONTE
				|| meteorite.getDirezione() == MeteoriteDirezione.RETRO) {
			if (dado > nave.getPlancia().length)
				System.out.println("meteorite non colpisce la nave");
			return false;
		}
		if (meteorite.getDirezione() == MeteoriteDirezione.LATO_DESTRA
				|| meteorite.getDirezione() == MeteoriteDirezione.LATO_SINISTRA) {
			if (dado > nave.getPlancia()[0].length)
				System.out.println("meteorite non colpisce la nave");
			return false;
		}
		return true;
	}

	@Override
	public void azione(Giocatore giocatore) {

		for (int i = 0; i < meteorite.size(); i++) {
			int dado = random.nextInt(12) + 1;
			if (verificaPlanciaDimensioneVSDado(dado, giocatore.getNave(), meteorite.get(i))) {
				switch (meteorite.get(i).getDirezione()) {
				case FRONTE:
					colpisciDaFronte(giocatore, dado, meteorite.get(i));
					break;
				case RETRO:
					colpisciDaRetro(giocatore, dado, meteorite.get(i));
					break;
				case LATO_SINISTRA:
					colpisciDaSinistra(giocatore, dado, meteorite.get(i));
					break;
				case LATO_DESTRA:
					colpisciDaDestra(giocatore, dado, meteorite.get(i));
					break;
				}

			}

		}
	}

	private boolean colpisciDaFronte(Giocatore giocatore, int j, Meteorite m) {
		for (int r = 0; r < giocatore.getNave().getPlancia().length; r++) {
			if (colpisci(giocatore, r, j, m))
				return true;

		}
		return false;
	}

	private boolean colpisciDaRetro(Giocatore giocatore, int j, Meteorite m) {
		for (int r = giocatore.getNave().getPlancia().length - 1; r >= 0; r--) {
			if (colpisci(giocatore, r, j, m))
				return true;
		}
		return false;
	}

	private boolean colpisciDaSinistra(Giocatore giocatore, int i, Meteorite m) {
		for (int r = 0; r < giocatore.getNave().getPlancia()[0].length; r++) {
			if (colpisci(giocatore, i, r, m))
				return true;
		}
		return false;

	}

	private boolean colpisciDaDestra(Giocatore giocatore, int i, Meteorite m) {
		for (int r = giocatore.getNave().getPlancia()[0].length - 1; r >= 0; r--) {
			if (colpisci(giocatore, i, r, m))
				return true;
		}

		return false;

	}

	private boolean colpisci(Giocatore giocatore, int i, int j, Meteorite m) {
		if (j < giocatore.getNave().getPlancia()[0].length && i < giocatore.getNave().getPlancia().length) {
			Casella casella = giocatore.getNave().getPlancia()[i][j];
			if (casella.isUtilizzabile() && casella.getComponente() != null) {
				if (m.getDimensione() == MeteoriteDimensione.PICCOLO) {
					if (hasLatoLiscio(casella.getComponente(), m.getDirezione()))
					// penalità meteorite piccolo
					return true;
					else
					 //giocatore.getNave().distruggiComponente(i, j);
					return true;
				} else {
					//giocatore.getNave().distruggiComponente(i, j);
					return true;
				}
			}

		}
		return false;
	}

	private boolean hasLatoLiscio(Componente comp, MeteoriteDirezione direzione) {
		switch (direzione) {

		case FRONTE:
			if (comp.getConnettoreSU() == Connettore.LISCIO)
				return true;
			break;
		case RETRO:
			if (comp.getConnettoreGIU() == Connettore.LISCIO)
				return true;
			break;
		case LATO_SINISTRA:
			if (comp.getConnettoreSX() == Connettore.LISCIO)
				return true;

			break;
		case LATO_DESTRA:
			if (comp.getConnettoreDX() == Connettore.LISCIO)
				return true;
			break;

		}
		
		return false;
	}
}
