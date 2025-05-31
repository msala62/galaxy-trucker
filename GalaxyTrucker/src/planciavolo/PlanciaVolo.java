package planciavolo;
import java.util.ArrayList;

import java.util.List;
import game_logic.Giocatore;

/**
 * La classe astratta PlanciaVolo definisce il comportamento generale di una plancia volo per un gioco di volo spaziale.
 * Implementa l'interfaccia GestisciAttacchi per gestire gli attacchi alle navicelle spaziali.
 * 
 * Questa classe fornisce una struttura base per rappresentare una plancia volo di gioco
 * (che in realtà fornisce le istruzioni su come procedere nel gioco ),
 * incluse le caselle ( per posizioni ), la gestione dei crediti, il tracciamento della posizione dei giocatori
 * e la gestione degli attacchi. Le sottoclassi di PlanciaVolo estendono questa classe
 * per implementare regole specifiche del gioco. 
 */

public abstract class PlanciaVolo implements GestisciAttacchi {
	protected List<Casella> caselle;           // Rappresenta posizioni sulla plancia
    protected int[] creditiCosmici;      // Crediti cosmici guadagnati in base alla posizione finale dal giocatore alla fine del gioco
    protected int valoreCargo;          //  Valore totale del cargo trasportato
    protected int danno;               // Danno subito dalla navicella
    private Casella posizioneCorrenteCasella;    // Per tracciare casella( posizione ) corrente del giocatore
    private List<Giocatore> ordineArrivo = new ArrayList<>(); // Ordine di arrivo dei giocatori
    private int[] posizioniArrivo;            // Array per memorizzare le Posizioni di arrivo dal giocatore (1°, 2°, 3°, 4°)

    /**
     * Costruttore per la classe PlanciaVolo.
     * @param numeroDiCaselle  Il numero totale di caselle ( posizioni ) sulla plancia di ogni livello.
     * @param creditiCosmici Un array contenente i crediti cosmici che sono guadagnabili in base della posizione finale dal giocatore.
     * @param posizioneIniziale La posizione iniziale di giocatore sulla plancia.
     * @param numPosizioniArrivo I numeri di posizioni di arrivo disponibili per giocatori .
     */
    public PlanciaVolo(int numeroDiCaselle, int[] creditiCosmici ,int posizioneIniziale, int numPosizioniArrivo) {
    	this.caselle = new ArrayList<>();
    	/**
         * Crea una lista di oggetti Casella per rappresentare le posizioni sulla plancia.
         * Il numero di caselle è determinato dal parametro 'numeroDiCaselle'.
         * Ogni casella è numerata da 1 a 'numeroDiCaselle'.
         * Assicura che verranno creati il numero corretto di oggetti Casella ( che sono posizioni )
    	 * per ogni livello, consentendo tenere traccia della posizione dei giocatori.
         */
        for (int numeroCasella = 1; numeroCasella <= numeroDiCaselle; numeroCasella++) {
            this.caselle.add(new Casella(numeroCasella));
        }
        this.creditiCosmici = creditiCosmici;   
        this.valoreCargo = 0;    // Inizializza il valore del cargo a 0.
        this.danno = 0;          // Inizializza il danno a 0.
        
     // Trova e imposta la casella iniziale del giocatore.
        for (Casella casella : this.caselle) {
            if (casella.getNumeroPosizione() == posizioneIniziale) {
         this.posizioneCorrenteCasella = casella;
                break;
            }
        }
     // Inizializza l'array delle posizioni di arrivo.
        this.posizioniArrivo = new int[numPosizioniArrivo]; 

    }
 
    /**
     * Metodo astratto per calcolare i crediti cosmici guadagnati da un giocatore.
     *Poi implementato dalle sottoclassi.
     *
     * @param posizione           La posizione del giocatore al fine dai gioco.
     * @param naveBello           Identificativo della nave più "bella". ( Chi ha una nave con meno connettori 
     *                            esposti alla fine del gioco ottiene crediti in base al livello.)
     * @param menoEsposti         Array per identificare nave con connettori meno esposti.
     * @return                    Il numero di crediti cosmici guadagnati.
     */
    public abstract  int calcolaCreditiCosmici(int posizione, int naveBello, int[] menoEsposti);
    
