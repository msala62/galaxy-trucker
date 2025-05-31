
//Evento negativo. Ogni cabina interconnessa a un’altra occupata perde un membro dell’equipaggio.

package carteAvventura;

import java.util.List;

import componenti.CabinaPartenza;
import game_logic.Giocatore;
import nave.Nave;
import planciavolo.PlanciaVolo;

public class Epidemia extends Carta {

	public Epidemia(Livello livello) {
		super(livello, "Epidemia" );
		// TODO Auto-generated constructor stub
	}
	
	@Override
    public void azione(List<Giocatore> giocatori, PlanciaVolo plancia) {
	
    	for (int x=0; x<giocatori.size(); x++)
    	{
    		Giocatore giocatore = giocatori.get(x);
    		System.out.println("==================== Giocatore: " + giocatore.getNome()+"========================");
    		for (int i= 0; i<giocatore.getNave().getPlancia().length; i++ )
    		{
        		for (int j= 0; j<giocatore.getNave().getPlancia()[0].length; j++ )
        		{
        			if(giocatore.getNave().getPlancia()[i][j].isUtilizzabile() && giocatore.getNave().getPlancia()[i][j].getComponente() instanceof CabinaPartenza)
        			{
        				CabinaPartenza c = (CabinaPartenza) giocatore.getNave().getPlancia()[i][j].getComponente();
        				if (c.getEquipaggio()>0 && verificaVicini(giocatore.getNave(), i, j))
        				{
        				c.setEquipaggio(c.getEquipaggio()-1);	
        				}
        			}
        		}

    		}
    	}
	}
    
    public boolean verificaVicini(Nave nave, int i , int j)
    {
    	if (i+1<nave.getPlancia().length)
    	{
    		if (nave.getPlancia()[i+1][j].isUtilizzabile() && nave.getPlancia()[i+1][j].getComponente() instanceof CabinaPartenza)
        	{
        		CabinaPartenza c = (CabinaPartenza) nave.getPlancia()[i+1][j].getComponente();
    			if (c.getEquipaggio()>0)
    			{
    				return true;
    			}
        	}	
    	}
    	 
    	if (i-1>=0)
    	{
    	 if (nave.getPlancia()[i-1][j].isUtilizzabile() && nave.getPlancia()[i-1][j].getComponente() instanceof CabinaPartenza)
        	{
        		CabinaPartenza c = (CabinaPartenza) nave.getPlancia()[i-1][j].getComponente();
    			if (c.getEquipaggio()>0)
    			{
    				return true;
    			}
        	}	
    	}
    	
    	if (j+1<nave.getPlancia()[0].length)
    	{
    	if (nave.getPlancia()[i][j+1].isUtilizzabile() && nave.getPlancia()[i][j+1].getComponente() instanceof CabinaPartenza)
        	{
        		CabinaPartenza c = (CabinaPartenza) nave.getPlancia()[i][j+1].getComponente();
    			if (c.getEquipaggio()>0)
    			{
    				return true;
    			}
        	}
        		
    	}
    	
    	if (j-1>=0)
    	{
    		if (nave.getPlancia()[i][j-1].isUtilizzabile() && nave.getPlancia()[i][j-1].getComponente() instanceof CabinaPartenza)
        	{
        		CabinaPartenza c = (CabinaPartenza) nave.getPlancia()[i][j-1].getComponente();
    			if (c.getEquipaggio()>0)
    			{
    				return true;
    			}
        	}	
    	}
    	
    	
    	return false;
    }

}
