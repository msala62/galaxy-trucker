package nave;

import componenti.Componente;

public class Casella {
	private final Coordinata posizione;
	protected boolean utilizzabile = false;
	private Componente componente;
	
	public Casella(Coordinata posizione) {
		this.posizione = posizione;
	}

	public Componente getComponente() {
		return componente;
	}

	public void setComponente(Componente componente) {
		this.componente = componente;
	}

	public Coordinata getPosizione() {
		return posizione;
	}
	
	@Override
	public String toString() {
		return posizione.toString();
	}
	
	public boolean isUtilizzabile() {
		return utilizzabile;
	}


}
