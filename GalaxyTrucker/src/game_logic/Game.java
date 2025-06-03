package game_logic;

import java.util.Scanner;
import java.util.concurrent.ThreadLocalRandom;

import alieni.Colore;

import carteAvventura.*;
import carteAvventura.Meteorite.Dimensione;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Random;

import componenti.*;
import merci.Cargo;
import planciavolo.*;
//import titoli.TitoliBuilder;
import titoli.Titolo;

public class Game {
	private final static Clessidra clessidra = new Clessidra();
	
	private static void StampaMenu(String titolo, List<String> voci) 
	{
		System.out.println("==========" + titolo + "==========");
		
		for(int i = 0; i < voci.size(); i++) {
			System.out.println(i+1 + " " + voci.get(i));
		}
		
		System.out.println("================================================");
	}
	
	/*private static List<Titolo> generaTitoli(int nGiocatori)
		{
			//verifica ngiocatori ricevuto
			if (nGiocatori <= 1 || nGiocatori > 4)
				throw new IllegalArgumentException("Numero di giocatori non valido (deve essere tra 2 e 4)");
			TitoliBuilder builder = new TitoliBuilder();
			//generare una lista di titoli = nGiocatori
			List<Titolo> titoli;
			try {
				// get random titoli based on the players size
				titoli = new ArrayList<>(builder.getAllTitoli().subList(0, nGiocatori));
			} catch (Exception e) {
				 e.printStackTrace();
			     titoli = Collections.emptyList(); // fallback per evitare null
			}
			return titoli;
		}*/

	private void assegnaTitoli(List<Giocatore> giocatori, List<Titolo> titoli)

	{
		List<Giocatore> copiaGiocatori = new ArrayList<>(giocatori);
		for(Titolo titolo : titoli)
		{
			if (titolo.assegnaTitolo(titolo.valutazione(copiaGiocatori)))
			{
				System.out.println(titolo.stampaTitolo() + "assegnato a:" + titolo.getProprietario().getNome());
				for (int i=0; i<copiaGiocatori.size();i++)
				{
					if (copiaGiocatori.get(i).getNome().startsWith(titolo.getProprietario().getNome()))
					{
						copiaGiocatori.remove(i);	
					}
				}
			}
			else {
				System.out.println("errore durante l'assegnazione del titolo");
			}
			
		}
	}

	private void titoloDifeso(List<Giocatore> giocatori, List<Titolo> titoli, Livello livello) {
		List<Giocatore> copiaGiocatori = new ArrayList<>(giocatori);
		List <Titolo> copiaTitoli = new ArrayList<>(titoli);
		assegnaTitoli(giocatori, titoli);
		int i=0;
		for(Titolo titolo : titoli)
		{
			//verifica se il proprietario e' ancora lo stesso
			if (titolo.getProprietario().equals(copiaTitoli.get(i).getProprietario()))
			{
				System.out.println(titolo.getProprietario().getNome() + " ha difeso il suo titolo:" + titolo.stampaTitolo());
				if (livello.II != null)
				{
					titolo.passaAGold();
				}
			}
			else {
				System.out.println(titolo.getProprietario().getNome() + " ha difeso il suo titolo:" + titolo.stampaTitolo());
				System.out.println("errore durante l'assegnazione del titolo");
			}
			
		}
	}



	private static List<Giocatore> InizializzaGiocatori() {
		List<Giocatore> giocatori = new ArrayList<Giocatore>();		
		Scanner sc = new Scanner(System.in);
		int numeroGiocatori = 0;
		
		do {
	        System.out.print("Quanti giocatori parteciperanno alla partita? (min 2, max 4): ");
	        numeroGiocatori = sc.nextInt();
	        sc.nextLine();
	    } while(numeroGiocatori < 2 || numeroGiocatori > 4);
		
		for(int i = 0; i < numeroGiocatori; i++) {
			String nome;
	        do {
	            System.out.println("\n\nBenvenuto giocatore numero " + (i+1) + "!" + "\nInserisci pure il tuo nome: ");
	            nome = sc.nextLine().trim();
	            
	            if(nome.isEmpty() || nome.length() < 2) {
	                System.out.println("Il nome deve essere almeno di 2 caratteri, riprova!");
	            }
	        } while(nome.isEmpty() || nome.length() < 2);
	        
	        giocatori.add(new Giocatore(nome));
		}
		
		return giocatori;
	}
	
