package nave;

import java.util.ArrayList;
import java.util.List;

import componenti.CabinaPartenza;
import componenti.Cannone;
import componenti.Componente;
import componenti.Connettore;
import componenti.Stiva;

public class Nave {
	private Casella[][] plancia;
	
	public Nave() {
		this.plancia = new Casella[5][7];

		for (int i = 0; i < 5; i++) {
			for (int j = 0; j < 7; j++) {
				plancia[i][j] = new Casella(new Coordinata(i, j));
			}
		}

		int[][] caselleUtilizzabili = { { 0, 3 }, { 1, 2 }, { 1, 3 }, { 1, 4 }, { 2, 1 }, { 2, 2 }, { 2, 3 }, { 2, 4 },
				{ 2, 5 }, { 3, 1 }, { 3, 2 }, { 3, 3 }, { 3, 4 }, { 3, 5 }, { 4, 1 }, { 4, 2 }, { 4, 4 }, { 4, 5 } };

		for (int[] coord : caselleUtilizzabili) {
			this.plancia[coord[0]][coord[1]].utilizzabile = true;
		}
	}

	public void stampa() {
		String RESET = "\u001B[0m";
		String GREEN = "\u001B[32m";
		String YELLOW = "\u001B[33m";

		for (int i = 0; i < 5; i++) {
			for (int j = 0; j < 7; j++) {
				if (this.plancia[i][j].utilizzabile) {
					System.out.print(GREEN + this.plancia[i][j].getComponente() + "\t" + RESET);
				} else {
					if ((i == 0 && j == 5) || (i == 0 && j == 6)) {
						System.out.print(YELLOW + this.plancia[i][j].getComponente() == null ? this.plancia[i][j] : this.plancia[i][j].getComponente() + "\t" + RESET);
					} else {
						System.out.print("  \t");
					}
				}

			}
			System.out.println("");
		}
	}

	public boolean aggiungiComponente(int y, int x, Componente tessera) {
		for(int i = 0; i < 5; i++) {
			for(int j = 0; j < 7; j++) {
				if(y == i && x == j) {
					if(plancia[y][x].utilizzabile) {
						int sopra = y - 1;
						int sotto = y + 1;
						int destra = x + 1;
						int sinistra = x - 1;
						
						Componente su; 
						Componente giu; 
						Componente dx; 
						Componente sx;
						
						// Punti di uscita dall'area della nave e punti proibiti
						if(y == 0 && (x == 5 || x == 6)) return false;
						if((x < 0 || x > 7) && (y < 0 || y > 5)) return false;
						
						if(y == 0) {
							giu = plancia[y][sotto].getComponente();
							
							if(giu == null) {
								this.plancia[y][x].setComponente(tessera);
								return true;								
							} else {
								if(tessera.getConnettoreGIU() == giu.getConnettoreSU() || giu.getConnettoreSU() == Connettore.UNIVERSALE) {
									this.plancia[y][x].setComponente(tessera);
									return true;
								}								
							}
						}
						
						else if(y == 4) {
							su = plancia[x][sopra].getComponente();
							
							if(su == null) {
								this.plancia[y][x].setComponente(tessera);
								return true;
							} else {
								if(tessera.getConnettoreSU() == su.getConnettoreGIU() || su.getConnettoreGIU() == Connettore.UNIVERSALE) {
									this.plancia[y][x].setComponente(tessera);
									return true;
								}								
							}
						}
						
						else {
							su = plancia[sopra][x].getComponente();
							dx = plancia[y][destra].getComponente();
							sx = plancia[y][sinistra].getComponente();
							
							if(sotto > 5 && dx == null) {
								if(((tessera.getConnettoreSU() == su.getConnettoreGIU()) || su.getConnettoreGIU() == Connettore.UNIVERSALE)
									&& ((tessera.getConnettoreSX() == sx.getConnettoreDX()) || sx.getConnettoreDX() == Connettore.UNIVERSALE)
									) {
									this.plancia[y][x].setComponente(tessera);
									return true;
								}
							} else if(sotto > 5 && sx == null) {
								if(((tessera.getConnettoreSU() == su.getConnettoreGIU()) || su.getConnettoreGIU() == Connettore.UNIVERSALE)
									&& ((tessera.getConnettoreDX() == dx.getConnettoreSX()) || dx.getConnettoreSX() == Connettore.UNIVERSALE)
									) {
									this.plancia[y][x].setComponente(tessera);
									return true;
								}
							} else {
								giu = plancia[sotto][x].getComponente();
								
								if(((tessera.getConnettoreSU() == su.getConnettoreGIU()) || su.getConnettoreGIU() == Connettore.UNIVERSALE)
										&& ((tessera.getConnettoreGIU() == giu.getConnettoreSU()) || giu.getConnettoreSU() == Connettore.UNIVERSALE)
										&& ((tessera.getConnettoreDX() == dx.getConnettoreSX()) || dx.getConnettoreSX() == Connettore.UNIVERSALE)
										&& ((tessera.getConnettoreSX() == sx.getConnettoreDX()) || sx.getConnettoreDX() == Connettore.UNIVERSALE)
										) {
									this.plancia[y][x].setComponente(tessera);
									return true;
								}
							}
							
						}
					} else {
						return false;
					}
				}
			}
		}
		
		return false;
	}

