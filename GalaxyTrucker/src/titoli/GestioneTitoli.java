package titoli;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.HashMap;
import carteAvventura.Livello;
import game_logic.Giocatore;

public class GestioneTitoli {

	public static List<Titolo> generaTitoli(int nGiocatori) {
		if (nGiocatori < 2 || nGiocatori > 4) {
			throw new IllegalArgumentException(
					"Numero di giocatori (" + nGiocatori + ") non valido. Deve essere tra 2 e 4.");
		}

		TitoliBuilder builder = new TitoliBuilder();
		List<Titolo> allAvailableTitoli = builder.getAllTitoli();

		if (allAvailableTitoli == null) {
			throw new IllegalStateException("TitoliBuilder ha restituito una lista di titoli nulla.");
		}

		if (allAvailableTitoli.isEmpty()) {
			throw new IllegalStateException("Nessun titolo disponibile dal TitoliBuilder.");
		}

		return new ArrayList<>(allAvailableTitoli.subList(0, nGiocatori));
	}

	public static void assegnaTitoli(List<Giocatore> giocatori, List<Titolo> titoli) {
		if (giocatori == null || giocatori.isEmpty()) {
			System.out.println("Nessun giocatore fornito per l'assegnazione dei titoli.");
			return;
		}
		if (titoli == null || titoli.isEmpty()) {
			System.out.println("Nessun titolo fornito per l'assegnazione.");
			return;
		}

		List<Giocatore> candidatiRimanenti = new ArrayList<>(giocatori); // Lista dei candidati
		for (Titolo titolo : titoli) {
			if (candidatiRimanenti.isEmpty()) {
				System.out.println("Non ci sono più giocatori candidati per i titoli rimanenti.");
				break;
			}

			Giocatore potenzialeProprietario = titolo.valutazione(candidatiRimanenti);

			if (potenzialeProprietario != null) {
				if (titolo.assegnaTitolo(potenzialeProprietario)) {
					System.out.println(titolo.toString() + " assegnato a: " + potenzialeProprietario.getNome());

					// eliminare il giocatore che ha ottenuto il titolo dalla lista
					candidatiRimanenti.remove(potenzialeProprietario);
				} else {
					// L'assegnazione è fallita. Se Titolo.assegnaTitolo() ritorna false
					System.out.println("Impossibile assegnare " + titolo.toString() + " a "
							+ potenzialeProprietario.getNome()
							+ ". Il titolo potrebbe essere già detenuto o il giocatore non idoneo secondo le regole di Titolo.assegnaTitolo().");
				}
			} else {
				System.out.println("Nessun giocatore idoneo trovato per il titolo: " + titolo.toString());
			}
		}
	}

	// Gestisce la logica per i titoli difesi, riassegnando i titoli e aggiornandoli
	// a versioni Argento o Oro se difesi.
	public static void titoloDifeso(List<Giocatore> giocatori, List<Titolo> titoli, Livello livello) {
		if (giocatori == null || giocatori.isEmpty()) {
			System.out.println("Nessun giocatore fornito per la gestione della difesa dei titoli.");
			return;
		}
		if (titoli == null || titoli.isEmpty()) {
			System.out.println("Nessun titolo fornito per la gestione della difesa.");
			return;
		}

		// Salva i proprietari precedenti
		Map<Titolo, Giocatore> proprietariPrecedenti = new HashMap<>();
		for (Titolo t : titoli) {
			if (t.getProprietario() != null) {
				proprietariPrecedenti.put(t, t.getProprietario());
			}
		}

		// Riassegna i titoli in base alle performance attuali
		System.out.println(
				"\n ======================== Inizio riassegnazione titoli per la fase di difesa ========================");
		assegnaTitoli(giocatori, titoli); // Riassegna i titoli

		// Controlla chi ha difeso/perso i titoli e assegna crediti/upgrade
		System.out.println("\n======================== Valutazione difesa titoli: ======================== ");
		for (Titolo titoloCorrente : titoli) {
			Giocatore proprietarioAttuale = titoloCorrente.getProprietario();
			Giocatore proprietarioPrecedente = proprietariPrecedenti.get(titoloCorrente);

			if (proprietarioAttuale != null && proprietarioAttuale.equals(proprietarioPrecedente)) {
				// Titolo difeso dallo stesso giocatore
				System.out.println(
						proprietarioAttuale.getNome() + " ha difeso il suo titolo: " + titoloCorrente.toString());

				if (livello == Livello.II) {
					titoloCorrente.passaAdArgento();
					System.out.print("  Il titolo è stato potenziato ad Argento. ");
				} else {
					titoloCorrente.passaAGold();
					System.out.print("  Il titolo è stato potenziato ad Oro. ");
				}
				if (titoloCorrente.assegnaCrediti()) {
					System.out.println(proprietarioAttuale.getNome() + " ha guadagnato "
							+ titoloCorrente.getCreditoPremio() + " crediti.");
				} else {

					System.out.println("Errore nell'assegnazione dei crediti a " + proprietarioAttuale.getNome()
							+ " per il titolo difeso.");
				}
			} else if (proprietarioPrecedente != null
					&& (proprietarioAttuale == null || !proprietarioAttuale.equals(proprietarioPrecedente))) {
				// Il proprietario precedente ha perso il titolo
				System.out.print(proprietarioPrecedente.getNome() + " non ha difeso il suo titolo: "
						+ titoloCorrente.toString());
				if (proprietarioAttuale != null) {
					System.out.println("Nuovo proprietario: " + proprietarioAttuale.getNome() + ".");
				} else {
					System.out.println("Il titolo è ora non assegnato.");
				}
			}
		}
	}
}
