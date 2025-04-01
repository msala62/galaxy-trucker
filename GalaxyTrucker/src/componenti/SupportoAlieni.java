package componenti;

public class SupportoAlieni extends Componente {
	
	private final ColoreAlieni colore;
	
	SupportoAlieni(Connettore SX, Connettore DX, Connettore SU, Connettore GIU, ColoreAlieni colore) {
		super(SX, DX, SU, GIU);
		this.colore = colore;
	}

	//TODO controllo connessione a cabina
	
	public ColoreAlieni getColore() {
		return colore;
	}

	@Override
	public String stampa() {
		return null;
		// TODO Auto-generated method stub

	}

}
