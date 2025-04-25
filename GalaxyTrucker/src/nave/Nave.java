package nave;

public class Nave {
	private Casella[][] plancia;
	
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
}
