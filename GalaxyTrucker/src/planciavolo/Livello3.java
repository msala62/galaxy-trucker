package planciavolo;

public class Livello3 extends PlanciaVolo {
	public Livello3(int posizioneIniziale) {
        super(34, new int[]{12, 9, 6, 3}, posizioneIniziale, 4); 
      //Posizioni di arrivo per Livello 3
        setPosizioniArrivo(new int[]{1, 5, 8, 10});
	}
	
	@Override
	public void Stampa() {
	    int indexRepeat = 0;
	    String sep = "   ";
	    int mult = 8;
	    int max = 7;
	    
	    // Prima parte (5 righe)
	    for(int i = 0; i < 8; i++) {
	        System.out.print(sep.repeat(mult));
	        System.out.print(this.caselle.get(caselle.size() - i - 1).ToString());
	        System.out.print(sep.repeat(max));
	        System.out.println(this.caselle.get(i).ToString());
	        mult--;
	        max += 2;
	        indexRepeat = i;
	    }
	    
	    // Riga centrale
	    indexRepeat++;
	    System.out.print(this.caselle.get(caselle.size() - indexRepeat - 1).ToString());
	    System.out.print(sep.repeat(23));
	    System.out.println(this.caselle.get(indexRepeat).ToString());
	    indexRepeat++;
	    
	    // Seconda parte (5 righe)
	    mult = 1;
	    max = 21;
	    for(int i = 1; i <= 8; i++) {
	    	System.out.print(sep.repeat(mult));
	        System.out.print(this.caselle.get(caselle.size() - indexRepeat - 1).ToString());
	        System.out.print(sep.repeat(max));
	        System.out.println(this.caselle.get(indexRepeat).ToString());
	        mult++;
	        max -= 2;
	        indexRepeat++;
	    }
	}

	 @Override
	    public int calcolaCreditiCosmici(int posizione, int naveBelloGiocatore, int[] menoEsposti) {
	        int crediti = creditiCosmici[posizione - 1]; 
	        int giocatoreMigliore = naveBello(menoEsposti);
	        if (giocatoreMigliore == naveBelloGiocatore) {
	            crediti += 6; 
	        }
	     crediti += creditiCosmiciTotali();
	     return crediti;
	 }		
	 @Override
	 public String gestisciAttacco(String tipoAttacco, boolean hasBordiLisci, boolean scudoAttivo, int potenzaCannoni, String parteColpita) {
	     return super.gestisciAttacco(tipoAttacco, hasBordiLisci, scudoAttivo, potenzaCannoni, parteColpita);
	}
}