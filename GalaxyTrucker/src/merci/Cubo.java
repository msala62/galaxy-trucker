package merci;
import java.util.Objects;

public class Cubo {
    public enum ColoreCubo {
        ROSSO(4),
        GIALLO(3),
        VERDE(2),
        BLU(1);

        private final int valore;

        ColoreCubo(int valore) {
            this.valore = valore;
        }

        public int getValore() {
            return valore;
        }

        @Override
        public String toString() {
            return name().substring(0, 1).toUpperCase() + name().substring(1).toLowerCase();
        }
    }

    private final ColoreCubo colore;

    public Cubo(ColoreCubo colore) {
        this.colore = colore;
    }

    public ColoreCubo getColore() {
        return colore;
    }

    public int getValore() {
        return colore.getValore();
    }

    @Override
    public String toString() {
        return "Cubo " + colore;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;
        Cubo cubo = (Cubo) obj;
        return colore == cubo.colore;
    }

    public int hashCode() {
        return Objects.hash(colore);
    }
    

    public static void main(String[] args) {
    	
        //Creazione Cubi :
    	
        Cubo cuboRosso = new Cubo(ColoreCubo.ROSSO);
        Cubo cuboBlu = new Cubo(ColoreCubo.BLU);
        Cubo cuboGiallo = new Cubo(ColoreCubo.GIALLO);
        Cubo cuboVerde = new Cubo(ColoreCubo.VERDE);

       
        System.out.println(cuboRosso);
        System.out.println("Colore del cubo rosso: " + cuboRosso.getColore());
        System.out.println("Valore del cubo rosso: " + cuboRosso.getValore());

        System.out.println(cuboBlu);
        System.out.println("Colore del cubo blu: " + cuboBlu.getColore());
        System.out.println("Valore del cubo blu: " + cuboBlu.getValore());

        System.out.println(cuboGiallo);
        System.out.println("Colore del cubo giallo: " + cuboGiallo.getColore());
        System.out.println("Valore del cubo giallo: " + cuboGiallo.getValore());

        System.out.println(cuboVerde);
        System.out.println("Colore del cubo verde: " + cuboVerde.getColore());
        System.out.println("Valore del cubo verde: " + cuboVerde.getValore());
    }
}