	private static List<Componente> GeneraComponenti(List<ComponentBuilder> builders) {
	    List<Componente> componenti = new ArrayList<>();
	    Connettore[] direzioni = {Connettore.SINGOLO, Connettore.DOPPIO, Connettore.UNIVERSALE, Connettore.LISCIO};
	    Direzione[] orientamento = {Direzione.SU, Direzione.GIU, Direzione.DX, Direzione.SX};
	    Colore[] coloriAlieni = {Colore.MARRONE, Colore.VIOLA};
	    
	    for(ComponentBuilder builder : builders) {
	        for(int i = 0; i < builder.quantita; i++) {
	            try {
	                int su = (int)(Math.random() * 4);
	                int giu = (int)(Math.random() * 4);
	                int dx = (int)(Math.random() * 4);
	                int sx = (int)(Math.random() * 4);
	                int colore = (int)(Math.random() * 2);
	                
	                Componente componente;
	                
	                if(builder.tipologia.equals(SupportoAlieni.class)) {
	                    componente = builder.tipologia.getConstructor(
	                        Connettore.class, Connettore.class, Connettore.class, Connettore.class, Colore.class)
	                        .newInstance(direzioni[sx], direzioni[dx], direzioni[su], direzioni[giu], coloriAlieni[colore]);
	                }
	                else if(builder.tipologia.equals(Scudo.class)) {
	                    componente = builder.tipologia.getConstructor(
	                        Connettore.class, Connettore.class, Connettore.class, Connettore.class, Direzione.class, Direzione.class)
	                        .newInstance(direzioni[sx], direzioni[dx], direzioni[su], direzioni[giu], orientamento[su], orientamento[giu]);
	                }
	                else if(builder.tipologia.equals(Batteria.class)) {
	                    componente = builder.tipologia.getConstructor(
	                        Connettore.class, Connettore.class, Connettore.class, Connettore.class, boolean.class)
	                        .newInstance(direzioni[sx], direzioni[dx], direzioni[su], direzioni[giu], builder.grande);
	                }
	                else if(builder.tipologia.equals(Stiva.class)) {
	                	componente = builder.tipologia.getConstructor(
		                        Connettore.class, Connettore.class, Connettore.class, Connettore.class, boolean.class, boolean.class)
		                        .newInstance(direzioni[sx], direzioni[dx], direzioni[su], direzioni[giu], builder.grande, builder.speciale);
	                }
	                else {
	                    componente = builder.tipologia.getConstructor(
	                        Connettore.class, Connettore.class, Connettore.class, Connettore.class)
	                        .newInstance(direzioni[sx], direzioni[dx], direzioni[su], direzioni[giu]);
	                }
	                
	                componenti.add(componente);
	                
	            } catch (Exception e) {
	                throw new RuntimeException("Errore nella creazione di un componente", e);
	            }
	        }
	    }
	    return componenti;
	}
	
