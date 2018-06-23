package Interface;

import javax.swing.*;

import Jogo.Controlador;

import java.awt.*;
import Listeners.TratadorIniciaJogo;

public class Inicializador extends JFrame {
	
	private static Inicializador inicializador = null;
	JPanel painel;
	
	private Inicializador(String titulo) {
		
		super(titulo);
		
		Toolkit tk = Toolkit.getDefaultToolkit();
		Dimension ss = tk.getScreenSize();
		JButton bNovoJogo = new JButton("Novo Jogo");
		JButton bCarregarJogo = new JButton("Carregar um jogo salvo");
		JLabel	label = new JLabel("Jogo de Xadrez");
		Font fonte1 = new Font("SERIF",Font.BOLD, 40);
		Font fonte2 = new Font("SERIF",Font.BOLD, 30);
		TratadorIniciaJogo tratador = new TratadorIniciaJogo();
		
		this.getContentPane().setBackground(Color.gray);
		this.setLocation(ss.width/4, (2*ss.height-ss.width)/4);
		this.setSize(ss.width/2, ss.width/2);
		
		painel = new JPanel();
		painel.setLayout(null);
		
		painel.setBounds(0,0, this.getWidth(), this.getHeight());
		
		label.setBounds(350, 30, 300, 200);
		label.setFont(fonte1);
		bNovoJogo.setBounds(300, 250, 350, 150);
		bNovoJogo.setFont(fonte2);
		bCarregarJogo.setBounds(300, 500, 350, 150);
		bCarregarJogo.setFont(fonte2);
		
		bNovoJogo.addActionListener(tratador);
		bCarregarJogo.addActionListener(tratador);
		
		painel.add(bNovoJogo);
		painel.add(bCarregarJogo);
		painel.add(label);
		

		this.getContentPane().add(painel);
		
	}

	public static Inicializador getInicializador() {
		if(inicializador == null)
			inicializador = new Inicializador("Xadrez");
		return inicializador;
	}
	
}
