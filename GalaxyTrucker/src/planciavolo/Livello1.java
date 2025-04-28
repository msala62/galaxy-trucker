package planciavolo;

public class Livello1 extends PlanciaVolo {
	public Livello1(int posizioneIniziale) {
        super(18, new int[]{4, 3, 2, 1}, posizioneIniziale, 4);          
      //Posizioni di arrivo per Livello 1
        setPosizioniArrivo(new int[]{1, 3, 4, 5});
	}

	 @Override
	    public int calcolaCreditiCosmici(int posizione, int naveBelloGiocatore, int[] menoEsposti) {
	        int crediti = creditiCosmici[posizione - 1]; 
	        int giocatoreMigliore = naveBello(menoEsposti);
	        if (giocatoreMigliore == naveBelloGiocatore) {
	            crediti += 2; 
	        }
	     crediti += creditiCosmiciTotali();
	     return crediti;
	 }		
	 @Override
	 public String gestisciAttacco(String tipoAttacco, boolean hasBordiLisci, boolean scudoAttivo, int potenzaCannoni, String parteColpita) {
	     return super.gestisciAttacco(tipoAttacco, hasBordiLisci, scudoAttivo, potenzaCannoni, parteColpita);
}
}