	public static List<Carta> InizializzaMazzo(Livello livello){
		List<Carta> mazzo = new ArrayList<Carta>();
		List<CardBuilder> builders = Arrays.asList(
			new CardBuilder(PioggiaDiMeteoriti.class, 3),
			new CardBuilder(SpazioAperto.class, 4),
			new CardBuilder(Pianeti.class, 4),
			new CardBuilder(NaveAbbandonata.class, 2),
			new CardBuilder(Contrabbandieri.class, 1),
			new CardBuilder(Schiavisti.class, 1),
			new CardBuilder(Pirati.class, 1),
			new CardBuilder(ZonaDiGuerra.class, 1),
			new CardBuilder(PolvereStellare.class, 1),
			new CardBuilder(StazioneAbbandonata.class, 2)
		);
		
		// utilizzato per rapportare bonus/malus al livello di gioco
		float multiplier = 1;
		
		if(livello == Livello.II) multiplier = 2; 
		else if(livello == Livello.III) multiplier = 2.5f;
		
		for(CardBuilder builder : builders) {
			for(int i = 0; i < builder.getQuantita(); i++) {
				try {
					Class<? extends Carta> tipologia = builder.getTipologia();
					
					if(tipologia == PioggiaDiMeteoriti.class) {
						int maxGrandi = (int) (new Random().nextInt(1, 3) * multiplier);
						int maxNormali = (int) (new Random().nextInt(1, 5) * multiplier);
						
						Meteorite.Direzione[] direzioni = new Meteorite.Direzione[] {
								Meteorite.Direzione.SU,
								Meteorite.Direzione.GIU,
								Meteorite.Direzione.DX,
								Meteorite.Direzione.SX
						};
						List<Meteorite> meteoriti = new ArrayList<Meteorite>();
						
						for(int j = 0; j < maxGrandi; j++) {
							meteoriti.add(new Meteorite(Dimensione.GROSSO, direzioni[new Random().nextInt(0, 4)]));
						}
						
						for(int j = 0; j < maxNormali; j++) {
							meteoriti.add(new Meteorite(Dimensione.PICCOLO, direzioni[new Random().nextInt(0, 4)]));
						}
						
						mazzo.add(new PioggiaDiMeteoriti(livello, meteoriti));
						
					} else if(tipologia == Pianeti.class) {
						int numeroPianeti = new Random().nextInt(1, 5);
						List<Pianeta> pianeti = new ArrayList<Pianeta>();
						
						for(int j = 0; j < numeroPianeti; j++) {
							Cargo.ColoreCargo[] colori = new Cargo.ColoreCargo[] {
								Cargo.ColoreCargo.BLU,
								Cargo.ColoreCargo.GIALLO,
								Cargo.ColoreCargo.ROSSO,
								Cargo.ColoreCargo.VERDE
							};
							
							int quantitaCargo = new Random().nextInt(1, 6);
							List<Cargo> cargo = new ArrayList<Cargo>();
							
							for(int z = 0; z < quantitaCargo; z++) {
								cargo.add(new Cargo(colori[new Random().nextInt(0, 4)]));
							}
							
							pianeti.add(new Pianeta(cargo));
						}
						
						mazzo.add(new Pianeti(livello, pianeti, new Random().nextInt(1, 4)));
						
					} else if(tipologia == SpazioAperto.class) {
						mazzo.add(new SpazioAperto(livello));
					} else if(tipologia == NaveAbbandonata.class) {
						int equipaggioDaPerdere = (int) (new Random().nextInt(1, 4) * multiplier);
						int crediti = (int) (new Random().nextInt(2, 5) * multiplier);
						int giorni = (int) (1 * multiplier);
						
						mazzo.add(new NaveAbbandonata(livello, equipaggioDaPerdere, giorni, crediti));
					} else if(tipologia == Contrabbandieri.class) {
						Cargo.ColoreCargo[] colori = new Cargo.ColoreCargo[] {
							Cargo.ColoreCargo.BLU,
							Cargo.ColoreCargo.GIALLO,
							Cargo.ColoreCargo.ROSSO,
							Cargo.ColoreCargo.VERDE
						};
						
						List<Cargo> cargoList = new ArrayList<Cargo>();
						
						for(int z = 0; z < 3; z++) {
							cargoList.add(new Cargo(colori[new Random().nextInt(0, 4)]));
						}
						
						mazzo.add(new Contrabbandieri(livello, 4, 1, 2, cargoList));
					} else if(tipologia == Schiavisti.class) {						
						mazzo.add(new Schiavisti(livello, 6, 2, 1, 5));
					} else if(tipologia == Pirati.class) {
						Cannonata.Dimensione[] dimensioni = new Cannonata.Dimensione[] {
								Cannonata.Dimensione.LEGGERA, 
								Cannonata.Dimensione.PESANTE
						};
						
						Cannonata.Direzione[] direzioni = new Cannonata.Direzione[] {
								Cannonata.Direzione.SU, 
								Cannonata.Direzione.GIU,
						};
						
						List<Cannonata> cannonate = new ArrayList<Cannonata>();
						
						for(int j = 0; j < 3; j++) {
							cannonate.add(new Cannonata(dimensioni[new Random().nextInt(0, 2)], direzioni[new Random().nextInt(0, 2)]));
						}
						
						if(livello == Livello.III) {
							cannonate.add(new Cannonata(dimensioni[0], Cannonata.Direzione.SX));
							cannonate.add(new Cannonata(dimensioni[0], Cannonata.Direzione.DX));
						}
						
						mazzo.add(new Pirati(livello, 5, 1, 4, cannonate));
					} else if(tipologia == ZonaDiGuerra.class) {
						Cannonata.Dimensione[] dimensioni = new Cannonata.Dimensione[] {
								Cannonata.Dimensione.LEGGERA, 
								Cannonata.Dimensione.PESANTE
						};
						
						Cannonata.Direzione[] direzioni = new Cannonata.Direzione[] {
								Cannonata.Direzione.SU, 
								Cannonata.Direzione.GIU,
						};
						
						List<Cannonata> cannonate = new ArrayList<Cannonata>();
						
						for(int j = 0; j < 3; j++) {
							cannonate.add(new Cannonata(dimensioni[new Random().nextInt(0, 2)], direzioni[new Random().nextInt(0, 2)]));
						}
						
						if(livello == Livello.II || livello == Livello.III) {
							cannonate.add(new Cannonata(dimensioni[0], Cannonata.Direzione.SX));
							cannonate.add(new Cannonata(dimensioni[0], Cannonata.Direzione.DX));
						}
						
						mazzo.add(new ZonaDiGuerra(livello, cannonate));
					} else if(tipologia == PolvereStellare.class) {
						mazzo.add(new PolvereStellare(livello));
					} else if(tipologia == StazioneAbbandonata.class) {
						Cargo.ColoreCargo[] colori = new Cargo.ColoreCargo[] {
								Cargo.ColoreCargo.BLU,
								Cargo.ColoreCargo.GIALLO,
								Cargo.ColoreCargo.ROSSO,
								Cargo.ColoreCargo.VERDE
							};
							
						List<Cargo> cargoList = new ArrayList<Cargo>();
						
						for(int z = 0; z < 3; z++) {
							cargoList.add(new Cargo(colori[new Random().nextInt(0, 4)]));
						}
						
						mazzo.add(new StazioneAbbandonata(livello, cargoList, (int)(1 * multiplier), (int)(new Random().nextInt(4, 7) * multiplier)));					}
					
				} catch(Exception e) {
					throw new RuntimeException("Errore nella creazione di una carta", e);
				}
			}
		}
		
		return mazzo;
	}
	
