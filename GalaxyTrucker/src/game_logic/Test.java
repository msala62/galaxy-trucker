// galaxy-trucker/GalaxyTrucker/src/game_logic/Test.java
package game_logic;

import carteAvventura.*;
import componenti.*;
import nave.*;
import merci.*;
import planciavolo.*; // Import all classes from planciavolo

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random; // Ensure Random is imported for creating components

public class Test {

    // --- Helper methods for testing ---
    private static void printTestResult(String testName, boolean success) {
        if (success) {
            System.out.println("[PASS] " + testName);
        } else {
            System.err.println("[FAIL] " + testName);
        }
    }

    private static void printPlayerState(Giocatore giocatore) {
        System.out.println("\n--- Player State: " + giocatore.getNome() + " ---");
        System.out.println("Credits: " + giocatore.getCrediti());
        System.out.println("Total Crew: " + giocatore.getNave().getEquipaggio()); // Assuming getEquipaggioTotale exists and is correct
        System.out.println("Ship Layout:");
        giocatore.getNave().stampa(); // Uses the improved stampa method
        System.out.println("------------------------------------");
    }

    // --- Main Test Scenario for Carte Avventura ---
    public static void testCarteAvventuraScenario() {
        System.out.println("\n--- Starting Comprehensive Carte Avventura Test Scenario ---");

        // 1. Initialize two players
        Giocatore player1 = new Giocatore("Tester_Alpha");
        Giocatore player2 = new Giocatore("Tester_Beta");
        List<Giocatore> players = Arrays.asList(player1, player2);

        // 2. Initialize and pre-build ships for both players (NaveLivello1)
        Livello testLevel = Livello.I;
        PlanciaVolo planciaVolo = new Livello1(1); // Create Level 1 flight board

        for (Giocatore player : players) {
            player.InizializzaNave(testLevel);
            Nave nave = player.getNave();

            // Pre-build a basic ship:
            // Assuming CabinaPartenza is added by InizializzaNave or needs to be added here.
            // Based on Game.java, CabinaPartenza is added during Assemblaggio directly.
            // Let's add it here for consistency in testing pre-built ships.
            if (player.getNome().equals("Tester_Alpha")) {
                nave.aggiungiComponente(2, 3, new CabinaPartenza(Connettore.UNIVERSALE, Connettore.UNIVERSALE, Connettore.UNIVERSALE, Connettore.UNIVERSALE, ColoreGiocatore.BLU));
            } else {
                nave.aggiungiComponente(2, 3, new CabinaPartenza(Connettore.UNIVERSALE, Connettore.UNIVERSALE, Connettore.UNIVERSALE, Connettore.UNIVERSALE, ColoreGiocatore.ROSSO));
            }

            // Add various components strategically for testing different card effects
            // (Coordinates are for NaveLivello1, check its usable cells)
            // Layout for NaveLivello1 usable cells:
            // {0,3}, {1,2}, {1,3}, {1,4}, {2,1}, {2,2}, {2,3}, {2,4}, {2,5},
            // {3,1}, {3,2}, {3,3}, {3,4}, {3,5}, {4,1}, {4,2}, {4,4}, {4,5}

            // Motors: for SpazioAperto and ZonaDiGuerra
            nave.aggiungiComponente(2, 4, new Motore(Connettore.LISCIO, Connettore.SINGOLO, Connettore.SINGOLO, Connettore.UNIVERSALE)); // Points towards player (behind)
            nave.aggiungiComponente(3, 4, new MotoreDoppio(Connettore.LISCIO, Connettore.DOPPIO, Connettore.DOPPIO, Connettore.UNIVERSALE)); // Needs battery

            // Cannons: for Pirati, Contrabbandieri, ZonaDiGuerra, Meteorite
            nave.aggiungiComponente(2, 2, new Cannone(Connettore.UNIVERSALE, Connettore.SINGOLO, Connettore.UNIVERSALE, Connettore.SINGOLO)); // Points SU (forward)
            nave.aggiungiComponente(1, 2, new CannoneDoppio(Connettore.UNIVERSALE, Connettore.DOPPIO, Connettore.UNIVERSALE, Connettore.DOPPIO)); // Needs battery, points SU

            // Shields: for Meteorite, Cannonata (light)
            nave.aggiungiComponente(2, 1, new Scudo(Connettore.LISCIO, Connettore.UNIVERSALE, Connettore.UNIVERSALE, Connettore.LISCIO, Direzione.DX, Direzione.SU)); // Protects DX, SU
            
            // Batteries: to power double components and shields
            nave.aggiungiComponente(3, 2, new Batteria(Connettore.UNIVERSALE, Connettore.UNIVERSALE, Connettore.UNIVERSALE, Connettore.UNIVERSALE, false)); // Small battery (2 charge)
            nave.aggiungiComponente(4, 2, new Batteria(Connettore.UNIVERSALE, Connettore.UNIVERSALE, Connettore.UNIVERSALE, Connettore.UNIVERSALE, true)); // Large battery (3 charge)

            // Stows: for Pianeti, Contrabbandieri, StazioneAbbandonata, TrasportatoreSupremo
            nave.aggiungiComponente(1, 3, new Stiva(Connettore.UNIVERSALE, Connettore.UNIVERSALE, Connettore.UNIVERSALE, Connettore.UNIVERSALE, false, false)); // Small normal
            nave.aggiungiComponente(3, 3, new Stiva(Connettore.UNIVERSALE, Connettore.UNIVERSALE, Connettore.UNIVERSALE, Connettore.UNIVERSALE, true, true)); // Large special (for red cargo)

            // Structural: to provide exposed connectors for PolvereStellare, and for integrity
            nave.aggiungiComponente(0, 3, new Strutturale(Connettore.LISCIO, Connettore.LISCIO, Connettore.LISCIO, Connettore.UNIVERSALE)); // Exposed on 3 sides
            nave.aggiungiComponente(4, 4, new Strutturale(Connettore.UNIVERSALE, Connettore.LISCIO, Connettore.LISCIO, Connettore.LISCIO)); // Exposed on 3 sides
        }

        // Set initial leader for player 1
        player1.setLeader(true);
        planciaVolo.PiazzaGiocatori(players); // Places players on the board based on initial positions

        // 3. Initialize the Level I Adventure Card Deck
        List<Carta> adventureDeck = Game.InizializzaMazzo(testLevel);
        // Shuffle the deck for a more realistic test order
        java.util.Collections.shuffle(adventureDeck, new Random());

        System.out.println("\n--- Pre-Flight Ship Status ---");
        for (Giocatore player : players) {
            printPlayerState(player);
        }
        System.out.println("Initial Leader: " + (player1.isLeader() ? player1.getNome() : player2.getNome()));

        // 4. Iterate through each card and activate its action
        int cardCount = 0;
        for (Carta card : adventureDeck) {
            cardCount++;
            System.out.println("\n===== ACTIVATING CARD #" + cardCount + ": " + card.getNome() + " =====");
            System.out.println(card.toString()); // Print card details

            // Call the action method for the card
            // NOTE: If action() methods require user input, you will need to provide it manually in the console.
            // To automate, pass a mocked Scanner to action() methods after refactoring them.
            card.azione(players);

            System.out.println("\n===== POST-CARD #" + cardCount + " STATUS =====");
            for (Giocatore player : players) {
                printPlayerState(player);
            }
            // Add a check to update leader if position changes (not handled by cards directly yet)
            // This would be part of the Volo.cambiaPosizione implementation.
        }

        System.out.println("\n--- End of Carte Avventura Test Scenario ---");
        System.out.println("Remember to manually verify the console output against Galaxy Trucker rules.");
    }

    public static void main(String[] args) {
        // Run the comprehensive test scenario for adventure cards
        testCarteAvventuraScenario();

        // You can keep other individual test methods here too, if desired
        // testShipAssembly();
        // testEliminaEquipaggio();
        // testCargoManagement();
        // testPioggiaDiMeteoriti();
        // testZonaDiGuerra();
    }
}
