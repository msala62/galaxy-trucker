package merci;

import java.util.ArrayList;
import java.util.List;

public class Compartimento {
    private final int capacita;
    private final boolean isRosso;
    private final List<Cubo> cubi;
    private boolean isDanneggiato;

    public Compartimento(int capacita, boolean isRosso) {
        this.capacita = capacita;
        this.isRosso = isRosso;
        this.cubi = new ArrayList<>(capacita);
        this.isDanneggiato = false;          // Inizialmente, il compartimento non è danneggiato
    }

    public int getSpazioDisponibile() {
        return capacita - cubi.size();
    }

    public List<Cubo> getCubi() {
        return cubi;
    }

    public boolean isDanneggiato() { 
        return isDanneggiato;
    }
    public String cuboAggiunto(Cubo cubo) {
    	 if (isDanneggiato) {
             return "Questo compartimento è danneggiato. Non è possibile aggiungere merce.";
         }
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
    	if (isDanneggiato) {
            return "Questo compartimento è danneggiato. Non è possibile rimuovere merce.";
        }
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
        String stato = isDanneggiato ? "Danneggiato" : "Intatto";
        return "Compartimento " + tipo + " (" +stato+ ", Capacità: " + capacita + ", Cubi: " + cubi + ")";
    }

    public String merciPersi(int numeroCubiDaPerdere) {
        if (isDanneggiato) {
            return "Questo compartimento è danneggiato. Non è possibile perdere merce.";
        }
        if (cubi.isEmpty()) {
            return "Il compartimento è vuoto. Nessuna merce da perdere.";
        }

        int numeroCubiPersi = 0;
        int cubiSize = cubi.size();

        for (int i = 0; i < numeroCubiDaPerdere && !cubi.isEmpty(); i++) {
            numeroCubiPersi++;
            if (cubiSize == 0) {
                break;
            }
        }

        return numeroCubiPersi + " cubi persi dal compartimento.";
    }

    public String danneggiaCompartimento() {
        if (isDanneggiato) {
            return "Questo compartimento è già danneggiato.";
        }
        isDanneggiato = true;

      
        int numeroCubiPersi = cubi.size();
        cubi.clear();

        return "Compartimento danneggiato e " + numeroCubiPersi + " cubi persi.";
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
    
        // Compartimento danneggiato e merci persi
        
        System.out.println("\nCompartimento danneggiato e merci persi:");
        compartimentoRosso.cuboAggiunto(cuboRosso1);                  //aggiungo un cubo rosso al compartimento rosso
        System.out.println(compartimentoRosso.danneggiaCompartimento());
        compartimentoBianco2Spazi.cuboAggiunto(cuboBlu1);            //aggiungo un cubo blu e giallo al compartimento bianco
        compartimentoBianco2Spazi.cuboAggiunto(cuboGiallo1);
        System.out.println(compartimentoBianco2Spazi.danneggiaCompartimento());
        compartimentoBianco3Spazi.cuboAggiunto(cuboVerde1); 
        compartimentoBianco3Spazi.cuboAggiunto(cuboGiallo1);
        compartimentoBianco3Spazi.cuboAggiunto(cuboBlu1);
        System.out.println(compartimentoBianco3Spazi.danneggiaCompartimento());

        System.out.println("\nStato dei Compartimenti dopo il danneggiamento:");
        System.out.println(compartimentoRosso);
        System.out.println("Valore totale del compartimento rosso: " + compartimentoRosso.getValoreTotale());
        System.out.println(compartimentoBianco2Spazi);
        System.out.println("Valore totale del compartimento bianco (2 Spazi) : " + compartimentoBianco2Spazi.getValoreTotale());
        System.out.println(compartimentoBianco3Spazi);
        System.out.println("Valore totale del compartimento bianco (3 Spazi) : " + compartimentoBianco3Spazi.getValoreTotale());
    }
}
