
import javax.swing.*;
import java.awt.*;

public class XadrezFrame extends JFrame {

	public XadrezFrame(String titulo) {
		
		super(titulo);
		Toolkit tk = Toolkit.getDefaultToolkit();
		Dimension ss = tk.getScreenSize();
		
		Tabuleiro tabuleiro = new Tabuleiro();
		
		this.getContentPane().setBackground(Color.gray);
		this.setLocation(ss.width/4, (2*ss.height-ss.width)/4);
		this.setSize(ss.width/2, ss.width/2);
		
		this.getContentPane().add(tabuleiro);
		
	}
	
}