    /**
     * Metodo per aggiungere il valore di un carico alla nave.
     * Il valore del cargo è determinato dal colore del carico.
     * @param colore Il colore del carico ( cargo ) da aggiungere.
     */
    public void aggiungiCargo(int colore) {
        switch (colore) {
            case 1: // Rosso
                valoreCargo += 4;
                break;
            case 2: // Giallo
                valoreCargo += 3;
                break;
            case 3: // Verde
                valoreCargo += 2;
                break;
            case 4: // Blu
                valoreCargo += 1;
                break;
        }
    }

    /* Quante parti vengono danneggiate ( per caso di aventura )
     * durante il gioco causa una penalità di -1 credito al fine dal gioco.
     */
    
    public void impostaDanno(int danno) {          // Di parte dannegiati durante volo.
        this.danno = danno;
    }

    /**
     * Metodo per calcolare i crediti cosmici totali del giocatore.
     * I crediti totali sono calcolati sottraendo il danno dal valore del cargo.
     *
     * @return I crediti cosmici totali del giocatore.
     */
    public int creditiCosmiciTotali() {
        int creditiTotali = valoreCargo - danno;      
        return creditiTotali;                        
    }
         
    /**
     * Metodo per determinare quale giocatore ha la nave più "bella".
     *
     * @param menoEsposti Array degli identificativi dei giocatori meno esposti.
     * @return            L'identificativo del giocatore con la nave più "bella".
     */
    protected int naveBello(int[] menoEsposti) {
        int giocatoreMigliore = 0;          // Inizialmente si assume che il giocatore 0 ( primo ) abbia la nave migliore.
        for (int indiceGiocatore = 1; indiceGiocatore < menoEsposti.length; indiceGiocatore++) {
       // Confronta i connettori esposti del giocatore corrente con il giocatore migliore trovato finora.
           if (menoEsposti[indiceGiocatore] < menoEsposti[giocatoreMigliore]) {
                giocatoreMigliore = indiceGiocatore;       // Aggiorna il giocatore con i connettori meno esposti.
            }
        }
        return giocatoreMigliore;    // Ritorna il indice dal giocatore che ricevera crediti cosmici per nave piu bello.
    }

    /**
     * Metodo per ottenere la lista di caselle della plancia.
     * @return La lista di caselle della plancia.
     */
    public List<Casella> getCaselle() {
        return caselle;
    }
  
    /**
     * Metodo per gestire lo spostamento di un giocatore sulla plancia.
     *
     * @param giocatore        Il giocatore da spostare.
     * @param cambioGiorniVolo Il numero di caselle da spostare il giocatore (può essere positivo o negativo).
     * @throws IllegalStateException Se il giocatore non viene trovato sulla plancia.
     */ // Movimenti del Giocatore 
    public void spostamentoGiocatore(Giocatore giocatore, int cambioGiorniVolo) {
      // Trovare casella ( posizione ) corrente del giocatore
    	Casella currentCasella = null;
        for (Casella c : caselle) {
            if (c.getGiocatorePresente() == giocatore) {
                currentCasella = c;
                break;
            }
        }
        
     // Se il giocatore non è stato trovato, lancia un'eccezione.
        if (currentCasella == null) {
            throw new IllegalStateException("Giocatore not found!");
        }
       
        // Calcola la nuova posizione del giocatore.
        int nuovaPosizioneNumero = currentCasella.getNumeroPosizione() + cambioGiorniVolo;

      // Assicura che il giocatore rimanga entro i limiti della plancia.
        if (nuovaPosizioneNumero < 1) {
            nuovaPosizioneNumero = 1;
        } else if (nuovaPosizioneNumero > caselle.size()) {
            nuovaPosizioneNumero = caselle.size();
        }

     // Trova la casella corrispondente alla nuova posizione calcolata.
        Casella nuovaCasella = null;
        for (Casella c : caselle) {
            if (c.getNumeroPosizione() == nuovaPosizioneNumero) {
                nuovaCasella = c;
                break;
            }
        }

        // Sposta il giocatore alla nuova casella
        if (nuovaCasella != null) {
        	// Rimuovi il giocatore dalla casella corrente.
            currentCasella.setGiocatorePresente(null);
         // Imposta il giocatore sulla nuova casella.
            nuovaCasella.setGiocatorePresente(giocatore);
            this.posizioneCorrenteCasella = nuovaCasella;
        }
    }
    /**
     * Metodo per registrare l'arrivo (posizione arrivo ) di un giocatore
      * e tenere traccia dell'ordine di arrivo.
      * @param giocatore Il giocatore che è arrivato.
     */ 
    public void registraArrivo(Giocatore giocatore) {
    	// Registra il giocatore nell'ordine di arrivo se ci sono ancora posizioni disponibili.
        if (ordineArrivo.size() < posizioniArrivo.length) {     
            ordineArrivo.add(giocatore);
        }
    }
    
