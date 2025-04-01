package componenti;

public abstract class Componente {
	//Ogni direzione ha un proprio tipo di connettore
	protected  Connettore connettoreSX;
	protected  Connettore connettoreDX;
	protected  Connettore connettoreSU;
	protected  Connettore connettoreGIU;
	
	Componente (Connettore SX, Connettore DX, Connettore SU, Connettore GIU)
	{
		this.connettoreSX = SX;
		this.connettoreDX = DX;
		this.connettoreSU = SU;
		this.connettoreGIU = GIU;
	}
	
	//Metodo per ruotare il componente. Ha bisogno di uno char che indichi la direzione in cui ruotare
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
				break;
			
			case 'o':	//'o' per "orario"
				temp = this.connettoreSX;
				this.connettoreSX = this.connettoreGIU;
				this.connettoreGIU = this.connettoreDX;
				this.connettoreDX = this.connettoreSU;
				this.connettoreSU = temp;
				break;
			
			default:	//Errore se non viene digitato 'a' oppure 'o'
				System.out.println("Errore di digitazione");
		}
	}
	
	public Connettore getConnettoreSX()
	{
		return connettoreSX;
	}
	
	public Connettore getConnettoreDX()
	{
		return connettoreDX;
	}
	
	public Connettore getConnettoreSU()
	{
		return connettoreSU;
	}
	
	public Connettore getConnettoreGIU()
	{
		return connettoreGIU;
	}
	
	//TODO Metodo per ritornare una rappresentazione string del componente
	public abstract String stampa();
}
