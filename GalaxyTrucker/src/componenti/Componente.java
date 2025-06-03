package componenti;

public abstract class Componente {
	// Ogni direzione ha un proprio tipo di connettore
	protected Connettore connettoreSX;
	protected Connettore connettoreDX;
	protected Connettore connettoreSU;
	protected Connettore connettoreGIU;

	protected boolean richiedeBatterie; // Usato dal titolo "Batterista" per determianare quanti pezzi che utilizzano
										// batterie ha ciascun giocatore

	public Componente(Connettore SX, Connettore DX, Connettore SU, Connettore GIU) {
		this.connettoreSX = SX;
		this.connettoreDX = DX;
		this.connettoreSU = SU;
		this.connettoreGIU = GIU;
		this.richiedeBatterie = false; // Inizializzato a false. I componenti interessati lo cambiano a true nel
										// proprio costruttore
	}

	// Metodo per ruotare il componente. Ha bisogno di un char che indichi la
	// direzione in cui ruotare
	public void ruota(char direzioneRotazione) {
		Connettore temp;
		switch (direzioneRotazione) {
		case 'a': // 'a' per "antiorario"
			temp = this.connettoreSX;
			this.connettoreSX = this.connettoreSU;
			this.connettoreSU = this.connettoreDX;
			this.connettoreDX = this.connettoreGIU;
			this.connettoreGIU = temp;
			break;

		case 'o': // 'o' per "orario"
			temp = this.connettoreSX;
			this.connettoreSX = this.connettoreGIU;
			this.connettoreGIU = this.connettoreDX;
			this.connettoreDX = this.connettoreSU;
			this.connettoreSU = temp;
			break;

		default: // Errore se non viene digitato 'a' oppure 'o'
			System.out.println("Errore di digitazione");
		}
	}

	public Connettore getConnettoreSX() {
		return connettoreSX;
	}

	public Connettore getConnettoreDX() {
		return connettoreDX;
	}

	public Connettore getConnettoreSU() {
		return connettoreSU;
	}

	public Connettore getConnettoreGIU() {
		return connettoreGIU;
	}

	public String toString() 
	{
		String componente = String.format("%1$8s", "") + connettoreSU.toString() + String.format("%1$6s", "") + "\n"
				+ connettoreSX.toString() + this.nomeComponente() + connettoreDX.toString() + "\n" 
				+ String.format("%1$8s", "") + connettoreGIU.toString() + String.format("%1$6s", "");
		
		return componente;		
	}
	
	public abstract String nomeComponente();

	// GEORGE: metodo aggiunto per ottenere il valore dell'attributo
	// richiedeBatterie
	public boolean getRichiedeBatterie() {
		return richiedeBatterie;
	}

	public void setRichiedeBatterie() {
		this.richiedeBatterie = true;
	}

	public Integer tryGetCargoCorrente() {
		if (this instanceof Stiva) {
			return ((Stiva) this).getCargoCorrente().size();
		} else {
			return null;
		}
	}

}
