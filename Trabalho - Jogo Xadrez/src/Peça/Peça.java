package Peça;

import java.awt.*;
import java.io.*;
import javax.imageio.*;

public abstract class Peça{
	
	protected Image img;
	protected int cor;	// define cor da peça -> 0 para peças brancas, 1 para peças pretas
	
	public Peça() {
		
	}
	
	public int getCor() { return this.cor;}
	
	public Image getImg() {	return this.img;}
	
}
