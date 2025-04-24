package carteAvventura;
/*
import java.util.List;
import java.util.Random;

import game_logic.Giocatore;

public class PioggiaDiMeteoriti extends Carta {

	private List<Meteorite> meteorite;
	private Random random = new Random();

	public PioggiaDiMeteoriti(Livello livello, List<Meteorite> Meteorite) {
		super(livello, "Pioggia Di Meteoriti");
		this.meteorite = Meteorite;
	}

	@Override
	public void azione(Giocatore giocatore) {
	    for (Meteorite m : meteorite) {
	        int dado = random.nextInt(12) + 1;

	        if (dado > getPlanciaColonne(giocatore) || dado > getPlanciaRighe(giocatore)) {
	            System.out.println("meteorite non colpisce la nave");
	            continue;
	        }

	        switch (m.getDirezione()) {
	            case FRONTE:
	                colpisciDaFronte(giocatore, dado, m.getDimensione());
	                break;
	            case RETRO:
	                colpisciDaRetro(giocatore, dado, m.getDimensione());
	                break;
	            case LATO_SINISTRA:
	                colpisciDaSinistra(giocatore, dado, m.getDimensione());
	                break;
	            case LATO_DESTRA:
	                colpisciDaDestra(giocatore, dado, m.getDimensione());
	                break;
	        }
	    }
	}

	// Direzione: fronte (scorre le righe dall'alto)
	private void colpisciDaFronte(Giocatore g, int col, MeteoriteDimensione d) {
	    for (int r = 0; r < getPlanciaRighe(g); r++) {
	        colpisciCella(g, r, col, d);
	    }
	}

	// Direzione: retro (scorre le righe dal basso)
	private void colpisciDaRetro(Giocatore g, int col, MeteoriteDimensione d) {
	    for (int r = getPlanciaRighe(g) - 1; r >= 0; r--) {
	        colpisciCella(g, r, col, d);
	    }
	}

	// Direzione: sinistra (scorre le colonne da destra)
	private void colpisciDaSinistra(Giocatore g, int row, MeteoriteDimensione d) {
	    for (int c = getPlanciaColonne(g) - 1; c >= 0; c--) {
	        colpisciCella(g, row, c, d);
	    }
	}

	// Direzione: destra (scorre le colonne da sinistra)
	private void colpisciDaDestra(Giocatore g, int row, MeteoriteDimensione d) {
	    for (int c = 0; c < getPlanciaColonne(g); c++) {
	        colpisciCella(g, row, c, d);
	    }
	}

	// Applica la penalità se la cella è utilizzabile e contiene un componente
	private void colpisciCella(Giocatore g, int r, int c, MeteoriteDimensione d) {
	    if (c < getPlanciaColonne(g) && r < getPlanciaRighe(g)) {
	        Cella cella = g.getNave().getPlancia()[r][c];
	        if (cella.isUtilizzabile() && cella.getComponente() != null) {
	            // Applica penalità in base alla dimensione
	            if (d == MeteoriteDimensione.PICCOLO) {
	                // penalità meteorite piccolo
	            } else {
	                // penalità meteorite grande
	            }
	        }
	    }
	}

	private int getPlanciaRighe(Giocatore g) {
	    return g.getNave().getPlancia().length;
	}

	private int getPlanciaColonne(Giocatore g) {
	    return g.getNave().getPlancia()[0].length;
	}

}
*/