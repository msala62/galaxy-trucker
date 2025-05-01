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

	// Ogni connettore ha un metodo toString per rappresentarlo nella nave. Idem i
	// componeneti (nomeComponente) che usano un'abbreviazione del proprio nome.
	// Se c'è * dopo all'abbreviazione significa che il componente è la versione
	// grande di se stesso
	public String toString() {
		String componente = "\t" + connettoreSU.toString(connettoreSU) + "\n" + connettoreSX.toString(connettoreSX)
				+ "\t" + this.nomeComponente() + "\t" + connettoreDX.toString(connettoreDX) + "\n\t"
				+ connettoreGIU.toString(connettoreGIU);

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
		if (this instanceof cargoInterfaccia) {
			return ((cargoInterfaccia) this).getCargoCorrente();
		} else {
			return null;
		}
	}

}
