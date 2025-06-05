package nave;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

import alieni.Alieno;
import componenti.*;
import game_logic.LettoreInput;
import merci.Cargo;
import merci.Cargo.ColoreCargo;

public abstract class Nave {
	protected final int ROWS;
	protected final int COLUMNS;
	protected Casella[][] plancia;
	protected int equipaggio;


	private static LettoreInput sc = new LettoreInput();
	
	public Nave(int rows, int columns, int[][] caselleUtilizzabili) {
		this.ROWS = rows;
		this.COLUMNS = columns;
		this.plancia = new Casella[ROWS][COLUMNS];
		
		for (int i = 0; i < ROWS; i++) {
			for (int j = 0; j < COLUMNS; j++) {
				plancia[i][j] = new Casella(new Coordinata(i, j));
			}
		}

		for (int[] coord : caselleUtilizzabili) {
			this.plancia[coord[0]][coord[1]].utilizzabile = true;
		}
	}
	
	public void AttivaScudo(int x, int y) {
		if(isUtilizzabile(this.plancia[y][x]))
			for (int i = 0; i < ROWS; i++) {
		        for (int j = 0; j < COLUMNS; j++) {
		            if (plancia[i][j].getComponente() instanceof Batteria) {
		                Batteria batteria = (Batteria)plancia[i][j].getComponente();
		                Scudo scudo = (Scudo)plancia[y][x].getComponente();
		                scudo.attivaScudo(batteria);
		                return;
		            }
		        }
		    }
	}
	
	public Casella[][] getPlancia(){
		return this.plancia;
	}

	public void stampa()
	{
		String RESET = "\u001B[0m";
		String GREEN = "\u001B[32m";
		String YELLOW = "\u001B[33m";
		
		StringBuilder sbSopra = new StringBuilder();//Per connettore SU
		StringBuilder sbMezzo = new StringBuilder();//Per connettori SX DX e nome componente
		StringBuilder sbSotto = new StringBuilder();//Per connettore GIU
		
		//Stampa il numero delle colonne. Ogni numero occupa 25 spazi: 12 prima + 11 dopo + il numero in se
		for (int i = 2; i < 11; i++)
				System.out.print(YELLOW + String.format("%1$25S", i + String.format("%1$11s", "")) + RESET);
		System.out.print("\n");
		
		//Stampa dei componenti veri e propri assiame al numero della riga. Non si parte dalla riga 0 per evitare di stampare inutili spazi vuoti e per essere più veritieri al gioco fisico. 
		for (int i = 3; i < 10; i++) 
		{
			sbMezzo.append(YELLOW + i + RESET);//Per mostrare le righe
			for (int j = 2; j < 12; j++) 
			{
				if (this.isUtilizzabile(this.plancia[i][j])) //Se la casella è utilizzabile si cerca il componente associato e ogni sua parte viene appended allo stringbuilder associato.
				{
					sbSopra.append(GREEN + String.format("%1$9s", "") + this.plancia[i][j].getComponente().getConnettoreSU().toString() + String.format("%1$6s", "") + RESET);//9 spazi prima + 10 occupati dal connettore + 16 dopo = 25
					sbMezzo.append(GREEN + this.plancia[i][j].getComponente().getConnettoreSX().toString() + this.plancia[i][j].getComponente().nomeComponente() + this.plancia[i][j].getComponente().getConnettoreDX().toString() + RESET);//10 connettore + 5 componente + 10 connettore = 25
					sbSotto.append(GREEN + String.format("%1$9s", "") + this.plancia[i][j].getComponente().getConnettoreGIU().toString() + String.format("%1$6s", "") + RESET);//9 spazi prima + 10 occupati dal connettore + 16 dopo = 25
				}
				else if(this.plancia[i][j].utilizzabile && this.plancia[i][j].getComponente()==null) //Se la casella è utilizzabile, ma vuota si appendano 24 slash e uno spazio per far capire all'utente durante la fase di assemblaggio che potrebbe posizionare qua un componente
				{
					sbSopra.append(" |//////////////////////|");
					sbMezzo.append("|//////////////////////| ");
					sbSotto.append(" |//////////////////////|");
				}
				else //Se la casella non è utilizzabile si appendano 25 spazi, spazio occupato dai componenti pieni, per far rimanere tutto allineato
				{
					sbSopra.append(String.format("%1$25s", ""));
					sbMezzo.append(String.format("%1$25s", ""));
					sbSotto.append(String.format("%1$25s", ""));
				}
			}
			System.out.println(sbSopra + "\n" + sbMezzo + "\n" + sbSotto + "\n");//Si printa una riga di componenti alla volta
			//Reset degli stringbuilder
			sbSopra.setLength(0);
			sbMezzo.setLength(0);
			sbSotto.setLength(0);
		}
	}

