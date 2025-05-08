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
				System.out.println("Solo le stive speciali possono contenere i carghi pericolosi di colore rosso");
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
			System.out.println("Spazio insufficiente");
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
		if (!this.isSpeciale) 
		{
			if (this.spazioCargo > 2)
				return "Stiva*";
			else
				return "Stiva";
		}
		else 
		{
			if (this.spazioCargo > 1)
				return "StivaS*";
			else
				return "StivaS";
		}
	}
	
}
