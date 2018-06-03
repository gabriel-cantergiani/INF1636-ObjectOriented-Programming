import Interface.XadrezFrame;
import Jogo.*;
import Listeners.*;

public class Main {
	
	public static void main(String args[]) {
		
		Controlador controlador = Controlador.getControlador();
		XadrezFrame xadrez = new XadrezFrame("Xadrez");
		xadrez.addMouseListener(new TratadorClique(controlador, xadrez));
			
		xadrez.setVisible(true);
		
	}
	
	
}
