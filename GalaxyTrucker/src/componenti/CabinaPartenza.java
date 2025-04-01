package componenti;

public class CabinaPartenza extends Componente {

	protected int equipaggio;
	
	protected static final int EQUIPAGGIO_MAX=2;
	
	public CabinaPartenza(Connettore SX, Connettore DX, Connettore SU, Connettore GIU) {
		super(SX, DX, SU, GIU);
		setEquipaggio(2);
	}
	
	public int getEquipaggio()
	{
		return equipaggio;
	}

	public void setEquipaggio(int equipaggio)	//Controllo equipaggio >0 da fare prima di chiamare
	{
		if (this.equipaggio + equipaggio > EQUIPAGGIO_MAX)
			System.out.println("Numero massimo umani raggiunto");
		else
			this.equipaggio = equipaggio;
	}

	@Override
	public String stampa() {
		// TODO Auto-generated method stub
		return null;
	}

}
