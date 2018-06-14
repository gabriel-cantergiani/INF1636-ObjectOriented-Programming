package Interface;

import java.awt.geom.*;
import java.awt.*;
import javax.swing.*;
import Peça.Peça;
import Jogo.Controlador;
import Jogo.Observado;
import Jogo.Observador;
import Listeners.*;

public class XadrezPainel extends JPanel implements Observador{

	private Rectangle2D Rect2D = new Rectangle2D.Double();
	private int larguraCasa, alturaCasa;
	private int larguraPeça, alturaPeça;
	private int posicaoX, posicaoY;
	private Color cor, casaVerde = new Color(41, 198, 47, 150);
	private int[][] casas;
	private Peça[][] pos;
	private Observado observado;
	private TratadorPromocao tratador;
	
	public XadrezPainel() {
		
		Controlador.getControlador().registra(this);
		observado = Controlador.getObservado();
	
		casas = observado.getCasas();
		pos = observado.getPeças();
		
		tratador = new TratadorPromocao();
		
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

	public void notify(Observado o, int i){
		
		casas = observado.getCasas();
		pos = observado.getPeças();
		
		if(i==1)
			repaint();
		else if(i==2)
			mostraMenuSelecao();
	}
	
	public void mostraMenuSelecao() {
		
		JPopupMenu menu = new JPopupMenu("Promoção de Peão");
		double larguraItem = this.getWidth()/(1.6);
		double alturaItem = this.getHeight()/(11.7);
		
		Font fonte = new Font("SERIF",Font.BOLD, 25);
		JMenuItem label = new JMenuItem("Seleciona uma peça para promover o Peão:");
		JRadioButtonMenuItem botTorre = new JRadioButtonMenuItem("Torre");
		JRadioButtonMenuItem botCavalo = new JRadioButtonMenuItem("Cavalo");
		JRadioButtonMenuItem botBispo = new JRadioButtonMenuItem("Bispo");
		JRadioButtonMenuItem botRainha = new JRadioButtonMenuItem("Rainha");
		
		label.setPreferredSize(new Dimension((int)larguraItem,(int)alturaItem));
		botTorre.setPreferredSize(new Dimension((int)larguraItem,(int)alturaItem));
		botCavalo.setPreferredSize(new Dimension((int)larguraItem,(int)alturaItem));
		botBispo.setPreferredSize(new Dimension((int)larguraItem,(int)alturaItem));
		botRainha.setPreferredSize(new Dimension((int)larguraItem,(int)alturaItem));
		label.setFont(fonte);
		botTorre.setFont(fonte);
		botCavalo.setFont(fonte);
		botBispo.setFont(fonte);
		botRainha.setFont(fonte);
		
		
		botTorre.addActionListener(tratador);
		botCavalo.addActionListener(tratador);
		botBispo.addActionListener(tratador);
		botRainha.addActionListener(tratador);
		
		menu.add(label);
		menu.add(botTorre);
		menu.add(botCavalo);
		menu.add(botBispo);
		menu.add(botRainha);
		menu.show(this, (int)(this.getWidth()-larguraItem)/2, (int)(this.getHeight()-5*alturaItem)/2);
	}
}
