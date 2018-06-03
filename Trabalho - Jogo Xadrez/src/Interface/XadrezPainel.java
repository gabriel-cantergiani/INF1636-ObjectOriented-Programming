package Interface;

import java.awt.geom.*;
import java.awt.*;
import javax.swing.*;
import Peça.Peça;
import Jogo.Controlador;
import Jogo.Observado;
import Jogo.Observador;

public class XadrezPainel extends JPanel implements Observador{

	private Rectangle2D Rect2D = new Rectangle2D.Double();
	private int larguraCasa, alturaCasa;
	private int larguraPeça, alturaPeça;
	private int posicaoX, posicaoY;
	private Color cor, casaVerde = new Color(41, 198, 47, 150);
	private int[][] casas;
	private Peça[][] pos;
	private Observado observado;

	
	public XadrezPainel() {
		
		Controlador.getControlador().registra(this);
		observado = Controlador.getObservado();
		
		casas = observado.getCasas();
		pos = observado.getPeças();
		
	}
	
	public void paintComponent(Graphics g) {
		
		Graphics2D g2d = (Graphics2D) g;
		cor = Color.WHITE;
		larguraCasa = this.getWidth()/8;
		alturaCasa = this.getHeight()/8;
		larguraPeça = 11*larguraCasa/20;
		alturaPeça = 11*alturaCasa/20;
	
		
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
					g2d.drawImage(pos[i][j].getImg(), posicaoX+((larguraCasa-larguraPeça)/2), posicaoY+((alturaCasa-alturaPeça)/2), larguraPeça, alturaPeça, null);
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

	public void notify(Observado o){
		repaint();
	}
	
}
