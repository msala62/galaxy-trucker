package titoli;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import carteAvventura.Livello;
import game_logic.Giocatore;

public class GestioneTitoli {

	private static List<Titolo> generaTitoli(int nGiocatori) {
		// verifica ngiocatori ricevuto
		if (nGiocatori <= 1 || nGiocatori > 4)
			throw new IllegalArgumentException("Numero di giocatori non valido (deve essere tra 2 e 4)");
		TitoliBuilder builder = new TitoliBuilder();
		// generare una lista di titoli = nGiocatori
		List<Titolo> titoli;
		try {
			// get random titoli based on the players size
			titoli = new ArrayList<>(builder.getAllTitoli().subList(0, nGiocatori));
		} catch (Exception e) {
			e.printStackTrace();
			titoli = Collections.emptyList(); // fallback per evitare null
		}
		return titoli;
	}

	private void assegnaTitoli(List<Giocatore> giocatori, List<Titolo> titoli)

	{
		List<Giocatore> copiaGiocatori = new ArrayList<>(giocatori);
		for (Titolo titolo : titoli) {
			Giocatore potenzialeProprietario = titolo.valutazione(copiaGiocatori);

			if (potenzialeProprietario != null) {

				if (titolo.assegnaTitolo(potenzialeProprietario)) {
					System.out.println(titolo.stampaTitolo() + "assegnato a:" + titolo.getProprietario().getNome());
					// Rimuovi il giocatore che ha vinto questo titolo dalla lista dei candidati per
					// i titoli successivi
					copiaGiocatori.remove(titolo.getProprietario());
				} else {
					System.out.println("errore durante l'assegnazione del titolo");
				}
			} else

			{
				System.out.println("Nessun giocatore idoneo trovato per il titolo: " + titolo.stampaTitolo());
			}

		}
	}

	private void titoloDifeso(List<Giocatore> giocatori, List<Titolo> titoli, Livello livello) {

		java.util.Map<Titolo, Giocatore> proprietariPrecedenti = new java.util.HashMap<>();
		for (Titolo t : titoli) {
			if (t.getProprietario() != null) {
				proprietariPrecedenti.put(t, t.getProprietario());
			}
		}
		assegnaTitoli(giocatori, titoli);

		for (int i = 0; i < titoli.size(); i++) {

			Titolo titoloCorrente = titoli.get(i);
			Giocatore proprietarioAttuale = titoloCorrente.getProprietario();
			Giocatore proprietarioPrecedente = proprietariPrecedenti.get(titoloCorrente);

			if (proprietarioAttuale != null && proprietarioAttuale.equals(proprietarioPrecedente)) {
				// Logica per titolo difeso

				System.out.println(
						proprietarioAttuale.getNome() + " ha difeso il suo titolo:" + titoloCorrente.stampaTitolo());
				if (livello == Livello.II ) {
					titoloCorrente.passaAdArgento();
					if (titoloCorrente.assegnaCrediti()) {
						System.out.println(proprietarioAttuale.getNome() + " ha guadagnato:" + titoloCorrente.getCreditoPremio()
								+ " crediti");
					}

				} else {
					titoloCorrente.passaAGold();
					if (titoloCorrente.assegnaCrediti()) {
						System.out.println(proprietarioAttuale.getNome() + " ha guadagnato:"
								+ titoloCorrente.getCreditoPremio() + " crediti");
					}

				}
			} else if (proprietarioPrecedente!=null && !proprietarioAttuale.equals(proprietarioPrecedente) ) {
				System.out.println(proprietarioPrecedente.getNome() + " Non ha difeso il suo titolo:"
						+ titoloCorrente.stampaTitolo());

			}

		}
	}

}
