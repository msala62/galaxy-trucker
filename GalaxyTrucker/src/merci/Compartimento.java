package merci;

import java.util.ArrayList;
import java.util.List;

public class Compartimento {
    private final int capacita;
    private final boolean isRosso;
    private final List<Cubo> cubi;

    public Compartimento(int capacita, boolean isRosso) {
        this.capacita = capacita;
        this.isRosso = isRosso;
        this.cubi = new ArrayList<>(capacita);
    }

    public int getSpazioDisponibile() {
        return capacita - cubi.size();
    }

    public List<Cubo> getCubi() {
        return cubi;
    }

    public String cuboAggiunto(Cubo cubo) {
        if (cubi.size() >= capacita) {
            return "Non possiamo aggiungere " + cubo + " perché non c'è spazio in questo compartimento.";
        }

        if (cubo.getColore() == Cubo.ColoreCubo.ROSSO && !isRosso) {
            return "Non possiamo aggiungere il " + cubo + " a un compartimento non rosso.";
        }

        if (cubo.getColore() != Cubo.ColoreCubo.ROSSO && isRosso) {
            return "Non possiamo aggiungere il " + cubo + " a un compartimento rosso. Solo i cubi rossi possono essere aggiunti qui.";
        }

        cubi.add(cubo);
        return cubo + " aggiunto al compartimento.";
    }

    public String rimuoviCubo(Cubo cubo) {
        if (cubi.contains(cubo)) {
            cubi.remove(cubo);
            return cubo + " rimosso dal compartimento.";
        } else {
            return cubo + " non trovato in questo compartimento.";
        }
    }

    public int getValoreTotale() {
        int valoreTotale = 0;
        for (Cubo cubo : cubi) {
            valoreTotale += cubo.getValore();
        }
        return valoreTotale;
    }

    @Override
    public String toString() {
        String tipo = isRosso ? "Rosso" : "Bianco";
        return "Compartimento " + tipo + " (Capacità: " + capacita + ", Cubi: " + cubi + ")";
    }

    public static void main(String[] args) {
        // Esempi di creazione di cubi 
        Cubo cuboRosso1 = new Cubo(Cubo.ColoreCubo.ROSSO);
        Cubo cuboBlu1 = new Cubo(Cubo.ColoreCubo.BLU);
        Cubo cuboGiallo1 = new Cubo(Cubo.ColoreCubo.GIALLO);
        Cubo cuboVerde1 = new Cubo(Cubo.ColoreCubo.VERDE);
        Cubo cuboRosso2 = new Cubo(Cubo.ColoreCubo.ROSSO);
        Cubo altroCuboBlu = new Cubo(Cubo.ColoreCubo.BLU); 

        // Esempi di creazione di compartimenti
        Compartimento compartimentoRosso = new Compartimento(1, true);
        Compartimento compartimentoBianco2Spazi = new Compartimento(2, false);
        Compartimento compartimentoBianco3Spazi = new Compartimento(3, false);

        // Tentativi di aggiungere cubi ai compartimenti
        System.out.println(compartimentoRosso.cuboAggiunto(cuboRosso1));
        System.out.println(compartimentoRosso.cuboAggiunto(cuboBlu1));   // Non dovrebbe funzionare perche compartimento rosso e cubo blu

        System.out.println(compartimentoBianco2Spazi.cuboAggiunto(cuboBlu1));
        System.out.println(compartimentoBianco2Spazi.cuboAggiunto(cuboGiallo1));
        System.out.println(compartimentoBianco2Spazi.cuboAggiunto(cuboVerde1)); // Non dovrebbe funzionare per mancanza di spazio
        System.out.println(compartimentoBianco2Spazi.cuboAggiunto(cuboRosso2)); // Non dovrebbe funzionare (cubo rosso in compartimento bianco)

        System.out.println(compartimentoBianco3Spazi.cuboAggiunto(cuboBlu1));
        System.out.println(compartimentoBianco3Spazi.cuboAggiunto(cuboGiallo1));
        System.out.println(compartimentoBianco3Spazi.cuboAggiunto(cuboVerde1));
        System.out.println(compartimentoBianco3Spazi.cuboAggiunto(cuboRosso2)); // Non dovrebbe funzionare

        // Tentativi di rimozione di cubi dai compartimenti
        
        System.out.println("\nTentativi di rimozione: ");
        System.out.println(compartimentoRosso.rimuoviCubo(cuboRosso1));

        System.out.println(compartimentoBianco2Spazi.rimuoviCubo(altroCuboBlu)); 
        System.out.println(compartimentoBianco2Spazi.rimuoviCubo(cuboGiallo1));

        System.out.println(compartimentoRosso.rimuoviCubo(cuboBlu1)); //  Prova a rimuovere un cubo blu dal compartimento rosso ( mai agguinto ) 
        
        // Visualizzazione dello stato dei compartimenti dopo la rimozione
        
        System.out.println("\nStato dei Compartimenti dopo la rimozione:");
        System.out.println(compartimentoRosso);
        System.out.println("Valore totale del compartimento rosso: " + compartimentoRosso.getValoreTotale());
        System.out.println(compartimentoBianco2Spazi);
        System.out.println("Valore totale del compartimento bianco (2 Spazi) : " + compartimentoBianco2Spazi.getValoreTotale());
        System.out.println(compartimentoBianco3Spazi);
        System.out.println("Valore totale del compartimento bianco (3 Spazi) : " + compartimentoBianco3Spazi.getValoreTotale());
    }
}