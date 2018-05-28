package Interface;

import java.awt.geom.*;
import java.awt.*;
import javax.swing.*;
import Peša.Peša;
import Jogo.Observado;
import Jogo.Observador;

public class XadrezPainel extends JPanel{

	private Rectangle2D Rect2D = new Rectangle2D.Double();
	private int larguraCasa, alturaCasa;
	private int larguraPeša, alturaPeša;
	private int posicaoX, posicaoY;
	private Color cor, casaVerde = new Color(41, 198, 47, 150);
	private int[][] casas;
	private Peša[][] pos;

	
	public XadrezPainel(int[][] c, Peša[][] posicao) {
		casas = c;
		pos = posicao;
		
	}
	
	public void paintComponent(Graphics g) {
		
		Graphics2D g2d = (Graphics2D) g;
		cor = Color.WHITE;
		larguraCasa = this.getWidth()/8;
		alturaCasa = this.getHeight()/8;
		larguraPeša = 11*larguraCasa/20;
		alturaPeša = 11*alturaCasa/20;
	
		
		for(int i=0; i<8; i++) {
			posicaoY = alturaCasa*i;
			for(int j=0; j<8; j++) {
				posicaoX = larguraCasa*j;
				g2d.setPaint(cor);
				Rect2D.setRect(posicaoX, posicaoY, larguraCasa, alturaCasa);					
				g2d.fill(Rect2D);
						
				
				if(casas[i][j]==1) {
					g2d.setPaint(casaVerde);
					Rect2D.setRect(posicaoX, posicaoY, larguraCasa, alturaCasa);
					g2d.fill(Rect2D);
				}
				
				if(pos[i][j]!=null) {
					g2d.drawImage(pos[i][j].getImg(), posicaoX+((larguraCasa-larguraPeša)/2), posicaoY+((alturaCasa-alturaPeša)/2), larguraPeša, alturaPeša, null);
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
