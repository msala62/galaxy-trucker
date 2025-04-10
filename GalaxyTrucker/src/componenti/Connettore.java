package componenti;

public enum Connettore {
	SINGOLO, DOPPIO, UNIVERSALE, LISCIO; //(lato) liscio = nessun connettore
	
	public String toString(Connettore connettore) 
	{
		switch(connettore) 
		{
			case SINGOLO:
				return "-";
				
			case DOPPIO:
				return "=";
				
			case UNIVERSALE:
				return "-=";
				
			default:
				return "";
		}
	}
}
