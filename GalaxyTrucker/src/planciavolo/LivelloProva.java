package planciavolo;

public class LivelloProva extends PlanciaVolo {
	public LivelloProva(int posizioneIniziale) {
        super(18, new int[]{4, 3, 2, 1}, posizioneIniziale , 4);      //Crediti cosmici per posizioni , CambiamentO Giorni di volo.
     //Posizioni di arrivo per Livello Prova
        setPosizioniArrivo(new int[]{1, 3, 4, 5});
       }

	 @Override
	    public int calcolaCreditiCosmici(int posizione, int naveBelloGiocatore, int[] menoEsposti) {
	        int crediti = creditiCosmici[posizione - 1];   
	        int giocatoreMigliore = naveBello(menoEsposti);
	        if (giocatoreMigliore == naveBelloGiocatore) {
	            crediti += 2; 
	        }
	     
	       crediti += creditiCosmiciTotali();          //Crediti = CreditiCosmiciTotali ( cargo - danno) + crediti
	     return crediti;
	 }		
	 @Override
	 public String gestisciAttacco(String tipoAttacco, boolean hasBordiLisci, boolean scudoAttivo, int potenzaCannoni, String parteColpita) {
	     return super.gestisciAttacco(tipoAttacco, hasBordiLisci, scudoAttivo, potenzaCannoni, parteColpita);
}
}