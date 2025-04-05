package carteAvventura;

public class Pirati extends Carta {
	
	
	private int potenzaRichiesta;
	private int giorniDaPerdere;
	private int creditiDaAquistare;

	public Pirati(Livello livello, int potenzaRichiesta, int giorniDaPerdere, int creditiDaAquistare) {
		super(livello);
		this.creditiDaAquistare = creditiDaAquistare;
		this.potenzaRichiesta = potenzaRichiesta;
		this.giorniDaPerdere = giorniDaPerdere;
	}
	
	public String getCartaInfo() {
	    return "Potenza Richiesta: " + potenzaRichiesta + ", Giorni Da Perdere: " + giorniDaPerdere
	    		+ ", Crediti Da Aquistare" + creditiDaAquistare;
	}
	
	
	@Override
    public void azione() {
		
	};
	
	public void azione (int potenza, boolean isLeader, int posizione, int credito) {
		
		if (potenza>=potenzaRichiesta) {
			credito= credito + creditiDaAquistare;
			posizione = posizione - giorniDaPerdere;
			if(isLeader) {
				System.out.println("Il leader ha protetto gli altri giocatori");
			}
		}
		else {
			//da continuare
		}
		
	}
	

}
