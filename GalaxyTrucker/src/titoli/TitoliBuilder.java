
package titoli;

import java.util.*;

public class TitoliBuilder {

	private List<Titolo> allTitoli;
	
	public TitoliBuilder() {
		allTitoli = new ArrayList<>();
		allTitoli.add(new Batterista());
		allTitoli.add(new CapitanoDaCrociera());
		allTitoli.add(new MastroCorridoista());
		allTitoli.add(new MastroIngegnere());
		allTitoli.add(new TrasportatoreSupremo());
		allTitoli.add(new Xenoquartiermastro());

	}
	public List<Titolo> getAllTitoli() {
		Collections.shuffle(allTitoli, new Random());
		return allTitoli;
	}

	/*private void titolibuilder() {
		
	}
	public TitoliBuilder(int nGiocatori) {
		if (nGiocatori <= 1 || nGiocatori > 4)
			throw new IllegalArgumentException("Numero richiesto non valido.");
		titoliCreation();
		Collections.shuffle(allTitoli, new Random());
		titoli = new ArrayList<>(allTitoli.subList(0, nGiocatori));
	}

	
	public Titolo getTitoloByNome(String nome) {
		for (Titolo titolo : titoli) {
			if (titolo.stampaTitolo().startsWith(nome))
				return titolo;
		}
		return null;
	}*/
} 
