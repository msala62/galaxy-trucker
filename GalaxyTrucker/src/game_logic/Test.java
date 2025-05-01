package game_logic;

import componenti.Batteria;
import componenti.Connettore;
import nave.Nave;

public class Test {
	public static void main(String[] args) {
		Nave nave = new Nave();
		nave.aggiungiComponente(0, 3, new Batteria(Connettore.DOPPIO, Connettore.LISCIO, Connettore.LISCIO, Connettore.UNIVERSALE, false));
		nave.stampa();
	}
}
