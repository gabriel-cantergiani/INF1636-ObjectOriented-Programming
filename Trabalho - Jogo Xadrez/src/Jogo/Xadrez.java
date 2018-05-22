package Jogo;

import javax.swing.*;

import Listeners.TratadorClique;
import Peça.Peça;
import Peça.tipoPeça;

import java.awt.*;
import java.awt.geom.Rectangle2D;

public class Xadrez extends JFrame {

	public Tabuleiro tabuleiro;
	private int[][] casas = new int[8][8];
	private Peça[][] posicoes = new Peça[8][8];
	private int selecao = 0; // selecao = 0 -> nada selecionado,  selecao = 1 -> peça selecionada
	private Peça peça;
	private int iOrigem, jOrigem;	// indices de origem da peça a ser movimentada
	
	
	public Xadrez(String titulo) {
		
		super(titulo);
		Toolkit tk = Toolkit.getDefaultToolkit();
		Dimension ss = tk.getScreenSize();
		
		criaPeças();
		zeraCasas();
		
		tabuleiro = new Tabuleiro(posicoes, casas);
		
		tabuleiro.addMouseListener(new TratadorClique(this, tabuleiro));
		this.getContentPane().setBackground(Color.gray);
		this.setLocation(ss.width/4, (2*ss.height-ss.width)/4);
		this.setSize(ss.width/2, ss.width/2);
		
		this.getContentPane().add(tabuleiro);
		
		
	}

