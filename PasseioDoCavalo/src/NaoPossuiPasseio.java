//Exce��o lan�ada caso n�o exista passeio no tabuleiro com a posi��o inicial informada
public class NaoPossuiPasseio extends RuntimeException {
	public NaoPossuiPasseio(){
		super("N�o possui passeio do cavalo com as coordenadas iniciais informadas");
	}
}
