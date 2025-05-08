package game_logic;

import java.util.Scanner;
import java.util.concurrent.ThreadLocalRandom;

import alieni.Colore;

import carteAvventura.*;
import carteAvventura.Meteorite.Dimensione;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;

import componenti.*;
import merci.Cargo;
import planciavolo.Livello1;
import planciavolo.PlanciaVolo;

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
	
	private static List<Giocatore> InizializzaGiocatori(Livello livello) {
		Scanner sc = new Scanner(System.in);
		List<Giocatore> giocatori = new ArrayList<Giocatore>();
		int numeroGiocatori = sc.nextInt();
		
		while(numeroGiocatori < 1 && numeroGiocatori > 4) {
			numeroGiocatori = sc.nextInt();
		}
		
		for(int i = 0; i < numeroGiocatori; i++) {
			System.out.println("Benvenuto giocatore numero " + i+1 + "!" + "\nInserisci pure il tuo nome: ");
			String nome = sc.nextLine();
			
			while(nome == "" && nome.length() < 2) {
				System.out.println("Il nome deve essere almeno di 2 caratteri, riprova!");
				nome = sc.nextLine();
			}
			
			giocatori.add(new Giocatore(nome));
		}
		
		return giocatori;
	}
	
	private static List<Componente> GeneraComponenti(List<ComponentBuilder> builders) {
	    List<Componente> componenti = new ArrayList<>();
	    Connettore[] direzioni = {Connettore.SINGOLO, Connettore.DOPPIO, Connettore.UNIVERSALE, Connettore.LISCIO};
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
	                        Connettore.class, Connettore.class, Connettore.class, Connettore.class, Connettore.class, Connettore.class)
	                        .newInstance(direzioni[sx], direzioni[dx], direzioni[su], direzioni[giu], direzioni[su], direzioni[giu]);
	                }
	                else if(builder.tipologia.equals(Batteria.class)) {
	                    componente = builder.tipologia.getConstructor(
	                        Connettore.class, Connettore.class, Connettore.class, Connettore.class, boolean.class)
	                        .newInstance(direzioni[sx], direzioni[dx], direzioni[su], direzioni[giu], builder.grande);
	                }
	                else if(builder.tipologia.equals(Stiva.class)) {
	                	componente = builder.tipologia.getConstructor(
		                        Connettore.class, Connettore.class, Connettore.class, Connettore.class, boolean.class)
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
		
		float multiplier = 1;
		
		if(livello == Livello.II) multiplier = 2; 
		else if(livello == Livello.III) multiplier = 2.5f;
		
		for(CardBuilder builder : builders) {
			for(int i = 0; i < builder.getQuantita(); i++) {
				try {
					Class<? extends Carta> tipologia = builder.getTipologia();
					
					if(tipologia == PioggiaDiMeteoriti.class) {
						int maxGrandi = new Random().nextInt(1, 3);
						int maxNormali = new Random().nextInt(1, 5);
						
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
						int equipaggioDaPerdere = new Random().nextInt(1, 4);
						int crediti = new Random().nextInt(2, 5);
						int giorni = 1;
						
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
						
					}
					
				} catch(Exception e) {
					throw new RuntimeException("Errore nella creazione di una carta", e);
				}
			}
		}
		
		return mazzo;
	}
	
	private static void Assemblaggio(List<Giocatore> giocatori) {
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
		
		for(Giocatore giocatore : giocatori) {
			int random = (int)(Math.random() * 4);
			int prenotazioni = 0;
			int posizionePrenotazione = 5;
			
			CabinaPartenza cabinaGiocatore = coloreGiocatori[random];
			giocatore.nave.aggiungiComponente(7, 7, cabinaGiocatore);
			
			System.out.println("Ti è stato assegnato il colore " + cabinaGiocatore.getColore());
			System.out.println("E' il tuo turno, " + giocatore.nome + "\ne' tempo di assemblare la tua nave per avviare il viaggio interspaziale!");
			System.out.println("Da questo momento in poi hai tempo 2 minuti esatti per scegliere le tue tessere.");
			clessidra.start(120);
			
			while(!clessidra.isTimeEnded()) {
				System.out.println("SCEGLI UNA TESSERA COMPONENTE TRA QUELLE PROPOSTE: ");
				System.out.println("Attualmente rimangono sul banco: " + componenti.size() + " tessere");
				
				Scanner sc = new Scanner(System.in);
				String scelta = sc.nextLine();
				
				if(!scelta.isEmpty()) {
					System.out.print("\033[H\033[2J");
				    System.out.flush();
					
					random = (int)(Math.random() * componenti.size());
					Componente pescata = componenti.get(random);
					System.out.println("Questa è la tessera che hai scelto:\n" + pescata.toString() + "\n\n Puoi scegliere se tenerla (T), scartartla (S) o prenotarla (P): ");
					giocatore.nave.stampa();
					
					String sceltaTessera = sc.nextLine();
					
					if(sceltaTessera.toLowerCase() == "t") {
						componenti.remove(random);
						System.out.println("Ottimo! dimmi ora in che cella vuoi posizionarlo (Ricordati che le caselle partono da 00 e non da 11!)");
						String posizione = sc.nextLine();
						
						char xChar = posizione.charAt(0);
						char yChar = posizione.charAt(1);
						
						int x = Character.getNumericValue(xChar);
						int y = Character.getNumericValue(yChar);
						
						giocatore.nave.aggiungiComponente(x, y, pescata);
					} else if(sceltaTessera.toLowerCase() == "p") {
						if(prenotazioni > 2) continue;
						giocatore.nave.aggiungiComponente(0, posizionePrenotazione, pescata);
						posizionePrenotazione++;
						prenotazioni++;
					}
				}
				
				sc.close();
				
				//Clears screen: https://www.quora.com/How-do-I-clear-the-console-screen-in-Java
				System.out.print("\033[H\033[2J");   
			    System.out.flush();
			}
			
			System.out.println("TEMPO SCADUTO, " + giocatore.nome + "! Attendi ora che gli altri giocatori terminimo la fase di assemblaggio delle loro navi!");
		}
	}
	
	public static void StartGame(Livello livello) {
		List<Giocatore> giocatori = InizializzaGiocatori(livello);
		List<Carta> mazzo = InizializzaMazzo(livello);
		Assemblaggio(giocatori);
		PlanciaVolo plancia;
		
		/*switch(livello) {
		case Livello.I:
			plancia = new Livello1();
			break;
		default:
		}*/
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
		
		sc.close();
		
		switch(decisione) {
		case 1:
			break;
		case 2:
			System.out.println("Ecco a te la pagina dedicata al regolamento del gioco: ");
			System.out.println("https://www.craniocreations.it/storage/media/product_downloads/179/2052/Galaxy-Trucker_ITA_Rules_compressed.pdf");
			break;
		default:
			return;
		}
	}

}
