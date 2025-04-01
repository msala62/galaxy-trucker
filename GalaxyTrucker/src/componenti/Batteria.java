package componenti;

public class Batteria extends Componente {

	private int carica;
	
	Batteria(Connettore SX, Connettore DX, Connettore SU, Connettore GIU, Grandezza grandezza) {
		super(SX, DX, SU, GIU);
		if(grandezza == Grandezza.NORMALE)
			this.carica=2;
		else
			this.carica=3;
	}

	public int getCarica()
	{
		return carica;
	}
	
	public void scalaCarica() 
	{
		this.carica--;
	}

	@Override
	public String stampa() {
		return null;
		// TODO Auto-generated method stub
	}

}
