package componenti;

import alieni.Colore;

public class ComponentBuilder {
	public final Class<? extends Componente> tipologia;
	public final int quantia;
	public final boolean speciale;
	public final Colore coloreAlieno;
	
	public ComponentBuilder(Class<? extends Componente> tipologia, int quantita) {
		this.tipologia = tipologia;
		this.quantia = quantita;
		this.speciale = false;	//default value to avoid errors
		this.coloreAlieno = null;
	}
	
	public ComponentBuilder(Class<? extends Componente> tipologia, int quantita, boolean speciale) {
		this.tipologia = tipologia;
		this.quantia = quantita;
		this.speciale = speciale;
		this.coloreAlieno = null;
	}
	
	public ComponentBuilder(Class<? extends Componente> tipologia, int quantita, Colore colore) {
		this.tipologia = tipologia;
		this.quantia = quantita;
		this.speciale = false;
		this.coloreAlieno = colore;
	}
}
