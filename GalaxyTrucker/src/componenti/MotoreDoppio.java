package componenti;

public class MotoreDoppio extends Motore implements CalcoloPotenza {

	public MotoreDoppio(Connettore SX, Connettore DX, Connettore SU, Connettore GIU) {
		super(SX, DX, SU, GIU);
		this.potenza=0;
		this.richiedeBatterie=true;
	}
	
	public void spegniMotore() //TODO Da chiamare solo aver usato il motore. La sua potenza torna a 0 perché non conta più
	{
		this.potenza=0;
	}

	public void accendiMotore(Batteria batteria) //Scegliere quale batteria usare prima
	{
		if(batteria.getCarica() > 0) 
		{
			batteria.scalaCarica();
			this.potenza=2;
		}
	}
	
	@Override
	public String nomeComponente() {
		return "Mot*";
	}
	
}
