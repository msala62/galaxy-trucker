
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


} 