    /**
     * Metodo che da istruzione per gestire un attacco.
     * Determina il risultato di un attacco in base al tipo di attacco,
     * alle caratteristiche della nave e all'esito dello scudo.
     * 
     * @param tipoAttacco Il tipo di attacco ("meteoriti", "Cannoni", ecc.).
     * @param hasBordiLisci Indica se la nave ha bordi lisci (per deviare piccoli meteoriti).
     * @param scudoAttivo Indica se lo scudo della nave è attivo (per bloccare attacchi leggeri).
     * @param potenzaCannoni La potenza dei cannoni della nave attaccante (per distruggere grossi meteoriti).
     * @param parteColpita La parte della nave colpita (se applicabile , per caso di attachi pesanti).
     * @return Una stringa che descrive l'esito dell'attacco.
     */
    @Override
    public String gestisciAttacco(String tipoAttacco, boolean hasBordiLisci, boolean scudoAttivo, int potenzaCannoni, String parteColpita) {
             switch (tipoAttacco.toLowerCase()) {
                case "piccoli meteoriti":
                    if (hasBordiLisci || scudoAttivo) {
                        return "L'attacco di piccoli meteoriti è stato deviato.";
                    } else {
                        return "La nave ha subito danni da piccoli meteoriti.";
                    }
                case "grossi meteoriti":
                    if (potenzaCannoni > 0 ) {
                        return "I grossi meteoriti sono stati distrutti dai cannoni.";
                    } else {
                        return "La nave ha subito gravi danni da grossi meteoriti.";
                    }
                case "cannonate leggero":
                    if (scudoAttivo) {
                        return "L'attacco di Cannonate Leggero è stato bloccato dallo scudo.";
                    } else {
                        return "La nave ha subito danni da fuoco leggero.";
                    }
                case "cannonate pesante":
                    return "L'attacco di Cannonate Pesante ha causato danni a parte della nave: " + parteColpita + ".";
                default:
                    return "Tipo di attacco sconosciuto.";
            }

    }

	public int[] getPosizioniArrivo() {
		return posizioniArrivo;
	}

	public void setPosizioniArrivo(int[] posizioniArrivo) {
		this.posizioniArrivo = posizioniArrivo;
	}

	/**
     * Metodo per ottenere la casella corrente in cui si trova il giocatore.
     * @return La casella corrente del giocatore.
     */
	public Casella getPosizioneCorrenteCasella() {
		return posizioneCorrenteCasella;
	}

	/**
     * Metodo per impostare la casella corrente del giocatore e gestire l'entrata/uscita dei giocatori dalle caselle.
     * @param nuovaCasella La nuova casella in cui il giocatore si sposta.
     * @param giocatore Il giocatore che si sta spostando.
     */
	public void setPosizioneCorrenteCasella(Casella nuovaCasella, Giocatore giocatore) {
		 // Prima di aggiungere il giocatore, controlla che la casella esista
        if (nuovaCasella == null) {
            System.out.println("La casella di destinazione non esiste.");
            return;
        }
        // Rimuovi il giocatore dalla casella precedente, se presente
        if (posizioneCorrenteCasella != null) {
            posizioneCorrenteCasella.setGiocatorePresente(null);
        }
        // Imposta la nuova casella come casella corrente
        posizioneCorrenteCasella = nuovaCasella;
        // Aggiungi il giocatore alla nuova casella
        posizioneCorrenteCasella.setGiocatorePresente(giocatore);
    }
	/**
	 * Ritorna lista dei giocatori in ordine di  arrivo .Fornisce accesso diretto alla lista dei 
	 * giocatori nell'ordine in cui hanno raggiunto la plancia.
    */
	  public List<Giocatore> getOrdineArrivo() {
	       return ordineArrivo;
	   }
}