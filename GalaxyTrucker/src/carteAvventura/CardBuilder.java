package carteAvventura;

import java.util.List;

public class CardBuilder {
	private Carta carta;
	private List<Meteorite> meteoriti;
	private List<Pianeta> pianeti;
	private int giorniDaPerdere = 0;
	private int equipaggioDaPerdere = 0;	//Nave abbandonata
	private int creditiDaAcquistare = 0;	// Nave abbandonata
	private List<Cargo> cargoDaPerdere;		//Contrabbandieri
}
