import java.util.ArrayList;

public class Cavalo {

	private int x;
	private int y;
	// Array X e Y ultilizados todos os movimentos do cavalo em um tabuleiro de
	// xadrez
	private int[] movX = { 2, 1, -1, -2, -2, -1, 1, 2 };
	private int[] movY = { 1, 2, 2, 1, -1, -2, -2, -1 };
	private Long totalDeMovimentos = (long) 0;
	// �ndice ultilizado para tecnica de backtracking
	private ArrayList<Integer> indicesPercorridos = new ArrayList<Integer>();
	private int maiorProfundidade;
	private boolean nuncaRetrocedeu = true;

	public Cavalo(int x, int y) {
		this.x = x;
		this.y = y;
	}

	// IN�CIO dos Getters and Setters
	public ArrayList<Integer> getIndicesPercorridos() {
		return indicesPercorridos;
	}

	public void setIndicesPercorridos(ArrayList<Integer> indicesPercorridos) {
		this.indicesPercorridos = indicesPercorridos;
	}

	public int getMaiorProfundidade() {
		return maiorProfundidade;
	}

	public void setMaiorProfundidade(int maiorProfundidade) {
		this.maiorProfundidade = maiorProfundidade;
	}

	public boolean nuncaRetrocedeu() {
		return nuncaRetrocedeu;
	}

	public void setnuncaRetrocedeu(boolean nuncaRetrocedeu) {
		this.nuncaRetrocedeu = nuncaRetrocedeu;
	}

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

	public Long getTotalDeMovimentos() {
		return totalDeMovimentos;
	}

	public void setTotalDeMovimentos(Long totalDeMovimentos) {
		this.totalDeMovimentos = totalDeMovimentos;
	}

	// FIM dos Getters and Setters

	// Faz a convers�o de posi��o de um sistema [X,Y] para indices de uma lista;
	public int convertePosicao(int i, int j, Tabuleiro t) {
		int dimensao = t.getDimensao();
		int novaPos = (i - 1) * dimensao - 1 + j;
		return novaPos;
	}

	// Verifica se a movimenta��o � possivel, e se a posi��o para a qual o
	// cavalo vai ja foi passada antes
	public boolean movimentoValido(int x, int y, Tabuleiro t) {
		int dimensao = t.getDimensao();
		if (x > dimensao || x < 1 || y > dimensao || y < 1) {
			return false;
		}
		if (t.getCelulas().get(this.convertePosicao(x, y, t)).isPassado()) {
			return false;

		}
		return true;
	}

	// Marca no tabuleiro que o cavalo ja passou por esta posi��o
	public void realizaMovimento(Tabuleiro t) {
		this.totalDeMovimentos++;
		Integer pos = new Integer(this.convertePosicao(x, y, t));
		this.indicesPercorridos.add(pos);
		t.getCelulas().get(pos).setPassado(true);
	}

	// Retorna um movimento, e volta a tentar o passeio do cavalo a partir dele
	// com os outros movimentos v�lidos
	public void voltaMovimento(Tabuleiro t) {
		// Condi��o que define a maior profundidade alcan�ada sem retroceder
		// nenhuma casa
		if (nuncaRetrocedeu) {
			maiorProfundidade = indicesPercorridos.size();
			this.setnuncaRetrocedeu(false);
		}
		// Posi��o da Celula no ArrayList celulas no tabuleiro;
		int pos = this.convertePosicao(x, y, t);
		t.getCelulas().get(pos).setPassado(false);

		// Condi��o que testa se possui um �ndice para retroceder nos �ndices
		// percorridos, caso n�o exista, quer dizer que ele ja tentou voltar a
		// uma posi��o anterior a inicial, indicando que n�o possui passeio do
		// cavalo a partir da posi��o inicial informada pelo usu�rio
		if (indicesPercorridos.size() < 2) {
			throw new NaoPossuiPasseio();
		} else {

			int indiceAnterior = indicesPercorridos.get(indicesPercorridos
					.size() - 2);

			this.x = t.getCelulas().get(indiceAnterior).getX();
			this.y = t.getCelulas().get(indiceAnterior).getY();
			System.out.println("Movimento cancelado em:"
					+ t.getCelulas().get(pos));
			indicesPercorridos.remove(indicesPercorridos.get(indicesPercorridos
					.size() - 1));
			System.out.println(this.toString());
		}
	}

	// M�todo principal que inicia o passeio do cavalo
	public void passeioDoCavalo(Tabuleiro t) {
		// Realiza o movimento
		this.realizaMovimento(t);
		System.out.println("Movimento realizado em:"
				+ t.getCelulas().get(
						indicesPercorridos.get(indicesPercorridos.size() - 1)));

		// Mostra os movimentos poss�veis para aquela jogada
		for (int i = 0; i < 8; i++) {
			int u = this.x + this.movX[i];
			int v = this.y + this.movY[i];
			if (movimentoValido(u, v, t)) {
				System.out.println("Movimento poss�vel em [X:" + u + ",Y:" + v
						+ "]");
			}
		}

		int i = 0;
		int totalDeCasas = t.getDimensao() * t.getDimensao();

		// Loop para conseguir o realizar o passeio do cavalo baseado no tamanho
		// do tabuleiro, e as jogadas permitidas pelo cavalo
		while (indicesPercorridos.size() < totalDeCasas && i < 8) {
			int u = this.x + this.movX[i];
			int v = this.y + this.movY[i];
			if (movimentoValido(u, v, t)) {
				this.x = u;
				this.y = v;
				passeioDoCavalo(t);
			}

			i++;
			// Condi��o que realiza o backtracking
			if (i > 7 && indicesPercorridos.size() < totalDeCasas) {
				this.voltaMovimento(t);
				break;
			}
		}

	}

	// M�todo que gera uma String pra exibi��o do resultado
	public String imprimePasseio(Tabuleiro t) {
		int pos = 0;
		String s = "Movimentos: \n";
		while (pos < indicesPercorridos.size()) {
			s += Integer.toString(pos + 1) + "�"
					+ t.getCelulas().get(indicesPercorridos.get(pos)) + "  ";
			// System.out.print(pos+1+"�"+t.getCelulas().get(indicesPercorridos.get(pos))+"  ");

			// Condi��es para uma melhor exibi��o da mensagem
			int exib = (pos + 1) % t.getDimensao();
			if (exib == 0) {
				s += "\n";
			}

			pos++;

		}
		s += "\nMaior profundidade alcan�ada sem retroceder nenhuma casa: "
				+ Integer.toString(this.getMaiorProfundidade());

		return s;
	}

	public String toString() {
		String s = ("Posi��o atual do cavalo[X:" + x + ",Y:" + y + "]");
		return s;
	}

}
