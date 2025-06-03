package componenti;

import alieni.Colore;

public class SupportoAlieni extends Componente {
	
	private final Colore colore;
	
	public SupportoAlieni(Connettore SX, Connettore DX, Connettore SU, Connettore GIU, Colore colore) {
		super(SX, DX, SU, GIU);
		this.colore = colore;
	}

	//TODO controllo connessione a cabina
	public Colore getColore() {
		return colore;
	}

	@Override
	public String nomeComponente() {
		if(this.colore==Colore.MARRONE)
			return String.format("%1$5s", "suM ");
		else
			return String.format("%1$5s", "suV ");
	}

}
