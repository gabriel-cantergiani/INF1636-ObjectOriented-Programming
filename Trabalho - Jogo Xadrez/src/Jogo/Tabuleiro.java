package Jogo;

import Peça.*;
import java.awt.*;

public class Tabuleiro implements Observado{
	
	private static Tabuleiro tabuleiro = null;
	private int[][] casas = new int[8][8];
	private Peça[][] posicoes = new Peça[8][8];
	private Regras regras = new Regras(casas,posicoes,this);
	private Observador obs;
	
	private Tabuleiro() {
		zeraCasas();
		inicializaTabuleiro();
	}
	
	public static Tabuleiro getTabuleiro() {
		if(tabuleiro == null)
			tabuleiro = new Tabuleiro();
		return tabuleiro;
	}

	protected void zeraCasas () {
		for (int i = 0; i < 8; i++) {
			for (int j = 0; j< 8; j++) {
				casas[i][j] = 0;
			}
		}
	}
	
	private void inicializaTabuleiro() {
		
		for(int i=0; i<8; i++) {
			posicoes[i] = new Peça[8];
		}
		
		posicoes[0][0] = new Torre(1); posicoes[7][0] = new Torre(0);
		posicoes[0][1] = new Cavalo(1); posicoes[7][1] = new Cavalo(0);
		posicoes[0][2] = new Bispo(1);  posicoes[7][2] = new Bispo(0);
		posicoes[0][3] = new Rainha(1); posicoes [7][3] = new Rainha(0);
		
		posicoes[0][4] = new Rei(1);    posicoes [7][4] = new Rei(0);
		posicoes[0][5] = new Bispo(1);  posicoes[7][5] = new Bispo(0);
		posicoes[0][6] = new Cavalo(1); posicoes[7][6] = new Cavalo(0);
		posicoes[0][7] = new Torre(1);  posicoes[7][7] = new Torre(0);
		
		
		for(int i=1; i<7; i++) {
			for(int j=0; j<8; j++) {
				if(i == 1)
					posicoes[i][j] = new Peão(1);
				else if(i == 6)
					posicoes[i][j] = new Peão(0);
				else
					posicoes[i][j] = null;
			}
		}
	}
	
	public Peça[][] getPeças(){
		return this.posicoes;
	}

	public int[][] getCasas(){
		return this.casas;
	}
	
	public void Recebe_Clique(int i, int j) {
		
		if(posicoes[i][j] != null)   	// peça foi selecionada para começar uma jogada
			regras.Peça_Selecionada(i,j);
		
		else if(posicoes[i][j] == null)	// casa vazia foi selecionada após a seleção de uma peça
			regras.Casa_Selecionada(i,j);
		
		// Nenhum dos casos acima, ignora o clique
		
		obs.notify(this); // mensagem para o o Painel atualizar (repaint() )
		return;	
			
	}
	
	public void add(Observador o) {
		obs = o;
	}
	
	public void remove(Observador o) {
		
	}

}
