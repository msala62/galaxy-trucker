	package carteAvventura;

public class Contrabbandieri extends Carta {
	
	int potenzaRichiesta;
	int giorniDaPerdere;
	int merciDaAquistare;
	int penalita; //merci da perdere in sconfittta
	
	boolean sconfitti = false;

	public Contrabbandieri(Livello livello, int potenzaRichiesta, int giorniDaPerdere, int merci, int penalita) {
		super(livello, "Contrabbandieri");
		this.merciDaAquistare =  merci;
		this.potenzaRichiesta = potenzaRichiesta;
		this.giorniDaPerdere = giorniDaPerdere;
		this.penalita = penalita;
	}
	
	public int azione(int potenza, int posizione, int numMerci) {
		
		if (!sconfitti)
		{
			if (potenza> potenzaRichiesta)
			{
				sconfitti = true;
				posizione = posizione - giorniDaPerdere;
				return merciDaAquistare; // ha sconfitto il nemico, premio: merci
				
			}
			else if (potenza == potenzaRichiesta) 
				return 0 ; //Nessun effetto per quel giocatore, ma il nemico non è sconfitto
			
			else if (potenza < potenzaRichiesta) 
				return penalita ; //il nemico non è sconfitto, penalia= n  merci dda perdere
			
		}
		return -1;
		
	}

}
