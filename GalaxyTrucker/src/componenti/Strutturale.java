package componenti;

public class Strutturale extends Componente {

	public Strutturale(Connettore SX, Connettore DX, Connettore SU, Connettore GIU) {
		super(SX, DX, SU, GIU);
	}

	@Override
	public String nomeComponente() {
		return String.format("%1$5S", "str ");
	}

}
