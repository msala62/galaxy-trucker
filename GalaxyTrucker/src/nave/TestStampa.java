package nave;
import componenti.CabinaPartenza;
import componenti.Connettore;
import componenti.Strutturale;
import game_logic.ColoreGiocatore;

public class TestStampa {

	public static void main(String[] args) {
		NaveLivello1 nave1 = new NaveLivello1(13, 13);
		NaveLivello2 nave2 = new NaveLivello2(13, 13);
		NaveLivello3 nave3 = new NaveLivello3(13, 13);
		Strutturale componente = new Strutturale(Connettore.UNIVERSALE, Connettore.UNIVERSALE, Connettore.UNIVERSALE, Connettore.UNIVERSALE);
		CabinaPartenza cabina = new CabinaPartenza(Connettore.UNIVERSALE, Connettore.UNIVERSALE, Connettore.UNIVERSALE, Connettore.UNIVERSALE, ColoreGiocatore.VERDE);
		
		for(int i = 0; i < nave1.getPlancia().length; i++) 
		{
			for(int j = 0; j < nave1.getPlancia()[0].length; j++) 
			{
				if(nave1.getPlancia()[i][j].utilizzabile && (i!=6 || j!=6))
					nave1.aggiungiComponente(i, j, componente);
			}
		}
		nave1.aggiungiComponente(6, 6, cabina);
		
		nave1.stampa();

		for(int i = 0; i < nave2.getPlancia().length; i++) 
		{
			for(int j = 0; j < nave2.getPlancia()[0].length; j++) 
			{
				if(nave2.getPlancia()[i][j].utilizzabile && (i!=6 || j!=6))
					nave2.aggiungiComponente(i, j, componente);
			}
		}
		nave2.aggiungiComponente(6, 6, cabina);
		
		nave2.stampa();
		
		for(int i = 0; i < nave3.getPlancia().length; i++) 
		{
			for(int j = 0; j < nave3.getPlancia()[0].length; j++) 
			{
				if(nave3.getPlancia()[i][j].utilizzabile && (i!=6 || j!=6))
					nave3.aggiungiComponente(i, j, componente);
			}
		}
		nave3.aggiungiComponente(6, 6, cabina);
		
		nave3.stampa();
	}

}
