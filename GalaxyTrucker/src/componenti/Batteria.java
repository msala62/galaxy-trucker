package componenti;

public class Batteria extends Componente {

	private int carica;
	private boolean grande;
	
	public Batteria(Connettore SX, Connettore DX, Connettore SU, Connettore GIU, boolean grande) {
		super(SX, DX, SU, GIU);
		this.grande=grande;
		if(!grande)
			this.carica=2;
		else
			this.carica=3;
	}

	public int getCarica()
	{
		return carica;
	}
	
	public boolean scalaCarica() 
	{
		if(carica == 0) return false;
		this.carica--;
		return true;
	}

	@Override
	public String nomeComponente() {
		if(this.grande)
			return "Bat*";
		else
			return "Bat";
	}

}
