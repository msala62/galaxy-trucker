package componenti;

public class Motore extends Componente implements CalcoloPotenza {

	protected int potenza;
	
	public Motore(Connettore SX, Connettore DX, Connettore SU, Connettore GIU) {
		super(SX, DX, SU, GIU);
		this.potenza = 1; //È sempre 1 per i motori normali 
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
	public String nomeComponente() {
		return "Mot";
	}
}
