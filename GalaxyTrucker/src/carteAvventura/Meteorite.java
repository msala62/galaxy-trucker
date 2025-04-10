package carteAvventura;

public class Meteorite {
	
	private MeteoriteDimensione dimensione;
	private MeteoriteDirezione direzione;
	
	public Meteorite (MeteoriteDimensione dimensione, MeteoriteDirezione direzione)
	{
		this.setDimensione(dimensione);
		this.setDirezione(direzione);
		
	}

	public MeteoriteDimensione getDimensione() {
		return dimensione;
	}

	public void setDimensione(MeteoriteDimensione dimensione) {
		this.dimensione = dimensione;
	}

	public MeteoriteDirezione getDirezione() {
		return direzione;
	}

	public void setDirezione(MeteoriteDirezione direzione) {
		this.direzione = direzione;
	}

}
