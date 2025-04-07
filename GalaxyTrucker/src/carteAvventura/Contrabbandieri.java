	package carteAvventura;

public class Contrabbandieri extends Carta {
	
	int potenzaRichiesta;
	int giorniDaPerdere;
	int merci
	boolean sconfitti = FALSE;

	public Contrabbandieri(Livello livello, int potenaRichiesta, int giorniDaPerdere) {
		super(livello);
		this.potenzaRichiesta = potenzaRichiesta;
		this.giorniDaPerdere = giorniDaPerdere;
	}
	
	@Override
    public void azione(int potenza, int posizione) {
		
		if (!Sconfitti)
		{
			if (potenza> potenzaRichiesta)
			{
				sconfitti = TRUE;
				posizione = posizione - giorniDaPerdere;
				
			}
		}
		
	};

}