	/**
	 * Aggiunge un componente alla posizione specificata nella nave, gestendo possibili errori.
	 * @param y Coordinata Y (riga)
	 * @param x Coordinata X (colonna)
	 * @param tessera Componente da aggiungere
	 */
	public void aggiungiComponente(int y, int x, Componente tessera) {
	    try {
	        if (tessera == null) {
	            throw new IllegalArgumentException("Il componente non può essere null");
	        }
	        
	        if (x < 0 || x >= COLUMNS || y < 0 || y >= ROWS) {
	            throw new IndexOutOfBoundsException("Coordinate (" + y + "," + x + ") fuori dai limiti");
	        }

	        if (!plancia[y][x].utilizzabile) {
	            throw new IllegalStateException("La cella (" + y + "," + x + ") non è utilizzabile");
	        }

	        // Controllo spazio occupato
	        if (plancia[y][x].getComponente() != null) {
	            throw new IllegalStateException("La cella (" + y + "," + x + ") è già occupata");
	        }
	        
	        boolean componenteSpeciale = tessera instanceof Scudo || 
	                                   tessera instanceof CannoneDoppio || 
	                                   tessera instanceof MotoreDoppio ||
	                                   tessera instanceof CabinaPartenza;
	        
	        if (!componenteSpeciale) {
	        	//Controllo che il componente sia collegato ad un altro componente TODO ancora da perfezionare
	        	if(((y > 0 && isUtilizzabile(plancia[y-1][x]) && plancia[y-1][x].getComponente().getConnettoreGIU()==Connettore.LISCIO) || (y > 0 && !isUtilizzabile(plancia[y-1][x])) || (y > 0 && plancia[y-1][x].getComponente() == null))
	        		&& ((y < ROWS-1 && isUtilizzabile(plancia[y+1][x]) && plancia[y+1][x].getComponente().getConnettoreSU()==Connettore.LISCIO) || (y < ROWS-1 && !isUtilizzabile(plancia[y+1][x])) || (y < ROWS-1 && plancia[y+1][x].getComponente() == null)) 
	        		&& ((x > 0 && isUtilizzabile(plancia[y][x-1]) && plancia[y][x-1].getComponente().getConnettoreDX()==Connettore.LISCIO) || (x > 0 && !isUtilizzabile(plancia[y][x-1])) || (x > 0 && plancia[y][x-1].getComponente() == null))
	        		&& ((x < COLUMNS-1 && isUtilizzabile(plancia[y][x+1]) && plancia[y][x+1].getComponente().getConnettoreSX()==Connettore.LISCIO) || (x < COLUMNS-1 && !isUtilizzabile(plancia[y][x+1])) || (x < COLUMNS-1 && plancia[y][x+1].getComponente() == null)))
	        			throw new IllegalStateException("Il componente deve essere connesso ad almeno un altro componente");
	        }
	        
	        boolean connessioneValida = true;
	        
	        // Controllo componente sopra
	        if (y > 0 && plancia[y-1][x].getComponente() != null) {
	            Componente sopra = plancia[y-1][x].getComponente();
	            connessioneValida &= connettoriCompatibili(tessera.getConnettoreSU(), sopra.getConnettoreGIU());
	        }
	        
	        // Controllo componente sotto
	        if (y < ROWS-1 && plancia[y+1][x].getComponente() != null) {
	            Componente sotto = plancia[y+1][x].getComponente();
	            connessioneValida &= connettoriCompatibili(tessera.getConnettoreGIU(), sotto.getConnettoreSU());
	        }
	        
	        // Controllo componente a sinistra
	        if (x > 0 && plancia[y][x-1].getComponente() != null) {
	            Componente sinistra = plancia[y][x-1].getComponente();
	            connessioneValida &= connettoriCompatibili(tessera.getConnettoreSX(), sinistra.getConnettoreDX());
	        }
	        
	        // Controllo componente a destra
	        if (x < COLUMNS-1 && plancia[y][x+1].getComponente() != null) {
	            Componente destra = plancia[y][x+1].getComponente();
	            connessioneValida &= connettoriCompatibili(tessera.getConnettoreDX(), destra.getConnettoreSX());
	        }

	        if (!connessioneValida) {
	            throw new IllegalStateException("Connettori incompatibili con i componenti adiacenti");
	        }
	        
	        plancia[y][x].setComponente(tessera);
	        
	        if (tessera instanceof Cabina) {
	            Cabina cabina = (Cabina)tessera;
	            if (cabina.getColore() != null) {
	                // Cabina aliena - aggiungi 1 alieno del colore specificato
	            	cabina.setColoreAlieno(cabina.getColoreAlieno());
	                cabina.setEquipaggioAlieno(1);
	            } else {
	                // Cabina normale - aggiungi 2 astronauti
	                this.equipaggio += 2;
	            }
	        }
	        
	        //Gestione speciale per Cabina. Spostato qua altrimenti l'equipaggio della nave veniva aumentato prima di esser sicuri di poter posizionare la cabina
	        //TODO scelta equipaggio alieno
	        /*if (tessera instanceof Cabina) {
	            this.equipaggio += 2;
	        }*/
	        
	    } catch (IndexOutOfBoundsException e) {
	        System.err.println("Errore di posizionamento: " + e.getMessage());
	    } catch (IllegalStateException e) {
	        System.err.println("Errore nelle regole di gioco: " + e.getMessage());
	    } catch (IllegalArgumentException e) {
	        System.err.println("Errore nei parametri: " + e.getMessage());
	    } catch (Exception e) {
	        System.err.println("Errore imprevisto durante l'aggiunta del componente: " + e.getMessage());
	        e.printStackTrace();
	    }
	}

