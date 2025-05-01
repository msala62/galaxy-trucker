package nave;

import java.util.ArrayList;
import java.util.List;

import componenti.CabinaPartenza;
import componenti.Componente;

public class Nave {
	private Casella[][] plancia;
	public List<Componente> tesserePescate = new ArrayList<Componente>();
	
	public Nave() {
		this.plancia = new Casella[5][7];
		
		for(int i = 0; i < 5; i++) {
			for(int j = 0; j < 7; j++) {
				plancia[i][j] = new Casella(new Coordinata(i, j));
			}
		}
		
		int[][] caselleUtilizzabili = {
		        {0, 3},
		        {1, 2}, {1, 3}, {1, 4},
		        {2, 1}, {2, 2}, {2, 3}, {2, 4}, {2, 5},
		        {3, 1}, {3, 2}, {3, 3}, {3, 4}, {3, 5},
		        {4, 1}, {4, 2}, {4, 4}, {4, 5}
		};
		    
	    for (int[] coord : caselleUtilizzabili) {
	        this.plancia[coord[0]][coord[1]].utilizzabile = true;
	    }
	}
	
	public void stampa() {
		String RESET = "\u001B[0m";
        String GREEN = "\u001B[32m";
        String YELLOW = "\u001B[33m";

		for(int i = 0; i < 5; i++) {
			for(int j = 0; j < 7; j++) {
				if(this.plancia[i][j].utilizzabile) {
					System.out.print(GREEN + this.plancia[i][j] + "\t" + RESET);
				} else {
					if((i == 0 && j == 5) || (i == 0 && j == 6)) {
						System.out.print(YELLOW + this.plancia[i][j] + "\t" + RESET);
					} else {
						System.out.print(this.plancia[i][j] + "\t");											
					}
				}
				
			}
			System.out.println("");
		}
	}

	public Casella[][] getPlancia() {
		return plancia;
	}

	public int getEquipaggioTotale() {
		int equipaggioTotale = 0;
		for (int i = 0; i < plancia.length; i++) {
			for (int j = 0; j < plancia[0].length; j++) {
				if (this.plancia[i][j].utilizzabile && this.plancia[i][j].getComponente() != null) {
					if (this.plancia[i][j].getComponente() instanceof CabinaPartenza) {
						CabinaPartenza c = (CabinaPartenza) this.plancia[i][j].getComponente();
						equipaggioTotale += c.getEquipaggio();
					}

				}
			}
		}
		return equipaggioTotale;
	}


	//GEORGE: metodo per eliminare l'equipaggio dalla nave
	public int eliminaEquipaggio (int equipaggioDaEliminare)
	{
		for(int i =0; i<plancia.length; i++)
		{
			for(int j = 0; j<plancia[0].length; j++)
			{
				if (this.plancia[i][j].utilizzabile && this.plancia[i][j].getComponente() instanceof CabinaPartenza)
				{
					CabinaPartenza c = (CabinaPartenza) this.plancia[i][j].getComponente();
					while (c.getEquipaggio()!=0)
					{
						if (equipaggioDaEliminare>0)
						{
						 c.setEquipaggio(c.getEquipaggio()-1);
						 equipaggioDaEliminare--;
						} else if (equipaggioDaEliminare==0)
							return 0;
					}
				}
			}
		}
		return -1;
	}
}
