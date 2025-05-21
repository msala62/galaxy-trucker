package planciavolo;

public class Livello2 extends PlanciaVolo{
	public Livello2( int posizioneIniziale) {
        super(24, new int[]{9, 6, 4, 2}, posizioneIniziale, 4); 
      //Posizioni di arrivo per Livello 2
        setPosizioniArrivo(new int[]{1, 4, 6, 7});
	}
	
	@Override
	public void Stampa() {
	    int indexRepeat = 0;
	    String sep = "   ";
	    int mult = 5;
	    int max = 6;
	    
	    // Prima parte (5 righe)
	    for(int i = 0; i < 6; i++) {
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
	    System.out.print(sep.repeat(16));
	    System.out.println(this.caselle.get(indexRepeat).ToString());
	    indexRepeat++;
	    
	    // Seconda parte (5 righe)
	    mult = 1;
	    max = 14;
	    for(int i = 1; i <= 5; i++) {
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
	            crediti += 4; 
	        }
	     crediti += creditiCosmiciTotali();
	     return crediti;
	 }		
	 @Override
	 public String gestisciAttacco(String tipoAttacco, boolean hasBordiLisci, boolean scudoAttivo, int potenzaCannoni, String parteColpita) {
	     return super.gestisciAttacco(tipoAttacco, hasBordiLisci, scudoAttivo, potenzaCannoni, parteColpita);
	}
}