	/**
	 * Metodo per verificare la compatibilità tra due connettori. Prende due connettorin e controlla la compatibilità di connessione.
	 * @param Connettore c1: primo connettore
	 * @param Connettore c2: secondo connettore
	 */
	private boolean connettoriCompatibili(Connettore c1, Connettore c2) {
	    return c1 == c2 || 
	           (c1 == Connettore.UNIVERSALE && c2!=Connettore.LISCIO) || 
	           (c2 == Connettore.UNIVERSALE && c1!=Connettore.LISCIO);
	}
	
	protected boolean isUtilizzabile(Casella casella) {
		return (casella.utilizzabile && casella.getComponente() != null) ? true : false;
	}
	
	protected int getEnergia() {
		int energia = 0;
		
		for(int i = 0; i < ROWS; i++) {
			for(int j = 0; j < COLUMNS; j++) {
				if(isUtilizzabile(this.plancia[i][j]) && this.plancia[i][j].getComponente() instanceof Batteria) {
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
		
		
		for (int i = 0; i < plancia.length; i++) {
			for (int j = 0; j < plancia[0].length; j++) {
				if (equipaggioDaEliminare == 0)
					return true;
				if (isUtilizzabile(this.plancia[i][j]) && this.plancia[i][j].getComponente() instanceof CabinaPartenza) {
					CabinaPartenza c = (CabinaPartenza) this.plancia[i][j].getComponente();
					while (c.getEquipaggio() != 0 && equipaggioDaEliminare > 0) {
						c.setEquipaggio(c.getEquipaggio() - 1);
						equipaggioDaEliminare--;
					}
				}
			}
		}
		return equipaggioDaEliminare == 0;
	}

	//Funzione per scambiare merce tra due stive, oppure per scartare la merce contenuta in una stiva
	public boolean scambiaCargo() 
	{
		int colonna, riga;
		
		System.out.println("Inserire le coordinate della stiva donatrice, ovvero quella DA cui scambiare, scrivendo la sua colonna e la sua riga: ");
		colonna = sc.leggiInt();
		riga = sc.leggiInt();
		//Controllo se le coordinate sono valide. Continuo a chiederle finche sono giuste oppure viene immesso -1 -1 per uscire dalla funzione con return vero
		while(isUtilizzabile(this.plancia[riga][colonna]) && !(this.plancia[riga][colonna].getComponente() instanceof Stiva)) 
		{
			System.out.println("Le coordinate inserite non corrispondono ad una stiva. Provate con delle nuove coordinate oppure inserite -1 -1 per uscire dalla funzione senza effettuare uno scambio: ");
			colonna = sc.leggiInt();
			riga = sc.leggiInt();
			if(colonna == -1 && riga == -1) 
			{
				return true;
			}	
		}
		Stiva donatore = (Stiva) this.plancia[colonna][riga].getComponente();
		
		//Se stiva donatrice è vuota, esco con return falso
		if(donatore.getCargoCorrente().isEmpty()) 
		{
			System.out.println("La stiva donatrice e' vuota.");
			return false;
		}
		
		System.out.println("Inserire le coordinate della stiva ricevente, ovvero quella A cui scambiare, scrivendo la sua colonna e la sua riga. Inserire -2 -2 per eiettare il cargo nello spazio al posto di scambiarlo con un'altra stiva: ");
		colonna = sc.leggiInt();
		riga = sc.leggiInt();
		//Controllo se le coordinate sono valide. Continuo a chiederle finche sono giuste oppure viene immesso -1 -1 per uscire dalla funzione, oppure -2 -2 per eliminare la merce
		while(isUtilizzabile(this.plancia[riga][colonna]) && !(this.plancia[riga][colonna].getComponente() instanceof Stiva) && (colonna!=-2 && riga!=-2)) 
		{
			System.out.println("Le coordinate inserite non corrispondono ad una stiva, o al codice per gettare il cargo nello spazio. Provate con delle nuove coordinate oppure inserite -1 -1 per uscire dalla funzione senza effettuare uno scambio: ");
			colonna = sc.leggiInt();
			riga = sc.leggiInt();
			if(colonna == -1 && riga == -1) 
			{
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
			selezioneDonatore = sc.leggiInt();
			//Controllo validità scelta
			while(selezioneDonatore < 1 || selezioneDonatore > donatore.getCargoCorrente().size()) 
			{
				System.out.println("Errore di digitazione. Inserire un numero tra quelli nella lista soprascritta: ");
				selezioneDonatore = sc.leggiInt();
			}
			
			//Se si prova a scambiare una merce rossa ad una stiva non speciale si esce con return falso
			if(donatore.getCargoCorrente().get(selezioneDonatore-1).getColore() == ColoreCargo.ROSSO && !ricevente.isSpeciale()) 
			{
				System.out.println("La stiva ricevente non puo' accettare merce speciale rossa");
				return false;
			}
			
			//Se la ricevente ha ancora spazio si effettua lo spostamento della merce dalla donatrice alla ricevente e si esce con return falso
			if(ricevente.getCargoCorrente().size() < ricevente.getSpazioCargo()) 
			{
				ricevente.getCargoCorrente().add(donatore.getCargoCorrente().get(selezioneDonatore-1));
				donatore.getCargoCorrente().remove(selezioneDonatore-1);
				return false;
			}
			
			//Se la ricevente è piena si cheide quale merce si vuol passare dalla ricevente alla donatrice
			int selezioneRicevente;
			ricevente.stampaCargoCorrente();
			System.out.println("Scegliere quale merce scambiare immettendo il relativo numero: ");
			selezioneRicevente = sc.leggiInt();
			while(selezioneRicevente < 1 || selezioneRicevente > ricevente.getCargoCorrente().size()) 
			{
				System.out.println("Errore di digitazione. Inserire un numero tra quelli nella lista soprascritta: ");
				selezioneRicevente = sc.leggiInt();
			}
			
			//Check come prima se donatrice può accogliere merce rossa
			if(ricevente.getCargoCorrente().get(selezioneRicevente-1).getColore() == ColoreCargo.ROSSO && !donatore.isSpeciale()) 
			{
				System.out.println("La stiva donatrice non puo' accettare merce speciale rossa");
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
			return false;
		}
		else 
		{
			//Se le coordinate sono -2 -2 si fa scegliere quale merce da eliminare. Si esce con falso
			int selezioneDonatore;
			donatore.stampaCargoCorrente();
			System.out.println("Scegliere quale merce buttare immettendo il relativo numero: ");
			selezioneDonatore = sc.leggiInt();
			while(selezioneDonatore < 1 || selezioneDonatore > donatore.getCargoCorrente().size()) 
			{
				System.out.println("Errore di digitazione. Inserire un numero tra quelli nella lista soprascritta: ");
				selezioneDonatore = sc.leggiInt();
			}
			
			donatore.getCargoCorrente().remove(selezioneDonatore-1);
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
		Iterator<Cargo> iterator = cargo.iterator();
		while (iterator.hasNext()) {
		    Cargo merce = iterator.next();
		    if (merce.getColore() == ColoreCargo.ROSSO) {
		        cargoRosso.add(merce);
		        iterator.remove();
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
		String risposta;
		for (int i = 0; i < this.plancia.length && this.getEnergia() > 0; i++) 
		{
			for (int j = 0; j < this.plancia[0].length && this.getEnergia() > 0; j++) 
			{
				if (isUtilizzabile(this.plancia[i][j]) && this.plancia[i][j].getComponente() instanceof MotoreDoppio) 
				{
					MotoreDoppio motore =  (MotoreDoppio) this.plancia[i][j].getComponente();
					
					System.out.println("La tua potenza motrice ora e' " + potenzaMotrice + ", e hai ancora " + this.getEnergia() + "segnalini batteria. Vuoi attivare un motore doppio? S/N\n");
					risposta = sc.leggiString();
					while(!risposta.equalsIgnoreCase("S") && !risposta.equalsIgnoreCase("N"))
					{
						System.out.println("Input errato. Inserire 'S' per si oppure 'N' per no");
						risposta = sc.leggiString();
					}
					
					if(risposta.equalsIgnoreCase("S")) 
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
						return potenzaMotrice;
					}
				}
			}			
		}
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
		String risposta;
		for (int i = 0; i < this.plancia.length && this.getEnergia() > 0; i++) 
		{
			for (int j = 0; j < this.plancia[0].length && this.getEnergia() > 0; j++) 
			{
				if (isUtilizzabile(this.plancia[i][j]) && this.plancia[i][j].getComponente() instanceof CannoneDoppio) 
				{
					CannoneDoppio cannone =  (CannoneDoppio) this.plancia[i][j].getComponente();
					
					System.out.println("La tua potenza motrice ora e' " + potenzaFuoco + ", e hai ancora " + this.getEnergia() + "segnalini batteria. Vuoi attivare un motore doppio? S/N\n");
					risposta = sc.leggiString();
					while(!risposta.equalsIgnoreCase("S") && !risposta.equalsIgnoreCase("N"))
					{
						System.out.println("Input errato. Inserire 'S' per si oppure 'N' per no");
						risposta = sc.leggiString();
					}
					
					if(risposta.equalsIgnoreCase("S")) 
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
						return potenzaFuoco;
					}
				}
			}			
		}
		return potenzaFuoco;
	}

	//GEORGE: Metodo aggiunto per eliminare un componente dalla nave a causa di un evento negativo
	public void eliminaComponente(int y, int x) {
		//verifica coordinate
        if (y < 0 || y >= ROWS || x < 0 || x >= COLUMNS || plancia[y][x].getComponente() == null) {
            System.out.println("Nessun componente da distruggere alle coordinate specificate (" + y + "," + x + ")");
            return;
        }

        Componente daEliminare = plancia[y][x].getComponente();
        plancia[y][x].setComponente(null); // Remove the component

        System.out.println("Componente " + daEliminare.nomeComponente() + " distrutto a (" + y + "," + x + ").");
        
        //verifica componenti disconnessi 
        List<Coordinata> componentiDisconnessi = cercaComponentiDisconnessi();
        for (Coordinata coord : componentiDisconnessi) {
            Componente disconnesso = plancia[coord.getX()][coord.getY()].getComponente();
            if (disconnesso != null) {
                plancia[coord.getX()][coord.getY()].setComponente(null);
                System.out.println("Componente " + disconnesso.nomeComponente() + " a (" + coord.getX() + "," + coord.getY() + ") si è staccato.");
     
            }
        }
    }

 //GEORGE: Cerca i componenti che si disconnettono dalla nave a causa dell'eliminazione di un altro componente
	private List<Coordinata> cercaComponentiDisconnessi() {
	    List<Coordinata> disconnessi = new ArrayList<>();
	    
	    // Trova le cabine di partenza
	    List<Coordinata> cabineIniziali = new ArrayList<>();
	    for (int riga = 0; riga < ROWS; riga++) {
	        for (int colonna = 0; colonna < COLUMNS; colonna++) {
	            if (plancia[riga][colonna].getComponente() instanceof CabinaPartenza) {
	                cabineIniziali.add(new Coordinata(riga, colonna));
	            }
	        }
	    }

	    if (cabineIniziali.isEmpty()) {
	        // Se non c'è una cabina di partenza, tutti i componenti sono considerati scollegati (o la nave è distrutta)
	        for (int riga = 0; riga < ROWS; riga++) {
	            for (int colonna = 0; colonna < COLUMNS; colonna++) {
	                if (plancia[riga][colonna].getComponente() != null) {
	                    disconnessi.add(new Coordinata(riga, colonna));
	                }
	            }
	        }
	        return disconnessi;
	    }

	    boolean[][] visitato = new boolean[ROWS][COLUMNS];
	    List<Coordinata> componentiConnessi = new ArrayList<>();
	    Queue<Coordinata> coda = new LinkedList<>();

	    for (Coordinata inizio : cabineIniziali) {
	        if (plancia[inizio.getX()][inizio.getY()].getComponente() != null && !visitato[inizio.getX()][inizio.getY()]) {
	            coda.offer(inizio);
	            visitato[inizio.getX()][inizio.getY()] = true;
	            componentiConnessi.add(inizio);

	            while (!coda.isEmpty()) {
	                Coordinata corrente = coda.poll();
	                int riga = corrente.getX();
	                int colonna = corrente.getY();
	                Componente componenteCorrente = plancia[riga][colonna].getComponente();

	                // verifica i vicini
	                int[][] direzioni = {{0, 1}, {0, -1}, {1, 0}, {-1, 0}}; // DESTRA, SINISTRA, GIÙ, SU
	                for (int[] direzione : direzioni) {
	                    int nuovaRiga = riga + direzione[0];
	                    int nuovaColonna = colonna + direzione[1];

	                    if (nuovaRiga >= 0 && nuovaRiga < ROWS && nuovaColonna >= 0 && nuovaColonna < COLUMNS && !visitato[nuovaRiga][nuovaColonna]) {
	                        Componente componenteVicino = plancia[nuovaRiga][nuovaColonna].getComponente();
	                        if (componenteVicino != null) {
	                            // Controlla la compatibilità della connessione tra componenteCorrente e componenteVicino
	                            boolean connesso = false;
	                            if (direzione[0] == 0 && direzione[1] == 1) { // DESTRA
	                                connesso = connettoriCompatibili(componenteCorrente.getConnettoreDX(), componenteVicino.getConnettoreSX());
	                            } else if (direzione[0] == 0 && direzione[1] == -1) { // SINISTRA
	                                connesso = connettoriCompatibili(componenteCorrente.getConnettoreSX(), componenteVicino.getConnettoreDX());
	                            } else if (direzione[0] == 1 && direzione[1] == 0) { // GIÙ
	                                connesso = connettoriCompatibili(componenteCorrente.getConnettoreGIU(), componenteVicino.getConnettoreSU());
	                            } else if (direzione[0] == -1 && direzione[1] == 0) { // SU
	                                connesso = connettoriCompatibili(componenteCorrente.getConnettoreSU(), componenteVicino.getConnettoreGIU());
	                            }

	                            if (connesso) {
	                                visitato[nuovaRiga][nuovaColonna] = true;
	                                coda.offer(new Coordinata(nuovaRiga, nuovaColonna));
	                                componentiConnessi.add(new Coordinata(nuovaRiga, nuovaColonna));
	                            }
	                        }
	                    }
	                }
	            }
	        }
	    }

	    // Raccogli tutti i componenti che non sono stati visitati
	    for (int riga = 0; riga < ROWS; riga++) {
	        for (int colonna = 0; colonna < COLUMNS; colonna++) {
	            if (plancia[riga][colonna].getComponente() != null && !visitato[riga][colonna]) {
	                disconnessi.add(new Coordinata(riga, colonna));
	            }
	        }
	    }

	    return disconnessi;
	}
}