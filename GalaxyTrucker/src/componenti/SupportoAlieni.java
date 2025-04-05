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
	public String stampa() {
		return null;
		// TODO Auto-generated method stub

	}

}
