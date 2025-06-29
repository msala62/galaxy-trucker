package nave;

public class Coordinata {
	private final int x;
	private final int y;
	
	public Coordinata(int x, int y) {
		this.x = x;
		this.y = y;
	}

	public int getX() {
		return x;
	}

	public int getY() {
		return y;
	}
	
	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append(x);
		builder.append(y);
		
		return builder.toString();
	}
}
