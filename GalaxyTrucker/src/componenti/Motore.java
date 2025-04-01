package componenti;

public class Motore extends Componente {

	protected Direzione direzione;
	protected int potenza;
	
	Motore(Connettore SX, Connettore DX, Connettore SU, Connettore GIU) {
		super(SX, DX, SU, GIU);
		this.direzione = Direzione.GIU;
		this.potenza = 1; //È sempre 1 per i motori normali 
	}
	
	public Direzione getDirezione()
	{
		return direzione;
	}

	public int getPotenza()
	{
		return potenza;
	}

	@Override
	public void ruota(char direzioneRotazione) 
	{
		System.out.println("I componenti motore possono solo guardare verso il retro della nave");
	}
	
	@Override
	public String stampa() {
		return null;
		// TODO Auto-generated method stub
	}
}
