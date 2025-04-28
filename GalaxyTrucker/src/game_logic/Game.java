package game_logic;

import java.util.Scanner;

import alieni.Colore;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import componenti.*;

public class Game {
	private final static Clessidra clessidra = new Clessidra();
	private List<Componente> componentiBanco;
	
	private static void StampaMenu(String titolo, List<String> voci) 
	{
		System.out.println("==========" + titolo + "==========");
		
		for(int i = 0; i < voci.size(); i++) {
			System.out.println(i+1 + " " + voci.get(i));
		}
		
		System.out.println("================================================");
	}
	
	private static List<Giocatore> InizializzaGiocatori() {
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
		List<Componente> componenti = new ArrayList<Componente>();
		Connettore[] direzioni = {Connettore.SINGOLO, Connettore.DOPPIO, Connettore.UNIVERSALE, Connettore.LISCIO};
		
		for(ComponentBuilder builder : builders) {
			for(int i = 0; i < builder.quantia; i++) {
				componenti.add(builder.tipologia.get());
				
				for(int j = 0; j < 4; j++) {
					//TODO
				}
			}
		}
		
		return componenti;
	}
	
	private static void Assemblaggio(List<Giocatore> giocatori) {
		List<ComponentBuilder> builders = Arrays.asList(
				new ComponentBuilder(() -> new Cannone(null, null, null, null), 25),
				new ComponentBuilder(() -> new CannoneDoppio(null, null, null, null), 11),
				new ComponentBuilder(()-> new SupportoAlieni(null, null, null, null, Colore.MARRONE), 6),
				new ComponentBuilder(()-> new SupportoAlieni(null, null, null, null, Colore.VIOLA), 6),
				new ComponentBuilder(() -> new Scudo(null, null, null, null, null, null), 8),
				new ComponentBuilder(() -> new Batteria(null, null, null, null, false), 11),
				new ComponentBuilder(() -> new Batteria(null, null, null, null, true), 6),
				new ComponentBuilder(() -> new Stiva(null, null, null, null, false), 9),
				new ComponentBuilder(() -> new Stiva(null, null, null, null, true), 6),
				new ComponentBuilder(() -> new StivaSpeciale(null, null, null, null, true), 3),
				new ComponentBuilder(() -> new StivaSpeciale(null, null, null, null, false), 6),
				new ComponentBuilder(() -> new Motore(null, null, null, null), 21),
				new ComponentBuilder(() -> new MotoreDoppio(null, null, null, null), 9),
				new ComponentBuilder(() -> new StivaSpeciale(null, null, null, null, true), 3),
				new ComponentBuilder(() -> new Strutturale(null, null, null, null), 8),
				new ComponentBuilder(() -> new CabinaPartenza(null, null, null, null), 4),
				new ComponentBuilder(() -> new Cabina(null, null, null, null), 17)
				);
		List<Componente> componenti = GeneraComponenti(builders);
		
		for(Giocatore giocatore : giocatori) {
			System.out.println("E' il tuo turno, " + giocatore.nome + "\ne' tempo di assemblare la tua nave per avviare il viaggio interspaziale!");
			clessidra.start(60);
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
