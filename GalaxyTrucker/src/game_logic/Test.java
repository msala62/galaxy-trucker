package game_logic;

import carteAvventura.Livello;
import componenti.*;
import nave.Nave;
import nave.NaveLivello1;
import planciavolo.*;

public class Test {
	public static void main(String[] args) {
		NaveLivello1 nave = new NaveLivello1(5, 7);
		nave.aggiungiComponente(1, 2, new Motore(Connettore.LISCIO, Connettore.DOPPIO, Connettore.DOPPIO, Connettore.DOPPIO));
		nave.aggiungiComponente(2, 2, new Motore(Connettore.LISCIO, Connettore.DOPPIO, Connettore.DOPPIO, Connettore.DOPPIO));
		nave.stampa();
	}
}
