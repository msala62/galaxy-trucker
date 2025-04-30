package componenti;

public class StivaSpeciale extends Componente implements cargoInterfaccia {

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

	//GEORGE:Modificato il tipo di ritorno del metodo in boolean per indicare se l'operazione è stata eseguita correttamente
	public boolean aumentaCargoCorrente()
	{
		if(this.spazioCargo >= this.cargoCorrente + 1)
		{
			this.cargoCorrente ++;
			return true;
		}
		else
		{
			System.out.println("Spazio insufficiente");
			return false;
		}
			
	}

	
	@Override
	public String nomeComponente() {
		if(this.spazioCargo>2)
			return "StivaS*";
		else
			return "StivaS";
	}

}
