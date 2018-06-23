package Listeners;

import java.awt.event.*;
import Jogo.Controlador;

public class TratadorIniciaJogo implements ActionListener{
	
	Controlador cont;
	
	public TratadorIniciaJogo() {
		cont = Controlador.getControlador();
	}
	
	public void actionPerformed(ActionEvent e) {
		
		String str = e.getActionCommand();
		
		if(str.equals("Novo Jogo"))
			cont.novoJogo();
		else
			cont.carregarJogo();
		
	}
	

}
