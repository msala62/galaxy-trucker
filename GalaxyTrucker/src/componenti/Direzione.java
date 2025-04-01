package componenti;

public enum Direzione {
	SX, DX, SU, GIU; //Servono per i cannoni che cambiano proprietà rispetto alla proprio direzione, e per i motori che possono solo guardare GIU
	
	//Cambio direzione a seguito di una rotazione. A = antiorario; O = orario
	public Direzione cambioDirezioneA() 
	{
		switch (this) 
		{
		case SX:
			return GIU;
		case DX:
			return SU;
		case SU:
			return SX;
		case GIU:
			return DX;
		default:	//Messo per evitare errore compilatore
			return SU;
		}
	}
	
	public Direzione cambioDirezioneO() 
	{
		switch (this) 
		{
		case SX:
			return SU;
		case DX:
			return GIU;
		case SU:
			return DX;
		case GIU:
			return SX;
		default:	//Messo per evitare errore compilatore
			return SU;
		}		
	}
	
}
