package Interface;

import java.awt.*;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Toolkit;
import javax.swing.*;

import Jogo.Observado;
import Jogo.Observador;
import Peça.Peça;

public class XadrezFrame extends JFrame  implements Observador{
	
	XadrezPainel painel;
	
	public XadrezFrame(String titulo, int[][] c, Peça[][] pos) {
		
		super(titulo);
		
		Toolkit tk = Toolkit.getDefaultToolkit();
		Dimension ss = tk.getScreenSize();
		
		painel = new XadrezPainel(c, pos);
		
		this.getContentPane().setBackground(Color.gray);
		this.setLocation(ss.width/4, (2*ss.height-ss.width)/4);
		this.setSize(ss.width/2, ss.width/2);
		this.getContentPane().add(painel);
	}
	

	public void notify(Observado o){
		painel.repaint();
	}
	
}
