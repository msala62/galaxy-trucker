package planciavolo;

/**
 * La classe Livello1 rappresenta Plancia Volo di livello Prova e primo livello del gioco .
 * 
 */
public class Livello1 extends PlanciaVolo {
	/**
   * Costruttore per la classe Livello1.
   * @param posizioneIniziale Un valore che rappresenta la specifica casella (posizione) da cui
   * un giocatore inizia a giocare su questa plancia di livello. Questa posizione è determinata
   * da fattori esterni al tabellone stesso, come la velocità di costruzione della nave del giocatore
   * prima dell'inizio del livello.
   * Questo parametro viene passato al costruttore della superclasse (PlanciaVolo) per
   * inizializzare la plancia di gioco con le caratteristiche del livello 1 e posizionare
   * il giocatore sulla casella designata come suo punto di partenza.
   * 
   * **Nota sulla differenza**
   * 'posizioneIniziale' che definisce il punto di partenza del giocatore specifico,
   * 'posizioniArrivo' (definite all'interno di ciascun livello, ad esempio {1, 3, 4, 5} per Livello1)
   * sono le caselle *specifiche* designate come punti di arrivo sul tabellone.
   */
 
	public Livello1(int posizioneIniziale) {
  // Chiama il costruttore della superclasse (PlanciaVolo) con i parametri specifici per il livello 1.
     // - 18 caselle totali
     // - Crediti cosmici per i posizioni finale dei giocatori: {4, 3, 2, 1}
        // - 4 posizioni di arrivo disponibili per giocatori.
        super(18, new int[]{4, 3, 2, 1}, posizioneIniziale, 4);          
      //Posizioni (Caselle ) di arrivo per Livello 1
        setPosizioniArrivo(new int[]{1, 3, 4, 5});
	}

	 @Override
	    public int calcolaCreditiCosmici(int posizione, int naveBelloGiocatore, int[] menoEsposti) {
		// Ottiene i crediti base in base alla posizione di giocatore al fine dal gioco.
	        int crediti = creditiCosmici[posizione - 1];   //  ( -1 : per iniziare dal 1 invce di 0)
	     // Determina il giocatore con la nave più "bella".
	        int giocatoreMigliore = naveBello(menoEsposti);
	     // Se il giocatore corrente è quello con la nave più "bella", aggiunge un bonus di 2 crediti.
	        if (giocatoreMigliore == naveBelloGiocatore) {
	            crediti += 2; 
	        }
	     // Aggiunge i crediti cosmici totali accumulati durante il gioco (valore del cargo - danno).
	     crediti += creditiCosmiciTotali();
	     return crediti;
	 }		
	 @Override
	 public String gestisciAttacco(String tipoAttacco, boolean hasBordiLisci, boolean scudoAttivo, int potenzaCannoni, String parteColpita) {
	     return super.gestisciAttacco(tipoAttacco, hasBordiLisci, scudoAttivo, potenzaCannoni, parteColpita);
}
}