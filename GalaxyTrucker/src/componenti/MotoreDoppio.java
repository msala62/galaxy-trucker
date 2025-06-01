package componenti;

public class MotoreDoppio extends Motore  {

	public MotoreDoppio(Connettore SX, Connettore DX, Connettore SU, Connettore GIU) {
		super(SX, DX, SU, GIU);
		this.potenza=2;
		this.richiedeBatterie=true;
	}
	
	public int getPotenza(Batteria batteria) 
	{
		batteria.scalaCarica();
		return potenza;
	}
	
	@Override
	public String nomeComponente() {
		return String.format("%1$5S", "MOT ");
	}
	
}
