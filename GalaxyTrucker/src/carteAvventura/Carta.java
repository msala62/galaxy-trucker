package carteAvventura;

import java.util.*;
import game_logic.*;
import planciavolo.PlanciaVolo;

public abstract class Carta {
	
	
	private String nome;
	public Livello livello;
	
	public Carta(Livello livello, String nome)
	{
		this.setLivello(livello);
		this.setNome(nome);
	}
	
	public abstract void azione(List<Giocatore> giocatori, PlanciaVolo Plancia);

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
	
	@Override
	public String toString() {
		return "Carta:" + nome + "\n" +
		       "livello=" + livello;
	}


}
