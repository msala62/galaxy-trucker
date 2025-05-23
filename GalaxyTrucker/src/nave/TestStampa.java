package nave;

import componenti.Cabina;
import componenti.Connettore;
import componenti.Strutturale;

public class TestStampa {

	public static void main(String[] args) {
		NaveLivello1 nave = new NaveLivello1(5, 7);
		Strutturale componente = new Strutturale(Connettore.UNIVERSALE, Connettore.UNIVERSALE, Connettore.UNIVERSALE, Connettore.UNIVERSALE);
		Cabina cabina = new Cabina(Connettore.UNIVERSALE, Connettore.UNIVERSALE, Connettore.UNIVERSALE, Connettore.UNIVERSALE);
		
		for(int i = 0; i < nave.getPlancia().length; i++) 
		{
			for(int j = 0; j < nave.getPlancia()[0].length; j++) 
			{
				nave.aggiungiComponente(i, j, componente);
			}
		}
		nave.aggiungiComponente(3, 4, cabina);
		
		nave.stampa();

	}

}