	private static void Assemblaggio(List<Giocatore> giocatori, Livello livello) {
		List<ComponentBuilder> builders = Arrays.asList(
				new ComponentBuilder(Cannone.class, 25),
				new ComponentBuilder(CannoneDoppio.class, 11),
				new ComponentBuilder(Scudo.class, 8),
				new ComponentBuilder(Batteria.class, 11, false),
				new ComponentBuilder(Batteria.class, 6, true),
				new ComponentBuilder(Stiva.class, 9, false, false),
				new ComponentBuilder(Stiva.class, 6, true, false),
				new ComponentBuilder(Stiva.class, 3, true, true),//Stiva speciale grande
				new ComponentBuilder(Stiva.class, 6, false, true),//stiva speciale piccola
				new ComponentBuilder(Motore.class, 21),
				new ComponentBuilder(MotoreDoppio.class, 9),				
				new ComponentBuilder(Stiva.class, 3, true, true),//Stiva speciale grande
				new ComponentBuilder(Strutturale.class, 8),			
				new ComponentBuilder(Cabina.class, 17),						
				new ComponentBuilder(SupportoAlieni.class, 6, Colore.MARRONE),
				new ComponentBuilder(SupportoAlieni.class, 6, Colore.VIOLA)
				);
		List<Componente> componenti = GeneraComponenti(builders);
		CabinaPartenza[] coloreGiocatori = new CabinaPartenza[] {
				new CabinaPartenza(Connettore.UNIVERSALE, Connettore.UNIVERSALE, Connettore.UNIVERSALE, Connettore.UNIVERSALE, ColoreGiocatore.BLU),
				new CabinaPartenza(Connettore.UNIVERSALE, Connettore.UNIVERSALE, Connettore.UNIVERSALE, Connettore.UNIVERSALE, ColoreGiocatore.ROSSO),
				new CabinaPartenza(Connettore.UNIVERSALE, Connettore.UNIVERSALE, Connettore.UNIVERSALE, Connettore.UNIVERSALE, ColoreGiocatore.GIALLO),
				new CabinaPartenza(Connettore.UNIVERSALE, Connettore.UNIVERSALE, Connettore.UNIVERSALE, Connettore.UNIVERSALE, ColoreGiocatore.VERDE)
				};
		
		Scanner sc = new Scanner(System.in);
		
		for(Giocatore giocatore : giocatori) {
			giocatore.InizializzaNave(livello);
			int random = (int)(Math.random() * 4);
			int prenotazioni = 0;
			int posizionePrenotazione = 5;
			
			CabinaPartenza cabinaGiocatore = coloreGiocatori[random];
			giocatore.getNave().aggiungiComponente(6, 6, cabinaGiocatore);
			
			System.out.println("\n\nTi è stato assegnato il colore " + cabinaGiocatore.getColore());
			System.out.println("E' il tuo turno, " + giocatore.nome + "\ne' tempo di assemblare la tua nave per avviare il viaggio interspaziale!");
			System.out.println("Da questo momento in poi hai tempo 2 minuti esatti per scegliere le tue tessere.");
			clessidra.start(120);
			
			while(!clessidra.isTimeEnded()) {
				System.out.print("\033[H\033[2J");
				System.out.flush();
				System.out.println("Attualmente rimangono sul banco: " + componenti.size() + " tessere");
				System.out.println("\n\nPESCA UNA TESSERA (premi invio): ");
				String scelta = sc.nextLine();
				
				if(!scelta.isEmpty()) continue;

				random = (int)(Math.random() * componenti.size());
				Componente pescata = componenti.get(random);
				System.out.println("Questa è la tessera che hai scelto:\n" + pescata.toString() + "\n\n Puoi scegliere se tenerla (T), scartartla (S) o prenotarla (P): ");
				
				String sceltaTessera = sc.nextLine();
				
				if(sceltaTessera.equalsIgnoreCase("t")) {
					componenti.remove(random);
					System.out.print("\033[H\033[2J");
					System.out.flush();
					giocatore.getNave().stampa();
					System.out.println("Ottimo! dimmi ora in che cella vuoi posizionarlo (Ricordati che le caselle partono da 00 e non da 11!)");
					String posizione = sc.nextLine();
					
					try {						
						int x = Character.getNumericValue(posizione.charAt(0));
						int y = Character.getNumericValue(posizione.charAt(1));
						
						giocatore.getNave().aggiungiComponente(x, y, pescata);
					} catch(Exception e) {
						System.out.println("Coordinate errate. Impossibile inserire");
					}
					
				} else if(sceltaTessera.equalsIgnoreCase("p") && prenotazioni < 3) {
					componenti.remove(random);
					giocatore.getNave().aggiungiComponente(0, posizionePrenotazione, pescata);
					posizionePrenotazione++;
					prenotazioni++;
				}
				
				//Clears screen: https://www.quora.com/How-do-I-clear-the-console-screen-in-Java (Nota: in Eclipse non funziona, funziona solo nella console vera e propria)
				System.out.print("\033[H\033[2J");   
			    System.out.flush();
			}
			
			System.out.println("TEMPO SCADUTO, " + giocatore.nome + "! Attendi ora che gli altri giocatori terminimo la fase di assemblaggio delle loro navi!");
		}
		
		sc.close();
	}
	
