import java.util.ArrayList;
import java.util.List;


public class Tabuleiro {
	private List<Celula> celulas;
	private int dimensao;
	

	public Tabuleiro(int dimensao) {
		this.setDimensao(dimensao);
		ArrayList<Celula> lista = new ArrayList<Celula>();
		for (int x = 0; x < dimensao; x++) {
			for (int y = 0; y < dimensao; y++) {
				Celula c = new Celula();
				c.setX(x+1);				
				c.setY(y+1);				
				lista.add(c);
			}
			
		}
		this.celulas = lista;
	}

	
	
	public List<Celula> getCelulas() {
		return celulas;
	}

	public void setCelulas(List<Celula> celulas) {
		this.celulas = celulas;
	}

	public int getDimensao() {
		return dimensao;
	}

	public void setDimensao(int dimensao) {
		this.dimensao = dimensao;
	}
	
}
