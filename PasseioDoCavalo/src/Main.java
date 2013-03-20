import javax.swing.JOptionPane;

public class Main {

	public static void main(String[] args) {

		// Instancia��o da classe que for�a o usu�rio a inserir dados v�lidos
		TestaEntrada testa = new TestaEntrada();
		String reader = " ";

		// loop para garantir que o usu�rio informe um n�mero inteiro > 4
		while (!testa.isDimensao(reader)) {
			reader = JOptionPane.showInputDialog(
					"Informe a dimens�o do tabuleiro", "8");

			// mensagem de erro caso entrada seja inv�lidada
			if (!testa.isDimensao(reader)) {
				JOptionPane.showMessageDialog(null,
						"Apenas valores n�mericos maiores que 4", "OK",
						JOptionPane.WARNING_MESSAGE);
			}

		}
		int dimensao = Integer.parseInt(reader);

		String readerX = " ";
		String readerY = " ";
		// loop que garante que o usu�rio insira uma posi��o inicial v�lida
		// dentro da dimensao informada
		while (!testa.isCoordenada(readerX, dimensao)
				|| !testa.isCoordenada(readerY, dimensao)) {
			readerX = JOptionPane.showInputDialog(
					"Informe um valor para posi��o inicial X: ", "1");
			readerY = JOptionPane.showInputDialog(
					"Informe um valor para posi��o inicial Y: ", "1");

			// mensagem de erro caso entrada seja inv�lidada
			if (!testa.isCoordenada(readerX, dimensao)
					|| !testa.isCoordenada(readerY, dimensao)) {
				JOptionPane.showMessageDialog(null,
						"Infome coordendas v�lidas dentro de um tabuleiro "
								+ dimensao + "x" + dimensao, "OK",
						JOptionPane.WARNING_MESSAGE);
			}
		}
		int x = Integer.parseInt(readerX);
		int y = Integer.parseInt(readerY);

		Tabuleiro t = new Tabuleiro(dimensao);
		Cavalo c = new Cavalo(x, y);
		try {
			// M�todo que inicia o passeio
			c.passeioDoCavalo(t);
			String resultado = c.imprimePasseio(t);
			// Exibi��o do resultado para o usu�rio
			JOptionPane.showMessageDialog(null, resultado, "Resultado",
					JOptionPane.INFORMATION_MESSAGE);
		} catch (NaoPossuiPasseio e) {
			String resultado = "N�o existe passeio do cavalo poss�vel em um tabuleiro "
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
