package planciavolo;

/**
 * L'interfaccia GestisciAttacchi definisce   la gestione degli attacchi
 * nel gioco. Le classi che implementano questa interfaccia devono fornire un'implementazione
 * del metodo 'gestisciAttacco'.
 */
public interface GestisciAttacchi {
	
	/**
     * Gestisce un attacco specifico nel gioco e determina il suo esito.
     * @param tipoAttacco    (es., "meteoriti", "cannonate").
     * @param hasBordiLisci  Se la nave attaccata ha bordi lisci (per deviare piccoli meteoriti).
     * @param scudoAttivo    Se lo scudo della nave attaccata è attivo.
     * @param potenzaCannoni La potenza dei cannoni della nave attaccante (per distruggere grossi meteoriti).
     * @param parteColpita   La parte della nave colpita ( a causa dell'attacci pesanti).
     * @return               Una stringa che descrive l'esito dell'attacco.
     */
	String gestisciAttacco(String tipoAttacco, boolean hasBordiLisci, boolean scudoAttivo, 
			int potenzaCannoni, String parteColpita);
}
