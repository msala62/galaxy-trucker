package carteAvventura;

import java.util.*;
import merci.*;

/* ogni carta di pianeti puo avere piu di una pianeta, ogni pianeta puo avere diversi tipi di merci*/

public class Pianeta {
	private List<Cargo> cargo;
	
	public Pianeta (List<Cargo> cargo) {
		this.cargo = cargo;
	}

	public List<Cargo> getPianeta() {
		return cargo;
	}
	
	@Override
	public String toString() {
		return cargo.toString();
		
	}
}
