package Listeners;

import java.awt.*;
import java.awt.event.*;

public class Peça_Selecionada extends MouseAdapter implements MouseListener{
	
	Component comp;
	
	public Peça_Selecionada(Component c) {
		comp = c;
	}
	
	public void mouseClicked(MouseEvent e) {
		
		System.out.println("Clicou em uma peça");
	}

}
