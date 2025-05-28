package carteAvventura;

import java.util.*;

import componenti.Componente;
import componenti.Connettore;
import componenti.*;
import game_logic.Giocatore;
import nave.Casella;
import nave.Nave;

public class Meteorite {

	public enum Dimensione {
		PICCOLO, GROSSO;
	}

	public enum Direzione {
		SX, DX, SU, GIU;
	}

	private Dimensione dimensione;
	private Direzione direzione;
	private Random random = new Random();
	int dado = random.nextInt(12) + 1;

	public Meteorite(Dimensione dimensione, Direzione direzione) {
		this.dimensione = dimensione;
		this.direzione = direzione;
	}

	public Dimensione getDimensione() {
		return dimensione;
	}

	public void setDimensione(Dimensione dimensione) {
		this.dimensione = dimensione;
	}

	public Direzione getDirezione() {
		return direzione;
	}

	public void setDirezione(Direzione direzione) {
		this.direzione = direzione;
	}

	private boolean hasLatoLiscio(Componente comp) {
		 if (comp == null) 
	            return false;
	        
		switch (direzione) {

		case SU:
			if (comp.getConnettoreSU() == Connettore.LISCIO)
				return true;
			break;
		case GIU:
			if (comp.getConnettoreGIU() == Connettore.LISCIO)
				return true;
			break;
		case SX:
			if (comp.getConnettoreSX() == Connettore.LISCIO)
				return true;
			break;
		case DX:
			if (comp.getConnettoreDX() == Connettore.LISCIO)
				return true;
			break;

		}

		return false;
	}

	public void applicaSu(Giocatore giocatore) {
		System.out.println("\n Meteorite:");
	    System.out.println("Tipo: " + dimensione + ", Direzione: " + direzione + ", Coordinata: " + dado);

		Casella c = getCasellaDacolpire(giocatore.getNave(), direzione, dado);
		if (c != null) {
			 System.out.println("\n Colpisce la nave in posizione: [" + c.getPosizione() + "]");
			if (dimensione == Dimensione.PICCOLO) {
				if (hasLatoLiscio(c.getComponente())) {
					System.out.println("Il meteorite piccolo ha colpito un lato liscio: nessun danno.");
				} else if (proteggeComp(giocatore.getNave(), direzione)) {
					System.out.println("Scudo attivato: componente salvo.");
				}
				else {
					// giocatore.getNave().distruggiComponente(c);
					System.out.println("Componente distrutto dal meteorite piccolo!");
				}
				
			} else {
				// Meteorite grosso – nessuna difesa se non con cannone (non gestito qui)
				// giocatore.getNave().distruggiComponente(c);
				System.out.println("Componente distrutto dal meteorite grosso!");
			}
		} else {
			System.out.println("Il meteorite ha mancato la nave.");
		}
		System.out.println("--- Fine Meteorite ---\n");
	}

	public Casella getCasellaDacolpire(Nave nave, Meteorite.Direzione direzione, int coordinata) {
		switch (direzione) {
		case SU:
			if (coordinata >= 0 && coordinata < nave.getPlancia()[0].length) {
				for (int i = 0; i < nave.getPlancia().length; i++) {
					if (nave.getPlancia()[i][coordinata].getComponente() != null)
						return nave.getPlancia()[i][coordinata];
				}
			}
			return null;

		case GIU:
			if (coordinata >= 0 && coordinata < nave.getPlancia()[0].length) {
				for (int i = nave.getPlancia().length - 1; i >= 0; i--) {
					if (nave.getPlancia()[i][coordinata].getComponente() != null)
						return nave.getPlancia()[i][coordinata];
				}
			}
			return null;

		case SX:
			if (coordinata >= 0 && coordinata < nave.getPlancia().length) {
				for (int j = 0; j < nave.getPlancia()[0].length; j++) {

						return nave.getPlancia()[coordinata][j];
				}
			}
			return null;

		case DX:
			if (coordinata >= 0 && coordinata < nave.getPlancia().length) {
				for (int j = nave.getPlancia()[0].length - 1; j >= 0; j--) {
					if (nave.getPlancia()[coordinata][j].getComponente() != null)
						return nave.getPlancia()[coordinata][j];
				}
			}
			return null;
		}
		return null;
	}

	private boolean proteggeComp(Nave nave, Direzione direzione) {
		Scudo s = trovaScudo(nave, direzione);
		Batteria b = trovaBatteria(nave);
		return s != null && b != null && s.attivaScudo(b);
	}

	private Scudo trovaScudo(Nave nave, Direzione direzione) {
		for (int i = 0; i < nave.getPlancia().length; i++) {
			for (int j = 0; j < nave.getPlancia()[0].length; j++) {
				Casella[][] plancia = nave.getPlancia();
				if (plancia[i][j].isUtilizzabile() && plancia[i][j].getComponente() instanceof Scudo) {
					Scudo s1 = (Scudo) plancia[i][j].getComponente();
					if (s1.getDirScudo1().equals(direzione) || s1.getDirScudo2().equals(direzione)) {
						return s1;
					}
				}
			}
		}
		return null;
	}

	private Batteria trovaBatteria(Nave nave) {
		for (int i = 0; i < nave.getPlancia().length; i++) {
			for (int j = 0; j < nave.getPlancia()[0].length; j++) {
				Casella[][] plancia = nave.getPlancia();
				if (plancia[i][j].isUtilizzabile() && plancia[i][j].getComponente() instanceof Batteria) {
					return (Batteria) plancia[i][j].getComponente();
				}
			}
		}
		return null;
	}

}
