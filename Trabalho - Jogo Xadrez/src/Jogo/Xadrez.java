package Jogo;

import javax.swing.*;

import Listeners.TratadorClique;
import Peça.Peça;

import java.awt.*;
import java.awt.geom.Rectangle2D;

public class Xadrez extends JFrame {

	private Rectangle2D[][] casas = new Rectangle2D[8][8];
	private Peça[][] posicoes = new Peça[8][8];
	
	public Xadrez(String titulo) {
		
		super(titulo);
		Toolkit tk = Toolkit.getDefaultToolkit();
		Dimension ss = tk.getScreenSize();
		
		criaCasas();
		criaPeças();
		
		Tabuleiro tabuleiro = new Tabuleiro(posicoes, casas);
		
		tabuleiro.addMouseListener(new TratadorClique(this, tabuleiro));
		this.getContentPane().setBackground(Color.gray);
		this.setLocation(ss.width/4, (2*ss.height-ss.width)/4);
		this.setSize(ss.width/2, ss.width/2);
		
		this.getContentPane().add(tabuleiro);
		
	}
	
	private void criaCasas() {
		
		for(int i=0; i<8; i++) {
			casas[i] = new Rectangle2D[8];
			for(int j=0; j<8; j++)
				casas[i][j] = new Rectangle2D.Double();
		}

	}
	
	private void criaPeças() {

		for(int i=0; i<8; i++) {
			posicoes[i] = new Peça[8];
			for(int j=0; j<8; j++)
				if(i>=2 && i<=5)
					posicoes[i][j] = null;
				else
					posicoes[i][j] = new Peça();
		}
		
	}

	
}
