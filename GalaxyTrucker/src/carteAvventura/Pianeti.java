package carteAvventura;

import java.util.*;

public class Pianeti extends Carta {
	private int giorniDaPerdere;
	private List<Pianeta> pianetiDisponibili;
	
	public Pianeti(Livello livello, List<Pianeta> pianeti,int giorniDaPerdere) {
		super(livello, "Pianeti");
		this.giorniDaPerdere = giorniDaPerdere;
		this.pianetiDisponibili = pianeti;
		
	}
	
	 public List<Pianeta> getPianetiDisponibili() {
	        return pianetiDisponibili;
	    }

		
	 public void azione(int pianetaScelta, int posizione) {
		 
		 if (pianetaScelta >= 0 && pianetaScelta < pianetiDisponibili.size())
		 {
			 posizione = posizione - giorniDaPerdere;
			 pianetiDisponibili.remove(pianetaScelta);
			 
		 }
		 
	 }
	

} 



