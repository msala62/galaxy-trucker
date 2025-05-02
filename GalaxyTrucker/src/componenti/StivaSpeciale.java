package componenti;

import java.util.List;

import merci.Cargo;
import merci.Cargo.ColoreCargo;

public class StivaSpeciale extends Componente implements cargoInterfaccia {

	protected final int spazioCargo;	//Numero cargo massimo trasportabile
	private List<Cargo> listaCargo;
	
	public StivaSpeciale(Connettore SX, Connettore DX, Connettore SU, Connettore GIU, boolean grande) {
		super(SX, DX, SU, GIU);
		if(!grande)
			this.spazioCargo=1;
		else
			this.spazioCargo=2;
	}

	//GEORGE:Modificato il tipo di ritorno del metodo in boolean per indicare se l'operazione è stata eseguita correttamente
	public boolean aumentaCargoCorrente(Cargo nuovoCargo)
	{
		if(this.spazioCargo >= this.listaCargo.size() + 1)	//Check se c'è abbastanza spazio nella stiva
		{
			listaCargo.add(nuovoCargo);
			return true;
		}
		else
		{
			System.out.println("Spazio insufficiente");
			return false;
		}
			
	}

	public int getSpazioCargo()
	{
		return spazioCargo;
	}
	
	public int getCargoCorrente() 	//Get numero di merci trasportate in questo momento
	{
		return listaCargo.size();
	}
	
	public void stampaCargoCorrente() 
	{
		StringBuilder sb = new StringBuilder();
		for (Cargo merce : listaCargo) 
		{
			sb.append(merce.toString() + "/t");
			sb.append(merce.getValore() + "/n");
		}
		System.out.println("Tipo cargo:/tValore:/n" + sb);
	}
	
	@Override
	public String nomeComponente() 
	{
		if(this.spazioCargo>2)
			return "StivaS*";
		else
			return "StivaS";
	}

}
