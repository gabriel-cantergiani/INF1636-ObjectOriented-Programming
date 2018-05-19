package Listeners;

import java.awt.*;
import java.awt.event.*;

import Jogo.Xadrez;

public class TratadorClique extends MouseAdapter implements MouseListener{
	
	Component comp;
	Xadrez jogo;
	
	public TratadorClique(Xadrez x, Component c) {
		comp = c;
		jogo = x;
	}
	
	public void mouseClicked(MouseEvent e) {
		
		int x = e.getX();
		int y = e.getY();
		int alturaTot, alturaCasa, i, j;
		int larguraTot, larguraCasa;
		
		if(comp.contains(x, y)) {
		
			// obtem o i
			
				alturaTot = comp.getHeight();
				alturaCasa = alturaTot/8;
				
				i = y/alturaCasa;
			
			// obtem o j
				
				larguraTot = comp.getWidth();
				larguraCasa = larguraTot/8;
				
				j = x/larguraCasa;
			
			
			System.out.println("Clique na posicao [i][j] = ["+i+"]["+j+"]");
			
		}
		
	}

}