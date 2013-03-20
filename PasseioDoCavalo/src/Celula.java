public class Celula {
	private int x;
	private int y;
	private boolean passado = false;

	public int getX() {
		return x;
	}

	public void setX(int x) {
		this.x = x;
	}

	public int getY() {
		return y;
	}

	public void setY(int y) {
		this.y = y;
	}

	public boolean isPassado() {
		return passado;
	}

	public void setPassado(boolean passado) {
		this.passado = passado;
	}

	@Override
	public String toString() {
		String s = ("[X:" + x + ", Y:" + y + "]");
		return s;
	}

}
