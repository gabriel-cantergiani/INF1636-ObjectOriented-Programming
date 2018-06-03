package Jogo;

public class Controlador {
	
	private static Controlador controlador = null;
	private static Observado observado = null;
	private static Observador observador = null;
	
	private Controlador() {
		observado = Tabuleiro.getTabuleiro();
	}
	
	public static Controlador getControlador() {
		if(controlador == null)
			controlador = new Controlador();
		return controlador;
	}

	public void registra(Observador o) {
		observador = o;
		observado.add(observador);
	}
	
	public static Observado getObservado() {
		return observado;
	}
	
	public void Recebe_Indices(int i, int j) {
		Tabuleiro.getTabuleiro().Recebe_Clique(i, j);
	}
	
}
