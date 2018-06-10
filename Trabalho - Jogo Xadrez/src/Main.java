import Interface.XadrezFrame;
import Jogo.Controlador;
import Listeners.TratadorClique;

public class Main {
	
	public static void main(String args[]) {
		
		Controlador controlador = Controlador.getControlador();
		XadrezFrame xadrez = new XadrezFrame("Xadrez");
		xadrez.addMouseListener(new TratadorClique(xadrez));
			
		xadrez.setVisible(true);
		
	}
	
	
}
