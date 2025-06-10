
/*
 * Evento di danno. Vengono lanciati dadi per determinare impatti su righe/colonne. Piccoli meteoriti
 * si bloccano con scudi; grandi devono essere abbattuti con cannoni, altrimenti distruggono pezzi.
 * 
 * */
package carteAvventura;

import java.util.List;
import java.util.Random;

import componenti.Componente;
import componenti.Connettore;
import game_logic.Giocatore;
import nave.*;
import planciavolo.PlanciaVolo;

public class PioggiaDiMeteoriti extends Carta {

	private List<Meteorite> meteoriti;

	public PioggiaDiMeteoriti(Livello livello, List<Meteorite> Meteoriti) {
		super(livello, "Pioggia Di Meteoriti");
		this.meteoriti = Meteoriti;
	}

	@Override
	public String toString() {
		return "Carta: " + getNome() + "\n" + "Livello: " + getLivello() + "\n" + "Meteoriti: " + meteoriti;
	}

	@Override
	public void azione(List<Giocatore> giocatori, PlanciaVolo plancia) {
		System.out.println("======================= evento: Pioggia di Meteoriti =======================");
	    System.out.println(" Ogni giocatore sarà colpito");
		for (Giocatore giocatore : giocatori) {
			System.out.println("==================== Giocatore: " + giocatore.getNome()+"========================");
			for (Meteorite meteorite : meteoriti) {
				meteorite.applicaSu(giocatore);
			}
		}

	}

}
