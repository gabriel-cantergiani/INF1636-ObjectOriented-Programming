package Jogo;

import Peça.Peça;
import Peça.tipoPeça;
import java.awt.*;

public class Tabuleiro implements Observado{
	
	private static Tabuleiro tabuleiro = null;
	private int[][] casas = new int[8][8];
	private Peça[][] posicoes = new Peça[8][8];
	private Regras regras = new Regras(casas,posicoes,this);
	private Observador obs;
	
	private Tabuleiro() {
		zeraCasas();
		criaPeças();
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
	
	private void inicializaTabuleiro() {
		
		for(int i=0; i<8; i++) {
			for(int j=0; j<8; j++) {
				switch(i){
					case 0:{
						if(j==0 || j==7) {
							posicoes[i][j].setTipo(tipoPeça.Torre);
							posicoes[i][j].setImg("imagens/Pecas_2/PurpleR.png");
						}
						else if(j==1 || j==6) {
							posicoes[i][j].setTipo(tipoPeça.Cavalo);
							posicoes[i][j].setImg("imagens/Pecas_2/PurpleN.png");
						}
						else if(j==2 || j==5) {
							posicoes[i][j].setTipo(tipoPeça.Bispo);
							posicoes[i][j].setImg("imagens/Pecas_2/PurpleB.png");
						}
						else if(j==3) {
							posicoes[i][j].setTipo(tipoPeça.Rainha);
							posicoes[i][j].setImg("imagens/Pecas_2/PurpleQ.png");
						}
						else {
							posicoes[i][j].setTipo(tipoPeça.Rei);
							posicoes[i][j].setImg("imagens/Pecas_2/PurpleK.png");
						}
						posicoes[i][j].setCor(1);
						break;
					}
					case 1:{
						posicoes[i][j].setTipo(tipoPeça.Peao);
						posicoes[i][j].setImg("imagens/Pecas_2/PurpleP.png");
						posicoes[i][j].setCor(1);
						break;
					}
					case 6:{
						posicoes[i][j].setTipo(tipoPeça.Peao);
						posicoes[i][j].setImg("imagens/Pecas_2/CyanP.png");
						posicoes[i][j].setCor(0);
						break;
					}
					case 7:{
						if(j==0 || j==7) {
							posicoes[i][j].setTipo(tipoPeça.Torre);
							posicoes[i][j].setImg("imagens/Pecas_2/CyanR.png");
						}
						else if(j==1 || j==6) {
							posicoes[i][j].setTipo(tipoPeça.Cavalo);
							posicoes[i][j].setImg("imagens/Pecas_2/CyanN.png");
						}
						else if(j==2 || j==5) {
							posicoes[i][j].setTipo(tipoPeça.Bispo);
							posicoes[i][j].setImg("imagens/Pecas_2/CyanB.png");
						}
						else if(j==3) {
							posicoes[i][j].setTipo(tipoPeça.Rainha);
							posicoes[i][j].setImg("imagens/Pecas_2/CyanQ.png");
						}
						else {
							posicoes[i][j].setTipo(tipoPeça.Rei);
							posicoes[i][j].setImg("imagens/Pecas_2/CyanK.png");
						}
						posicoes[i][j].setCor(0);
						break;
					}
				
				}
					
			}
		
		}
		
		// end of loop

	}
	
	public Peça[][] getPeças(){
		return this.posicoes;
	}

	public int[][] getCasas(){
		return this.casas;
	}
	
	public void Recebe_Clique(int i, int j) {
		
		if(posicoes[i][j] != null) {		// peça foi selecionada para começar uma jogada
			regras.Peça_Selecionada(i,j);
		}
		else if(posicoes[i][j] == null)// && selecao==1)	// casa vazia foi selecionada após a seleção de uma peça
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