	private void zeraCasas () {
		for (int i = 0; i < 8; i++) {
			for (int j = 0; j< 8; j++) {
				this.casas[i][j] = 0;
			}
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
	
	public void Recebe_Clique(int i, int j) {
		
		if(posicoes[i][j] != null)		// peça foi selecionada para começar uma jogada
			Peça_Selecionada(i,j);
		else if(posicoes[i][j] == null && selecao==1)	// casa vazia foi selecionada após a seleção de uma peça
			Casa_Selecionada(i,j);
		else											// Nenhum dos casos acima, ignora o clique
			return;	
			
	}
	
	public void Peça_Selecionada(int i, int j) {
		
		if(selecao == 0 || peça.getCor() == posicoes[i][j].getCor()) { // primeira seleção ou outra peça da mesma cor foi selecionada -> Reinicia a jogada com a nova peça
			iOrigem = i;
			jOrigem = j;
			peça = posicoes[i][j];
			selecao = 1;
			novaMovimentacao(i,j);
		}
		else {								// Peça da outra cor foi selecionada. Verifica se o movimento é viável...
			if(posicoes[i][j] == peça)	// peça clicada é a mesma anterior  ->aborta
				return;
			return;	// falta implementar captura de outra peça
		}
		
	}

	public void Casa_Selecionada(int i, int j) {
		
		if(casas[i][j] == 1) {	// casa é válida -> efetua a movimentação
			posicoes[iOrigem][jOrigem] = null;
			posicoes[i][j] = peça;
			tabuleiro.repaint();
		}
		zeraCasas();
		selecao = 0;
	}
	
	public void novaMovimentacao (int i, int j) {
		
		if (posicoes[i][j].getTipo() == tipoPeça.Torre) movimentaTorre(i,j);
		if (posicoes[i][j].getTipo() == tipoPeça.Bispo) movimentaBispo(i,j);
		if (posicoes[i][j].getTipo() == tipoPeça.Cavalo) movimentaCavalo(i,j);
		if (posicoes[i][j].getTipo() == tipoPeça.Rainha) movimentaRainha(i,j);
		if (posicoes[i][j].getTipo() == tipoPeça.Peao) movimentaPeao(i,j);
		if (posicoes[i][j].getTipo() == tipoPeça.Rei) movimentaRei(i,j);
		
	}
	
	public void movimentaTorre (int i, int j){
		
		int auxi, auxj;
		
		for (auxi = i; auxi < 8; auxi++) {
			if (posicoes[i][j].getCor() != posicoes[auxi][j].getCor()) {
				casas[auxi][j] = 1;   // é um movimento possível
				break;
			}
			else if (posicoes[i][j].getCor() == posicoes[auxi][j].getCor()) {
				break;  // não é um movimento possível
			}
			casas[auxi][j] = 1;  // é um movimento possível
		}
		
		for (auxi = i; auxi >= 0; auxi--) {
			if (posicoes[i][j].getCor() != posicoes[auxi][j].getCor()) {
				casas[auxi][j] = 1;   // é um movimento possível
				break;
			}
			else if (posicoes[i][j].getCor() == posicoes[auxi][j].getCor()){
				break;  // não é um movimento possível
			}
			casas[auxi][j] = 1;  // é um movimento possível
		}
		for (auxj = j; auxj < 8; auxj++) {
			if (posicoes[i][j].getCor() != posicoes[i][auxj].getCor()) {
				casas[i][auxj] = 1;  // é um movimento possível
				break;
			}
			else if (posicoes[i][j].getCor() == posicoes[i][auxj].getCor()) {
				break;  // não é um movimento possível
			}
			casas[i][auxj] = 1;  // é um movimento possível
		}
		for (auxj = j; auxj >= 0; auxj--) {
			if (posicoes[i][j].getCor() != posicoes[i][auxj].getCor()) {
				casas[i][auxj] = 1;  // é um movimento possível
				break;
			}
			else if (posicoes[i][j].getCor() == posicoes[i][auxj].getCor()) {
				break;  // não é um movimento possível
			}
			casas[i][auxj] = 1;  // é um movimento possível
		}
	}
	
	public void movimentaBispo (int i, int j) {
		
		int auxi, auxj;
		
		for (auxi = i, auxj = j; auxi < 8 && auxj < 8; auxi++, auxj++) {
			if (posicoes[i][j].getCor() != posicoes[auxi][auxj].getCor()) {
				casas[auxi][auxj] = 1;  // é um movimento possível
				break;
			}
			else if (posicoes[i][j].getCor() == posicoes[auxi][auxj].getCor()){
				break;  // não é um movimento possível
			}
			casas[auxi][auxj] = 1;  // é um movimento possível
		}
		
		for (auxi = i, auxj = j; auxi >= 0 && auxj >= 0; auxi--, auxj--) {
			if (posicoes[i][j].getCor() != posicoes[auxi][auxj].getCor()) {
				casas[auxi][auxj] = 1;  // é um movimento possível
				break;
			}
			else if (posicoes[i][j].getCor() == posicoes[auxi][auxj].getCor()){
				break;  // não é um movimento possível
			}
			casas[auxi][auxj] = 1;  // é um movimento possível
		}
		
		for (auxi = i, auxj = j; auxi >= 0 && auxj < 8; auxi--, auxj++) {
			if (posicoes[i][j].getCor() != posicoes[auxi][auxj].getCor()) {
				casas[auxi][auxj] = 1;  // é um movimento possível
				break;
			}
			else if (posicoes[i][j].getCor() == posicoes[auxi][auxj].getCor()){
				break;  // não é um movimento possível
			}
			casas[auxi][auxj] = 1;  // é um movimento possível
		}
		
		for (auxi = i, auxj = j; auxi < 8 && auxj >= 0; auxi++, auxj--) {
			if (posicoes[i][j].getCor() != posicoes[auxi][auxj].getCor()) {
				casas[auxi][auxj] = 1;  // é um movimento possível
				break;
			}
			else if (posicoes[i][j].getCor() == posicoes[auxi][auxj].getCor()){
				break;  // não é um movimento possível
			}
			casas[auxi][auxj] = 1;  // é um movimento possível
		}
	}
	
	public void movimentaCavalo (int i, int j) {
		
		int auxi, auxj;
		
		if ((auxi = i - 1) >= 0) {
			if ((auxj = j - 2) >= 0) {
				if (posicoes[auxi][auxj] == null || posicoes[i][j].getCor() != posicoes[auxi][auxj].getCor()) {
					casas[auxi][auxj] = 1;  // é um movimento possível
				}
			}
			if ((auxj = j + 2) < 8) {
				if (posicoes[auxi][auxj] == null || posicoes[i][j].getCor() != posicoes[auxi][auxj].getCor()) {
					casas[auxi][auxj] = 1;  // é um movimento possível
				}
			}
		}
		
		if ((auxi = i - 2) >= 0) {
			if ((auxj = j - 1) >= 0) {
				if (posicoes[auxi][auxj] == null || posicoes[i][j].getCor() != posicoes[auxi][auxj].getCor()) {
					casas[auxi][auxj] = 1;  // é um movimento possível
				}
			}
			if ((auxj = j + 1) < 8) {
				if (posicoes[auxi][auxj] == null || posicoes[i][j].getCor() != posicoes[auxi][auxj].getCor()) {
					casas[auxi][auxj] = 1;  // é um movimento possível
				}
			}
		}
		
		if ((auxi = i + 1) < 8) {
			if ((auxj = j - 2) >= 0) {
				if (posicoes[auxi][auxj] == null || posicoes[i][j].getCor() != posicoes[auxi][auxj].getCor()) {
					casas[auxi][auxj] = 1;  // é um movimento possível
				}
			}
			if ((auxj = j + 2) < 8) {
				if (posicoes[auxi][auxj] == null || posicoes[i][j].getCor() != posicoes[auxi][auxj].getCor()) {
					casas[auxi][auxj] = 1;  // é um movimento possível
				}
			}
		}
		
		if ((auxi = i + 2) < 8) {
			if ((auxj = j - 1) >= 0) {
				if (posicoes[auxi][auxj] == null || posicoes[i][j].getCor() != posicoes[auxi][auxj].getCor()) {
					casas[auxi][auxj] = 1;  // é um movimento possível
				}
			}
			if ((auxj = j + 1) < 8) {
				if (posicoes[auxi][auxj] == null || posicoes[i][j].getCor() != posicoes[auxi][auxj].getCor()) {
					casas[auxi][auxj] = 1;  // é um movimento possível
				}
			}
		}
		
	}

	public void movimentaRainha (int i, int j) {
		movimentaTorre(i,j);
		movimentaBispo(i,j);
	}
	
	public void movimentaPeao (int i, int j) {
		
	}
	
	public void movimentaRei (int i, int j) {
		
		int auxi, auxj;
		
		if ((auxi = i + 1) < 8) {
			if (posicoes[i][j].getCor() != posicoes[auxi][j].getCor() || posicoes[auxi][j] == null) {
				casas[auxi][j] = 1;
			}
			if ((auxj = j + 1) < 8) {
				if (posicoes[i][j].getCor() != posicoes[auxi][auxj].getCor() || posicoes[auxi][auxj] == null) {
					casas[auxi][auxj] = 1;
				}
			}
			if ((auxj = j - 1) >= 0) {
				if (posicoes[i][j].getCor() != posicoes[auxi][auxj].getCor() || posicoes[auxi][auxj] == null) {
					casas[auxi][auxj] = 1;
				}
			}
		}
		
		if ((auxi = i - 1) >= 0) {
			if (posicoes[i][j].getCor() != posicoes[auxi][j].getCor() || posicoes[auxi][j] == null) {
				casas[auxi][j] = 1;
			}
			if ((auxj = j + 1) < 8) {
				if (posicoes[i][j].getCor() != posicoes[auxi][auxj].getCor() || posicoes[auxi][auxj] == null) {
					casas[auxi][auxj] = 1;
				}
			}
			if ((auxj = j - 1) >= 0) {
				if (posicoes[i][j].getCor() != posicoes[auxi][auxj].getCor() || posicoes[auxi][auxj] == null) {
					casas[auxi][auxj] = 1;
				}
			}
		}
		
		if ((auxj = j - 1) >= 0) {
			if (posicoes[i][j].getCor() != posicoes[i][auxj].getCor() || posicoes[i][auxj] == null) {
				casas[i][auxj] = 1;
			}
		}
		
		if ((auxj = j + 1) < 8) {
			if (posicoes[i][j].getCor() != posicoes[i][auxj].getCor() || posicoes[i][auxj] == null) {
				casas[i][auxj] = 1;
			}
		}
	}
}