	public static void SchermataIniziale(List<Giocatore> giocatori, PlanciaVolo plancia) {
		System.out.print("\033[H\033[2J");   
	    System.out.flush();
		System.out.println("========== VISUALIZZA LA TUA NAVE O LA PLANCIA ==========");
		
		giocatori.get(0).getNave().stampa();
		Scanner sc = new Scanner(System.in);
		StringBuilder builder = new StringBuilder();
		
		for(Giocatore giocatore : giocatori) builder.append(giocatore.getNome() + " - ");
		builder.append("\nLegenda:\t" + "5. Visualizza Plancia\t 6. Prosegui con il gioco");
		builder.toString();
		
		int scelta = 0;
		
		while(scelta != 6) {
			builder.toString();
			scelta = sc.nextInt();
			
			if(scelta == 5) //TODO: stampare plancia
			
			while(scelta < 0 && scelta > giocatori.size()) {
				builder.toString();
				scelta = sc.nextInt();
			}
			
			System.out.print("\033[H\033[2J");   
		    System.out.flush();
		    
		    giocatori.get(scelta).getNave().stampa();
		}
	}
	
	public static void StartGame(List<Giocatore> giocatori) {
		List<Livello> livelli = Arrays.asList(Livello.I, Livello.II, Livello.III);
		int counter = 0;
		
		while(!livelli.isEmpty()) {
			List<Carta> mazzo = InizializzaMazzo(livelli.get(counter));
			PlanciaVolo plancia = null;
			
			switch(livelli.get(counter)) {
			case Livello.I:
				plancia = new Livello1(1);
				break;
			case Livello.II:
				plancia = new Livello2(1);
				break;
			case Livello.III:
				plancia = new Livello3(1);
				break;
			default:
				break;
			}
			
			plancia.PiazzaGiocatori(giocatori);
			Assemblaggio(giocatori, livelli.get(counter));
			
			System.out.println("==================== GIOCATORI, PRONTI ALLA PARTENZA DEL VIAGGIO SPAZIALE! ====================");
			giocatori.getFirst().setLeader(true);
			
			while(!mazzo.isEmpty()) {
				int scelta = new Random().nextInt(0, mazzo.size());
				Carta pescata = mazzo.get(scelta);
				pescata.azione(giocatori, plancia);
			    SchermataIniziale(giocatori, plancia);
			}
			
			System.out.println("==================== GIOCATORI, SIAMO GIUNTI ALLA FINE DEL VIAGGIO! ====================");
			counter++;
		}
		
		int crediti = 0;
		int index = 0;
		
		for(int i = 0; i < giocatori.size(); i++) {
			int tmp = giocatori.get(i).getCrediti();
			if(tmp > crediti) crediti = tmp; index = i;
		}
		
		System.out.println("Si annuncia con orgoglio che il vincitore della partitia, nonché miglior Trasportare Galattico è");
		System.out.println(giocatori.get(index).getNome() + " !!!!!!!!!!!!!!!!!!");
	}
	
