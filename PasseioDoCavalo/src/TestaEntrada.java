
public class TestaEntrada {	
	public boolean isDimensao(String s){
		return s.matches("[4-9]*");
	}
	
	public boolean isCoordenada(String s, int dimensao){
		String regex = "[1-"+Integer.toString(dimensao)+"]*";
		return s.matches(regex);
	}
}
