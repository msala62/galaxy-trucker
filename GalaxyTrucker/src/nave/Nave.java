package nave;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import componenti.*;
import merci.Cargo;
import merci.Cargo.ColoreCargo;

public abstract class Nave {
	protected final int ROWS;
	protected final int COLUMNS;
	protected Casella[][] plancia;
	protected int equipaggio;

	public Nave(int rows, int columns, int[][] caselleUtilizzabili) {
		this.ROWS = rows;
		this.COLUMNS = columns;
		this.plancia = new Casella[ROWS][COLUMNS];
		this.equipaggio = getEquipaggioTotale();
		
		for (int i = 0; i < 5; i++) {
			for (int j = 0; j < 7; j++) {
				plancia[i][j] = new Casella(new Coordinata(i, j));
			}
		}

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
		
		for (int i = 0; i < COLUMNS; i++)//Per mostrare le colonne
			System.out.print(YELLOW + "/t" + i + "/t" + RESET);
		
		for (int i = 0; i < ROWS; i++) 
		{
			sbMezzo.append(YELLOW + i + RESET);//Per mostrare le righe
			sbMezzo.append("/t");
			for (int j = 0; j < COLUMNS; j++) 
			{
				if (this.isUtilizzabile(this.plancia[i][j])) //Se la casella è utilizzabile si cerca il componente associato e ogni sua parte viene appended allo stringbuilder associato. TODO cosa fare se casella è utilizzabile, ma vuota
				{
					sbSopra.append(GREEN + "/t" + this.plancia[i][j].getComponente().getConnettoreSU() + "/t" + RESET);
					sbMezzo.append(GREEN + this.plancia[i][j].getComponente().getConnettoreSX() + "/t" + this.plancia[i][j].getComponente().nomeComponente() + "/t" + this.plancia[i][j].getComponente().getConnettoreDX() + RESET);
					sbSotto.append(GREEN + "/t" + this.plancia[i][j].getComponente().getConnettoreGIU() + "/t" + RESET);
				}
				else //Se la casella non è utilizzabile si appenda due tabulazioni
				{
					sbSopra.append("/t/t");
					sbMezzo.append("/t/t");
					sbSotto.append("/t/t");
				}
			}
			System.out.println(sbSopra + "/n" + sbMezzo + "/n" + sbSotto + "/n");//Si printa una riga di componenti alla volta
		}
		System.out.println(sbSopra + "/n" + sbMezzo + "/n" + sbSotto + "/n");//Si printa una riga di componenti alla volta
		//Reset degli stringbuilder (spero)
		sbSopra.setLength(0);
		sbMezzo.setLength(0);
		sbSotto.setLength(0);
	}

