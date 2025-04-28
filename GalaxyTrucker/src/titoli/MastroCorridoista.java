package titoli;

import java.util.List;

import componenti.Componente;
import componenti.Connettore;
import game_logic.Giocatore;
import nave.Casella;
import nave.Nave;

public class MastroCorridoista extends Titolo  {

	public MastroCorridoista() {
		super("MastroCorridoista");
		// TODO Auto-generated constructor stub
	}
	
	public int contatore(Nave nave) {
		int lunghezza;
		int lunghezzaMassima;
		
		Casella[][] plancia;
		plancia = nave.getPlancia();
		
		for (int i = 0; i < plancia.length; i++) {
			for (int j = 0; j < plancia[0].length; j++) {
				if (plancia[i][j].isUtilizzabile())
				{
					if (plancia[i][j].getComponente()!=null)
					{
						if (isComponenteCorridoio(plancia[i][j].getComponente()))
						{
						
							
						}
					}
				}
			}
		}

		return 0;
	}
	
	public Giocatore valutazione(List<Giocatore> giocatori) {
		Giocatore giocatore;
		Giocatore vincetore = giocatori.getFirst();

		for (int i = 0; i < giocatori.size(); i++) {
			giocatore = giocatori.get(i);
			if (contatore(giocatore.getNave()) > contatore(vincetore.getNave())) {
				vincetore = giocatori.get(i);
			}

		}

		return vincetore;
	}

	public boolean isComponenteCorridoio(Componente componente) {
		int count = 0;
		if (componente.getConnettoreDX() != Connettore.LISCIO)
			count++;
		if (componente.getConnettoreSX() != Connettore.LISCIO)
			count++;
		if (componente.getConnettoreGIU() != Connettore.LISCIO)
			count++;
		if (componente.getConnettoreSU() != Connettore.LISCIO)
			count++;

		if (count != 0 && count <= 2)
			return true;

		return false;
	}

}
