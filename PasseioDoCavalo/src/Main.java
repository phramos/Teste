import javax.swing.JOptionPane;

public class Main {

	public static void main(String[] args) {

		// Instanciação da classe que força o usuário a inserir dados válidos
		TestaEntrada testa = new TestaEntrada();
		String reader = " ";

		// loop para garantir que o usuário informe um número inteiro > 4
		while (!testa.isDimensao(reader)) {
			reader = JOptionPane.showInputDialog(
					"Informe a dimensão do tabuleiro", "8");

			// mensagem de erro caso entrada seja inválidada
			if (!testa.isDimensao(reader)) {
				JOptionPane.showMessageDialog(null,
						"Apenas valores númericos maiores que 4", "OK",
						JOptionPane.WARNING_MESSAGE);
			}

		}
		int dimensao = Integer.parseInt(reader);

		String readerX = " ";
		String readerY = " ";
		// loop que garante que o usuário insira uma posição inicial válida
		// dentro da dimensao informada
		while (!testa.isCoordenada(readerX, dimensao)
				|| !testa.isCoordenada(readerY, dimensao)) {
			readerX = JOptionPane.showInputDialog(
					"Informe um valor para posição inicial X: ", "1");
			readerY = JOptionPane.showInputDialog(
					"Informe um valor para posição inicial Y: ", "1");

			// mensagem de erro caso entrada seja inválidada
			if (!testa.isCoordenada(readerX, dimensao)
					|| !testa.isCoordenada(readerY, dimensao)) {
				JOptionPane.showMessageDialog(null,
						"Infome coordendas válidas dentro de um tabuleiro "
								+ dimensao + "x" + dimensao, "OK",
						JOptionPane.WARNING_MESSAGE);
			}
		}
		int x = Integer.parseInt(readerX);
		int y = Integer.parseInt(readerY);

		Tabuleiro t = new Tabuleiro(dimensao);
		Cavalo c = new Cavalo(x, y);
		try {
			// Método que inicia o passeio
			c.passeioDoCavalo(t);
			String resultado = c.imprimePasseio(t);
			// Exibição do resultado para o usuário
			JOptionPane.showMessageDialog(null, resultado, "Resultado",
					JOptionPane.INFORMATION_MESSAGE);
		} catch (NaoPossuiPasseio e) {
			String resultado = "Não existe passeio do cavalo possível em um tabuleiro "
					+ dimensao
					+ "x"
					+ dimensao
					+ " iniciando em [X:"
					+ x
					+ ", Y:" + y + "]";
			JOptionPane.showMessageDialog(null, resultado, "Resultado",
					JOptionPane.INFORMATION_MESSAGE);
		}

	}
}
