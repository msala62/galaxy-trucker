package componenti;

public class ComponentBuilder {
	public final Componente tipologia;
	public final int quantia;
	
	public ComponentBuilder(Componente tipologia, int quantita) {
		this.tipologia = tipologia;
		this.quantia = quantita;
	}
}
