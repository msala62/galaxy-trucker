package game_logic;

import java.util.Scanner;

import alieni.Colore;
import carteAvventura.Carta;
import carteAvventura.Livello;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import componenti.*;
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
	        for(int i = 0; i < builder.quantia; i++) {
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
	                else if(builder.tipologia.equals(Batteria.class) || 
	                        builder.tipologia.equals(Stiva.class) || 
	                        builder.tipologia.equals(StivaSpeciale.class)) {
	                    componente = builder.tipologia.getConstructor(
	                        Connettore.class, Connettore.class, Connettore.class, Connettore.class, boolean.class)
	                        .newInstance(direzioni[sx], direzioni[dx], direzioni[su], direzioni[giu], builder.speciale);
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
	
	public List<Carta> GeneraMazzo(Livello livello){
		List<Carta> mazzo = new ArrayList<Carta>();
		
		return mazzo;
	}
	
	private static void Assemblaggio(List<Giocatore> giocatori) {
		List<ComponentBuilder> builders = Arrays.asList(
				new ComponentBuilder(Cannone.class, 25),
				new ComponentBuilder(CannoneDoppio.class, 11),
				new ComponentBuilder(Scudo.class, 8),
				new ComponentBuilder(Batteria.class, 11, false),
				new ComponentBuilder(Batteria.class, 6, true),
				new ComponentBuilder(Stiva.class, 9, false),
				new ComponentBuilder(Stiva.class, 6, true),
				new ComponentBuilder(StivaSpeciale.class, 3, true),
				new ComponentBuilder(StivaSpeciale.class, 6, false),
				new ComponentBuilder(Motore.class, 21),
				new ComponentBuilder(MotoreDoppio.class, 9),				
				new ComponentBuilder(StivaSpeciale.class, 3, true),		
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
		Assemblaggio(giocatori);
		PlanciaVolo plancia;
		
		switch(livello) {
		case Livello.I:
			plancia = new Livello1();
			break;
		default:
		}
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
