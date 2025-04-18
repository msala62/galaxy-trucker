package merci;

import java.util.ArrayList;
import java.util.List;

public class Compartimento {
	    private List<Cubo> cubi; 
	    private boolean isRed; 
	    private int maxSlots; 

	    public Compartimento(boolean isRed, int maxSlots) {
	        this.cubi = new ArrayList<>(); 
	        this.isRed = isRed;
	        this.maxSlots = maxSlots; 
	    }

	    public boolean aggiungiCubo(Cubo cubo) {
	        if (cubi.size() < maxSlots) {
	            if (cubo.isRed() && !isRed) {
	                System.out.println("Non puoi aggiungere un cubo rosso a un compartimento non rosso.");
	                return false;
	            }
	            cubi.add(cubo);
	            System.out.println("Cubo aggiunto al compartimento. Valore: " + cubo.getValore());
	            return true; 
	        } else {
	            System.out.println("Compartmento pieno. Non puoi aggiungere più cubi.");
	            return false; 
	        }
	    }

	    public Cubo rimuoviCubo() {
	        if (cubi.isEmpty()) {
	            Cubo cubo = cubi.remove(cubi.size() - 1);
	            System.out.println("Cubo rimosso dal compartimento. Valore: " + cubo.getValore());
	            return cubo;
	        } else {
	            System.out.println("Nessun cubo da rimuovere dal compartimento.");
	            return null;
	        }
	    }

	    public int calcolaValoreTotale() {
	        int valoreTotale = 0;
	        for (Cubo cubo : cubi) {
	            valoreTotale += cubo.getValore();
	        }
	        return valoreTotale; 
	    }

	    public boolean isRed() {
	        return isRed; 
	    }

	    public int getNumeroCubi() {
	        return cubi.size(); 
	    }

	    public boolean isPieno() {
	        return cubi.size() >= maxSlots; 
	    }
	}