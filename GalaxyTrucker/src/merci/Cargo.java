package merci;
import java.util.Objects;

public class Cargo {
    public enum ColoreCargo {
        ROSSO(4),
        GIALLO(3),
        VERDE(2),
        BLU(1);

        private final int valore;   //Attributo

        ColoreCargo(int valore) {     // Costruttore
            this.valore = valore;
        }

        public int getValore() {     //Acessori
            return valore;
        }

        // Rappresentaione visuale
        @Override
        public String toString() {
            return name().substring(0, 1).toUpperCase() + name().substring(1).toLowerCase();
        }
    }

    private final ColoreCargo colore;       //Attributo

    public Cargo(ColoreCargo colore) {      //costruttore
        this.colore = colore;
    }

    public ColoreCargo getColore() {        // Acessori
        return colore;
    }

    public int getValore() {            //Acessori per valore e colore
        return colore.getValore();
    }

    @Override
    public String toString() {           // Rappresentazione Visuale
        return "Cargo " + colore;
    }
    
    //Per assicurarsi che l'uguaglianza dei cubi dipende dal colore, 
    //non solo se hanno esattamente gli stessi oggetti in memoria
    @Override    
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;
        Cargo cargo = (Cargo) obj;
        return colore == cargo.colore;
    }
    
    // Per fornire un numero di identificazione consistente (hashCode) per gli oggetti
    // che sono considerati uguali. Pertanto, funzionano correttamente con JAVA Collection.
    public int hashCode() {
        return Objects.hash(colore);
    }
    

    public static void main(String[] args) {
    	
        //Creazione Cubi :
    	
        Cargo CargoRosso = new Cargo(ColoreCargo.ROSSO);
        Cargo CargoBlu = new Cargo(ColoreCargo.BLU);
        Cargo CargoGiallo = new Cargo(ColoreCargo.GIALLO);
        Cargo CargoVerde = new Cargo(ColoreCargo.VERDE);

       
        System.out.println(CargoRosso);
        System.out.println("Colore del cargo rosso: " + CargoRosso.getColore());
        System.out.println("Valore del cargo rosso: " + CargoRosso.getValore());

        System.out.println(CargoBlu);
        System.out.println("Colore del cargo blu: " + CargoBlu.getColore());
        System.out.println("Valore del cargo blu: " + CargoBlu.getValore());

        System.out.println(CargoGiallo);
        System.out.println("Colore del cargo giallo: " + CargoGiallo.getColore());
        System.out.println("Valore del cargo giallo: " + CargoGiallo.getValore());

        System.out.println(CargoVerde);
        System.out.println("Colore del cargo verde: " + CargoVerde.getColore());
        System.out.println("Valore del cargo verde: " + CargoVerde.getValore());
    }
}