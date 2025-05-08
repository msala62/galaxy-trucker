package nave;

import java.util.ArrayList;
import java.util.List;

import componenti.*;
import merci.Cargo;

public class Nave {
	private Casella[][] plancia;
	private int equipaggio;

	public Nave() {
		this.plancia = new Casella[5][7];
		this.equipaggio = getEquipaggioTotale();

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
	
	public Casella[][] getPlancia(){
		return this.plancia;
	}

	/*	public void stampa() {
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
}*///Lasciata in forma di commento nel caso la versione sottostante non funzionasse

public void stampa() 
{
	String RESET = "\u001B[0m";
	String GREEN = "\u001B[32m";
	String YELLOW = "\u001B[33m";
	
	StringBuilder sbSopra = new StringBuilder();//Per connettore SU
	StringBuilder sbMezzo = new StringBuilder();//Per connettori SX DX e nome componente
	StringBuilder sbSotto = new StringBuilder();//Per connettore GIU
	
	for (int i = 0; i < 7; i++)//Per mostrare le colonne
		System.out.print(YELLOW + "/t" + i + "/t" + RESET);
	
	for (int i = 0; i < 5; i++) 
	{
		sbMezzo.append(YELLOW + i + RESET);//Per mostrare le righe
		sbMezzo.append("/t");
		for (int j = 0; j < 7; j++) 
		{
			if (this.plancia[i][j].utilizzabile)//Se la casella è utilizzabile si cerca il componente associato e ogni sua parte viene appended allo stringbuilder associato. TODO cosa fare se casella è utilizzabile, ma vuota
			{
				sbSopra.append(GREEN + "/t" + this.plancia[i][j].getComponente().getConnettoreSU() + "/t" + RESET);
				sbMezzo.append(GREEN + this.plancia[i][j].getComponente().getConnettoreSX() + "/t" + this.plancia[i][j].getComponente().nomeComponente() + "/t" + this.plancia[i][j].getComponente().getConnettoreDX() + RESET);
				sbSotto.append(GREEN + "/t" + this.plancia[i][j].getComponente().getConnettoreGIU() + "/t" + RESET);
			}
			else//Se la casella non è utilizzabile si appenda due tabulazioni
			{
				sbSopra.append("/t/t");
				sbMezzo.append("/t/t");
				sbSotto.append("/t/t");
			}
		}
		System.out.println(sbSopra + "/n" + sbMezzo + "/n" + sbSotto + "/n");//Si printa una riga di componenti alla volta
	}
}

	//IMPORTANTE: Y sono le righe e X le colonne
	public boolean aggiungiComponente(int y, int x, Componente tessera) {
		Componente inserimento = tessera;
		
		for(int i = 0; i < 5; i++) {
			for(int j = 0; j < 7; j++) {
				if(y == i && x == j) {
					if(plancia[y][x].utilizzabile) {
						if(tessera.equals(Cabina.class)) {
							this.equipaggio += 2;
							inserimento = new Cabina(tessera.getConnettoreSX(), tessera.getConnettoreDX(), tessera.getConnettoreSU(), tessera.getConnettoreGIU());
							((Cabina) inserimento).setEquipaggio(2);
						} else if(tessera.equals(Scudo.class) && getEnergia() > 0) {
							for(int k = 0; k < 5; k++) {
								for(int z = 0; z < 7; z++) {
									if(isUtilizzabile(plancia[k][z]) && plancia[k][z].getComponente().equals(Batteria.class)) {
										if(((Batteria) plancia[k][z].getComponente()).getCarica() > 0) {
											((Batteria) plancia[k][z].getComponente()).scalaCarica();
											break;											
										} else {
											continue;
										}
									}
								}
							}
						}
						
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
						
						//Posizionamento in cima alla nave
						if(y == 0) {
							giu = plancia[y][sotto].getComponente();
							
							if(giu == null) {
								if(tessera.equals(SupportoAlieni.class)) return false;
								this.plancia[y][x].setComponente(inserimento);
								return true;								
							} else {
								if(tessera.equals(SupportoAlieni.class) && giu.equals(Cabina.class)) {
									if(tessera.getConnettoreGIU() == giu.getConnettoreSU() || giu.getConnettoreSU() == Connettore.UNIVERSALE) {
										this.plancia[y][x].setComponente(inserimento);
										return true;
									}
								} else if((tessera.equals(Cannone.class) || tessera.equals(CannoneDoppio.class))) return false; 
								else {
									if(tessera.getConnettoreGIU() == giu.getConnettoreSU() || giu.getConnettoreSU() == Connettore.UNIVERSALE) {
										this.plancia[y][x].setComponente(inserimento);
										return true;
									}									
								}								
							}
						}
						
						//Posizionamento in fondo alla nave
						else if(y == 4) {
							su = plancia[x][sopra].getComponente();
							
							if(su == null) {
								if(tessera.equals(SupportoAlieni.class)) return false;
								this.plancia[y][x].setComponente(inserimento);
								return true;
							} else {
								if(tessera.equals(SupportoAlieni.class) && su.equals(Cabina.class)) {
									if(tessera.getConnettoreSU() == su.getConnettoreGIU() || su.getConnettoreGIU() == Connettore.UNIVERSALE) {
										this.plancia[y][x].setComponente(inserimento);
										return true;
									}
								} else if((tessera.equals(Cannone.class) || tessera.equals(CannoneDoppio.class))) return false;
								else {
									if(tessera.getConnettoreSU() == su.getConnettoreGIU() || su.getConnettoreGIU() == Connettore.UNIVERSALE) {
										this.plancia[y][x].setComponente(inserimento);
										return true;
									}							
									
								}								
							}
						}
						
						//Qualsiasi altra posizione centrale
						else {
							su = plancia[sopra][x].getComponente();
							dx = plancia[y][destra].getComponente();
							sx = plancia[y][sinistra].getComponente();
							
							if(sotto > 5 && dx == null) {
								if(((tessera.getConnettoreSU() == su.getConnettoreGIU()) || su.getConnettoreGIU() == Connettore.UNIVERSALE)
										&& ((tessera.getConnettoreSX() == sx.getConnettoreDX()) || sx.getConnettoreDX() == Connettore.UNIVERSALE)
										) {
									if(tessera.equals(SupportoAlieni.class) && sx.equals(Cabina.class)) {
										this.plancia[y][x].setComponente(inserimento);
										return true;										
									} else if((tessera.equals(Cannone.class) || tessera.equals(CannoneDoppio.class))) {
										this.plancia[y][x].setComponente(inserimento);
										return true;
									}
									else {
										this.plancia[y][x].setComponente(inserimento);
										return true;
									}
								}									
								
							} else if(sotto > 5 && sx == null) {
								if(((tessera.getConnettoreSU() == su.getConnettoreGIU()) || su.getConnettoreGIU() == Connettore.UNIVERSALE)
										&& ((tessera.getConnettoreDX() == dx.getConnettoreSX()) || dx.getConnettoreSX() == Connettore.UNIVERSALE)
										) {
									if(tessera.equals(SupportoAlieni.class) && dx.equals(Cabina.class)) {
										this.plancia[y][x].setComponente(inserimento);
										return true;										
									} else if((tessera.equals(Cannone.class) || tessera.equals(CannoneDoppio.class))) {
										this.plancia[y][x].setComponente(inserimento);
										return true;
									}
									else {
										this.plancia[y][x].setComponente(inserimento);
										return true;
									}
								}
								
							} else {
								giu = plancia[sotto][x].getComponente();
									if(((tessera.getConnettoreSU() == su.getConnettoreGIU()) || su.getConnettoreGIU() == Connettore.UNIVERSALE)
										&& ((tessera.getConnettoreGIU() == giu.getConnettoreSU()) || giu.getConnettoreSU() == Connettore.UNIVERSALE)
										&& ((tessera.getConnettoreDX() == dx.getConnettoreSX()) || dx.getConnettoreSX() == Connettore.UNIVERSALE)
										&& ((tessera.getConnettoreSX() == sx.getConnettoreDX()) || sx.getConnettoreDX() == Connettore.UNIVERSALE)
										) {
										if(tessera.equals(SupportoAlieni.class) && 
												(dx.equals(Cabina.class) || sx.equals(Cabina.class) ||
												 giu.equals(Cabina.class) || su.equals(Cabina.class))) {
											this.plancia[y][x].setComponente(inserimento);
											return true;											
										} else if((tessera.equals(Cannone.class) || tessera.equals(CannoneDoppio.class))) return false;
										else {
											this.plancia[y][x].setComponente(inserimento);
											return true;
									}
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
	
	public boolean isUtilizzabile(Casella casella) {
		return (casella.utilizzabile && casella.getComponente() != null) ? true : false;
	}
	
	public int getEnergia() {
		int energia = 0;
		
		for(int i = 0; i < 5; i++) {
			for(int j = 0; i < 7; j++) {
				if(isUtilizzabile(this.plancia[i][j]) && this.plancia[i][j].getComponente().equals(Batteria.class)) {
					Batteria batteria = (Batteria)this.plancia[i][j].getComponente();
					energia += batteria.getCarica();
				}
			}
		}
		
		return energia;
	}

	public int getEquipaggioTotale() {
		int equipaggioTotale = 0;
		
		for (int i = 0; i < plancia.length; i++) {
			for (int j = 0; j < plancia[0].length; j++) {
				if (isUtilizzabile(this.plancia[i][j]) && this.plancia[i][j].getComponente() instanceof CabinaPartenza) {
					CabinaPartenza c = (CabinaPartenza) this.plancia[i][j].getComponente();
					equipaggioTotale += c.getEquipaggio();

				}
			}
		}
		return equipaggioTotale;
	}

	// GEORGE: metodo per eliminare l'equipaggio dalla nave
	public boolean eliminaEquipaggio(int equipaggioDaEliminare) {
		if (equipaggioDaEliminare == 0)
			return true;
		
		for (int i = 0; i < plancia.length; i++) {
			for (int j = 0; j < plancia[0].length; j++) {
				if (isUtilizzabile(this.plancia[i][j]) && this.plancia[i][j].getComponente() instanceof CabinaPartenza) {
					CabinaPartenza c = (CabinaPartenza) this.plancia[i][j].getComponente();
					while (c.getEquipaggio() != 0 && equipaggioDaEliminare > 0) {
						c.setEquipaggio(c.getEquipaggio() - 1);
						equipaggioDaEliminare--;
					}
				}
			}
		}
		return false;
	}

	//TODO Funzione per scambiare merce tra due stive.
	//TODO overhaul anche di questa funzione. Assegnazione automatica o manuale?
	public boolean caricaCargo(List<Cargo> cargo) {
		if (cargo.isEmpty())
			return false;

		for (int i = 0; i < plancia.length; i++) {
			for (int j = 0; j < plancia[0].length; j++) {
				if (cargo.isEmpty())
					return true;

				if (isUtilizzabile(this.plancia[i][j]) && this.plancia[i][j].getComponente() instanceof Stiva) {
					Stiva stiva = (Stiva) this.plancia[i][j].getComponente();
					while (!cargo.isEmpty() && stiva.aumentaCargoCorrente(cargo.getFirst())) {
						cargo.removeFirst();
					}
				}
			}
		}

		return cargo.isEmpty();
	}
	
	//Per eliminare un certo numero di merci partendo dalle più costose, come nel caso si venisse derubati da pirati o contrabbandieri
	public boolean eliminaCargo(int cargoDaPerdere) {
		
		//I primi due for loop setacciano l'intera nave per istanze di componenti stiva
		for (int i = 0; i < plancia.length; i++) 
		{
			for (int j = 0; j < plancia[0].length; j++) 
			{
				if (cargoDaPerdere <= 0)
					return true;

				if (isUtilizzabile(this.plancia[i][j]) && this.plancia[i][j].getComponente() instanceof Stiva) 
				{
					Stiva stivaSpeciale = (Stiva) this.plancia[i][j].getComponente();
					//Dato che si perde merci a partire dalle più costose si cercano per prima le merci rosse
					while (stivaSpeciale.isSpeciale() && cargoDaPerdere > 0 && stivaSpeciale.getCargoCorrente().size() > 0 ) 
					{
						//Anche le stive speciali possono trasportare merce normale, dunque per ogni merce in stiva si cerca se è rosso, se sì viene rimossa
						for (Cargo merce : stivaSpeciale.getCargoCorrente()) 
						{
							if(merce.getColore() == ColoreCargo.ROSSO) 
							{
								stivaSpeciale.getCargoCorrente().remove(merce);
								cargoDaPerdere--;
							}
							
							//Se è abbastanza per saldare il debito si esce dalla funzione
							if (cargoDaPerdere <= 0)
								return true;
						}
					}
				}
			}
		}

		//Si passa poi alle merci gialle procedendo nella stessa maniera di prima
		for (int i = 0; i < plancia.length; i++) 
		{
			for (int j = 0; j < plancia[0].length; j++) 
			{
				if (cargoDaPerdere <= 0)
					return true;

				if (isUtilizzabile(this.plancia[i][j]) && this.plancia[i][j].getComponente() instanceof Stiva) 
				{
					Stiva stiva = (Stiva) this.plancia[i][j].getComponente();
					
					while (cargoDaPerdere > 0 && stiva.getCargoCorrente().size() > 0 ) 
					{
						
						for (Cargo merce : stiva.getCargoCorrente()) 
						{
							if(merce.getColore() == ColoreCargo.GIALLO) 
							{
								stiva.getCargoCorrente().remove(merce);
								cargoDaPerdere--;
							}
							
							//Se è abbastanza per saldare il debito si esce dalla funzione
							if (cargoDaPerdere <= 0)
								return true;
						}
					}
				}
			}
		}
		
		//Si passa poi alle merci verdi procedendo nella stessa maniera di prima
		for (int i = 0; i < plancia.length; i++) 
		{
			for (int j = 0; j < plancia[0].length; j++) 
			{
				if (cargoDaPerdere <= 0)
					return true;

				if (isUtilizzabile(this.plancia[i][j]) && this.plancia[i][j].getComponente() instanceof Stiva) 
				{
					Stiva stiva = (Stiva) this.plancia[i][j].getComponente();
					
					while (cargoDaPerdere > 0 && stiva.getCargoCorrente().size() > 0 ) 
					{
						
						for (Cargo merce : stiva.getCargoCorrente()) 
						{
							if(merce.getColore() == ColoreCargo.VERDE) 
							{
								stiva.getCargoCorrente().remove(merce);
								cargoDaPerdere--;
							}
							
							//Se è abbastanza per saldare il debito si esce dalla funzione
							if (cargoDaPerdere <= 0)
								return true;
						}
					}
				}
			}
		}
		
		//Si passa poi alle merci blu procedendo nella stessa maniera di prima
		for (int i = 0; i < plancia.length; i++) 
		{
			for (int j = 0; j < plancia[0].length; j++) 
			{
				if (cargoDaPerdere <= 0)
					return true;

				if (isUtilizzabile(this.plancia[i][j]) && this.plancia[i][j].getComponente() instanceof Stiva) 
				{
					Stiva stiva = (Stiva) this.plancia[i][j].getComponente();
					
					while (cargoDaPerdere > 0 && stiva.getCargoCorrente().size() > 0 ) 
					{
						
						for (Cargo merce : stiva.getCargoCorrente()) 
						{
							if(merce.getColore() == ColoreCargo.BLU) 
							{
								stiva.getCargoCorrente().remove(merce);
								cargoDaPerdere--;
							}
							
							//Se è abbastanza per saldare il debito si esce dalla funzione
							if (cargoDaPerdere <= 0)
								return true;
						}
					}
				}
			}
		}
		
		//Se non si riesce a trovare abbastanza merci da togliere, si passa ai segnalini batteria
		for (int i = 0; i < plancia.length; i++) 
		{
			for (int j = 0; j < plancia[0].length; j++) 
			{
				if (cargoDaPerdere <= 0)
					return true;

				if (isUtilizzabile(this.plancia[i][j]) && this.plancia[i][j].getComponente() instanceof Batteria) 
				{
					Batteria batteria = (Batteria) this.plancia[i][j].getComponente();
					
					while (cargoDaPerdere > 0 && batteria.getCarica() > 0 ) 
					{
						//Si tolgono quanti più segnalini si può da ciascun componente batteria 
						for (int h = cargoDaPerdere; h > 0; h--) 
						{
							if(batteria.getCarica() > 0) 
							{
								batteria.scalaCarica();
								cargoDaPerdere--;
							}
							else 
							{
								break;
							}
							
							//Se è abbastanza per saldare il debito si esce dalla funzione
							if (cargoDaPerdere <= 0)
								return true;
						}
					}
				}
			}
		}
		
		//Se dopo tutto questo il debito non è ancora saldato si esce dalla funzione
		return false;
	}

	public int getPotenzaMotrice() {
		int potenzaMotrice = 0;
		
		for (int i = 0; i < this.plancia.length; i++) {
			for (int j = 0; j < this.plancia[0].length; j++) {
				if (isUtilizzabile(this.plancia[i][j]) && this.plancia[i][j].getComponente() instanceof CalcoloPotenza) {
					CalcoloPotenza p = (CalcoloPotenza) this.plancia[i][j].getComponente();
					potenzaMotrice = potenzaMotrice + p.getPotenza();
				}
			}			
		}
		return potenzaMotrice;
	}

	public double getPotenzaFuoco() {
		double potenzaFuoco = 0;
		for (int i = 0; i < this.plancia.length; i++) {
			for (int j = 0; j < this.plancia[0].length; j++) {
				if (isUtilizzabile(this.plancia[i][j]) && this.plancia[i][j].getComponente() instanceof Cannone) {
					Cannone p = (Cannone) this.plancia[i][j].getComponente();
					potenzaFuoco = potenzaFuoco + p.getPotenza();
				}
			}
			
		}
		return potenzaFuoco;
	}

	public int getEquipaggio() {
		return equipaggio;
	}
}
