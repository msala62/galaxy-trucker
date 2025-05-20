package planciavolo;

import game_logic.Giocatore;

/**
 * La classe Casella rappresenta una singola posizione sulla Plancia Volo.
 * Ogni casella ha un numero di posizione univoco e può contenere un giocatore.
 */

public class Casella {
    private int numeroPosizione;
    private Giocatore giocatorePresente; // Il giocatore attualmente presente su questa casella (può essere null).

    /**
    * Costruttore per la classe Casella.
    * @param numeroPosizione : Il numero che identifica la casella (posizioni) sulla plancia Volo.
    */
    public Casella(int numeroPosizione) {
        this.numeroPosizione = numeroPosizione;
        this.giocatorePresente = null;        // Inizialmente non ci sono Giocatori
    }

    public int getNumeroPosizione() {
        return numeroPosizione;
    }

    public Giocatore getGiocatorePresente() {
        return giocatorePresente;
    }

    public void setGiocatorePresente(Giocatore giocatorePresente) {
        this.giocatorePresente = giocatorePresente;
    }

    /**
     * Verifica se la casella è occupata da un giocatore.
     * @return true se la casella è occupata, false altrimenti.
     */
    public boolean isOccupata() {
        return giocatorePresente != null;
    }
	
}