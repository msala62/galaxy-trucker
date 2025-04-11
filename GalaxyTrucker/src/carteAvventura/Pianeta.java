package carteAvventura;


/* ogni carta di pianeti puo avere piu di una pianeta, ogni pianeta puo avere diversi tipi di merci*/

public class Pianeta {
	private int merciVerdi;
	private int merciRossi;
	private int merciBlu;
	private int merciGialli;	
	
	public Pianeta (int merciVerdi, int merciRossi, int merciBlu ,int merciGialli) {
		this.merciVerdi = merciVerdi;
		this.merciRossi = merciVerdi;
		this.merciBlu = merciBlu;
		this.merciGialli = merciGialli;

	}

	public String getMerci() {
		return "Merci Gialli" + merciGialli +
				"Merci Rossi" + merciRossi +
				"Merci Verdi " + merciVerdi +
				"Merci Blu " + merciBlu ;
	}
	
	
	
}
