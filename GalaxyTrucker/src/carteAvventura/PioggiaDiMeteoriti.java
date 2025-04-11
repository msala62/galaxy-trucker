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
    	
    	for (int i=0; i<meteorite.size(); i++)
    	{
    		int dado1 = random.nextInt(6) + 1;
            int dado2 = random.nextInt(6) + 1;
            int coordinata = dado1 + dado2;
    		
    		
    	}
    	if (livello.equals('I'))
    	{
    		
    	}
    	else if (livello.equals('II'))
    	{
    		
    	}
    	else {
    		
    	}
    	
	};
	
	

}
