package componenti;

public class StivaSpeciale extends Componente implements cargoInterface {

	protected final int spazioCargo;
	private int cargoCorrente;
	
	public StivaSpeciale(Connettore SX, Connettore DX, Connettore SU, Connettore GIU, boolean grande) {
		super(SX, DX, SU, GIU);
		if(!grande)
			this.spazioCargo=1;
		else
			this.spazioCargo=2;
		this.cargoCorrente=0;
	}

	public int getSpazioCargo()
	{
		return spazioCargo;
	}
	
	public int getCargoCorrente()
	{
		return cargoCorrente;
	}

	public void aumentaCargoCorrente()
	{
		if(this.spazioCargo >= this.cargoCorrente + 1)
			this.cargoCorrente ++;
		else
			System.out.println("Spazio insufficiente");
	}
	
	@Override
	public String nomeComponente() {
		if(this.spazioCargo>2)
			return "StivaS*";
		else
			return "StivaS";
	}

}
