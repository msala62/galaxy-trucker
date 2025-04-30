//Evento negativo. Il giocatore con meno equipaggio subisce la distruzione di un componente scelto casualmente (tiro di dadi).
package carteAvventura;

import java.util.List;
import java.util.Random;

import game_logic.Giocatore;

public class Sabotaggio extends Carta {
	int tentativi = 0;
	boolean colpito = false;
	private Random random = new Random();

	public Sabotaggio(Livello livello) {
		super(livello, "Sabotaggio");
		// TODO Auto-generated constructor stub
	}

	public void azione(List<Giocatore> giocatori) {
	
		Giocatore gMinorEquipaggio = minorEquipaggio(giocatori);
		while (tentativi<3 && !colpito)
		{
			int i= random.nextInt(12)+1;
			int j= random.nextInt(12)+1;
			if (gMinorEquipaggio.getNave().getPlancia()[i][j].isUtilizzabile() && gMinorEquipaggio.getNave().getPlancia()[i][j].getComponente()!=null)
			{
				//TODO: gMinorEquipaggio.getNave().distruggiComponente(i, j);
				colpito = true;
			} else {
				tentativi++;
			}

		}
		
	}

	private Giocatore minorEquipaggio(List<Giocatore> giocatori) {
		Giocatore gMinorEquipaggio = giocatori.getFirst();
		for (int i = 0; i < giocatori.size(); i++) {
			if (giocatori.get(i).getNave().getEquipaggioTotale() < gMinorEquipaggio.getNave().getEquipaggioTotale())
				gMinorEquipaggio = giocatori.get(i);

			else if (giocatori.get(i).getNave().getEquipaggioTotale() == gMinorEquipaggio.getNave()
					.getEquipaggioTotale()) {
			} // pareggi si risolvono penalizzando il giocatore più avanti nella rotta:DA FARE
				// DOPO

		}

		return gMinorEquipaggio;
	}

}
