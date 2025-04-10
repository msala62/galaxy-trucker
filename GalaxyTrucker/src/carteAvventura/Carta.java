package carteAvventura;

public abstract class Carta {
	
	
	private String nome;
	private Livello livello;
	public Carta(Livello livello, String nome)
	{
		this.setLivello(livello);
		this.setNome(nome);
	}
	
	public void azione() {
		
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




}