	public static void StartGame(Livello livello) {
		List<Giocatore> giocatori = InizializzaGiocatori();
		List<Carta> mazzo = InizializzaMazzo(livello);
		Assemblaggio(giocatori, livello);
		
		for(Giocatore giocatore : giocatori) giocatore.InizializzaNave(livello);
		PlanciaVolo plancia = null;
		
		switch(livello) {
		case Livello.I:
			plancia = new Livello1(1);
			break;
		case Livello.II:
			plancia = new Livello2(1);
			break;
		case Livello.III:
			plancia = new Livello3(1);
			break;
		default:
			break;
		}
		
		plancia.PiazzaGiocatori(giocatori);
		System.out.println("==================== GIOCATORI, PRONTI ALLA PARTENZA DEL VIAGGIO SPAZIALE! ====================");
		giocatori.getFirst().setLeader(true);
		
		while(!mazzo.isEmpty()) {
			int scelta = new Random().nextInt(0, mazzo.size());
			Carta pescata = mazzo.get(scelta);
			pescata.azione(giocatori,plancia);
		}
		
		System.out.println("==================== GIOCATORI, SIAMO GIUNTI ALLA FINE DEL VIAGGIO! ====================");
		
		int crediti = 0;
		int index = 0;
		
		for(int i = 0; i < giocatori.size(); i++) {
			int tmp = giocatori.get(i).getCrediti();
			if(tmp > crediti) crediti = tmp; index = i;
		}
		
		System.out.println("Si annuncia con orgoglio che il vincitore della partitia, nonché miglior Trasportare Galattico è");
		System.out.println(giocatori.get(index).getNome() + " !!!!!!!!!!!!!!!!!!");
	}
	
	public static void main(String[] args) {
		String[] vociTmp = new String[] {"Avvia una partita", "Informazioni sul regolamento", "Esci"};
		List<String> voci = Arrays.asList(vociTmp);
		StampaMenu("BENVENUTO SU GALAXY TRUCKER", voci);
		
		Scanner sc = new Scanner(System.in);
		int decisione = sc.nextInt();
		
		while(decisione != 1 && decisione != 2 && decisione != 3) {
			System.out.println("Prova a ricontrollare la tua scelta!");
			decisione = sc.nextInt();
		}
		
		switch(decisione) {
		case 1:
			System.out.println("Ottimo, siete allora pronti per affrontare la vostra prima avventura.");
			System.out.println("Prima di cominciare però, sappiate che avete a disposizione 3 scelte: \n");
			
			voci = Arrays.asList("Livello 1", "Livello 2", "Livello 3", "Trasporta Intergalattica");
			StampaMenu("Seleziona il livello desiderato", voci);
			decisione = sc.nextInt();
			
			while(decisione != 1 && decisione != 2 && decisione != 3 && decisione != 4) {
				System.out.println("Prova a ricontrollare la tua scelta!");
				decisione = sc.nextInt();
			}
			
			if(decisione == 1) StartGame(Livello.I);
			else if(decisione == 2) StartGame(Livello.II);
			else if(decisione == 3) StartGame(Livello.III);
			else {
				List<Giocatore> giocatori = InizializzaGiocatori();
				StartGame(giocatori);
			}
		case 2:
			System.out.println("Ecco a te la pagina dedicata al regolamento del gioco: ");
			System.out.println("https://www.craniocreations.it/storage/media/product_downloads/179/2052/Galaxy-Trucker_ITA_Rules_compressed.pdf");
			break;
		default:
			sc.close();
			return;
		}
		
		sc.close();
	}

}
