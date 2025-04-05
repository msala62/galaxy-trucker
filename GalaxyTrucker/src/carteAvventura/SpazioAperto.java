package carteAvventura;

public class SpazioAperto extends Carta {
	
	public SpazioAperto(Livello livello) {
		super(livello);
		// TODO Auto-generated constructor stub
	}

	@Override
    public void azione() {
		
	}
    public void azione(int potenzaMotrice, int posizione) {
		
		if (potenzaMotrice<1)
		{
			System.out.println("giocatore costretto ad abbandonare la corsa​");
		}
		else {
			posizione =posizione + potenzaMotrice;
		}
		
	};

}
