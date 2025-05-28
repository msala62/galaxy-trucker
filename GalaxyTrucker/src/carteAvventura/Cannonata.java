package carteAvventura;

import java.util.Random;
import componenti.*;
import game_logic.*;
import nave.*;

public class Cannonata {

	public enum Dimensione {
		LEGGERA, PESANTE;
	}

	public enum Direzione {
		SX, DX, SU, GIU;
	}

	private Dimensione dimensione;
	private Direzione direzione;
	private Random random = new Random();
	int dado = random.nextInt(12) + 1;

	public Cannonata(Dimensione dimensione, Direzione direzione) {
		this.dimensione = dimensione;
		this.direzione = direzione;
	}

	public Dimensione getDimensione() {
		return dimensione;
	}

	public void setDimensione(Dimensione dimensione) {
		this.dimensione = dimensione;
	}

	public Direzione sgetDirezione() {
		return direzione;
	}

	public void setDirezione(Direzione direzione) {
		this.direzione = direzione;
	}

	public void applicaSu(Giocatore giocatore) {
		
		System.out.println("\nCannonata in arrivo su " + giocatore.getNome());
        System.out.println("Tipo: " + dimensione + ", Direzione: " + direzione + ", Coordinata: " + dado);

		Casella c = getCasellaDacolpire(giocatore.getNave(), direzione, dado);
		if (c!=null) {
			System.out.println("Colpisce la nave in posizione [" + c.getPosizione() + "]");
			if (dimensione == Dimensione.LEGGERA && proteggeComp(giocatore.getNave(), direzione)) {
				System.out.println("Scudo attivato: componente salvo.");
			} else {
				 giocatore.getNave().eliminaComponente(c.getPosizione().getX(), c.getPosizione().getY());
				System.out.println("Componente distrutto!");
			}
		} else {
			System.out.println("Il colpo è andato a vuoto.");
		}
	}

	public Casella getCasellaDacolpire(Nave nave, Cannonata.Direzione direzione, int coordinata) {
		switch (direzione) {
			case SU:
				if (coordinata > 0 && coordinata <= nave.getPlancia()[0].length) {
					for (int i = 0; i < nave.getPlancia().length; i++) {
						if (nave.getPlancia()[i][coordinata].getComponente() != null)
							return nave.getPlancia()[i][coordinata];
					}
				}
				return null;
   
			case GIU:
				if (coordinata > 0 && coordinata <= nave.getPlancia()[0].length) {
					for (int i = nave.getPlancia().length; i > 0; i--) {
						if (nave.getPlancia()[i][coordinata].getComponente() != null)
							return nave.getPlancia()[i][coordinata];
					}
				}
				return null;

			case SX:
				if (coordinata > 0 && coordinata <= nave.getPlancia().length) {
					for (int j = 0; j < nave.getPlancia()[0].length; j++) {
						if (nave.getPlancia()[coordinata][j].getComponente() != null)
							return nave.getPlancia()[coordinata][j];
					}
				}
				return null;

			case DX:
				if (coordinata > 0 && coordinata <= nave.getPlancia().length) {
					for (int j = nave.getPlancia()[0].length; j > 0; j--) {
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
		if (s != null && b != null) {
			if (s.attivaScudo(b))
				return true;
		}
		return false;
	}

	private Scudo trovaScudo(Nave nave, Direzione direzione) {
		Scudo s = null;
		outerLoop:
		for (int i = 0; i < nave.getPlancia().length; i++) {
			for (int j = 0; j < nave.getPlancia()[0].length; j++) {
				Casella[][] plancia = nave.getPlancia();
				if (plancia[i][j].isUtilizzabile() && plancia[i][j].getComponente() instanceof Scudo) {
					Scudo s1 = (Scudo) plancia[i][j].getComponente();
					if (s1.getDirScudo1().equals(direzione) || s1.getDirScudo2().equals(direzione)) {
						s = s1;
						break outerLoop;
					}
				}
			}
		}
		return s;
	}

	private Batteria trovaBatteria(Nave nave) {
		Batteria b = null;
		outerLoop:
		for (int i = 0; i < nave.getPlancia().length; i++) {
			for (int j = 0; j < nave.getPlancia()[0].length; j++) {
				Casella[][] plancia = nave.getPlancia();
				if (plancia[i][j].isUtilizzabile() && plancia[i][j].getComponente() instanceof Batteria) {
					b = (Batteria) plancia[i][j].getComponente();
					break outerLoop;
				}
			}
		}
		return b;
	}
}