	public int getEquipaggioTotale() {
		int equipaggioTotale = 0;
		for (int i = 0; i < plancia.length; i++) {
			for (int j = 0; j < plancia[0].length; j++) {
				if (this.plancia[i][j].utilizzabile && this.plancia[i][j].getComponente() != null
						&& this.plancia[i][j].getComponente() instanceof CabinaPartenza) {
					CabinaPartenza c = (CabinaPartenza) this.plancia[i][j].getComponente();
					equipaggioTotale += c.getEquipaggio();

				}
			}
		}
		return equipaggioTotale;
	}

	// GEORGE: metodo per eliminare l'equipaggio dalla nave
	public boolean eliminaEquipaggio(int equipaggioDaEliminare) {
		for (int i = 0; i < plancia.length; i++) {
			for (int j = 0; j < plancia[0].length; j++) {
				if (this.plancia[i][j].utilizzabile && this.plancia[i][j].getComponente() instanceof CabinaPartenza) {
					CabinaPartenza c = (CabinaPartenza) this.plancia[i][j].getComponente();
					while (c.getEquipaggio() != 0 && equipaggioDaEliminare > 0) {
						c.setEquipaggio(c.getEquipaggio() - 1);
						equipaggioDaEliminare--;
					}
					if (equipaggioDaEliminare == 0)
						return true;
				}
			}
		}
		return false;
	}

	public boolean caricaCargo(List<Cargo> cargo) {
		if (cargo.isEmpty())
			return false;

		for (int i = 0; i < plancia.length; i++) {
			for (int j = 0; j < plancia[0].length; j++) {
				if (cargo.isEmpty())
					return true;

				if (this.plancia[i][j].utilizzabile && this.plancia[i][j].getComponente() instanceof Stiva) {
					Stiva stiva = (Stiva) this.plancia[i][j].getComponente();
					while ( !cargo.isEmpty() && stiva.aumentaCargoCorrente()) {
						cargo.removeFirst();
					}
				}
			}
		}

		return cargo.isEmpty();
	}
	
	public boolean eliminaCargo(List<Cargo> cargo) {

		if (cargo.isEmpty())
			return false;

		for (int i = 0; i < plancia.length; i++) {
			for (int j = 0; j < plancia[0].length; j++) {
				if (cargo.isEmpty())
					return true;

				if (this.plancia[i][j].utilizzabile && this.plancia[i][j].getComponente() instanceof Stiva) {
					Stiva stiva = (Stiva) this.plancia[i][j].getComponente();
					while ( !cargo.isEmpty() && stiva.aumentaCargoCorrente()) {
						cargo.removeFirst();
					}
				}
			}
		}

		return cargo.isEmpty();
	}

	public int getPotenzaMotrice() {
		int potenzaMotrice = 0;
		for (int i = 0; i < this.plancia.length; i++)
			for (int j = 0; j < this.plancia[0].length; j++) {
				if (this.plancia[i][j].isUtilizzabile() && this.plancia[i][j].getComponente() != null)
					if (getPlancia()[i][j].getComponente() instanceof CalcoloPotenza) {
						CalcoloPotenza p = (CalcoloPotenza) this.plancia[i][j].getComponente();
						potenzaMotrice = potenzaMotrice + p.getPotenza();
					}
			}
		return potenzaMotrice;
	}

	public double getPotenzaFuoco() {
		double potenzaFuoco = 0;
		for (int i = 0; i < this.plancia.length; i++)
		for (int j = 0; j < this.plancia[0].length; j++) {
		if (this.plancia[i][j].isUtilizzabile() && this.plancia[i][j].getComponente() != null)
		if (this.plancia[i][j].getComponente() instanceof Cannone) {
		Cannone p = (Cannone) this.plancia[i][j].getComponente();
		potenzaFuoco = potenzaFuoco + p.getPotenza();
		}
		}
				return potenzaFuoco;
			}
	
}