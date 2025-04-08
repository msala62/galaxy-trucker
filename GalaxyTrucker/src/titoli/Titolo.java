package titoli;

import game_logic.Giocatore;

public class Titolo {
	
	private String titolo; 
	private Giocatore proprietario;
	private int creditiPremio = 2;
	private boolean isGold = false;  
    private boolean isOwned = false; 
    
    public Titolo (String titolo) {
    	this.titolo = titolo;
    }
    
    public void assegnatitolo (Giocatore giocatore) {
    		//giocatore.setTitolo =  titolo;
    		this.isOwned = true;
    }
    
    public void passaAGold () {
    	this.isGold = true;
    	this.creditiPremio = 12;
    	
    }
    public Giocatore getProprietario() {
    	return proprietario;
    }
    
}
