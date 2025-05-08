package carteAvventura;

public class CardBuilder {
	private Class<? extends Carta> tipologia;
	private int quantita;
	private Livello livello;
	
	public CardBuilder(Class<? extends Carta> tipologia, int quantita) {
		this.tipologia = tipologia;
		this.quantita = quantita;
	}
	
	public Livello getLivello() {
		return this.livello;
	}
	
	public int getQuantita() {
		return this.quantita;
	}
	
	public Class<? extends Carta> getTipologia(){
		return this.tipologia;
	}
}
