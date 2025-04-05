package carteAvventura;

public class StazioneAbbandonata extends Carta {
	
	private int equipaggioRichiesto;
	private int giorniDaPerdere;
	private int merciVerdi;
	private int merciGialli;
	private int merciRossi;
	public StazioneAbbandonata(Livello livello, int equipaggioRichiesto,int giorniDaPerdere,
     int merciVerdi, int merciGialli,
	 int merciRossi) {
		super(livello);
		this.equipaggioRichiesto = equipaggioRichiesto;
		this.giorniDaPerdere = giorniDaPerdere;
		this.merciGialli = merciGialli;
		this.merciRossi = merciRossi;
		this.merciVerdi = merciVerdi;
		
				}
	
	
	public String getCartaInfo() {
	    return "Equipaggio Richiesto: " + equipaggioRichiesto + ", Giorni Da Perdere: " + giorniDaPerdere
	    		+ ", Merci Verdi: " + merciVerdi + ", Merci Gialli: " + merciGialli + ", Merci Rossi: " + merciRossi;
	}
	
	@Override
    public void azione() {
		
	};
	
	public void azione(int equipaggio, int posizione, int merci_Verdi, int merci_Gialli, int merci_Rossi) {
		if(equipaggio<equipaggioRichiesto) {
			System.out.println("gioicatore non può attraccare alla stazione");
		} else {
			posizione = posizione  - giorniDaPerdere;
			merci_Verdi = merci_Verdi + merciVerdi;
			merci_Rossi = merci_Rossi + merciRossi;
			merci_Gialli = merci_Verdi + merciRossi;
			
			
			
			
		}
		
	}

	
}

