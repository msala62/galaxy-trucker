package carteAvventura;

public abstract class Carta {
	
	
	private String nome;
	public Livello livello;
	public Carta(Livello livello, String nome)
	{
		this.livello = livello;
		this.nome = nome;
	}
	
	public void azione() {
		
	}



}
