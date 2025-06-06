package carteAvventura;

import java.util.*;
import componenti.Componente;
import componenti.Connettore;
import componenti.*;
import game_logic.Giocatore;
import game_logic.LettoreInput;
import nave.Casella;
import nave.Nave;

public class Meteorite {

	public enum Dimensione {
		PICCOLO, GROSSO;
	}

	public enum Direzione {
		SX, DX, SU, GIU;
	}

	private LettoreInput l = new LettoreInput();
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
				} else {
					 giocatore.getNave().eliminaComponente(c.getPosizione().getX(), c.getPosizione().getY());
					System.out.println("Componente distrutto dal meteorite piccolo!");
				}

			} else {
				// Meteorite grosso – nessuna difesa se non con cannone (non gestito qui) TODO
				if (proteggiConCannone(giocatore.getNave(), direzione, dado)) {
					System.out.println("Un cannone ha distrutto il meteorite grosso in arrivo!");
				} else {
					giocatore.getNave().eliminaComponente(c.getPosizione().getX(), c.getPosizione().getY());
					System.out.println("Componente distrutto dal meteorite grosso!");
				}
			}
		} else {
			System.out.println("Il meteorite ha mancato la nave.");
		}
		System.out.println("--- Fine Meteorite ---\n");
	}

	public Casella getCasellaDacolpire(Nave nave, Meteorite.Direzione direzione, int coordinata) {
		Casella[][] plancia = nave.getPlancia();
		int ROWS = plancia.length;
		int COLS = plancia[0].length;

		if (ROWS == 0 || COLS == 0) return null;

		switch (direzione) {
		case SU:
			if (coordinata >= 1 && coordinata <= COLS) {
				int actualColumnIndex = coordinata - 1;
				for (int i = 0; i < ROWS; i++) {
					if (plancia[i][actualColumnIndex].getComponente() != null)
						return plancia[i][actualColumnIndex];
				}
			}
			return null;

		case GIU:
			if (coordinata >= 1 && coordinata <= COLS) {
				int actualColumnIndex = coordinata - 1;
				for (int i = ROWS - 1; i >= 0; i--) {
					if (plancia[i][actualColumnIndex].getComponente() != null)
						return plancia[i][actualColumnIndex];
				}
			}
			return null;

		case SX:
			if (coordinata >= 1 && coordinata <= ROWS) {
				int actualRowIndex = coordinata - 1;
				for (int j = 0; j < COLS; j++) {
					if (plancia[actualRowIndex][j].getComponente() != null)
						return plancia[actualRowIndex][j];
				}
			}
			return null;

		case DX:
			if (coordinata >= 1 && coordinata <= ROWS) {
				int actualRowIndex = coordinata - 1;
				for (int j = COLS - 1; j >= 0; j--) {
					if (plancia[actualRowIndex][j].getComponente() != null)
						return plancia[actualRowIndex][j];
				}
			}
			return null;
		}
		return null;
	}

	private boolean proteggiConCannone(Nave nave, Direzione direzione, int coordinata) {

		Cannone cannone = trovaCannonePerDifesa(nave, direzione, coordinata);

		if (cannone == null) {
			return false;
		}

		if (cannone instanceof CannoneDoppio) {
			// CannoneDoppio
			Batteria b = trovaBatteria(nave);
			if (b != null && b.getCarica() > 0) {
				System.out.println("Trovato un cannone doppio e una batteria. Vuoi usare 1 carica per distruggere il meteorite? (s/n)");
				
				String risposta = l.leggiString();
				if (risposta.equalsIgnoreCase("s")) {
					if (((CannoneDoppio) cannone).getPotenza(b) > 0) {
						System.out.println("Cannone doppio attivato usando una batteria.");
						return true;
					} else {
						System.out.println("Impossibile attivare il cannone doppio, batteria esaurita.");
						return false;
					}
				} else {
					System.out.println("Hai scelto di non attivare il cannone doppio.");
					return false;
				}
			} else {
				System.out.println("Il cannone doppio non ha trovato una batteria carica per attivarsi.");
				return false;
			}
		}

		// in caso di cannone normale (NON RICHIEDE ATTIVAZIONE)
		return true;
	}

	private Direzione getOpposite(Direzione d) {
		switch (d) {
			case SU: return Direzione.GIU;
			case GIU: return Direzione.SU;
			case SX: return Direzione.DX;
			case DX: return Direzione.SX;
		}
		return null; // Should not be reached
	}

	private Cannone trovaCannonePerDifesa(Nave nave, Direzione direzione, int coordinata) {
		int ROWS = nave.getPlancia().length;
		int COLS = nave.getPlancia()[0].length;

		switch (direzione) {
		case SU:
		case GIU:
			if (coordinata >= 1 && coordinata <= COLS) {
				int col = coordinata - 1;
				for (int i = 0; i < ROWS; i++) {
					Casella casella = nave.getPlancia()[i][col];
					if (casella.isUtilizzabile() && casella.getComponente() instanceof Cannone) {
						Cannone c = (Cannone) casella.getComponente();
						if (c.getDirezione().name().equals(getOpposite(direzione).name())) {
							return c; // trovato
						}
					}
				}
			}
			break;
		case SX:
		case DX:
			if (coordinata >= 1 && coordinata <= ROWS) {
				int row = coordinata - 1;
				for (int i = 0; i < COLS; i++) {
					Casella casella = nave.getPlancia()[row][i];
					if (casella.isUtilizzabile() && casella.getComponente() instanceof Cannone) {
						Cannone c = (Cannone) casella.getComponente();
						if (c.getDirezione().name().equals(getOpposite(direzione).name())) {
							return c; // trovato
						}
					}
				}
			}
			break;
		}
		return null; 
	}

	private boolean proteggeComp(Nave nave, Direzione direzione) {
		Scudo s = trovaScudo(nave, direzione);
		Batteria b = trovaBatteria(nave);
		if (s != null && b != null )
		{
			String risposta = l.leggiString();
			if (risposta.equalsIgnoreCase("s")) {
				if (s.attivaScudo(b)) {					
					return true;
				} else {
					System.out.println("Impossibile attivare lo scudo");
					return false;
				}
			} else {
				System.out.println("Hai scelto di non attivare il cannone doppio.");
				return false;
			}
		}
		return false;
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