	//IMPORTANTE: Y sono le righe e X le colonne
	public boolean aggiungiComponente(int y, int x, Componente tessera) {
		Componente inserimento = tessera;
		
		for(int i = 0; i < ROWS; i++) {
			for(int j = 0; j < COLUMNS; j++) {
				if(y == i && x == j) {
					if(plancia[y][x].utilizzabile) {
						if(tessera.equals(Cabina.class)) {
							this.equipaggio += 2;
							inserimento = new Cabina(tessera.getConnettoreSX(), tessera.getConnettoreDX(), tessera.getConnettoreSU(), tessera.getConnettoreGIU());
							((Cabina) inserimento).setEquipaggio(2);
						} else if(tessera.equals(Scudo.class) && getEnergia() > 0) {
							for(int k = 0; k < ROWS; k++) {
								for(int z = 0; z < COLUMNS; z++) {
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
						if((x < 0 || x > COLUMNS) && (y < 0 || y > ROWS)) return false;
						
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
	
	protected boolean isUtilizzabile(Casella casella) {
		return (casella.utilizzabile && casella.getComponente() != null) ? true : false;
	}
	
	protected int getEnergia() {
		int energia = 0;
		
		for(int i = 0; i < ROWS; i++) {
			for(int j = 0; i < COLUMNS; j++) {
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

	//Funzione per scambiare merce tra due stive, oppure per scartare la merce contenuta in una stiva
	public boolean scambiaCargo() 
	{
		Scanner sc = new Scanner(System.in);
		int colonna, riga;
		
		System.out.println("Inserire le coordinate della stiva donatrice, ovvero quella DA cui scambiare, scrivendo la sua colonna e la sua riga: ");
		colonna = sc.nextInt();
		riga = sc.nextInt();
		//Controllo se le coordinate sono valide. Continuo a chiederle finche sono giuste oppure viene immesso -1 -1 per uscire dalla funzione con return vero
		while(isUtilizzabile(this.plancia[riga][colonna]) && !(this.plancia[riga][colonna].getComponente() instanceof Stiva)) 
		{
			System.out.println("Le coordinate inserite non corrispondono ad una stiva. Provate con delle nuove coordinate oppure inserite -1 -1 per uscire dalla funzione senza effettuare uno scambio: ");
			colonna = sc.nextInt();
			riga = sc.nextInt();
			if(colonna == -1 && riga == -1) 
			{
				sc.close();
				return true;
			}	
		}
		Stiva donatore = (Stiva) this.plancia[colonna][riga].getComponente();
		
		//Se stiva donatrice è vuota, esco con return falso
		if(donatore.getCargoCorrente().isEmpty()) 
		{
			System.out.println("La stiva donatrice e' vuota.");
			sc.close();
			return false;
		}
		
		System.out.println("Inserire le coordinate della stiva ricevente, ovvero quella A cui scambiare, scrivendo la sua colonna e la sua riga. Inserire -2 -2 per eiettare il cargo nello spazio al posto di scambiarlo con un'altra stiva: ");
		colonna = sc.nextInt();
		riga = sc.nextInt();
		//Controllo se le coordinate sono valide. Continuo a chiederle finche sono giuste oppure viene immesso -1 -1 per uscire dalla funzione, oppure -2 -2 per eliminare la merce
		while(isUtilizzabile(this.plancia[riga][colonna]) && !(this.plancia[riga][colonna].getComponente() instanceof Stiva) && (colonna!=-2 && riga!=-2)) 
		{
			System.out.println("Le coordinate inserite non corrispondono ad una stiva, o al codice per gettare il cargo nello spazio. Provate con delle nuove coordinate oppure inserite -1 -1 per uscire dalla funzione senza effettuare uno scambio: ");
			colonna = sc.nextInt();
			riga = sc.nextInt();
			if(colonna == -1 && riga == -1) 
			{
				sc.close();
				return true;
			}
		}
		
		if(riga!=-2 && colonna!=-2) 
		{
			//Se le coordinate non sono -2 -2 faccio scegliere quale merce della donatrice si vuole scambiare
			int selezioneDonatore;
			Stiva ricevente = (Stiva) this.plancia[colonna][riga].getComponente();
			
			donatore.stampaCargoCorrente();
			System.out.println("Scegliere quale merce scambiare immettendo il relativo numero: ");
			selezioneDonatore = sc.nextInt();
			//Controllo validità scelta
			while(selezioneDonatore < 1 || selezioneDonatore > donatore.getCargoCorrente().size()) 
			{
				System.out.println("Errore di digitazione. Inserire un numero tra quelli nella lista soprascritta: ");
				selezioneDonatore = sc.nextInt();
			}
			
			//Se si prova a scambiare una merce rossa ad una stiva non speciale si esce con return falso
			if(donatore.getCargoCorrente().get(selezioneDonatore-1).getColore() == ColoreCargo.ROSSO && !ricevente.isSpeciale()) 
			{
				System.out.println("La stiva ricevente non puo' accettare merce speciale rossa");
				sc.close();
				return false;
			}
			
			//Se la ricevente ha ancora spazio si effettua lo spostamento della merce dalla donatrice alla ricevente e si esce con return falso
			if(ricevente.getCargoCorrente().size() < ricevente.getSpazioCargo()) 
			{
				ricevente.getCargoCorrente().add(donatore.getCargoCorrente().get(selezioneDonatore-1));
				donatore.getCargoCorrente().remove(selezioneDonatore-1);
				sc.close();
				return false;
			}
			
			//Se la ricevente è piena si cheide quale merce si vuol passare dalla ricevente alla donatrice
			int selezioneRicevente;
			ricevente.stampaCargoCorrente();
			System.out.println("Scegliere quale merce scambiare immettendo il relativo numero: ");
			selezioneRicevente = sc.nextInt();
			while(selezioneRicevente < 1 || selezioneRicevente > ricevente.getCargoCorrente().size()) 
			{
				System.out.println("Errore di digitazione. Inserire un numero tra quelli nella lista soprascritta: ");
				selezioneRicevente = sc.nextInt();
			}
			
			//Check come prima se donatrice può accogliere merce rossa
			if(ricevente.getCargoCorrente().get(selezioneRicevente-1).getColore() == ColoreCargo.ROSSO && !donatore.isSpeciale()) 
			{
				System.out.println("La stiva donatrice non puo' accettare merce speciale rossa");
				sc.close();
				return false;
			}
			
			//Si crea una stiva temporanea dove trattenere la merce del donatore
			Stiva temp = new Stiva(null, null, null, null, true, true);
			temp.getCargoCorrente().add(donatore.getCargoCorrente().get(selezioneDonatore-1));
			//Al donatore si rimuove la merce da scambiare che ora è in temp e si mette quella della ricevente
			donatore.getCargoCorrente().remove(selezioneDonatore-1);
			donatore.getCargoCorrente().add(ricevente.getCargoCorrente().get(selezioneRicevente-1));
			//Alla ricevente si toglie la merce scambiata e si mette quella di temp. Si esce con return falso
			ricevente.getCargoCorrente().remove(selezioneRicevente-1);
			ricevente.getCargoCorrente().add(temp.getCargoCorrente().get(0));
			sc.close();
			return false;
		}
		else 
		{
			//Se le coordinate sono -2 -2 si fa scegliere quale merce da eliminare. Si esce con falso
			int selezioneDonatore;
			donatore.stampaCargoCorrente();
			System.out.println("Scegliere quale merce buttare immettendo il relativo numero: ");
			selezioneDonatore = sc.nextInt();
			while(selezioneDonatore < 1 || selezioneDonatore > donatore.getCargoCorrente().size()) 
			{
				System.out.println("Errore di digitazione. Inserire un numero tra quelli nella lista soprascritta: ");
				selezioneDonatore = sc.nextInt();
			}
			
			donatore.getCargoCorrente().remove(selezioneDonatore-1);
			sc.close();
			return false;
		}
		
	}
	
	//Usata per caricare cargo sulla nave
	public boolean caricaCargo(List<Cargo> cargo) 
	{
		if (cargo.isEmpty())
			return false;
		
		//Creo una lista per sole merci rosse
		List<Cargo> cargoRosso = new ArrayList<Cargo>();
		for (Cargo merce : cargo)
		{
			if(merce.getColore()==ColoreCargo.ROSSO)
			{
				cargoRosso.add(merce);
				cargo.remove(merce);
			}
		}
		
		//Ordino le merci rimanenti in ordine decrescente di valore
		Cargo temp = new Cargo(null);
		for(int i=0; i<cargo.size()-1; i++)
		{
			for(int j=0; j<cargo.size()-i-1; j++)
			{
				if(cargo.get(j).getValore()>cargo.get(j+1).getValore())
				{
					temp=cargo.get(j);
					cargo.set(j, cargo.get(j+1));
					cargo.set(j+1, temp);
				}
			}
		}
		
		//Faccio utilizzare all'utente la funzione scambiaCargo per fare spazio alle nuove merci e utilizzo il suo valore di return per uscire dal ciclo
		System.out.println("Sposta le merci che hai già sulla nave per far spazio a quelle nuove. Quando sei soddisfatto esci digitando -1 -1 e le nuove merci verranno automaticamente aggiunte negli spazi disponibili a bordo. !ATTENZIONE! merci nuove per cui non c'e' spazio verranno automaticamente lasciate a fluttuare nello spazio, perdendole per sempre!");
		boolean esci=false;
		while(esci) 
		{
			esci = this.scambiaCargo();
		}
		
		//Riempimento stive speciali
		for(int i=0; i<this.COLUMNS; i++)
		{
			for(int j=0; j<this.ROWS; j++)
			{
				if(isUtilizzabile(this.plancia[i][j]) && this.plancia[i][j].getComponente() instanceof Stiva)
				{
					Stiva stiva = (Stiva) this.plancia[i][j].getComponente();
					if(stiva.isSpeciale())
					{
						while(!cargoRosso.isEmpty() && stiva.aumentaCargoCorrente(cargoRosso.getFirst()))
						{
							cargoRosso.removeFirst();
						}
					}
				}
			}
		}
		
		//Riempimento stive normali
		for (int i = 0; i < plancia.length; i++) 
		{
			for (int j = 0; j < plancia[0].length; j++) 
			{
				if (cargo.isEmpty())
					return true;

				if (isUtilizzabile(this.plancia[i][j]) && this.plancia[i][j].getComponente() instanceof Stiva) 
				{
					Stiva stiva = (Stiva) this.plancia[i][j].getComponente();
					while (!cargo.isEmpty() && stiva.aumentaCargoCorrente(cargo.getFirst())) 
					{
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
		
		//Prima si calcola la potenza di tutti i motori regolari perche sempre attivi
		for (int i = 0; i < this.plancia.length; i++) {
			for (int j = 0; j < this.plancia[0].length; j++) {
				if (isUtilizzabile(this.plancia[i][j]) && this.plancia[i][j].getComponente() instanceof Motore) {
					Motore motore =  (Motore) this.plancia[i][j].getComponente();
					potenzaMotrice = potenzaMotrice + motore.getPotenza();
				}
			}			
		}
		
		//Poi se si hanno segnalini energia si chiede all'utente se vuole attivare anche i motori doppi uno a uno
		Scanner sc = new Scanner(System.in);
		String risposta;
		for (int i = 0; i < this.plancia.length && this.getEnergia() > 0; i++) 
		{
			for (int j = 0; j < this.plancia[0].length && this.getEnergia() > 0; j++) 
			{
				if (isUtilizzabile(this.plancia[i][j]) && this.plancia[i][j].getComponente() instanceof MotoreDoppio) 
				{
					MotoreDoppio motore =  (MotoreDoppio) this.plancia[i][j].getComponente();
					
					System.out.println("La tua potenza motrice ora e' " + potenzaMotrice + ", e hai ancora " + this.getEnergia() + "segnalini batteria. Vuoi attivare un motore doppio? S/N\n");
					risposta = sc.nextLine();
					while(risposta!="S" || risposta!="N")
					{
						System.out.println("Input errato. Inserire 'S' per si oppure 'N' per no");
						risposta = sc.nextLine();
					}
					
					if(risposta=="S") 
					{
						//Usato per essere sicuri chje si scali solamente un solo segnalino batteria
						boolean caricaScalata=false;
						
						//Cerco una batteria a cui scalare la carica
						for (int k = 0; k < this.plancia.length; k++) 
						{
							for (int l = 0; l < this.plancia[0].length; l++) 
							{
								if (!caricaScalata && (isUtilizzabile(this.plancia[k][l]) && this.plancia[k][l].getComponente() instanceof Batteria)) 
								{
									Batteria batteria =  (Batteria) this.plancia[k][l].getComponente();
									if(batteria.getCarica() > 0)
									{
										caricaScalata=true;
										potenzaMotrice = potenzaMotrice + motore.getPotenza(batteria);
									}
								}
							}
						}
					}
					//Se la risposta è no esco dalla funzione con la potenza corrente
					else 
					{
						sc.close();
						return potenzaMotrice;
					}
				}
			}			
		}
		sc.close();
		return potenzaMotrice;
	}

	public double getPotenzaFuoco() {
		double potenzaFuoco = 0;
		
		//Prima si calcola la potenza di tutti i cannoni regolari perche sempre attivi
		for (int i = 0; i < this.plancia.length; i++) {
			for (int j = 0; j < this.plancia[0].length; j++) {
				if (isUtilizzabile(this.plancia[i][j]) && this.plancia[i][j].getComponente() instanceof Cannone) {
					Cannone p = (Cannone) this.plancia[i][j].getComponente();
					potenzaFuoco = potenzaFuoco + p.getPotenza();
				}
			}
			
		}
		
		//Poi se si hanno segnalini energia si chiede all'utente se vuole attivare anche i cannoni doppi uno a uno
		Scanner sc = new Scanner(System.in);
		String risposta;
		for (int i = 0; i < this.plancia.length && this.getEnergia() > 0; i++) 
		{
			for (int j = 0; j < this.plancia[0].length && this.getEnergia() > 0; j++) 
			{
				if (isUtilizzabile(this.plancia[i][j]) && this.plancia[i][j].getComponente() instanceof CannoneDoppio) 
				{
					CannoneDoppio cannone =  (CannoneDoppio) this.plancia[i][j].getComponente();
					
					System.out.println("La tua potenza motrice ora e' " + potenzaFuoco + ", e hai ancora " + this.getEnergia() + "segnalini batteria. Vuoi attivare un motore doppio? S/N\n");
					risposta = sc.nextLine();
					while(risposta!="S" || risposta!="N")
					{
						System.out.println("Input errato. Inserire 'S' per si oppure 'N' per no");
						risposta = sc.nextLine();
					}
					
					if(risposta=="S") 
					{
						//Usato per essere sicuri chje si scali solamente un solo segnalino batteria
						boolean caricaScalata=false;
						
						//Cerco una batteria a cui scalare la carica
						for (int k = 0; k < this.plancia.length; k++) 
						{
							for (int l = 0; l < this.plancia[0].length; l++) 
							{
								if (!caricaScalata && (isUtilizzabile(this.plancia[k][l]) && this.plancia[k][l].getComponente() instanceof Batteria)) 
								{
									Batteria batteria =  (Batteria) this.plancia[k][l].getComponente();
									if(batteria.getCarica() > 0)
									{
										caricaScalata=true;
										potenzaFuoco = potenzaFuoco + cannone.getPotenza(batteria);
									}
								}
							}
						}
					}
					//Se la risposta è no esco dalla funzione con la potenza corrente
					else 
					{
						sc.close();
						return potenzaFuoco;
					}
				}
			}			
		}
		sc.close();
		return potenzaFuoco;
	}

	public int getEquipaggio() {
		return equipaggio;
	}
}
