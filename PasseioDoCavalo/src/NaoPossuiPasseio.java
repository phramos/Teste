//Exceção lançada caso não exista passeio no tabuleiro com a posição inicial informada
public class NaoPossuiPasseio extends RuntimeException {
	public NaoPossuiPasseio(){
		super("Não possui passeio do cavalo com as coordenadas iniciais informadas");
	}
}
