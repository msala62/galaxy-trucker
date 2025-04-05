package carteAvventura;

public class NaveAbbandonata extends Carta {
	
	private int equipaggioDaPerdere;
	private int giorniDaPerdere;
	private int creditiDaAquistare;
	

	public NaveAbbandonata(Livello livello, int equipaggioDaPerdere, int giorniDaPerdere, int creditiDaAquistare) {
		super(livello);
		this.creditiDaAquistare = creditiDaAquistare;
		this.equipaggioDaPerdere = equipaggioDaPerdere;
		this.giorniDaPerdere = giorniDaPerdere;
	}
	
	public String getCartaInfo() {
	    return "Equipaggio Da Peerdere: " + equipaggioDaPerdere + ", Giorni Da Perdere: " + giorniDaPerdere
	    		+ ", Crediti Da Aquistare" + creditiDaAquistare;
	}
	
	@Override
    public void azione() {
		
	};
	
    public void azione(int equipaggio,  int posizione, int credito ) {

    	if (equipaggio>= equipaggioDaPerdere)
    	{
    	posizione =  posizione - giorniDaPerdere;
    	equipaggio = equipaggio- equipaggioDaPerdere;
    	credito = credito + creditiDaAquistare;
    	
    	} else {
    		System.out.println("Equipaggio Insufficiente");
    	}
    	
	};

	
}
