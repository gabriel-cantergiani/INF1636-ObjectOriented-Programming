package Jogo;

import javax.swing.*;
import java.awt.*;
import java.awt.geom.*;
import java.awt.event.*;
import java.awt.color.*;
import Peça.*;
import Listeners.*;

public class Tabuleiro extends JComponent {

	private Rectangle2D Rect2D = new Rectangle2D.Double();
	private Peça[][] posicoes;
	private int[][] casas;
	
	private int larguraCasa, alturaCasa;
	private int larguraPeça, alturaPeça;
	private int posicaoX, posicaoY;
	
	public Tabuleiro(Peça[][] p, int[][] c) {
		
		posicoes = p;
		casas = c;
		
		for(int i=0; i<8; i++) {
			for(int j=0; j<8; j++) {
				switch(i){
					case 0:{
						if(j==0 || j==7) {
							posicoes[i][j].setTipo(tipoPeça.Torre);
							//posicoes[i][j].setImg("imagens/Pecas_1/p_torre.gif");
							posicoes[i][j].setImg("imagens/Pecas_2/PurpleR.png");
						}
						else if(j==1 || j==6) {
							posicoes[i][j].setTipo(tipoPeça.Cavalo);
							//posicoes[i][j].setImg("imagens/Pecas_1/p_cavalo.gif");
							posicoes[i][j].setImg("imagens/Pecas_2/PurpleN.png");
						}
						else if(j==2 || j==5) {
							posicoes[i][j].setTipo(tipoPeça.Bispo);
							//posicoes[i][j].setImg("imagens/Pecas_1/p_bispo.gif");
							posicoes[i][j].setImg("imagens/Pecas_2/PurpleB.png");
						}
						else if(j==3) {
							posicoes[i][j].setTipo(tipoPeça.Rainha);
							//posicoes[i][j].setImg("imagens/Pecas_1/p_dama.gif");
							posicoes[i][j].setImg("imagens/Pecas_2/PurpleQ.png");
						}
						else {
							posicoes[i][j].setTipo(tipoPeça.Rei);
							//posicoes[i][j].setImg("imagens/Pecas_1/p_rei.gif");
							posicoes[i][j].setImg("imagens/Pecas_2/PurpleK.png");
						}
						posicoes[i][j].setCor(1);
						break;
					}
					case 1:{
						posicoes[i][j].setTipo(tipoPeça.Peao);
						//posicoes[i][j].setImg("imagens/Pecas_1/p_peao.gif");
						posicoes[i][j].setImg("imagens/Pecas_2/PurpleP.png");
						posicoes[i][j].setCor(1);
						break;
					}
					case 6:{
						posicoes[i][j].setTipo(tipoPeça.Peao);
						//posicoes[i][j].setImg("imagens/Pecas_1/b_peao.gif");
						posicoes[i][j].setImg("imagens/Pecas_2/CyanP.png");
						posicoes[i][j].setCor(0);
						break;
					}
					case 7:{
						if(j==0 || j==7) {
							posicoes[i][j].setTipo(tipoPeça.Torre);
							//posicoes[i][j].setImg("imagens/Pecas_1/b_torre.gif");
							posicoes[i][j].setImg("imagens/Pecas_2/CyanR.png");
						}
						else if(j==1 || j==6) {
							posicoes[i][j].setTipo(tipoPeça.Cavalo);
							//posicoes[i][j].setImg("imagens/Pecas_1/b_cavalo.gif");
							posicoes[i][j].setImg("imagens/Pecas_2/CyanN.png");
						}
						else if(j==2 || j==5) {
							posicoes[i][j].setTipo(tipoPeça.Bispo);
							//posicoes[i][j].setImg("imagens/Pecas_1/b_bispo.gif");
							posicoes[i][j].setImg("imagens/Pecas_2/CyanB.png");
						}
						else if(j==3) {
							posicoes[i][j].setTipo(tipoPeça.Rainha);
							//posicoes[i][j].setImg("imagens/Pecas_1/b_dama.gif");
							posicoes[i][j].setImg("imagens/Pecas_2/CyanQ.png");
						}
						else {
							posicoes[i][j].setTipo(tipoPeça.Rei);
							//posicoes[i][j].setImg("imagens/Pecas_1/b_rei.gif");
							posicoes[i][j].setImg("imagens/Pecas_2/CyanK.png");
						}
						posicoes[i][j].setCor(0);
						break;
					}
				
				}
					
			}
		
		}
		
		// end of loop

	}
	
	public void paintComponent(Graphics g) {
		
		super.paintComponent(g);
		
		Graphics2D g2d = (Graphics2D) g;
		Color cor = Color.WHITE;
		larguraCasa = this.getWidth()/8;
		alturaCasa = this.getHeight()/8;
		larguraPeça = 11*larguraCasa/20;
		alturaPeça = 11*alturaCasa/20;
		Color casaVerde = new Color(41, 198, 47, 150);
		
		for(int i=0; i<8; i++) {
			posicaoY = alturaCasa*i;
			for(int j=0; j<8; j++) {
				posicaoX = larguraCasa*j;
				g2d.setPaint(cor);
				Rect2D.setRect(posicaoX, posicaoY, larguraCasa, alturaCasa);					
				g2d.fill(Rect2D);
				
				if(casas[i][j]==1) {
					//g2d.setPaint(casaVerde);
					//Rect2D.setRect(posicaoX, posicaoY, larguraCasa, alturaCasa);
					g2d.setPaint(Color.green);
					Rect2D.setRect(posicaoX+((larguraCasa)/10), posicaoY+((alturaCasa)/10), 4*larguraCasa/5, 4*alturaCasa/5);
					g2d.fill(Rect2D);
				}
				
				
				if(posicoes[i][j]!=null) {
					g2d.drawImage(posicoes[i][j].getImg(), posicaoX+((larguraCasa-larguraPeça)/2), posicaoY+((alturaCasa-alturaPeça)/2), larguraPeça, alturaPeça, null);
				}
				
				if(cor == Color.WHITE)
					cor = Color.BLACK;
				else
					cor = Color.WHITE;
			}
			if(cor == Color.WHITE)
				cor = Color.BLACK;
			else
				cor = Color.WHITE;
			
		}
		
		
	}
	
}
