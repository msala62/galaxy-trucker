package componenti;

import alieni.Colore;

public class ComponentBuilder {
	public final Class<? extends Componente> tipologia;
	public final int quantita;
	public final boolean grande;
	public final boolean speciale;
	public final Colore coloreAlieno;
	
	public ComponentBuilder(Class<? extends Componente> tipologia, int quantita) {
		this.tipologia = tipologia;
		this.quantita = quantita;
		this.grande = false;	//default value to avoid errors
		this.speciale = false;
		this.coloreAlieno = null;
	}
	
	public ComponentBuilder(Class<? extends Componente> tipologia, int quantita, boolean grande) {
		this.tipologia = tipologia;
		this.quantita = quantita;
		this.grande = grande;
		this.speciale = false;
		this.coloreAlieno = null;
	}
	
	public ComponentBuilder(Class<? extends Componente> tipologia, int quantita, boolean grande, boolean speciale) {
		this.tipologia = tipologia;
		this.quantita = quantita;
		this.grande = grande;
		this.speciale = speciale;
		this.coloreAlieno = null;
	}
	
	public ComponentBuilder(Class<? extends Componente> tipologia, int quantita, Colore colore) {
		this.tipologia = tipologia;
		this.quantita = quantita;
		this.grande = false;
		this.speciale = false;
		this.coloreAlieno = colore;
	}
}
