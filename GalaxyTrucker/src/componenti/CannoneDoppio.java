package componenti;

public class CannoneDoppio extends Cannone {

	private boolean utilizzato;

	public CannoneDoppio(Connettore SX, Connettore DX, Connettore SU, Connettore GIU) {
		super(SX, DX, SU, GIU);
		this.direzione = Direzione.SU;
		this.potenza=0;
		this.richiedeBatterie=true;
	}
	
	public double getPotenza(Batteria batteria) 
	{
		if (batteria != null && batteria.scalaCarica()) {
			switch(this.direzione) 
			{
			case SX:
			case DX:
			case GIU:
				return potenza=1;
			case SU:
				return potenza=2;
			default:
				return 2;
			}
		}
		return 0;
	}
	
	@Override
	public String nomeComponente() {
		return String.format("%1$5S", "CAN ");
	}
	
}
