package componenti;

import java.util.function.Supplier;

public class ComponentBuilder {
	public final Supplier<Componente> tipologia;
	public final int quantia;
	
	public ComponentBuilder(Supplier<Componente> tipologia, int quantita) {
		this.tipologia = tipologia;
		this.quantia = quantita;
	}
}
