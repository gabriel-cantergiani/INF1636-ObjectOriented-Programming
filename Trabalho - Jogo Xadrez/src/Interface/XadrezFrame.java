package Interface;

import java.awt.*;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Toolkit;
import javax.swing.*;


public class XadrezFrame extends JFrame{
	
	XadrezPainel painel;
	
	public XadrezFrame(String titulo) {
		
		super(titulo);
		
		Toolkit tk = Toolkit.getDefaultToolkit();
		Dimension ss = tk.getScreenSize();
			
		painel = new XadrezPainel();
		
		this.getContentPane().setBackground(Color.gray);
		this.setLocation(ss.width/4, (2*ss.height-ss.width)/4);
		this.setSize(ss.width/2, ss.width/2);
		this.getContentPane().add(painel);
		
	}
	
	
}
