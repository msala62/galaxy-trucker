package componenti;

public class Cannone extends Componente {
	
	protected Direzione direzione;
	protected double potenza;

	public Cannone(Connettore SX, Connettore DX, Connettore SU, Connettore GIU) {
		super(SX, DX, SU, GIU);
		this.direzione = Direzione.SU;
		this.potenza= 1;
	}

	protected void calcolaPotenza() 
	{
		switch(this.direzione) 
		{
		case SX:
		case DX:
		case GIU:
			this.potenza=0.5;
			break;
		case SU:
			this.potenza=1;
		}
	}
	
	public Direzione getDirezione()
	{
		return direzione;
	}
	
	public double getPotenza()
	{
		return potenza;
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
				this.direzione = this.direzione.cambioDirezioneA();	//Cambio direzione cannone
				//TODO check direzione valida valida prima di saldare
				this.calcolaPotenza();	//Ricalcolo potenza dopo cambio direzione
				break;
			
			case 'o':	//'o' per "orario"
				temp = this.connettoreSX;
				this.connettoreSX = this.connettoreGIU;
				this.connettoreGIU = this.connettoreDX;
				this.connettoreDX = this.connettoreSU;
				this.connettoreSU = temp;
				this.direzione = this.direzione.cambioDirezioneO();
				//TODO check direzione valida valida prima di saldare
				this.calcolaPotenza(); 
				break;
			
			default:	//Errore se non viene digitato 'a' oppure 'o'
				System.out.println("Errore di digitazione");
		}
	}
	
	@Override
	public String nomeComponente() {
		return "Can";
	}

}
