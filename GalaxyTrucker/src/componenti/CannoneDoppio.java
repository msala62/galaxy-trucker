package componenti;

public class CannoneDoppio extends Cannone {

	public CannoneDoppio(Connettore SX, Connettore DX, Connettore SU, Connettore GIU, Direzione dir) {
		super(SX, DX, SU, GIU, dir);
		this.direzione = Direzione.SU;
		this.potenza=0;
	}

	public void spegniCannone() //TODO Solo aver usato il cannone. La sua potenza torna a 0 perché non conta più
	{
		this.potenza=0;
	}

	public void accendiCannone(Batteria batteria) //Scegliere quale batteria usare prima
	{
		if(batteria.getCarica() > 0) 
		{
			batteria.scalaCarica();
			this.potenza=2;
		}
	}
	
	@Override
	protected void calcolaPotenza() 
	{
		switch(this.direzione) 
		{
		case SX:
		case DX:
		case GIU:
			this.potenza=1;
			break;
		case SU:
			this.potenza=2;
		}
	}
	
}
