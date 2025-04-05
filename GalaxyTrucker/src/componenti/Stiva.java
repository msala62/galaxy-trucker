package componenti;

public class Stiva extends Componente {
	
	private final int spazioCargo;
	private int cargoCorrente;
	
	public Stiva(Connettore SX, Connettore DX, Connettore SU, Connettore GIU, Grandezza grandezza) {
		super(SX, DX, SU, GIU);
		if(grandezza == Grandezza.NORMALE)
			this.spazioCargo=2;
		else
			this.spazioCargo=3;
		this.cargoCorrente=0;
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
	
	public int getSpazioCargo()
	{
		return spazioCargo;
	}

	@Override
	public String stampa() {
		return null;
		// TODO Auto-generated method stub

	}

}
