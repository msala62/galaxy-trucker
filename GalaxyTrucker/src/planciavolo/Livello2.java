package planciavolo;

public class Livello2 extends PlanciaVolo{
	public Livello2( int posizioneIniziale) {
        super(24, new int[]{9, 6, 4, 2}, posizioneIniziale, 4); 
      //Posizioni di arrivo per Livello 2
        setPosizioniArrivo(new int[]{1, 4, 6, 7});
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