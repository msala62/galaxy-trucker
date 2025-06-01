package componenti;

import java.util.List;

import merci.Cargo;
import merci.Cargo.ColoreCargo;

public class Stiva extends Componente{

	private final int spazioCargo; //Numero cargo massimo trasportabile
	private final boolean isSpeciale;
	private List<Cargo> listaCargo;

	public Stiva(Connettore SX, Connettore DX, Connettore SU, Connettore GIU, boolean grande, boolean speciale) {
		super(SX, DX, SU, GIU);
		isSpeciale = speciale;
		if (!grande && !speciale)//Ne grande ne speciale
			this.spazioCargo = 2;
		else if (grande && !speciale)//Grande ma non speciale
			this.spazioCargo = 3;
		else if (!grande && speciale)//Speciale ma non grande
			this.spazioCargo = 1;
		else//Speciale e grande
			this.spazioCargo = 2;
	}

	//GEORGE:Modificato il tipo di ritorno del metodo in boolean per indicare se l'operazione è stata eseguita correttamente
	public boolean aumentaCargoCorrente(Cargo nuovoCargo)
	{
		if(this.spazioCargo >= this.listaCargo.size() + 1)	//Check se c'è abbastanza spazio nella stiva
		{
			if(nuovoCargo.getColore() == ColoreCargo.ROSSO && !this.isSpeciale)	//Check se il cargo che si vuole aggiungere è rosso (non idoneo a stive normali)
			{
				return false;
			}
			else 
			{
				listaCargo.add(nuovoCargo);
				return true;
			}
		}
		else
		{
			return false;
		}
			
	}

	public int getSpazioCargo() 
	{
		return spazioCargo;
	}
	
	public List<Cargo> getCargoCorrente() //Get merci trasportate in questo momento
	{
		return listaCargo;
	}
	
	public boolean isSpeciale() 
	{
		return this.isSpeciale;
	}
	
	public void stampaCargoCorrente() 
	{
		StringBuilder sb = new StringBuilder();
		int i = 1;
		for (Cargo merce : listaCargo) 
		{
			sb.append(i + "/t");
			sb.append(merce.toString() + "/t");
			sb.append(merce.getValore() + "/n");
			i++;
		}
		System.out.println("/tTipo cargo:/tValore:/n" + sb);
	}
	
	@Override
	public String nomeComponente() 
	{
		if (!this.isSpeciale) 
		{
			if (this.spazioCargo > 2)
				return String.format("%1$5S", "STI ");
			else
				return String.format("%1$5S", "sti ");
		}
		else 
		{
			if (this.spazioCargo > 1)
				return String.format("%1$5S", "STS ");
			else
				return String.format("%1$5S", "sts ");
		}
	}
	
}
