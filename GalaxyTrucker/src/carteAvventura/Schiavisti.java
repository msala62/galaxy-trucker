package carteAvventura;

public class Schiavisti extends Carta {
	
	
	private int potenzaRichiesta;
	private int equipaggioDaPerdere;
	private int giorniDaPerdere;
	private int creditiDaAquistare;
	
	public Schiavisti(Livello livello, int potenzaRichiesta,int equipaggioDaPerdere, int giorniDaPerdere, int creditiDaAquistare) {
		super(livello, "Schiavisti");
		this.potenzaRichiesta = potenzaRichiesta;
		this.creditiDaAquistare = creditiDaAquistare;
		this.equipaggioDaPerdere = equipaggioDaPerdere;
		this.giorniDaPerdere = giorniDaPerdere;
	}

	
	public String getCartaInfo() {
	    return "Equipaggio Da Peerdere: " + equipaggioDaPerdere + ", Giorni Da Perdere: " + giorniDaPerdere
	    		+ ", Crediti Da Aquistare" + creditiDaAquistare + ", Potenza Richiesta" + potenzaRichiesta;
	}
	
	
public void azione (int potenza, boolean isLeader, int posizione, int credito, int equipaggio) {
		
		if (potenza>=potenzaRichiesta) {
			credito= credito + creditiDaAquistare;
			posizione = posizione - giorniDaPerdere;
			if(isLeader) {
				System.out.println("Il leader ha protetto gli altri giocatori");
			}
		}
		else if (equipaggio<= equipaggioDaPerdere) {
			System.out.println("giocatore costretto ad abbandonare la corsa");
		}
		else {
			equipaggio = equipaggio  -  equipaggioDaPerdere;
		}
		
	}

}


