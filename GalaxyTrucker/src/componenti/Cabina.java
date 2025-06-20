package componenti;

import alieni.Colore;

public class Cabina extends CabinaPartenza {
	
	private int equipaggioAlieno;
	
	private static final int EQUIPAGGIO_ALIENO_MAX=1;
	private Colore coloreAlieno;
	private boolean accettaAlieni;
	
	public Cabina(Connettore SX, Connettore DX, Connettore SU, Connettore GIU) {
		super(SX, DX, SU, GIU, null);
	}

	public int getEquipaggioAlieno()
	{
		return equipaggioAlieno;
	}

	//Controllo equipaggioAlieno >0 e accettaAlieni da fare prima di chiamare
	public void setEquipaggioAlieno(int equipaggioAlieno)
	{
		if(this.equipaggio != 0)
			System.out.println("E' gia presente un umano nella cabina");
		else if (this.equipaggioAlieno + equipaggioAlieno > EQUIPAGGIO_ALIENO_MAX)
			System.out.println("Numero massimo alieni raggiunto");
		else
			this.equipaggioAlieno = equipaggioAlieno;
	}

	public boolean isAccettaAlieni()
	{
		return accettaAlieni;
	}

	public void setAccettaAlieni(boolean accettaAlieni)
	{
		this.accettaAlieni = accettaAlieni;
	}
	
	public Colore getColoreAlieno() {
        return this.coloreAlieno;
    }
    
    public void setColoreAlieno(Colore colore) {
        this.coloreAlieno = colore;
        this.accettaAlieni = (colore != null);
    }

	@Override
	public void setEquipaggio(int equipaggio)	//Controllo equipaggio >0 da fare prima di chiamare
	{
		if(this.equipaggioAlieno != 0)
			System.out.println("E' gia presente un alieno nella cabina");
		else if (this.equipaggio + equipaggio > EQUIPAGGIO_MAX)
			System.out.println("Numero massimo umani raggiunto");
		else {
			this.equipaggio = equipaggio;
			this.coloreAlieno = coloreAlieno;
		}
	}
	
	@Override
	public String nomeComponente() {
		return String.format("%1$5s", "cab ");
	}
	
}
