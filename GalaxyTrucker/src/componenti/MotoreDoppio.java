package componenti;

import java.util.Scanner;

public class MotoreDoppio extends Motore implements CalcoloPotenza {

	public MotoreDoppio(Connettore SX, Connettore DX, Connettore SU, Connettore GIU) {
		super(SX, DX, SU, GIU);
		this.potenza=0;
		this.richiedeBatterie=true;
	}
	
	public void spegniMotore() //TODO Da chiamare solo aver usato il motore. La sua potenza torna a 0 perché non conta più
	{
		this.potenza=0;
	}

	public void accendiMotore(Batteria batteria) //Scegliere quale batteria usare prima
	{
		if(batteria.getCarica() > 0) 
		{
			batteria.scalaCarica();
			this.potenza=2;
		}
	}
	
/*	public int getPotenza() 
	{
		Scanner sc = new Scanner(System.in);
		System.out.println("Al momento il motore e' spento e pertanto non conta verso il totale della potenza motrice, volete accenderlo? S/N");
		String risposta = sc.nextLine();
		
		while(risposta!="S" && risposta!="N")
		{
			System.out.println("Errore di digitazione. Premere 'S' per si e 'N' per no. riprovare: ");
			risposta = sc.nextLine();
		}
		
		if(risposta=="S")
		{
			System.out.println("Scegleire quale batteria utilizzare inserendone le coordinate: ");
			int colonna = sc.nextInt();
			int riga = sc.nextInt();
		}
	}
*///WIP TODO
	
	@Override
	public String nomeComponente() {
		return "Mot*";
	}
	
}
