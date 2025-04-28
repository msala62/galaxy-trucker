package planciavolo;

public class Livello3 extends PlanciaVolo {
	public Livello3(int posizioneIniziale) {
        super(34, new int[]{12, 9, 6, 3}, posizioneIniziale, 4); 
      //Posizioni di arrivo per Livello 3
        setPosizioniArrivo(new int[]{1, 5, 8, 10});
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