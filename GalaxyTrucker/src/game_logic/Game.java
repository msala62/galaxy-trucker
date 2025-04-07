package game_logic;

import java.util.Scanner;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import componenti.Componente;

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
	
	private static void Assemblaggio(List<Giocatore> giocatori) {
		for(Giocatore giocatore : giocatori) {
			System.out.println("E' il tuo turno, " + giocatore.nome + "\ne' tempo di assemblare la tua nave per avviare il viaggio interspaziale!");
			clessidra.run();
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
