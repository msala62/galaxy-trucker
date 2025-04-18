package merci;

public class Cubo {
    private int valore;
    private boolean isRed; 

    public Cubo(int valore, boolean isRed) {
        this.valore = valore; 
        this.isRed = isRed; 
    }

    public int getValore() {
        return valore; 
    }

    public boolean isRed() {
        return isRed; 
    }

    public void danneggia() {
        if (valore > 0) {
            valore -= 1; 
            System.out.println("Cubo danneggiato. Nuovo valore: " + valore);
        } else {
            System.out.println("Il cubo è già a valore zero e non può essere danneggiato ulteriormente.");
        }
    }

    public void premia() {
        valore += 1; 
        System.out.println("Cubo premiato. Nuovo valore: " + valore);
    }
}