	package carteAvventura;

import java.util.List;
import java.util.Random;

public class PioggiaDiMeteoriti extends Carta {
	
	private List<Meteorite> meteorite;
	private Random random = new Random();
	

	public PioggiaDiMeteoriti(Livello livello, List<Meteorite> Meteorite) {
		super(livello, "Pioggia Di Meteoriti");
		this.meteorite  = Meteorite;
	}

    public void azione() {
    	
    	int dado1 = random.nextInt(6) + 1;
        int dado2 = random.nextInt(6) + 1;
        int coordinata = dado1 + dado2;
		
	};
	
	

}
