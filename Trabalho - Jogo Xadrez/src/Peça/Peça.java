package Peça;

import java.awt.*;
import java.io.*;
import javax.imageio.*;

public class Peça extends Component{
	
	private tipoPeça tipo;
	private Image img;
	private int posicaoX;
	private int posicaoY;
	private int cor;	// define cor da peça -> 0 para peças brancas, 1 para peças pretas
	
	public Peça() {
		
	}
	
	public void setCor(int x) { this.cor = x;}
	
	public int getCor() { return this.cor;}
	
	public void setPosicao(int x, int y) {
		this.posicaoX = x;
		this.posicaoY = y;
	}
	
	public int getPosicaoX() { return this.posicaoX;}
	
	public int getPosicaoY() { return this.posicaoY;}
	
	public void setTipo(tipoPeça t) { this.tipo = t;}
	
	public tipoPeça getTipo () { return this.tipo;}
	
	public void setImg(String enderecoImg) {
		
		try {
			this.img = ImageIO.read(new File(enderecoImg));
		}
		catch (Exception e){
			System.out.println(e.getMessage());
			System.exit(1);
		}
		
	}
	
	public Image getImg() {	return this.img;}
	
	
	public void movimento (int origemI, int origemJ, int destinoI, int destinoJ) {
			
	}

	
	
}
