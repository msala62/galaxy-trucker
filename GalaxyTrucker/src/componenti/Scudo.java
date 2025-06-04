package componenti;

public class Scudo extends Componente {

	private Direzione dirScudo1;
	private Direzione dirScudo2;
	
	public Scudo(Connettore SX, Connettore DX, Connettore SU, Connettore GIU, Direzione dir1, Direzione dir2) {
		super(SX, DX, SU, GIU);
		this.dirScudo1 = dir1; //Imposta direzione iniziale protezione scudo
		this.dirScudo2 = dir2;
		this.richiedeBatterie=true;
	}

	public Direzione getDirScudo1()
	{
		return dirScudo1;
	}

	public Direzione getDirScudo2()
	{
		return dirScudo2;
	}

	//Boolean così il chiamante può controllarne il risultato e sapere se scalare il danno del pericolo (true) oppure no (false)
	public boolean attivaScudo(Batteria batteria) 
	{
		if(batteria.getCarica() > 0) 
		{
			batteria.scalaCarica();
			return true;
		}
		return false;
	}
	
	@Override
	public void ruota(char direzioneRotazione) 
	{
		Connettore temp;
		switch (direzioneRotazione)
		{
			case 'a':	//'a' per "antiorario"
				temp = this.connettoreSX;
				this.connettoreSX = this.connettoreSU;
				this.connettoreSU = this.connettoreDX;
				this.connettoreDX = this.connettoreGIU;
				this.connettoreGIU = temp;
				this.dirScudo1 = this.dirScudo1.cambioDirezioneA();	//Cambio direzione scudo
				this.dirScudo2 = this.dirScudo2.cambioDirezioneA();
				break;
			
			case 'o':	//'o' per "orario"
				temp = this.connettoreSX;
				this.connettoreSX = this.connettoreGIU;
				this.connettoreGIU = this.connettoreDX;
				this.connettoreDX = this.connettoreSU;
				this.connettoreSU = temp;
				this.dirScudo1 = this.dirScudo1.cambioDirezioneO();
				this.dirScudo2 = this.dirScudo2.cambioDirezioneO();
				break;
			
			default:	//Errore se non viene digitato 'a' oppure 'o'
				System.out.println("Errore di digitazione");
		}
	}	
	
	@Override
	public String nomeComponente()
	{
		return String.format("%1$5s", "scu ");
	}

	@Override
	public String toString() 
	{
		String componente = String.format("%1$8s", "") + connettoreSU.toString() + String.format("%1$6s", "") + "\n"
				+ connettoreSX.toString() + this.nomeComponente() + connettoreDX.toString() + "\n" 
				+ String.format("%1$8s", "") + connettoreGIU.toString() + String.format("%1$6s", "")
				+ "\n Direzioni scudo: " + this.getDirScudo1().toString() + ", " + this.getDirScudo2().toString();
		
		return componente;	
	}
}
