package componenti;

public enum Connettore {
	SINGOLO, DOPPIO, UNIVERSALE, LISCIO; //(lato) liscio = nessun connettore
	
	public String toString() 
	{
		switch(this) 
		{
			case SINGOLO:
				return String.format("%1$10S", "-     ");
				
			case DOPPIO:
				return String.format("%1$10S", "=     ");
				
			case UNIVERSALE:
				return String.format("%1$10S", "#     ");
				
			default:
				return "";
		}
	}
}
