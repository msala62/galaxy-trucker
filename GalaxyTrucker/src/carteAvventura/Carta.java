package carteAvventura;

import game_logic.Giocatore;

public abstract class Carta {
	private String nome;
	public Livello livello;
	
	public Carta(Livello livello, String nome)
	{
		this.setLivello(livello);
		this.setNome(nome);
	}
	
	public void azione(Giocatore giocatore) {
				
	}
	
	public String getCartaInfo() {
	 return nome;
	}

	public String getNome() {
		return nome;
	}
	
	public void setNome(String nome) {
		this.nome = nome;
	}

	public Livello getLivello() {
		return livello;
	}

	public void setLivello(Livello livello) {
		this.livello = livello;
	}
	
	public String stampaCarta() {
		return nome;
		
	}
}
