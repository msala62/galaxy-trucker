package planciavolo;

import java.util.ArrayList;
import java.util.List;

public abstract class PlanciaVolo implements GestisciAttacchi {
	protected int posizioni;            // Numero di posizioni
    protected int[] creditiCosmici;      // Crediti cosmici per posizione
    protected int valoreCargo;          // Valore totale del cargo
    protected int danno;               // Danno subito
    private int posizioneCorrente;      
    private List<Integer> ordineArrivo = new ArrayList<>(); // Ordine di arrivo dei giocatori
    private int[] posizioniArrivo;            // Posizioni di arrivo (1°, 2°, 3°, 4°)

    public PlanciaVolo(int posizioni, int[] creditiCosmici , int posizioneIniziale, int numPosizioniArrivo) {
        this.posizioni = posizioni;
        this.creditiCosmici = creditiCosmici;
        this.valoreCargo = 0;
        this.danno = 0;
        this.posizioneCorrente = posizioneIniziale;
        this.posizioniArrivo = new int[numPosizioniArrivo]; // Initialize with size

    }
 
    public abstract  int calcolaCreditiCosmici(int posizione, int naveBello, int[] menoEsposti);
    
    public void aggiungiCargo(int colore) {
        switch (colore) {
            case 1: // Rosso2
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

    public void impostaDanno(int danno) {          // Di parte dannegiati durante volo.
        this.danno = danno;
    }

    public int creditiCosmiciTotali() {
        int creditiTotali = valoreCargo - danno;      // Sottrai il danno dal valore del cargo
        return creditiTotali;
    }
    

    public int getPosizioni() {
        return posizioni;
    }
         
   //  Metodo per verificare nave bello (Ha connettori meno esposti)
    protected int naveBello(int[] menoEsposti) {
        int giocatoreMigliore = 0;                          // Iniziamo col primo Giocatore.
        for (int i = 1; i < menoEsposti.length; i++) {
           if (menoEsposti[i] < menoEsposti[giocatoreMigliore]) {
                giocatoreMigliore = i;                       // Aggiorna il giocatore con i connettori meno esposti.
            }
        }
        return giocatoreMigliore;             // Ritorna il giocatore che ricevera crediti cosmici per nmave piu bello.
    }

  
    public void spostamentoGiocatore(int cambimentoGiorniVolo) {
        posizioneCorrente += cambimentoGiorniVolo;     // CambiamentoGiorniVolo = ( -1 ) || ( +1)

        if (posizioneCorrente < 1) {
        	posizioneCorrente = 1;                // Non può andare prima dell'inizio
        } else if (posizioneCorrente > posizioni) {
        	posizioneCorrente = posizioni;         // Non può andare oltre la fine
        }
    }
    
    public void registraArrivo(int numeroGiocatore) {
        if (ordineArrivo.size() < posizioniArrivo.length) {     // posizione Iniziale di Giocatori
            ordineArrivo.add(numeroGiocatore);
        }
    }
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
                    if (potenzaCannoni > 0) {
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
	public int getPosizioneCorrente() {
		return posizioneCorrente;
	}

	public int[] getPosizioniArrivo() {
		return posizioniArrivo;
	}

	public void setPosizioniArrivo(int[] posizioniArrivo) {
		this.posizioniArrivo = posizioniArrivo;
	}

}