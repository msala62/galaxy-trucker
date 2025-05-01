package componenti;

public class Stiva extends Componente implements cargoInterfaccia {

	private final int spazioCargo;
	private int cargoCorrente;

	public Stiva(Connettore SX, Connettore DX, Connettore SU, Connettore GIU, boolean grande) {
		super(SX, DX, SU, GIU);
		if (!grande)
			this.spazioCargo = 2;
		else
			this.spazioCargo = 3;
		this.cargoCorrente = 0;
	}

	public int getCargoCorrente() {
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

	public int getSpazioCargo() {
		return spazioCargo;
	}

	@Override
	public String nomeComponente() {
		if (this.spazioCargo > 2)
			return "Stiva*";
		else
			return "Stiva";

	}
	
}
