package alieni;

import componenti.Componente;
import componenti.Motore;
import componenti.Cannone;

import java.util.List;

public class Alieno {
	private final Colore colore;
	private List<Componente> bonus;
	
	public Alieno(Colore colore) {
		this.colore = colore;
		
		if(this.colore == Colore.VIOLA) {
			bonus.add(new Motore(null, null, null, null));
			bonus.add(new Motore(null, null, null, null));
		} else {
			bonus.add(new Cannone(null, null, null, null));
			bonus.add(new Cannone(null, null, null, null));
		}
	}
}
